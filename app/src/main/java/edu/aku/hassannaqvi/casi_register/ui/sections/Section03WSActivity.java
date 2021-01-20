package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection03WsBinding;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.ui.other.MainActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;

import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

public class Section03WSActivity extends AppCompatActivity {

    ActivitySection03WsBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section03_ws);
        bi.setCallback(this);
        setListeners();
        setupContent();
    }

    private void setupContent() {
    }


    public void BtnContinue() {
        if (!formValidation()) return;
        SaveDraft();
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, Section01CSActivity.class));
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


    private void SaveDraft() {

        /*
        form = new Form();
        form.setSysdate(new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date().getTime()));
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());
        MainApp.setGPS(this);
        */

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date().getTime()));
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setCountry(MainActivity.mainInfo.getCountry());
        form.setDistrict(MainActivity.mainInfo.getDistrict());
        form.setUc(MainActivity.mainInfo.getUc());
        form.setVillage(MainActivity.mainInfo.getVillage());
        MainApp.setGPS(this);


        form.setsA(form.sAtoString());

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