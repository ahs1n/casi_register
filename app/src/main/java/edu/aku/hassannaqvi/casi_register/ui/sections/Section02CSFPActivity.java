package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import edu.aku.hassannaqvi.casi_register.CONSTANTS;
import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection02CsfpBinding;
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.HealthFacility;
import edu.aku.hassannaqvi.casi_register.ui.other.EndingActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage;

import static edu.aku.hassannaqvi.casi_register.CONSTANTS.CHILD_FOLLOWUP_TYPE;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.appInfo;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.mainInfo;
import static edu.aku.hassannaqvi.casi_register.utils.ActivityExtKt.gotoActivityWithSerializable;

public class Section02CSFPActivity extends AppCompatActivity {

    ActivitySection02CsfpBinding bi;
    ChildFollowup item;
    List<String> facilityName;
    Map<String, String> facilityMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section02_csfp);
        bi.setCallback(this);
        item = (ChildFollowup) getIntent().getSerializableExtra(CONSTANTS.ITEM_DATA);
        bi.setChildInformation(item);
        setupSkips();
        setUIContent();
    }


    private void setupSkips() {

        bi.fc1702.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(bi.fc1702.getText()) || TextUtils.isEmpty(bi.fc1701.getText()))
                    return;

                int age = Integer.parseInt(bi.fc1702.getText().toString()) + (Integer.parseInt(bi.fc1701.getText().toString()) * 12);

                bi.fldGrpCVfc19.setVisibility(View.VISIBLE);
                bi.fldGrpCVfc20.setVisibility(View.VISIBLE);
                bi.fldGrpCVfc21.setVisibility(View.VISIBLE);

                if (age >= 6) {
                    bi.fldGrpCVfc19.setVisibility(View.GONE);
                } else bi.fldGrpCVfc19.setVisibility(View.VISIBLE);

                if (age < 6 || age >= 24) {
                    bi.fldGrpCVfc20.setVisibility(View.GONE);
                } else bi.fldGrpCVfc20.setVisibility(View.VISIBLE);

                if (age >= 24) {
                    bi.fldGrpCVfc20.setVisibility(View.GONE);
                } else bi.fldGrpCVfc20.setVisibility(View.VISIBLE);

                if (age >= 24) {
                    bi.fldGrpCVfc21.setVisibility(View.GONE);
                } else bi.fldGrpCVfc21.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        bi.fc16b.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpfc16b));

        CompoundButton.OnCheckedChangeListener compound = (compoundButton, b) -> {
            if (!bi.fc2501.isChecked() && !bi.fc2502.isChecked() && !bi.fc2503.isChecked()) {
                Clear.clearAllFields(bi.fc25check, false);
                Clear.clearAllFields(bi.fldGrpCVfc29);
                bi.fldGrpCVfc29.setVisibility(View.GONE);
            } else {
                Clear.clearAllFields(bi.fc25check, true);
                bi.fldGrpCVfc29.setVisibility(View.VISIBLE);
            }
        };

        bi.fc2501.setOnCheckedChangeListener(compound);
        bi.fc2502.setOnCheckedChangeListener(compound);
        bi.fc2503.setOnCheckedChangeListener(compound);

        bi.fc2605.setOnCheckedChangeListener((compoundButton, b) -> Clear.clearAllFields(bi.fc26check, !b));

        bi.fc34.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpfc3401));

    }


    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            gotoActivityWithSerializable(this, EndingActivity.class, "complete", true);
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long rowid = db.addForm(form);
        form.set_ID(String.valueOf(rowid));
        if (rowid > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            long count = db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            if (count > 0) {
                db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_CSFP, form.cSFPtoString());
                return true;
            } else {
                Toast.makeText(this, "SORRY! Failed to update DB)", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void setUIContent() {

        /*
         * Populating HealthFacilities
         * */
        facilityName = new ArrayList<String>() {
            {
                add("....");
            }
        };
        facilityMap = new HashMap<>();
        for (HealthFacility item : appInfo.dbHelper.getFacility(mainInfo.getRegion_code())) {
            facilityName.add(item.getHealth_facility());
            facilityMap.put(item.getHealth_facility(), item.getHf_code());
        }
        bi.fc03.setAdapter(new ArrayAdapter<>(Section02CSFPActivity.this, android.R.layout.simple_spinner_dropdown_item, facilityName));

        /*
         * Implementing child registration no
         * */
        String regID = SharedStorage.INSTANCE.getLastRegistrationID(this, "c-" + MainApp.mainInfo.getUc_code() + MainApp.mainInfo.getVillage_code());
        if (!regID.equals(StringUtils.EMPTY)) {
            String substring = regID.substring(regID.length() - 4);
            String result = regID.replace(substring, String.format(Locale.ENGLISH, "%04d", Integer.parseInt(substring) + 1));
            bi.fc10.setText(result);
        } else bi.fc10.setText(MainApp.mainInfo.getVillage_code().concat("0001"));

    }


    private void SaveDraft() {

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        form.setUsername(MainApp.user.getUserName());
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());
        form.setReg_no(bi.fc10.getText().toString());

        form.setDistrict(MainApp.mainInfo.getDistrict());
        form.setUc(MainApp.mainInfo.getUc());
        form.setVillage(MainApp.mainInfo.getVillage());

        form.setFormType(CHILD_FOLLOWUP_TYPE);

        form.setCountryCode(MainApp.mainInfo.getCountry_code());
        form.setDistrictCode(MainApp.mainInfo.getDistrict_code());
        form.setUcCode(MainApp.mainInfo.getUc_code());
        form.setVillageCode(MainApp.mainInfo.getVillage_code());

        form.setFc01(item.getCs01());
        form.setFc01a(item.getCs01a());
        form.setFc01b(item.getCs01b());
        form.setFc04(item.getCs04());
        form.setFc05(item.getCs05());
        form.setScr_date(item.getCs08());
        form.set_luid(item.getLUID());

        /*form.setFc01(bi.fc01.getText().toString());

        form.setFc01a(bi.fc01a.getText().toString());

        form.setFc01b(bi.fc01b.getText().toString());*/

        form.setFc02(bi.fc0201.isChecked() ? "1"
                : bi.fc0202.isChecked() ? "2"
                : bi.fc0203.isChecked() ? "3"
                : "-1");

        form.setFc03(facilityMap.get(bi.fc03.getSelectedItem().toString()));

        /*form.setFc04(bi.fc04.getText().toString());

        form.setFc05(bi.fc05.getText().toString());*/

        form.setFc05a(bi.fc05a.getText().toString());

        form.setFc06(bi.fc0601.isChecked() ? "1"
                : bi.fc0602.isChecked() ? "2"
                : bi.fc0603.isChecked() ? "3"
                : bi.fc0604.isChecked() ? "4"
                : bi.fc0605.isChecked() ? "5"
                : bi.fc0696.isChecked() ? "96"
                : "-1");

        form.setFc0696x(bi.fc0696x.getText().toString());
        form.setFc07(bi.fc07.getText().toString());

        form.setFc0801(bi.fc0801.getText().toString());
        form.setFc0802(bi.fc0802.getText().toString());
        form.setFc0803(bi.fc0803.getText().toString());
        form.setFc09(bi.fc09.getText().toString());

        form.setFc10(bi.fc10.getText().toString());

        form.setFc10a(bi.fc10a.getText().toString());

        form.setFc15(bi.fc15.getText().toString());

        form.setFc15a(bi.fc15a.getText().toString());

        form.setFc16(bi.fc16.getText().toString());

        form.setFc16a(bi.fc16a.getText().toString());

        form.setFc16b(bi.fc16b01.isChecked() ? "1"
                : bi.fc16b02.isChecked() ? "2"
                : bi.fc16b03.isChecked() ? "3"
                : bi.fc16b04.isChecked() ? "4"
                : bi.fc16b05.isChecked() ? "5"
                : bi.fc16b96.isChecked() ? "96"
                : "-1");

        form.setFc16b96x(bi.fc16b96x.getText().toString());
        form.setFc1701(bi.fc1701.getText().toString());
        form.setFc1702(bi.fc1702.getText().toString());
        form.setFc18(bi.fc1801.isChecked() ? "1"
                : bi.fc1802.isChecked() ? "2"
                : "-1");

        form.setFc19(bi.fc1901.isChecked() ? "1"
                : bi.fc1902.isChecked() ? "2"
                : "-1");

        form.setFc20(bi.fc2001.isChecked() ? "1"
                : bi.fc2002.isChecked() ? "2"
                : "-1");

        form.setFc21(bi.fc2101.isChecked() ? "1"
                : bi.fc2102.isChecked() ? "2"
                : "-1");

        form.setFc22(bi.fc22.getText().toString());

        form.setFc23(bi.fc23.getText().toString());

        form.setFc24(bi.fc24.getText().toString());

        form.setFc2501(bi.fc2501.isChecked() ? "1" : "-1");

        form.setFc2502(bi.fc2502.isChecked() ? "2" : "-1");

        form.setFc2503(bi.fc2503.isChecked() ? "3" : "-1");

        form.setFc2504(bi.fc2504.isChecked() ? "4" : "-1");

        form.setFc2505(bi.fc2505.isChecked() ? "5" : "-1");

        form.setFc2506(bi.fc2506.isChecked() ? "6" : "-1");

        form.setFc2601(bi.fc2601.isChecked() ? "1" : "-1");

        form.setFc2602(bi.fc2602.isChecked() ? "2" : "-1");

        form.setFc2603(bi.fc2603.isChecked() ? "3" : "-1");

        form.setFc2604(bi.fc2604.isChecked() ? "4" : "-1");

        form.setFc2605(bi.fc2605.isChecked() ? "5" : "-1");

        form.setFc2701(bi.fc2701.getText().toString());
        form.setFc2702(bi.fc2702.getText().toString());
        form.setFc2703(bi.fc2703.getText().toString());
        form.setFc2704(bi.fc2704.getText().toString());
        form.setFc28(bi.fc2801.isChecked() ? "1"
                : bi.fc2802.isChecked() ? "2"
                : bi.fc2803.isChecked() ? "3"
                : bi.fc2804.isChecked() ? "4"
                : "-1");

        form.setFc2901(bi.fc2901.isChecked() ? "1" : "-1");

        form.setFc2902(bi.fc2902.isChecked() ? "2" : "-1");

        form.setFc2903(bi.fc2903.isChecked() ? "3" : "-1");

        form.setFc2904(bi.fc2904.isChecked() ? "4" : "-1");

        form.setFc2905(bi.fc2905.isChecked() ? "5" : "-1");

        form.setFc2996(bi.fc2996.isChecked() ? "96" : "-1");

        form.setFc2996x(bi.fc2996x.getText().toString());
        form.setFc30(bi.fc30.getText().toString());

        form.setFc31(bi.fc3101.isChecked() ? "1"
                : bi.fc3102.isChecked() ? "2"
                : "-1");

        form.setFc3201(bi.fc3201.getText().toString());
        form.setFc3202(bi.fc3202.getText().toString());
        form.setFc3203(bi.fc3203.getText().toString());
        form.setFc3301(bi.fc3301.getText().toString());
        form.setFc3302(bi.fc3302.getText().toString());
        form.setFc3303(bi.fc3303.getText().toString());
        form.setFc34(bi.fc3401.isChecked() ? "1"
                : bi.fc3402.isChecked() ? "2"
                : bi.fc3403.isChecked() ? "3"
                : bi.fc3404.isChecked() ? "4"
                : "-1");

        form.setFc3501(bi.fc3501.getText().toString());
        form.setFc3601(bi.fc3601.getText().toString());
        form.setFc37(bi.fc3701.isChecked() ? "1"
                : bi.fc3702.isChecked() ? "2"
                : bi.fc3796.isChecked() ? "96"
                : "-1");

        form.setFc3796x(bi.fc3796x.getText().toString());
        form.setFc38(bi.fc3801.isChecked() ? "1"
                : bi.fc3802.isChecked() ? "2"
                : "-1");

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openSectionEndingActivity(this, false);
    }

}