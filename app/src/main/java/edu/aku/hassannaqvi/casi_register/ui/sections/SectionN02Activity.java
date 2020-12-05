package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySectionN02Binding;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.utils.EndSectionActivity;

import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

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
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }
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

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date().getTime()));
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setCr02(bi.cr02.getText().toString());

        form.setCr03(bi.cr03.getText().toString());

        form.setCr06(bi.cr06a.isChecked() ? "1"
                : bi.cr06b.isChecked() ? "2"
                : bi.cr06c.isChecked() ? "3"
                : bi.cr06d.isChecked() ? "4"
                : bi.cr06e.isChecked() ? "5"
                : bi.cr06x.isChecked() ? "6"
                : "-1");
        form.setCr06(bi.cr06xx.getText().toString());

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

        form.setCr14(bi.cr14.getText().toString());

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

        form.setCr27(bi.cr27a.isChecked() ? "1"
                : bi.cr27b.isChecked() ? "2"
                : bi.cr27c.isChecked() ? "3"
                : "-1");

        form.setCr28A(bi.cr28a01.isChecked() ? "1"
                : bi.cr28a02.isChecked() ? "2"
                : "-1");
        form.setCr28B(bi.cr28b01.isChecked() ? "1"
                : bi.cr28b02.isChecked() ? "2"
                : "-1");
        form.setCr28C(bi.cr28c01.isChecked() ? "1"
                : bi.cr28c02.isChecked() ? "2"
                : "-1");
        form.setCr28D(bi.cr28d01.isChecked() ? "1"
                : bi.cr28d02.isChecked() ? "2"
                : "-1");
        form.setCr28E(bi.cr28e01.isChecked() ? "1"
                : bi.cr28e02.isChecked() ? "2"
                : "-1");
        form.setCr28F(bi.cr28f01.isChecked() ? "1"
                : bi.cr28f02.isChecked() ? "2"
                : "-1");
        form.setCr28FX(bi.cr28fx.getText().toString());

        MainApp.setGPS(this);

//        json.put("status", flag);

        //  form.setsC(json.toString());
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