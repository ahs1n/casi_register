package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection04WsfpBinding;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

public class Section04WSFPActivity extends AppCompatActivity {

    ActivitySection04WsfpBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section04_wsfp);
        bi.setCallback(this);
        setListeners();
        setupContent();
    }

    private void setupContent() {
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
            startActivity(new Intent(this, Section01CS1Activity.class));
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
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();
        json.put("fw01", bi.fw01.getText().toString());

        json.put("fw01a", bi.fw01a.getText().toString());

        json.put("fw01b", bi.fw01b.getText().toString());

        json.put("fw02", bi.fw0201.isChecked() ? "1"
                : bi.fw0202.isChecked() ? "2"
                : bi.fw0203.isChecked() ? "3"
                : "-1");

        json.put("fw03", bi.fw03.getText().toString());

        json.put("fw04", bi.fw04.getText().toString());

        json.put("fw05", bi.fw05.getText().toString());

        json.put("fw05a", bi.fw05a.getText().toString());

        json.put("fw06", bi.fw0601.isChecked() ? "1"
                : bi.fw0602.isChecked() ? "2"
                : bi.fw0603.isChecked() ? "3"
                : bi.fw0604.isChecked() ? "4"
                : bi.fw0605.isChecked() ? "5"
                : bi.fw0696.isChecked() ? "96"
                : "-1");

        json.put("fw0696x", bi.fw0696x.getText().toString());
        json.put("fw07", bi.fw07.getText().toString());

        json.put("fw0801", bi.fw0801.getText().toString());

        json.put("fw0802", bi.fw0802.getText().toString());

        json.put("fw0803", bi.fw0803.getText().toString());

        json.put("fw09", bi.fw09.getText().toString());

        json.put("fw10", bi.fw10.getText().toString());

        json.put("fw11", bi.fw11.getText().toString());

        json.put("fw12", bi.fw12.getText().toString());

        json.put("fw12a", bi.fw12a.getText().toString());

        json.put("fw12b", bi.fw12b01.isChecked() ? "1"
                : bi.fw12b02.isChecked() ? "2"
                : bi.fw12b03.isChecked() ? "3"
                : bi.fw12b04.isChecked() ? "4"
                : bi.fw12b05.isChecked() ? "5"
                : bi.fw12b96.isChecked() ? "96"
                : "-1");

        json.put("fw12b96x", bi.fw12b96x.getText().toString());
        json.put("fw13", bi.fw13.getText().toString());

        json.put("fw14", bi.fw1401.isChecked() ? "1"
                : bi.fw1402.isChecked() ? "2"
                : bi.fw1403.isChecked() ? "3"
                : "-1");

        json.put("fw15", bi.fw15.getText().toString());

        json.put("fw16", bi.fw16.getText().toString());

        json.put("fw17", bi.fw17.getText().toString());

        json.put("fw1801", bi.fw1801.isChecked() ? "1" : "-1");

        json.put("fw1802", bi.fw1802.isChecked() ? "2" : "-1");

        json.put("fw1803", bi.fw1803.isChecked() ? "3" : "-1");

        json.put("fw1804", bi.fw1804.isChecked() ? "4" : "-1");

        json.put("fw1805", bi.fw1805.isChecked() ? "5" : "-1");

        json.put("fw1806", bi.fw1806.isChecked() ? "6" : "-1");

        json.put("fw19", bi.fw1901.isChecked() ? "1"
                : bi.fw1902.isChecked() ? "2"
                : "-1");

        json.put("fw2001", bi.fw2001.getText().toString());

        json.put("fw21", bi.fw2101.isChecked() ? "1"
                : bi.fw2102.isChecked() ? "2"
                : bi.fw2103.isChecked() ? "3"
                : bi.fw2104.isChecked() ? "4"
                : "-1");

        json.put("fw22", bi.fw22.getText().toString());

        json.put("fw2301", bi.fw2301.getText().toString());

        json.put("fw24", bi.fw24.getText().toString());

        json.put("fw25", bi.fw25.getText().toString());

        json.put("fw26", bi.fw2601.isChecked() ? "1"
                : bi.fw2602.isChecked() ? "2"
                : "-1");

        json.put("fw27", bi.fw2701.isChecked() ? "1"
                : bi.fw2702.isChecked() ? "2"
                : bi.fw2703.isChecked() ? "3"
                : bi.fw2704.isChecked() ? "4"
                : "-1");

        json.put("fw2801", bi.fw2801.isChecked() ? "1" : "-1");

        json.put("fw2802", bi.fw2802.isChecked() ? "2" : "-1");

        json.put("fw2803", bi.fw2803.isChecked() ? "3" : "-1");

        json.put("fw2804", bi.fw2804.isChecked() ? "4" : "-1");

        json.put("fw2896", bi.fw2896.isChecked() ? "96" : "-1");

        json.put("fw2896x", bi.fw2896x.getText().toString());
        json.put("fw29a", bi.fw29a01.isChecked() ? "1"
                : bi.fw29a02.isChecked() ? "2"
                : "-1");

        json.put("fw29b", bi.fw29b01.isChecked() ? "1"
                : bi.fw29b02.isChecked() ? "2"
                : "-1");

        json.put("fw29c", bi.fw29c01.isChecked() ? "1"
                : bi.fw29c02.isChecked() ? "2"
                : "-1");

        json.put("fw29d", bi.fw29d01.isChecked() ? "1"
                : bi.fw29d02.isChecked() ? "2"
                : "-1");

        json.put("fw29e", bi.fw29e01.isChecked() ? "1"
                : bi.fw29e02.isChecked() ? "2"
                : "-1");

        json.put("fw29f", bi.fw29f01.isChecked() ? "1"
                : bi.fw29f02.isChecked() ? "2"
                : "-1");

        json.put("fw29g", bi.fw29g01.isChecked() ? "1"
                : bi.fw29g02.isChecked() ? "2"
                : "-1");

        json.put("fw29h", bi.fw29h01.isChecked() ? "1"
                : bi.fw29h02.isChecked() ? "2"
                : "-1");

        json.put("fw29i", bi.fw29i01.isChecked() ? "1"
                : bi.fw29i02.isChecked() ? "2"
                : "-1");

        json.put("fw29j", bi.fw29j01.isChecked() ? "1"
                : bi.fw29j02.isChecked() ? "2"
                : "-1");

        json.put("fw29k", bi.fw29k96.isChecked() ? "96"
                : bi.fw29k02.isChecked() ? "2"
                : "-1");

        json.put("fw29k96x", bi.fw29k96x.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
        AppUtilsKt.contextEndActivity(this);
    }


    private void setListeners() {
    }

    /*@Override
    public void endSecActivity(boolean flag) {
        SaveDraft();
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }*/

/*    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press Not Allowed", Toast.LENGTH_SHORT).show();
    }*/
}