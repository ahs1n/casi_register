package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

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
/*        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_WS, MainApp.form.getcS());
        return updcount == 1;*/

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long rowid = db.addForm(form);
        form.set_ID(String.valueOf(rowid));
        if (rowid > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private void SaveDraft() throws JSONException {

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
        JSONObject wS = new JSONObject();


        wS.put("ws02", bi.ws0201.isChecked() ? "1"
                : bi.ws0202.isChecked() ? "2"
                : bi.ws0203.isChecked() ? "3"
                : "-1");

        wS.put("ws03", bi.ws03.getText().toString());


        wS.put("ws05a", bi.ws05a.getText().toString());

        wS.put("ws06", bi.ws0601.isChecked() ? "1"
                : bi.ws0602.isChecked() ? "2"
                : bi.ws0603.isChecked() ? "3"
                : bi.ws0604.isChecked() ? "4"
                : bi.ws0605.isChecked() ? "5"
                : bi.ws0696.isChecked() ? "96"
                : "-1");

        wS.put("ws0696x", bi.ws0696x.getText().toString());
        wS.put("ws07", bi.ws07.getText().toString());

        wS.put("ws0801", bi.ws0801.getText().toString());

        wS.put("ws0802", bi.ws0802.getText().toString());

        wS.put("ws0803", bi.ws0803.getText().toString());

        wS.put("ws09", bi.ws09.getText().toString());

        wS.put("ws10", bi.ws10.getText().toString());

        wS.put("ws11", bi.ws11.getText().toString());

        wS.put("ws12", bi.ws12.getText().toString());

        wS.put("ws12a", bi.ws12a.getText().toString());

        wS.put("ws13", bi.ws13.getText().toString());

        wS.put("ws14", bi.ws1401.isChecked() ? "1"
                : bi.ws1402.isChecked() ? "2"
                : bi.ws1403.isChecked() ? "3"
                : "-1");

        wS.put("ws15", bi.ws15.getText().toString());

        wS.put("ws16", bi.ws16.getText().toString());

        wS.put("ws17", bi.ws17.getText().toString());

        wS.put("ws18", bi.ws1801.isChecked() ? "1"
                : bi.ws1802.isChecked() ? "2"
                : "-1");

        wS.put("ws19", bi.ws19.getText().toString());

        wS.put("ws2001", bi.ws2001.isChecked() ? "1" : "-1");

        wS.put("ws2002", bi.ws2002.isChecked() ? "2" : "-1");

        wS.put("ws2003", bi.ws2003.isChecked() ? "3" : "-1");

        wS.put("ws2004", bi.ws2004.isChecked() ? "4" : "-1");

        wS.put("ws2005", bi.ws2005.isChecked() ? "5" : "-1");

        wS.put("ws2006", bi.ws2006.isChecked() ? "6" : "-1");

        wS.put("ws21", bi.ws2101.isChecked() ? "1"
                : bi.ws2102.isChecked() ? "2"
                : bi.ws2103.isChecked() ? "3"
                : bi.ws2104.isChecked() ? "4"
                : "-1");

        wS.put("ws2201", bi.ws2201.isChecked() ? "1" : "-1");

        wS.put("ws2202", bi.ws2202.isChecked() ? "2" : "-1");

        wS.put("ws2203", bi.ws2203.isChecked() ? "3" : "-1");

        wS.put("ws2296", bi.ws2296.isChecked() ? "96" : "-1");

        wS.put("ws2296x", bi.ws2296x.getText().toString());
        wS.put("ws2301", bi.ws2301.getText().toString());

        wS.put("ws2302", bi.ws2302.getText().toString());

        wS.put("ws2303", bi.ws2303.getText().toString());

        wS.put("ws25a", bi.ws25a01.isChecked() ? "1"
                : bi.ws25a02.isChecked() ? "2"
                : "-1");

        wS.put("ws25b", bi.ws25b01.isChecked() ? "1"
                : bi.ws25b02.isChecked() ? "2"
                : "-1");

        wS.put("ws25c", bi.ws25c01.isChecked() ? "1"
                : bi.ws25c02.isChecked() ? "2"
                : "-1");

        wS.put("ws25d", bi.ws25d01.isChecked() ? "1"
                : bi.ws25d02.isChecked() ? "2"
                : "-1");

        wS.put("ws25e", bi.ws25e01.isChecked() ? "1"
                : bi.ws25e02.isChecked() ? "2"
                : "-1");

        wS.put("ws25f", bi.ws25f01.isChecked() ? "1"
                : bi.ws25f02.isChecked() ? "2"
                : "-1");

        wS.put("ws25g", bi.ws25g01.isChecked() ? "1"
                : bi.ws25g02.isChecked() ? "2"
                : "-1");

        wS.put("ws25h", bi.ws25h01.isChecked() ? "1"
                : bi.ws25h02.isChecked() ? "2"
                : "-1");

        wS.put("ws25i", bi.ws25i01.isChecked() ? "1"
                : bi.ws25i02.isChecked() ? "2"
                : "-1");

        wS.put("ws25j", bi.ws25j01.isChecked() ? "1"
                : bi.ws25j02.isChecked() ? "2"
                : "-1");

        wS.put("ws25k", bi.ws25k01.isChecked() ? "96"
                : bi.ws25k02.isChecked() ? "2"
                : "-1");

        wS.put("ws25k01x", bi.ws25k01x.getText().toString());

        form.setwS((String.valueOf(wS)));

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