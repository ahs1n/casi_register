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
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection02CsfpBinding;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

public class Section02CSFPActivity extends AppCompatActivity {

    ActivitySection02CsfpBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section02_csfp);
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
        json.put("fc01", bi.fc01.getText().toString());

        json.put("fc01a", bi.fc01a.getText().toString());

        json.put("fc01b", bi.fc01b.getText().toString());

        json.put("fc02", bi.fc0201.isChecked() ? "1"
                : bi.fc0202.isChecked() ? "2"
                : bi.fc0203.isChecked() ? "3"
                : "-1");

        json.put("fc03", bi.fc03.getText().toString());

        json.put("fc04", bi.fc04.getText().toString());

        json.put("fc05", bi.fc05.getText().toString());

        json.put("fc05a", bi.fc05a.getText().toString());

        json.put("fc06", bi.fc0601.isChecked() ? "1"
                : bi.fc0602.isChecked() ? "2"
                : bi.fc0603.isChecked() ? "3"
                : bi.fc0604.isChecked() ? "4"
                : bi.fc0605.isChecked() ? "5"
                : bi.fc0696.isChecked() ? "96"
                : "-1");

        json.put("fc0696x", bi.fc0696x.getText().toString());
        json.put("fc07", bi.fc07.getText().toString());

        json.put("fc0801", bi.fc0801.getText().toString());

        json.put("fc0802", bi.fc0802.getText().toString());

        json.put("fc0803", bi.fc0803.getText().toString());

        json.put("fc09", bi.fc09.getText().toString());

        json.put("fc10", bi.fc10.getText().toString());

        json.put("fc15", bi.fc15.getText().toString());

        json.put("fc15a", bi.fc15a.getText().toString());

        json.put("fc16", bi.fc16.getText().toString());

        json.put("fc16a", bi.fc16a.getText().toString());

        json.put("fc16b", bi.fc16b01.isChecked() ? "1"
                : bi.fc16b02.isChecked() ? "2"
                : bi.fc16b03.isChecked() ? "3"
                : bi.fc16b04.isChecked() ? "4"
                : bi.fc16b05.isChecked() ? "5"
                : bi.fc16b96.isChecked() ? "96"
                : "-1");

        json.put("fc16b96x", bi.fc16b96x.getText().toString());
        json.put("fc1701", bi.fc1701.getText().toString());

        json.put("fc1702", bi.fc1702.getText().toString());

        json.put("fc18", bi.fc1801.isChecked() ? "1"
                : bi.fc1802.isChecked() ? "2"
                : "-1");

        json.put("fc19", bi.fc1901.isChecked() ? "1"
                : bi.fc1902.isChecked() ? "2"
                : "-1");

        json.put("fc20", bi.fc2001.isChecked() ? "1"
                : bi.fc2002.isChecked() ? "2"
                : "-1");

        json.put("fc21", bi.fc2101.isChecked() ? "1"
                : bi.fc2102.isChecked() ? "2"
                : "-1");

        json.put("fc22", bi.fc22.getText().toString());

        json.put("fc23", bi.fc23.getText().toString());

        json.put("fc24", bi.fc24.getText().toString());

        json.put("fc2501", bi.fc2501.isChecked() ? "1" : "-1");

        json.put("fc2502", bi.fc2502.isChecked() ? "2" : "-1");

        json.put("fc2503", bi.fc2503.isChecked() ? "3" : "-1");

        json.put("fc2504", bi.fc2504.isChecked() ? "4" : "-1");

        json.put("fc2505", bi.fc2505.isChecked() ? "5" : "-1");

        json.put("fc2506", bi.fc2506.isChecked() ? "6" : "-1");

        json.put("fc2601", bi.fc2601.isChecked() ? "1" : "-1");

        json.put("fc2602", bi.fc2602.isChecked() ? "2" : "-1");

        json.put("fc2603", bi.fc2603.isChecked() ? "3" : "-1");

        json.put("fc2604", bi.fc2604.isChecked() ? "4" : "-1");

        json.put("fc2605", bi.fc2605.isChecked() ? "5" : "-1");

        json.put("fc2701", bi.fc2701.getText().toString());

        json.put("fc2702", bi.fc2702.getText().toString());

        json.put("fc2703", bi.fc2703.getText().toString());

        json.put("fc2704", bi.fc2704.getText().toString());

        json.put("fc28", bi.fc2801.isChecked() ? "1"
                : bi.fc2802.isChecked() ? "2"
                : bi.fc2803.isChecked() ? "3"
                : bi.fc2804.isChecked() ? "4"
                : "-1");

        json.put("fc2901", bi.fc2901.isChecked() ? "1" : "-1");

        json.put("fc2902", bi.fc2902.isChecked() ? "2" : "-1");

        json.put("fc2903", bi.fc2903.isChecked() ? "3" : "-1");

        json.put("fc2904", bi.fc2904.isChecked() ? "4" : "-1");

        json.put("fc2905", bi.fc2905.isChecked() ? "5" : "-1");

        json.put("fc2996", bi.fc2996.isChecked() ? "96" : "-1");

        json.put("fc2996x", bi.fc2996x.getText().toString());
        json.put("fc30", bi.fc30.getText().toString());

        json.put("fc31", bi.fc3101.isChecked() ? "1"
                : bi.fc3102.isChecked() ? "2"
                : "-1");

        json.put("fc3201", bi.fc3201.getText().toString());

        json.put("fc3202", bi.fc3202.getText().toString());

        json.put("fc3203", bi.fc3203.getText().toString());

        json.put("fc3301", bi.fc3301.getText().toString());

        json.put("fc3302", bi.fc3302.getText().toString());

        json.put("fc3303", bi.fc3303.getText().toString());

        json.put("fc34", bi.fc3401.isChecked() ? "1"
                : bi.fc3402.isChecked() ? "2"
                : bi.fc3403.isChecked() ? "3"
                : bi.fc3404.isChecked() ? "4"
                : "-1");

        json.put("fc3501", bi.fc3501.getText().toString());

        json.put("fc3601", bi.fc3601.getText().toString());

        json.put("fc37", bi.fc3701.isChecked() ? "1"
                : bi.fc3702.isChecked() ? "2"
                : bi.fc3796.isChecked() ? "96"
                : "-1");

        json.put("fc3796x", bi.fc3796x.getText().toString());
        json.put("fc38", bi.fc3801.isChecked() ? "1"
                : bi.fc3802.isChecked() ? "2"
                : "-1");


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