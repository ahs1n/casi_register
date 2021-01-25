package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection01Cs2Binding;
import edu.aku.hassannaqvi.casi_register.ui.other.MainActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.JSONUtils;

import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

public class Section01CS2Activity extends AppCompatActivity {

    ActivitySection01Cs2Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section01_cs2);
        bi.setCallback(this);
        setupContent();
        setListeners();
    }

    private void setupContent() {
    }

    private void setListeners() {

        bi.cs29.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpcs29));

        bi.cs31.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpcs31));
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_CS, MainApp.form.getcS());
        return updcount == 1;



        /*long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();


        json.put("cs28a", bi.cs28a01.isChecked() ? "1"
                : bi.cs28a02.isChecked() ? "2"
                : "-1");

        json.put("cs28b", bi.cs28b01.isChecked() ? "1"
                : bi.cs28b02.isChecked() ? "2"
                : "-1");

        json.put("cs28c", bi.cs28c01.isChecked() ? "1"
                : bi.cs28c02.isChecked() ? "2"
                : "-1");

        json.put("cs28d", bi.cs28d01.isChecked() ? "1"
                : bi.cs28d02.isChecked() ? "2"
                : "-1");

        json.put("cs28e", bi.cs28e01.isChecked() ? "1"
                : bi.cs28e02.isChecked() ? "2"
                : "-1");

        json.put("cs28f", bi.cs28f01.isChecked() ? "1"
                : bi.cs28f02.isChecked() ? "2"
                : "-1");

        json.put("cs28f01x", bi.cs28f01x.getText().toString());
        json.put("cs29", bi.cs2901.isChecked() ? "1"
                : bi.cs2902.isChecked() ? "2"
                : "-1");

        json.put("cs30a", bi.cs30a01.isChecked() ? "1"
                : bi.cs30a02.isChecked() ? "2"
                : "-1");

        json.put("cs30b", bi.cs30b01.isChecked() ? "1"
                : bi.cs30b02.isChecked() ? "2"
                : "-1");

        json.put("cs30c", bi.cs30c01.isChecked() ? "1"
                : bi.cs30c02.isChecked() ? "2"
                : "-1");

        json.put("cs30d", bi.cs30d01.isChecked() ? "1"
                : bi.cs30d02.isChecked() ? "2"
                : "-1");

        json.put("cs30e", bi.cs30e01.isChecked() ? "1"
                : bi.cs30e02.isChecked() ? "2"
                : "-1");

        json.put("cs30f", bi.cs30f096.isChecked() ? "96"
                : bi.cs30f02.isChecked() ? "2"
                : "-1");

        json.put("cs30f096x", bi.cs30f096x.getText().toString());
        json.put("cs31", bi.cs3101.isChecked() ? "1"
                : bi.cs3102.isChecked() ? "2"
                : "-1");

        json.put("cs32a", bi.cs32a01.isChecked() ? "1"
                : bi.cs32a02.isChecked() ? "2"
                : "-1");

        json.put("cs32b", bi.cs32b01.isChecked() ? "1"
                : bi.cs32b02.isChecked() ? "2"
                : "-1");

        json.put("cs32c", bi.cs32c01.isChecked() ? "1"
                : bi.cs32c02.isChecked() ? "2"
                : "-1");

        json.put("cs32d", bi.cs32d01.isChecked() ? "1"
                : bi.cs32d02.isChecked() ? "2"
                : "-1");

        json.put("cs32e", bi.cs32e01.isChecked() ? "1"
                : bi.cs32e02.isChecked() ? "2"
                : "-1");

        json.put("cs32f", bi.cs32f01.isChecked() ? "1"
                : bi.cs32f02.isChecked() ? "2"
                : "-1");

        json.put("cs32g", bi.cs32g01.isChecked() ? "1"
                : bi.cs32g02.isChecked() ? "2"
                : "-1");

        json.put("cs32h", bi.cs32h096.isChecked() ? "96"
                : bi.cs32h02.isChecked() ? "2"
                : "-1");

        json.put("cs32h096x", bi.cs32h096x.getText().toString());


//        form.setCs02(bi.cs02.getText().toString());

        /*form.setCs03(bi.cs03.getText().toString());

        form.setCs06(bi.cr06a.isChecked() ? "1"
                : bi.cr06b.isChecked() ? "2"
                : bi.cr06c.isChecked() ? "3"
                : bi.cr06d.isChecked() ? "4"
                : bi.cr06e.isChecked() ? "5"
                : bi.cr06x.isChecked() ? "96"
                : "-1");
        form.setCr06x(bi.cr06xx.getText().toString());

        form.setCr07(bi.cr07.getText().toString());

        form.setCr08d(bi.cr08d.getText().toString());
        form.setCr08m(bi.cr08m.getText().toString());
        form.setCr08y(bi.cr08y.getText().toString());

        form.setCr09(bi.cr09.getText().toString());

        form.setCr10(bi.cr10.getText().toString());

        form.setCr11(bi.cr11.getText().toString());

        form.setCr12(bi.cr12.getText().toString());

        form.setCr13(bi.cr13M.isChecked() ? "1"
                : bi.cr13F.isChecked() ? "2"
                : "-1");

        form.setCr14d(bi.cr14d.getText().toString().trim().isEmpty() ? "-1" : bi.cr14d.getText().toString());
        form.setCr14m(bi.cr14m.getText().toString().trim().isEmpty() ? "-1" : bi.cr14m.getText().toString());
        form.setCr14y(bi.cr14y.getText().toString().trim().isEmpty() ? "-1" : bi.cr14y.getText().toString());

        form.setCr15m(bi.cr15m.getText().toString().trim().isEmpty() ? "-1" : bi.cr15m.getText().toString());
        form.setCr15y(bi.cr15y.getText().toString().trim().isEmpty() ? "-1" : bi.cr15y.getText().toString());

        form.setCr16(bi.cr16.getText().toString());

        form.setCr17(bi.cr17a.isChecked() ? "1"
                : bi.cr17b.isChecked() ? "2"
                : "-1");

        form.setCr18(bi.cr18a.isChecked() ? "1"
                : bi.cr18b.isChecked() ? "2"
                : "-1");

        form.setCr19(bi.cr19a.isChecked() ? "1"
                : bi.cr19b.isChecked() ? "2"
                : "-1");

        form.setCr20(bi.cr20a.isChecked() ? "1"
                : bi.cr20b.isChecked() ? "2"
                : "-1");

        form.setCr21(bi.cr21.getText().toString());

        form.setCr22(bi.cr22.getText().toString());

        form.setCr23(bi.cr23.getText().toString());

        form.setCr24a(bi.cr24a.isChecked() ? "1" : "-1");
        form.setCr24b(bi.cr24b.isChecked() ? "2" : "-1");
        form.setCr24c(bi.cr24c.isChecked() ? "3" : "-1");
        form.setCr24d(bi.cr24d.isChecked() ? "4" : "-1");
        form.setCr24e(bi.cr24e.isChecked() ? "5" : "-1");
        form.setCr24f(bi.cr24f.isChecked() ? "6" : "-1");

        form.setCr25(bi.cr25a.isChecked() ? "1"
                : bi.cr25b.isChecked() ? "2"
                : bi.cr25c.isChecked() ? "3"
                : "-1");

        form.setCr26(bi.cr26a.isChecked() ? "1"
                : bi.cr26b.isChecked() ? "2"
                : bi.cr26c.isChecked() ? "3"
                : bi.cr26d.isChecked() ? "4"
                : "-1");

        form.setCr27a(bi.cr27a.getText().toString());
        form.setCr27b(bi.cr27b.getText().toString());
        form.setCr27c(bi.cr27c.getText().toString());


        form.setCr28a(bi.cr28a01.isChecked() ? "1"
                : bi.cr28a02.isChecked() ? "2"
                : "-1");
        form.setCr28b(bi.cr28b01.isChecked() ? "1"
                : bi.cr28b02.isChecked() ? "2"
                : "-1");
        form.setCr28c(bi.cr28c01.isChecked() ? "1"
                : bi.cr28c02.isChecked() ? "2"
                : "-1");
        form.setCr28d(bi.cr28d01.isChecked() ? "1"
                : bi.cr28d02.isChecked() ? "2"
                : "-1");
        form.setCr28e(bi.cr28e01.isChecked() ? "1"
                : bi.cr28e02.isChecked() ? "2"
                : "-1");
        form.setCr28f(bi.cr28f01.isChecked() ? "1"
                : bi.cr28f02.isChecked() ? "2"
                : "-1");
        form.setCr28fx(bi.cr28fx.getText().toString());*/

//        form.setsA(form.sAtoString())
        try {
            JSONObject jsonMerge = JSONUtils.mergeJSONObjects(new JSONObject(form.getcS()), json);
            form.setcS(String.valueOf(jsonMerge));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.contextEndActivity(this);
    }


    //    @Override
    public void endSecActivity(boolean flag) {
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press Not Allowed", Toast.LENGTH_SHORT).show();
    }

}