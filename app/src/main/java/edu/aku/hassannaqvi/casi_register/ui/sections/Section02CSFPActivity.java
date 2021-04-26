package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
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
import edu.aku.hassannaqvi.casi_register.core.ZScore;
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection02CsfpBinding;
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.HealthFacility;
import edu.aku.hassannaqvi.casi_register.ui.other.EndingActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.DateUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage;
import kotlin.Pair;

import static edu.aku.hassannaqvi.casi_register.CONSTANTS.CHILD_FOLLOWUP_TYPE;
import static edu.aku.hassannaqvi.casi_register.CONSTANTS.DAYS_IN_A_MONTH;
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
        this.setTitle(getString(R.string.childFollowup));
        item = (ChildFollowup) getIntent().getSerializableExtra(CONSTANTS.ITEM_DATA);
        bi.setChildInformation(item);
        setupSkips();
        setUIContent();
        int country = SharedStorage.INSTANCE.getCountryCode(this);

        if (country == 3) {
            bi.fc0602.setVisibility(View.GONE);
            bi.fc0603.setVisibility(View.GONE);
            bi.fc0604.setVisibility(View.GONE);
            bi.fc0605.setVisibility(View.GONE);
            bi.fldGrpCVfc09.setVisibility(View.GONE);
            bi.fc2602.setVisibility(View.GONE);
            bi.fc2604.setVisibility(View.GONE);
            bi.fldGrpcs3202.setVisibility(View.GONE);
            bi.fldGrpcs3203.setVisibility(View.GONE);
            bi.fldGrpcs3302.setVisibility(View.GONE);
            bi.fldGrpcs3303.setVisibility(View.GONE);
            bi.fc3703.setVisibility(View.VISIBLE);
            bi.fldGrpCVfc10a.setVisibility(View.GONE);

        } else {
            bi.fc0602.setVisibility(View.VISIBLE);
            bi.fc0603.setVisibility(View.VISIBLE);
            bi.fc0604.setVisibility(View.VISIBLE);
            bi.fc0605.setVisibility(View.VISIBLE);
            bi.fldGrpCVfc09.setVisibility(View.VISIBLE);
            bi.fc2602.setVisibility(View.VISIBLE);
            bi.fc2604.setVisibility(View.VISIBLE);
            bi.fldGrpcs3202.setVisibility(View.VISIBLE);
            bi.fldGrpcs3203.setVisibility(View.VISIBLE);
            bi.fldGrpcs3302.setVisibility(View.VISIBLE);
            bi.fldGrpcs3303.setVisibility(View.VISIBLE);
            bi.fc3703.setVisibility(View.GONE);
        }

        bi.fc08.setMinDate(item.getCs08().replace("-", "/"));
    }


    private void setupSkips() {

        bi.fc16b.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpfc16b);

            if (bi.fc16b01.getId() == i) {
                populateDate();
            }
        });

        bi.fc2605.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.fc26check, false);
                Clear.clearAllFields(bi.fc2606, false);
            } else {
                Clear.clearAllFields(bi.fc26check, true);
                Clear.clearAllFields(bi.fc2606, true);
            }
        });

        bi.fc2606.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.fc26check, false);
                Clear.clearAllFields(bi.fc2605, false);
                bi.fldGrpfc2606.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpfc2606, false);
            } else {
                Clear.clearAllFields(bi.fc26check, true);
                Clear.clearAllFields(bi.fc2605, true);
                bi.fldGrpfc2606.setVisibility(View.VISIBLE);
                Clear.clearAllFields(bi.fldGrpfc2606, true);
            }
        });

        bi.fc28.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVfc29));

        bi.fc34.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpfc3401));

        bi.fc07User.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                bi.fc07.setEnabled(false);
                bi.fc07.setText(null);
            } else
                bi.fc07.setEnabled(true);
        });

        bi.fc2501.setOnCheckedChangeListener((compoundButton, b) -> Clear.clearAllFields(bi.fc25check, !b));

        bi.fc08.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) return;
                populateDate();
            }
        });
    }


    public void fc17mOnTextChanged(CharSequence s, int start, int before, int count) {
        bi.fc1701.setText(null);
    }


    public void cs17yOnTextChanged(CharSequence s, int i, int i1, int i2) {

        if (TextUtils.isEmpty(bi.fc1702.getText()) || TextUtils.isEmpty(bi.fc1701.getText()))
            return;

        int age = Integer.parseInt(bi.fc1702.getText().toString()) + (Integer.parseInt(bi.fc1701.getText().toString()) * 12);

        if (age != 0) {
            bi.fldGrpCVfc19.setVisibility(View.GONE);
            bi.fldGrpCVfc20.setVisibility(View.GONE);
            bi.fldGrpCVfc21.setVisibility(View.GONE);

            if (age == 0) {
                bi.fc1701.setError("Month and year both cannot be zero");
            }

            if (age >= 6) {
                bi.fldGrpCVfc19.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVfc19);
            } else bi.fldGrpCVfc19.setVisibility(View.VISIBLE);

            if (age < 6 || age >= 24) {
                bi.fldGrpCVfc20.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVfc20);
            } else bi.fldGrpCVfc20.setVisibility(View.VISIBLE);

            if (age >= 24) {
                bi.fldGrpCVfc21.setVisibility(View.GONE);
                Clear.clearAllFields(bi.fldGrpCVfc21);
            } else bi.fldGrpCVfc21.setVisibility(View.VISIBLE);
        }
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            gotoActivityWithSerializable(this, EndingActivity.class, "complete", true);
        } else {
            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, getString(R.string.failedUpdateDb), Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, getString(R.string.updateDbError1) + "/n" + getString(R.string.updateDbError2), Toast.LENGTH_SHORT).show();
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
        for (HealthFacility item : appInfo.dbHelper.getFacility(
                mainInfo.getCountry_code(),
                mainInfo.getRegion_code(),
                mainInfo.getDistrict_code(),
                mainInfo.getUc_code(),
                MainApp.user.getUserName()
        )) {
            facilityName.add(item.getHealth_facility());
            facilityMap.put(item.getHealth_facility(), item.getHf_code());
        }
        bi.fc03.setAdapter(new ArrayAdapter<>(Section02CSFPActivity.this, android.R.layout.simple_spinner_dropdown_item, facilityName));

        bi.fc10a98.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                bi.fc10a.setEnabled(false);
                bi.fc10a.setText(null);
            } else
                bi.fc10a.setEnabled(true);
        });

        bi.fc2498.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                bi.fc24.setEnabled(false);
                bi.fc24.setText(null);
            } else
                bi.fc24.setEnabled(true);
        });
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
        form.setFupno(item.getFupNo());

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

        form.setFc07(bi.fc07.getText().toString().trim().isEmpty() ? "-1" : bi.fc07.getText().toString());

        form.setFc07User(bi.fc07User.isChecked() ? form.getUsername() : "98");

        form.setFc08(bi.fc08.getText().toString());

        form.setFc09(bi.fc09.getText().toString());

        form.setFc10(bi.fc10.getText().toString());

        form.setFc10a(bi.fc10a.getText().toString().trim().isEmpty() ? "-1" : bi.fc10a.getText().toString());

        form.setFc10a98(bi.fc10a98.isChecked() ? "98" : "-1");

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
        form.setFc2498(bi.fc2498.isChecked() ? "98" : "-1");

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

        form.setFc2606(bi.fc2606.isChecked() ? "6" : "-1");

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
                : bi.fc3405.isChecked() ? "5"
                : "-1");

        form.setFc3501(bi.fc3501.getText().toString());
        form.setFc3601(bi.fc3601.getText().toString());
        form.setFc37(bi.fc3701.isChecked() ? "1"
                : bi.fc3702.isChecked() ? "2"
                : bi.fc3703.isChecked() ? "3"
                : bi.fc3796.isChecked() ? "96"
                : "-1");

        form.setFc3796x(bi.fc3796x.getText().toString());
        form.setFc38(bi.fc3801.isChecked() ? "1"
                : bi.fc3802.isChecked() ? "2"
                : "-1");

    }


    private boolean formValidation() {
        if (!Validator.emptyCheckingContainer(this, bi.GrpName))
            return false;

        if (bi.ZScore.getText().toString().equals(StringUtils.EMPTY) && bi.fc16b01.isChecked()) {
            Toast.makeText(this, "Please click on Z-Score Button", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*
     * Click events
     * */
    public void CheckZScore(View view) {
        if ((item.getCs13().equals("1") || item.getCs13().equals("2"))
                && Validator.emptyTextBox(this, bi.fc1701)
                && Validator.emptyTextBox(this, bi.fc1702)
                && Validator.emptyTextBox(this, bi.fc22)
                && Validator.emptyTextBox(this, bi.fc23)
        ) {
            int ageinmonths = Integer.parseInt(bi.fc1702.getText().toString()) + Integer.parseInt(bi.fc1701.getText().toString());
            int ageindays = (int) Math.floor(ageinmonths * DAYS_IN_A_MONTH);
            int gender = item.getCs13().equals("1") ? 1 : item.getCs13().equals("2") ? 2 : 0;

            ZScore zs = new ZScore(ageindays, gender);
            double HLAZ = zs.getZScore_HLAZ(bi.fc22.getText().toString());
            double WAZ = zs.getZScore_WAZ(bi.fc23.getText().toString());
            double WHZ = zs.getZScore_WHZ(bi.fc23.getText().toString(), bi.fc22.getText().toString());

            bi.ZScore.setText(String.format(Locale.ENGLISH, "%s %.2f \r\n%s %.2f \r\n%s %.2f", getString(R.string.hlaz), HLAZ, getString(R.string.waz), WAZ, getString(R.string.whz), WHZ));
        } else {
            Toast.makeText(this, getString(R.string.zScoreEmpty), Toast.LENGTH_SHORT).show();
        }
    }


    public void ZScoreOnTextChanged(CharSequence s, int start, int before, int count) {
        bi.ZScore.setText(null);
    }


    public void BtnEnd() {
        AppUtilsKt.openSectionEndingActivity(this, false);
    }


    private void populateDate() {
        Pair<Integer, Integer> dt = DateUtilsKt.getMonthAndYearFromStr(item.getDob(), bi.fc08.getText().toString().replace("-", "/"));
        bi.fc1702.setText(String.valueOf(dt.getSecond()));
        bi.fc1701.setText(String.valueOf(dt.getFirst()));
    }
}