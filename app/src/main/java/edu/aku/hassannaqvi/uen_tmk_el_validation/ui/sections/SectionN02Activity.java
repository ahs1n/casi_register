package edu.aku.hassannaqvi.uen_tmk_el_validation.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.uen_tmk_el_validation.R;
import edu.aku.hassannaqvi.uen_tmk_el_validation.core.MainApp;
import edu.aku.hassannaqvi.uen_tmk_el_validation.databinding.ActivitySectionN02Binding;
import edu.aku.hassannaqvi.uen_tmk_el_validation.utils.EndSectionActivity;

public class SectionN02Activity extends AppCompatActivity implements EndSectionActivity {

    ActivitySectionN02Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_n02);
        bi.setCallback(this);
        setupSkip();
        setupContent();
    }

    private void setupContent() {

    }

    private void setupSkip() {

    }

    public void BtnContinue() {
        if (!formValidation()) return;
        try {
            SaveDraft(true);
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, EndSectionActivity.class));
            } else {
                Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean UpdateDB() {
        /*DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addMWRACHILD(anthro);
        anthro.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            anthro.set_UID(anthro.getDeviceID() + anthro.get_ID());
            db.updatesMWRAChildColumn(Mwra_ChildrenContract.MWRAChildTable.COLUMN_UID, anthro.get_UID(), anthro.get_ID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();

        }*/
        return false;
    }

    private void SaveDraft(boolean flag) throws JSONException {

       /* anthro = new MWRA_CHILD();
        anthro.setUsername(MainApp.userName);
        anthro.setDeviceID(MainApp.appInfo.getDeviceID());
        anthro.setDevicetagID(MainApp.appInfo.getTagName());
        anthro.setAppversion(MainApp.appInfo.getAppVersion());
        anthro.setElb1(MainApp.form.getElb1());
        anthro.setElb11(MainApp.form.getElb11());
        anthro.setType(CHILD_ANTHRO_TYPE);
        anthro.setSysdate(MainApp.form.getSysdate());
        anthro.setUUID(MainApp.form.get_UID());*/

        JSONObject json = new JSONObject();

        json.put("elb8a", MainApp.form.getElb8a());
        json.put("name", bi.cr05.getText().toString());

        json.put("cr06", bi.cr06.getText().toString());

        json.put("cr07", bi.cr07.getText().toString());

        json.put("cr08", bi.cr08.getText().toString());

        json.put("cr09", bi.cr09.getText().toString());

        json.put("cr10", bi.cr10.getText().toString());

        MainApp.setGPS(this);

        json.put("status", flag);

//        anthro.setsB(json.toString());
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
//        if (!Validator.emptyTextBox(this, bi.can6)) return;
//        AppUtilsKt.openWarningActivity(this, "Are you sure, you want to end " + bi.can6.getText().toString() + " anthro form?");
    }

    @Override
    public void endSecActivity(boolean flag) {
        try {
            SaveDraft(false);
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, EndSectionActivity.class));
            } else {
                Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}