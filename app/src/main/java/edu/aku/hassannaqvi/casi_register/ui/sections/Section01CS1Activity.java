package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.core.ZScore;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection01Cs1Binding;
import edu.aku.hassannaqvi.casi_register.datecollection.AgeModel;
import edu.aku.hassannaqvi.casi_register.datecollection.DateRepository;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.ui.other.MainActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.EndSectionActivity;

import static edu.aku.hassannaqvi.casi_register.core.MainApp.DAYS_IN_A_MONTH;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

public class Section01CS1Activity extends AppCompatActivity implements EndSectionActivity {

    ActivitySection01Cs1Binding bi;
    boolean dtFlag = false;
    LocalDate calculatedDOB;
    LocalDate localDate;
    private double HLAZ;
    private double WAZ;
    private double WHZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section01_cs1);
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
            startActivity(new Intent(this, Section01CS2Activity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {

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
        form.setLocalDate(localDate);

        MainApp.setGPS(this);
        JSONObject cS = new JSONObject();

        cS.put("cs02", bi.cs0201.isChecked() ? "1"
                : bi.cs0202.isChecked() ? "2"
                : bi.cs0203.isChecked() ? "3"
                : "-1");

        cS.put("cs03", bi.cs03.getText().toString());

        cS.put("cs05a", bi.cs05a.getText().toString());

        cS.put("cs06", bi.cs0601.isChecked() ? "1"
                : bi.cs0602.isChecked() ? "2"
                : bi.cs0603.isChecked() ? "3"
                : bi.cs0604.isChecked() ? "4"
                : bi.cs0605.isChecked() ? "5"
                : bi.cs06096.isChecked() ? "96"
                : "-1");

        cS.put("cs06096x", bi.cs06096x.getText().toString());
        cS.put("cs07", bi.cs07.getText().toString());

        cS.put("cs08", bi.cs08.getText().toString());

        cS.put("cs0801", bi.cs0801.getText().toString());

        cS.put("cs0802", bi.cs0802.getText().toString());

        cS.put("cs0803", bi.cs0803.getText().toString());

        cS.put("cs09", bi.cs09.getText().toString());

        cS.put("cs10", bi.cs10.getText().toString());

        cS.put("cs10a", bi.cs10a.getText().toString());

        cS.put("cs11", bi.cs11.getText().toString());

        cS.put("cs11a", bi.cs11a.getText().toString());

        cS.put("cs12", bi.cs12.getText().toString());

        cS.put("cs12a", bi.cs12a.getText().toString());

        cS.put("cs13", bi.cs1301.isChecked() ? "1"
                : bi.cs1302.isChecked() ? "2"
                : "-1");

        cS.put("cs1401", bi.cs1401.getText().toString());

        cS.put("cs1402", bi.cs1402.getText().toString());

        cS.put("cs1403", bi.cs1403.getText().toString());

        cS.put("cs1501", bi.cs1501.getText().toString());

        cS.put("cs1502", bi.cs1502.getText().toString());

        cS.put("cs16", bi.cs16.getText().toString());

        cS.put("cs17", bi.cs1701.isChecked() ? "1"
                : bi.cs1702.isChecked() ? "2"
                : "-1");

        cS.put("cs18", bi.cs1801.isChecked() ? "1"
                : bi.cs1802.isChecked() ? "2"
                : "-1");

        cS.put("cs19", bi.cs1901.isChecked() ? "1"
                : bi.cs1902.isChecked() ? "2"
                : "-1");

        cS.put("cs20a", bi.cs20a01.isChecked() ? "1"
                : bi.cs20a02.isChecked() ? "2"
                : "-1");

        cS.put("cs20b", bi.cs20b01.isChecked() ? "1"
                : bi.cs20b02.isChecked() ? "2"
                : "-1");

        cS.put("cs21", bi.cs21.getText().toString());

        cS.put("cs22", bi.cs22.getText().toString());

        cS.put("cs23", bi.cs23.getText().toString());

        cS.put("cs2401", bi.cs2401.isChecked() ? "1" : "-1");

        cS.put("cs2402", bi.cs2402.isChecked() ? "2" : "-1");

        cS.put("cs2403", bi.cs2403.isChecked() ? "3" : "-1");

        cS.put("cs2404", bi.cs2404.isChecked() ? "4" : "-1");

        cS.put("cs2405", bi.cs2405.isChecked() ? "5" : "-1");

        cS.put("cs2406", bi.cs2406.isChecked() ? "6" : "-1");

        cS.put("cs25", bi.cs2501.isChecked() ? "1"
                : bi.cs2502.isChecked() ? "2"
                : bi.cs2503.isChecked() ? "3"
                : bi.cs2504.isChecked() ? "4"
                : "-1");

        cS.put("cs25a01", bi.cs25a01.getText().toString());

        cS.put("cs25b01", bi.cs25b01.getText().toString());

        cS.put("cs25c", bi.cs25c01.isChecked() ? "1"
                : bi.cs25c02.isChecked() ? "2"
                : bi.cs25c096.isChecked() ? "96"
                : "-1");

        cS.put("cs25c096x", bi.cs25c096x.getText().toString());
        cS.put("cs2601", bi.cs2601.isChecked() ? "1" : "-1");

        cS.put("cs2602", bi.cs2602.isChecked() ? "2" : "-1");

        cS.put("cs2603", bi.cs2603.isChecked() ? "3" : "-1");

        cS.put("cs2604", bi.cs2604.isChecked() ? "4" : "-1");

        cS.put("cs2605", bi.cs2605.isChecked() ? "5" : "-1");

        cS.put("cs2701", bi.cs2701.getText().toString());

        cS.put("cs2702", bi.cs2702.getText().toString());

        cS.put("cs2703", bi.cs2703.getText().toString());

        cS.put("cs2704", bi.cs2704.getText().toString());


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

//        form.setsA(form.sAtoString());

        form.setcS((String.valueOf(cS)));

    }


    private boolean formValidation() {
        if (!Validator.emptyCheckingContainer(this, bi.GrpName))
            return false;
        if (!dtFlag) {
            return Validator.emptyCustomTextBox(this, bi.cs1403, "Invalid date!");
        }
        if (Integer.parseInt(bi.cs1502.getText().toString()) == 0 && Integer.parseInt(bi.cs1501.getText().toString()) == 0)
            return Validator.emptyCustomTextBox(this, bi.cs1501, "Both Month & Year don't be zero!!", false);
        return true;
    }


    public void BtnEnd() {
        AppUtilsKt.contextEndActivity(this);
    }

    private void setListeners() {

        EditText[] txtListener = new EditText[]{bi.cs1401, bi.cs1402};
        for (EditText txtItem : txtListener) {

            txtItem.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //Clear.clearAllFields(bi.fldGrpCVcs15);
                    bi.cs1502.setText(null);
                    bi.cs1501.setText(null);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        bi.cs2401.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.cs24check, false);
                Clear.clearAllFields(bi.fldGrpcs2401);
                bi.fldGrpcs2401.setVisibility(View.GONE);
            } else {
                Clear.clearAllFields(bi.cs24check, true);
                bi.fldGrpcs2401.setVisibility(View.VISIBLE);
            }
        });

        bi.cs25.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.fldGrpcs2501));

        bi.cs2605.setOnCheckedChangeListener((compoundButton, b) -> Clear.clearAllFields(bi.cs26check, !b));

    }

    public void cs15yOnTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(bi.cs1501.getText().toString()) || TextUtils.isEmpty(bi.cs1502.getText().toString()))
            return;

        if (!bi.cs1502.isRangeTextValidate() || !bi.cs1501.isRangeTextValidate())
            return;
        int age = Integer.parseInt(bi.cs1502.getText().toString()) + (Integer.parseInt(bi.cs1501.getText().toString()) * 12);

        if (age != 0) {
            bi.fldGrpCVcs18.setVisibility(View.GONE);
            bi.fldGrpCVcs19.setVisibility(View.GONE);
            bi.fldGrpCVcs20a.setVisibility(View.GONE);
            bi.fldGrpCVcs20b.setVisibility(View.GONE);

            if (age >= 6) {
                bi.fldGrpCVcs18.setVisibility(View.GONE);
            } else bi.fldGrpCVcs18.setVisibility(View.VISIBLE);

            if (age < 6 || age >= 24) {
                bi.fldGrpCVcs19.setVisibility(View.GONE);
                bi.fldGrpCVcs20b.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVcs19.setVisibility(View.VISIBLE);
                bi.fldGrpCVcs20b.setVisibility(View.VISIBLE);
            }

            if (age >= 24) {
                bi.fldGrpCVcs20a.setVisibility(View.GONE);
            } else bi.fldGrpCVcs20a.setVisibility(View.VISIBLE);
        }
    }

    public void cs14ddmmOnTextChanged(CharSequence s, int start, int before, int count) {
        bi.cs1403.setText(null);
    }

    public void cs08OnTextChanged(CharSequence s, int start, int before, int count) {
        localDate = null;
        if (TextUtils.isEmpty(bi.cs08.getText()))
            return;

        //Setting Date
        try {
            Instant instant = Instant.parse(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(
                    bi.cs08.getText().toString()
            )) + "T06:24:01Z");
            localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void cs14yOnTextChanged(CharSequence s, int start, int before, int count) {
        bi.cs1502.setEnabled(false);
        bi.cs1502.setText(null);
        bi.cs1501.setEnabled(false);
        bi.cs1501.setText(null);
        calculatedDOB = null;
        if (TextUtils.isEmpty(bi.cs1401.getText()) || TextUtils.isEmpty(bi.cs1402.getText()) || TextUtils.isEmpty(bi.cs1403.getText()))
            return;
        if (!bi.cs1401.isRangeTextValidate() || !bi.cs1402.isRangeTextValidate() || !bi.cs1403.isRangeTextValidate())
            return;
        if (bi.cs1401.getText().toString().equals("98") && bi.cs1402.getText().toString().equals("98") && bi.cs1403.getText().toString().equals("9998")) {
            bi.cs1502.setEnabled(true);
            bi.cs1501.setEnabled(true);
            dtFlag = true;
            return;
        }
        int day = bi.cs1401.getText().toString().equals("00") ? 0 : Integer.parseInt(bi.cs1401.getText().toString());
        int month = Integer.parseInt(bi.cs1402.getText().toString());
        int year = Integer.parseInt(bi.cs1403.getText().toString());

        AgeModel age;
        if (localDate != null)
            age = DateRepository.Companion.getCalculatedAge(localDate, year, month, day);
        else
            age = DateRepository.Companion.getCalculatedAge(year, month, day);
        if (age == null) {
            bi.cs1403.setError("Invalid date!");
            dtFlag = false;
            return;
        }
        dtFlag = true;
        bi.cs1502.setText(String.valueOf(age.getMonth()));
        bi.cs1501.setText(String.valueOf(age.getYear()));

        //Setting Date
        try {
            Instant instant = Instant.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(
                    bi.cs1401.getText().toString() + "-" + bi.cs1402.getText().toString() + "-" + bi.cs1403.getText().toString()
            )) + "T06:24:01Z");
            calculatedDOB = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
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

    public void CheckZScore(View view) {
        if (!bi.cs1501.getText().toString().equals("")
                && !bi.cs1502.getText().toString().equals("")
                && !bi.cs21.getText().toString().equals("")
                && !bi.cs22.getText().toString().equals("")
                && (bi.cs1301.isChecked() || bi.cs1302.isChecked())
        ) {


            int ageinmonths = Integer.parseInt(bi.cs1502.getText().toString()) + Integer.parseInt(bi.cs1501.getText().toString());
            int ageindays = (int) Math.floor(ageinmonths * DAYS_IN_A_MONTH);
            int gender = bi.cs1301.isChecked() ? 1 : bi.cs1302.isChecked() ? 2 : 0;

            ZScore ha = new ZScore(ageindays, gender);
            HLAZ = ha.getZScore_HLAZ(bi.cs21.getText().toString());
            ZScore wa = new ZScore(ageindays, gender);
            WAZ = wa.getZScore_WAZ(bi.cs22.getText().toString());
            ZScore wh = new ZScore(ageindays, gender);
            WHZ = wh.getZScore_WHZ(bi.cs22.getText().toString(), bi.cs21.getText().toString());

            bi.ZScore.setText("HLAZ: " + HLAZ + " \r\nWAZ: " + WAZ + " \r\nWHZ: " + WHZ);
        } else {

            Toast.makeText(this, "Z-Score cannot be evaluated with missing values.", Toast.LENGTH_SHORT).show();

        }


    }


/*    public void cs08OnTextChanged(CharSequence s, int start, int before, int count) {
        //Setting Screening/today Date
        try {
            Instant instant = Instant.parse(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd-MM-yyyy").parse(bi.cs08.getText().toString())) + "T06:24:01Z");
            localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

/*    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press Not Allowed", Toast.LENGTH_SHORT).show();
    }*/
}