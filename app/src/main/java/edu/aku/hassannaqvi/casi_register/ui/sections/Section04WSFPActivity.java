package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

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
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection04WsfpBinding;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.HealthFacility;
import edu.aku.hassannaqvi.casi_register.models.WraFollowup;
import edu.aku.hassannaqvi.casi_register.ui.other.EndingActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage;

import static edu.aku.hassannaqvi.casi_register.CONSTANTS.WRA_FOLLOWUP_TYPE;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.appInfo;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.mainInfo;
import static edu.aku.hassannaqvi.casi_register.utils.ActivityExtKt.gotoActivityWithSerializable;

public class Section04WSFPActivity extends AppCompatActivity {

    ActivitySection04WsfpBinding bi;
    WraFollowup item;
    List<String> facilityName;
    Map<String, String> facilityMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section04_wsfp);
        bi.setCallback(this);
        this.setTitle(getString(R.string.wraFollowup));
        item = (WraFollowup) getIntent().getSerializableExtra(CONSTANTS.ITEM_DATA);
        bi.setWraInformation(item);
        setListeners();
        setUIContent();
        int country = SharedStorage.INSTANCE.getCountryCode(this);

        if (country == 3) {
            bi.fw0602.setVisibility(View.GONE);
            bi.fw0603.setVisibility(View.GONE);
            bi.fw0604.setVisibility(View.GONE);
            bi.fw0605.setVisibility(View.GONE);
            bi.fldGrpCVfw09.setVisibility(View.GONE);
        } else {
            bi.fw0602.setVisibility(View.VISIBLE);
            bi.fw0603.setVisibility(View.VISIBLE);
            bi.fw0604.setVisibility(View.VISIBLE);
            bi.fw0605.setVisibility(View.VISIBLE);
            bi.fldGrpCVfw09.setVisibility(View.VISIBLE);
        }
        bi.fw08.setMinDate(item.getWs08().replace("-", "/"));

        bi.fw12a.setText(item.getWs12a());
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
        bi.fw03.setAdapter(new ArrayAdapter<>(Section04WSFPActivity.this, android.R.layout.simple_spinner_dropdown_item, facilityName));
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
                db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_WSFP, form.wSFPtoString());
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


    private void SaveDraft() {

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        form.setUsername(MainApp.user.getUserName());
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());
        form.setReg_no(bi.fw10.getText().toString());

        form.setDistrict(MainApp.mainInfo.getDistrict());
        form.setUc(MainApp.mainInfo.getUc());
        form.setVillage(MainApp.mainInfo.getVillage());

        form.setFormType(WRA_FOLLOWUP_TYPE);

        form.setCountryCode(MainApp.mainInfo.getCountry_code());
        form.setDistrictCode(MainApp.mainInfo.getDistrict_code());
        form.setUcCode(MainApp.mainInfo.getUc_code());
        form.setVillageCode(MainApp.mainInfo.getVillage_code());

        form.setFw01(item.getWs01());
        form.setFw01a(item.getWs01a());
        form.setFw01b(item.getWs01b());
        form.setFw04(item.getWs04());
        form.setFw05(item.getWs05());
        form.setScr_date(item.getWs08());
        form.set_luid(item.getLUID());
        form.setFupno(item.getFupNo());


        /*form.setFw01(bi.fw01.getText().toString());

        form.setFw01a(bi.fw01a.getText().toString());

        form.setFw01b(bi.fw01b.getText().toString());*/

        form.setFw02(bi.fw0201.isChecked() ? "1"
                : bi.fw0202.isChecked() ? "2"
                : bi.fw0203.isChecked() ? "3"
                : "-1");

        form.setFw03(facilityMap.get(bi.fw03.getSelectedItem().toString()));

        /*form.setFw04(bi.fw04.getText().toString());

        form.setFw05(bi.fw05.getText().toString());*/

        form.setFw05a(bi.fw05a.getText().toString());

        form.setFw06(bi.fw0601.isChecked() ? "1"
                : bi.fw0602.isChecked() ? "2"
                : bi.fw0603.isChecked() ? "3"
                : bi.fw0604.isChecked() ? "4"
                : bi.fw0605.isChecked() ? "5"
                : bi.fw0696.isChecked() ? "96"
                : "-1");
        form.setFw0696x(bi.fw0696x.getText().toString());

        form.setFw07(bi.fw07.getText().toString().trim().isEmpty() ? "-1" : bi.fw07.getText().toString());

        form.setFw07User(bi.fw07User.isChecked() ? form.getUsername() : "98");

        form.setFw08(bi.fw08.getText().toString());

        form.setFw09(bi.fw09.getText().toString());

        form.setFw10(bi.fw10.getText().toString());

        form.setFw11(bi.fw11.getText().toString());

        form.setFw12(bi.fw12.getText().toString());

        form.setFw12a(bi.fw12a.getText().toString());

        form.setFw12b(bi.fw12b01.isChecked() ? "1"
                : bi.fw12b02.isChecked() ? "2"
                : bi.fw12b03.isChecked() ? "3"
                : bi.fw12b04.isChecked() ? "4"
                : bi.fw12b05.isChecked() ? "5"
                : bi.fw12b96.isChecked() ? "96"
                : "-1");

        form.setFw12b96x(bi.fw12b96x.getText().toString());
        form.setFw13(bi.fw13.getText().toString());

        form.setFw14(bi.fw1401.isChecked() ? "1"
                : bi.fw1402.isChecked() ? "2"
                : bi.fw1403.isChecked() ? "3"
                : "-1");

        form.setFw15(bi.fw15.getText().toString());

        form.setFw16(bi.fw16.getText().toString());

        form.setFw17(bi.fw17.getText().toString());

        form.setFw1801(bi.fw1801.isChecked() ? "1" : "-1");

        form.setFw1802(bi.fw1802.isChecked() ? "2" : "-1");

        form.setFw1803(bi.fw1803.isChecked() ? "3" : "-1");

        form.setFw1804(bi.fw1804.isChecked() ? "4" : "-1");

        form.setFw1805(bi.fw1805.isChecked() ? "5" : "-1");

        form.setFw1806(bi.fw1806.isChecked() ? "6" : "-1");

        form.setFw19(bi.fw1901.isChecked() ? "1"
                : bi.fw1902.isChecked() ? "2"
                : "-1");

        form.setFw2001(bi.fw2001.getText().toString());
        form.setFw21(bi.fw2101.isChecked() ? "1"
                : bi.fw2102.isChecked() ? "2"
                : bi.fw2103.isChecked() ? "3"
                : bi.fw2104.isChecked() ? "4"
                : "-1");

        form.setFw22(bi.fw22.getText().toString());

        form.setFw2301(bi.fw2301.getText().toString());
        form.setFw24(bi.fw24.getText().toString());

        form.setFw25(bi.fw25.getText().toString());

        form.setFw26(bi.fw2601.isChecked() ? "1"
                : bi.fw2602.isChecked() ? "2"
                : "-1");

        form.setFw27(bi.fw2701.isChecked() ? "1"
                : bi.fw2702.isChecked() ? "2"
                : bi.fw2703.isChecked() ? "3"
                : bi.fw2704.isChecked() ? "4"
                : "-1");

        form.setFw2801(bi.fw2801.isChecked() ? "1" : "-1");

        form.setFw2802(bi.fw2802.isChecked() ? "2" : "-1");

        form.setFw2803(bi.fw2803.isChecked() ? "3" : "-1");

        form.setFw2804(bi.fw2804.isChecked() ? "4" : "-1");

        form.setFw2805(bi.fw2805.isChecked() ? "5" : "-1");

        form.setFw2896(bi.fw2896.isChecked() ? "96" : "-1");

        form.setFw2896x(bi.fw2896x.getText().toString());
        form.setFw29a(bi.fw29a01.isChecked() ? "1"
                : bi.fw29a02.isChecked() ? "2"
                : "-1");

        form.setFw29b(bi.fw29b01.isChecked() ? "1"
                : bi.fw29b02.isChecked() ? "2"
                : "-1");

        form.setFw29c(bi.fw29c01.isChecked() ? "1"
                : bi.fw29c02.isChecked() ? "2"
                : "-1");

        form.setFw29d(bi.fw29d01.isChecked() ? "1"
                : bi.fw29d02.isChecked() ? "2"
                : "-1");

        form.setFw29e(bi.fw29e01.isChecked() ? "1"
                : bi.fw29e02.isChecked() ? "2"
                : "-1");

        form.setFw29f(bi.fw29f01.isChecked() ? "1"
                : bi.fw29f02.isChecked() ? "2"
                : "-1");

        form.setFw29g(bi.fw29g01.isChecked() ? "1"
                : bi.fw29g02.isChecked() ? "2"
                : "-1");

        form.setFw29h(bi.fw29h01.isChecked() ? "1"
                : bi.fw29h02.isChecked() ? "2"
                : "-1");

        form.setFw29i(bi.fw29i01.isChecked() ? "1"
                : bi.fw29i02.isChecked() ? "2"
                : "-1");

        form.setFw29j(bi.fw29j01.isChecked() ? "1"
                : bi.fw29j02.isChecked() ? "2"
                : "-1");

        form.setFw29k(bi.fw29k96.isChecked() ? "96"
                : bi.fw29k02.isChecked() ? "2"
                : "-1");

        form.setFw29k96x(bi.fw29k96x.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openSectionEndingActivity(this, false);
    }


    private void setListeners() {

        bi.fw12b.setOnCheckedChangeListener((radioGroup, i) -> {
            Clear.clearAllFields(bi.fldGrpfw12b);
            if (bi.fw12b01.isChecked()) {
                bi.fw13.setText(item.getWs13());
            }
        });

        bi.fw14.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpfw1403));

        bi.fw19.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpfw19));

        bi.fw21.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVfw22));

        bi.fw26.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVfw27));

        bi.fw1801.setOnCheckedChangeListener((compoundButton, b) -> {
            Clear.clearAllFields(bi.fw18check, !b);
        });

        bi.fw2805.setOnCheckedChangeListener((compoundButton, b) -> Clear.clearAllFields(bi.fw28check, !b));

        bi.fw1798.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                bi.fw17.setEnabled(false);
                bi.fw17.setText(null);
            } else
                bi.fw17.setEnabled(true);
        });
    }

    public void fw16OnTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(bi.fw16.getText().toString()))
            return;

        double weight = Double.parseDouble(bi.fw16.getText().toString());

        if (weight < 45) {
            if (bi.fw1801.isChecked()) {
                bi.fw1801.setChecked(false);
            }
            bi.fw1802.setChecked(true);
            bi.fw1801.setEnabled(false);
        } else {
            if (!bi.fw1804.isChecked()) {
                bi.fw1801.setEnabled(true);
            }
            bi.fw1802.setChecked(false);
        }
    }

    public void fw17OnTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(bi.fw17.getText().toString()))
            return;

        double mauc = Double.parseDouble(bi.fw17.getText().toString());

        if (mauc < 23) {
            if (bi.fw1801.isChecked()) {
                bi.fw1801.setChecked(false);
            }
            bi.fw1804.setChecked(true);
            bi.fw1801.setEnabled(false);
        } else {
            if (!bi.fw1802.isChecked()) {
                bi.fw1801.setEnabled(true);
            }
            bi.fw1804.setChecked(false);
        }
    }

}