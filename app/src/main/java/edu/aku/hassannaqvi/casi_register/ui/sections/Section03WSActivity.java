package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.os.Bundle;
import android.text.TextUtils;
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

import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.database.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection03WsBinding;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.HealthFacility;
import edu.aku.hassannaqvi.casi_register.ui.other.EndingActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage;

import static edu.aku.hassannaqvi.casi_register.CONSTANTS.WRA_TYPE;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.appInfo;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.mainInfo;
import static edu.aku.hassannaqvi.casi_register.utils.ActivityExtKt.gotoActivityWithSerializable;

public class Section03WSActivity extends AppCompatActivity {

    ActivitySection03WsBinding bi;
    List<String> facilityName;
    Map<String, String> facilityMap;
    String concatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section03_ws);
        bi.setCallback(this);
        setListeners();
        setUIContent();
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
        bi.ws03.setAdapter(new ArrayAdapter<>(Section03WSActivity.this, android.R.layout.simple_spinner_dropdown_item, facilityName));

        /*
         * Implementing child registration no
         * */
        concatID = MainApp.mainInfo.getCountry_code() + MainApp.mainInfo.getDistrict_code() + String.format(Locale.ENGLISH, "%02d", Integer.parseInt(MainApp.mainInfo.getUc_code())) + String.format(Locale.ENGLISH, "%02d", Integer.parseInt(MainApp.mainInfo.getVillage_code()));
        String regID = SharedStorage.INSTANCE.getLastRegistrationID(this, "w-" + concatID);
        if (!regID.equals(StringUtils.EMPTY)) {
            String substring = regID.substring(regID.length() - 4);
            String result = regID.replace(substring, String.format(Locale.ENGLISH, "%04d", Integer.parseInt(substring) + 1));
            bi.ws10.setText(result);
        } else
            bi.ws10.setText(concatID.concat("0001"));

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
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            long count = db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            if (count > 0)
                count = db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_WS, form.wStoString());
            if (count > 0) {
                SharedStorage.INSTANCE.setLastRegistrationID(this, "w-" + concatID, bi.ws10.getText().toString());
                return true;
            } else {
                Toast.makeText(this, "SORRY! Failed to update DB)", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
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

        form.setReg_no(MainApp.mainInfo.getCountry());
        form.setDistrict(MainApp.mainInfo.getDistrict());
        form.setUc(MainApp.mainInfo.getUc());
        form.setVillage(MainApp.mainInfo.getVillage());

        form.setFormType(WRA_TYPE);

        form.setWs01(MainApp.mainInfo.getCountry_code());
        form.setWs01a(MainApp.mainInfo.getRegion_code());
        form.setWs01b(MainApp.mainInfo.getDistrict_code());
        form.setWs04(MainApp.mainInfo.getUc_code());
        form.setWs05(MainApp.mainInfo.getVillage_code());

        form.setCountryCode(MainApp.mainInfo.getCountry_code());
        form.setDistrictCode(MainApp.mainInfo.getDistrict_code());
        form.setUcCode(MainApp.mainInfo.getUc_code());
        form.setVillageCode(MainApp.mainInfo.getVillage_code());

        form.setWs02(bi.ws0201.isChecked() ? "1"
                : bi.ws0202.isChecked() ? "2"
                : bi.ws0203.isChecked() ? "3"
                : "-1");

        form.setWs03(facilityMap.get(bi.ws03.getSelectedItem().toString()));

        form.setWs05a(bi.ws05a.getText().toString());

        form.setWs06(bi.ws0601.isChecked() ? "1"
                : bi.ws0602.isChecked() ? "2"
                : bi.ws0603.isChecked() ? "3"
                : bi.ws0604.isChecked() ? "4"
                : bi.ws0605.isChecked() ? "5"
                : bi.ws0696.isChecked() ? "96"
                : "-1");
        form.setWs0696x(bi.ws0696x.getText().toString());

        form.setWs07(bi.ws07.getText().toString().trim().isEmpty() ? "-1" : bi.ws07.getText().toString());

        form.setWs07User(bi.ws07User.isChecked() ? form.getUsername() : "98");

        form.setWs08(bi.ws08.getText().toString());

        form.setWs09(bi.ws09.getText().toString());

        form.setWs10(bi.ws10.getText().toString());

        form.setWs11(bi.ws11.getText().toString());

        form.setWs12(bi.ws12.getText().toString());

        form.setWs12a(bi.ws12a.getText().toString());

        form.setWs13(bi.ws13.getText().toString());

        form.setWs14(bi.ws1401.isChecked() ? "1"
                : bi.ws1402.isChecked() ? "2"
                : bi.ws1403.isChecked() ? "3"
                : "-1");

        form.setWs15(bi.ws15.getText().toString());

        form.setWs16(bi.ws16.getText().toString());

        form.setWs17(bi.ws17.getText().toString());

        form.setWs18(bi.ws1801.isChecked() ? "1"
                : bi.ws1802.isChecked() ? "2"
                : "-1");

        form.setWs19(bi.ws19.getText().toString());

        form.setWs2001(bi.ws2001.isChecked() ? "1" : "-1");

        form.setWs2002(bi.ws2002.isChecked() ? "2" : "-1");

        form.setWs2003(bi.ws2003.isChecked() ? "3" : "-1");

        form.setWs2004(bi.ws2004.isChecked() ? "4" : "-1");

        form.setWs2005(bi.ws2005.isChecked() ? "5" : "-1");

        form.setWs2006(bi.ws2006.isChecked() ? "6" : "-1");

        form.setWs21(bi.ws2101.isChecked() ? "1"
                : bi.ws2102.isChecked() ? "2"
                : bi.ws2103.isChecked() ? "3"
                : bi.ws2104.isChecked() ? "4"
                : "-1");

        form.setWs2201(bi.ws2201.isChecked() ? "1" : "-1");

        form.setWs2202(bi.ws2202.isChecked() ? "2" : "-1");

        form.setWs2203(bi.ws2203.isChecked() ? "3" : "-1");

        form.setWs2296(bi.ws2296.isChecked() ? "96" : "-1");

        form.setWs2296x(bi.ws2296x.getText().toString());
        form.setWs2301(bi.ws2301.getText().toString());
        form.setWs2302(bi.ws2302.getText().toString());
        form.setWs2303(bi.ws2303.getText().toString());
        form.setWs25a(bi.ws25a01.isChecked() ? "1"
                : bi.ws25a02.isChecked() ? "2"
                : "-1");

        form.setWs25b(bi.ws25b01.isChecked() ? "1"
                : bi.ws25b02.isChecked() ? "2"
                : "-1");

        form.setWs25c(bi.ws25c01.isChecked() ? "1"
                : bi.ws25c02.isChecked() ? "2"
                : "-1");

        form.setWs25d(bi.ws25d01.isChecked() ? "1"
                : bi.ws25d02.isChecked() ? "2"
                : "-1");

        form.setWs25e(bi.ws25e01.isChecked() ? "1"
                : bi.ws25e02.isChecked() ? "2"
                : "-1");

        form.setWs25f(bi.ws25f01.isChecked() ? "1"
                : bi.ws25f02.isChecked() ? "2"
                : "-1");

        form.setWs25g(bi.ws25g01.isChecked() ? "1"
                : bi.ws25g02.isChecked() ? "2"
                : "-1");

        form.setWs25h(bi.ws25h01.isChecked() ? "1"
                : bi.ws25h02.isChecked() ? "2"
                : "-1");

        form.setWs25i(bi.ws25i01.isChecked() ? "1"
                : bi.ws25i02.isChecked() ? "2"
                : "-1");

        form.setWs25j(bi.ws25j01.isChecked() ? "1"
                : bi.ws25j02.isChecked() ? "2"
                : "-1");

        form.setWs25k(bi.ws25k01.isChecked() ? "96"
                : bi.ws25k02.isChecked() ? "2"
                : "-1");

        form.setWs25k01x(bi.ws25k01x.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.openSectionEndingActivity(this, false);
    }


    private void setListeners() {

        bi.ws18.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpCVws19));

        bi.ws21.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpws21));

        bi.ws14.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpws14));

        bi.ws2001.setOnCheckedChangeListener((compoundButton, b) -> {
            Clear.clearAllFields(bi.ws20check, !b);
        });

    }

    public void ws16OnTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(bi.ws16.getText().toString()))
            return;

        double weight = Double.parseDouble(bi.ws16.getText().toString());

        if (weight < 45) {
            if (bi.ws2001.isChecked()) {
                bi.ws2001.setChecked(false);
            }
            bi.ws2002.setChecked(true);
            bi.ws2001.setEnabled(false);
        } else {
            if (!bi.ws2004.isChecked()) {
                bi.ws2001.setEnabled(true);
            }
            bi.ws2002.setChecked(false);
        }
    }

    public void ws17OnTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(bi.ws17.getText().toString()))
            return;

        double mauc = Double.parseDouble(bi.ws17.getText().toString());

        if (mauc < 23) {
            if (bi.ws2001.isChecked()) {
                bi.ws2001.setChecked(false);
            }
            bi.ws2004.setChecked(true);
            bi.ws2001.setEnabled(false);
        } else {
            if (!bi.ws2002.isChecked()) {
                bi.ws2001.setEnabled(true);
            }
            bi.ws2004.setChecked(false);
        }
    }

}