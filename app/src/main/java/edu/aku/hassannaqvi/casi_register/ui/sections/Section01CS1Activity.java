package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONObject;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.core.ZScore;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection01Cs1Binding;
import edu.aku.hassannaqvi.casi_register.utils.datecollection.AgeModel;
import edu.aku.hassannaqvi.casi_register.utils.datecollection.DateRepository;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.ui.MainActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.EndSectionActivity;
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage;

import static edu.aku.hassannaqvi.casi_register.CONSTANTS.DAYS_IN_A_MONTH;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

public class Section01CS1Activity extends AppCompatActivity implements EndSectionActivity {

    ActivitySection01Cs1Binding bi;
    boolean dtFlag = false;
    LocalDate calculatedDOB;
    LocalDate localDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section01_cs1);
        bi.setCallback(this);
        setListeners();
        setupContent();
    }

    private void setupContent() {
        String regID = SharedStorage.INSTANCE.getLastRegistrationID(this,"f-");

    }

    /*
     * Save functions
     * */
    private boolean updateDB() {

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

    private void saveDraft() {

        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        form.setUsername(MainApp.user.getUserName());
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setCountry(MainActivity.mainInfo.getCountry());
        form.setDistrict(MainActivity.mainInfo.getDistrict());
        form.setUc(MainActivity.mainInfo.getUc());
        form.setVillage(MainActivity.mainInfo.getVillage());
        form.setLocalDate(localDate);

        JSONObject cS = new JSONObject();

        form.setCs02(bi.cs0201.isChecked() ? "1"
                : bi.cs0202.isChecked() ? "2"
                : bi.cs0203.isChecked() ? "3"
                : "-1");

        form.setCs03(bi.cs03.getText().toString());

        form.setCs05a(bi.cs05a.getText().toString());

        form.setCs06(bi.cs0601.isChecked() ? "1"
                : bi.cs0602.isChecked() ? "2"
                : bi.cs0603.isChecked() ? "3"
                : bi.cs0604.isChecked() ? "4"
                : bi.cs0605.isChecked() ? "5"
                : bi.cs06096.isChecked() ? "96"
                : "-1");
        form.setCs06096x(bi.cs06096x.getText().toString());

        form.setCs07(bi.cs07.getText().toString());

        form.setCs08(bi.cs08.getText().toString());

        form.setCs0801(bi.cs0801.getText().toString());

        form.setCs0802(bi.cs0802.getText().toString());

        form.setCs0803(bi.cs0803.getText().toString());

        form.setCs09(bi.cs09.getText().toString());

        form.setCs10(bi.cs10.getText().toString());

        form.setCs10a(bi.cs10a.getText().toString());

        form.setCs11(bi.cs11.getText().toString());

        form.setCs11a(bi.cs11a.getText().toString());

        form.setCs12(bi.cs12.getText().toString());

        form.setCs12a(bi.cs12a.getText().toString());

        form.setCs13(bi.cs1301.isChecked() ? "1"
                : bi.cs1302.isChecked() ? "2"
                : "-1");

        form.setCs1401(bi.cs1401.getText().toString());

        form.setCs1402(bi.cs1402.getText().toString());

        form.setCs1403(bi.cs1403.getText().toString());

        form.setCs1501(bi.cs1501.getText().toString());

        form.setCs1502(bi.cs1502.getText().toString());

        form.setCs16(bi.cs16.getText().toString());

        form.setCs17(bi.cs1701.isChecked() ? "1"
                : bi.cs1702.isChecked() ? "2"
                : "-1");

        form.setCs18(bi.cs1801.isChecked() ? "1"
                : bi.cs1802.isChecked() ? "2"
                : "-1");

        form.setCs19(bi.cs1901.isChecked() ? "1"
                : bi.cs1902.isChecked() ? "2"
                : "-1");

        form.setCs20a(bi.cs20a01.isChecked() ? "1"
                : bi.cs20a02.isChecked() ? "2"
                : "-1");

        form.setCs20b(bi.cs20b01.isChecked() ? "1"
                : bi.cs20b02.isChecked() ? "2"
                : "-1");

        form.setCs21(bi.cs21.getText().toString());

        form.setCs22(bi.cs22.getText().toString());

        form.setCs23(bi.cs23.getText().toString());

        form.setCs2401(bi.cs2401.isChecked() ? "1" : "-1");

        form.setCs2402(bi.cs2402.isChecked() ? "2" : "-1");

        form.setCs2403(bi.cs2403.isChecked() ? "3" : "-1");

        form.setCs2404(bi.cs2404.isChecked() ? "4" : "-1");

        form.setCs2405(bi.cs2405.isChecked() ? "5" : "-1");

        form.setCs2406(bi.cs2406.isChecked() ? "6" : "-1");

        form.setCs25(bi.cs2501.isChecked() ? "1"
                : bi.cs2502.isChecked() ? "2"
                : bi.cs2503.isChecked() ? "3"
                : bi.cs2504.isChecked() ? "4"
                : "-1");

        form.setCs25a01(bi.cs25a01.getText().toString());

        form.setCs25b01(bi.cs25b01.getText().toString());

        form.setCs25c(bi.cs25c01.isChecked() ? "1"
                : bi.cs25c02.isChecked() ? "2"
                : bi.cs25c096.isChecked() ? "96"
                : "-1");
        form.setCs25c096x(bi.cs25c096x.getText().toString());

        form.setCs2601(bi.cs2601.isChecked() ? "1" : "-1");

        form.setCs2602(bi.cs2602.isChecked() ? "2" : "-1");

        form.setCs2603(bi.cs2603.isChecked() ? "3" : "-1");

        form.setCs2604(bi.cs2604.isChecked() ? "4" : "-1");

        form.setCs2605(bi.cs2605.isChecked() ? "5" : "-1");

        form.setCs2701(bi.cs2701.getText().toString());

        form.setCs2702(bi.cs2702.getText().toString());

        form.setCs2703(bi.cs2703.getText().toString());

        form.setCs2704(bi.cs2704.getText().toString());

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


    /*
     * Watch listeners
     * */
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
            Instant instant = Instant.parse(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Objects.requireNonNull(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(
                    bi.cs1401.getText().toString() + "-" + bi.cs1402.getText().toString() + "-" + bi.cs1403.getText().toString()
            ))) + "T06:24:01Z");
            calculatedDOB = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /*
     * Click events
     * */
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

            ZScore zs = new ZScore(ageindays, gender);
            double HLAZ = zs.getZScore_HLAZ(bi.cs21.getText().toString());
            double WAZ = zs.getZScore_WAZ(bi.cs22.getText().toString());
            double WHZ = zs.getZScore_WHZ(bi.cs22.getText().toString(), bi.cs21.getText().toString());

            bi.ZScore.setText("HLAZ: " + HLAZ + " \r\nWAZ: " + WAZ + " \r\nWHZ: " + WHZ);
        } else {

            Toast.makeText(this, "Z-Score cannot be evaluated with missing values.", Toast.LENGTH_SHORT).show();

        }


    }

    public void BtnEnd() {
        AppUtilsKt.contextEndActivity(this);
    }

    public void BtnContinue() {
        if (!formValidation()) return;
        saveDraft();
        if (updateDB()) {
            finish();
            startActivity(new Intent(this, Section01CS2Activity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void endSecActivity(boolean flag) {
        saveDraft();
        if (updateDB()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

}