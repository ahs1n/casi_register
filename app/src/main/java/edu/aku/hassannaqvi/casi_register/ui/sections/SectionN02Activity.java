package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Validator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySectionN02Binding;
import edu.aku.hassannaqvi.casi_register.datecollection.AgeModel;
import edu.aku.hassannaqvi.casi_register.datecollection.DateRepository;
import edu.aku.hassannaqvi.casi_register.utils.EndSectionActivity;

import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

public class SectionN02Activity extends AppCompatActivity implements EndSectionActivity {

    ActivitySectionN02Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_n02);
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
            startActivity(new Intent(this, SectionN02Activity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_SA, form.sAtoString());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

       /* DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount == 1) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_SA, form.sAtoString());
            return true;
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
            return false;
        }*/
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

        form.setCr02(bi.cr02.getText().toString());

        form.setCr03(bi.cr03.getText().toString());

        form.setCr06(bi.cr06a.isChecked() ? "1"
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
        form.setCr28fx(bi.cr28fx.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }


    public void BtnEnd() {
//        if (!Validator.emptyTextBox(this, bi.can6)) return;
//        AppUtilsKt.openWarningActivity(this, "Are you sure, you want to end " + bi.can6.getText().toString() + " anthro form?");
    }

    private void setListeners() {


        bi.cr15m.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(bi.cr15m.getText()) || TextUtils.isEmpty(bi.cr15y.getText()))
                    return;

                int age = Integer.parseInt(bi.cr15m.getText().toString()) + (Integer.parseInt(bi.cr15y.getText().toString()) * 12);

                if (age >= 6 || age < 24) {
                    bi.fldGrpCVcr18.setVisibility(View.GONE);
                    bi.fldGrpCVcr19.setVisibility(View.VISIBLE);
                } else if (age < 6 || age >= 24) {
                    bi.fldGrpCVcr18.setVisibility(View.VISIBLE);
                    bi.fldGrpCVcr19.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        EditText[] txtListener = new EditText[]{bi.cr14d, bi.cr14m};
        for (EditText txtItem : txtListener) {

            txtItem.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //Clear.clearAllFields(bi.fldGrpCVcr15);
                    bi.cr15m.setText(null);
                    bi.cr15y.setText(null);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    public void cr15yOnTextChanged(CharSequence s, int start, int before, int count) {
        if (!bi.cr15m.isRangeTextValidate() || !bi.cr15y.isRangeTextValidate())
            return;

        int age = Integer.parseInt(bi.cr15m.getText().toString()) + (Integer.parseInt(bi.cr15y.getText().toString()) * 12);

        if (age != 0) {
            bi.fldGrpCVcr18.setVisibility(View.GONE);
            bi.fldGrpCVcr19.setVisibility(View.GONE);
            bi.fldGrpCVcr20.setVisibility(View.GONE);
            if (age >= 6) {
                bi.fldGrpCVcr19.setVisibility(View.VISIBLE);
            } else if (age < 6 || age >= 24) {
                bi.fldGrpCVcr18.setVisibility(View.VISIBLE);
            }
        }

            /*if (age < 6 || age >= 24) bi.fldGrpCVcr19.setVisibility(View.GONE);
            else bi.fldGrpCVcr19.setVisibility(View.VISIBLE);*/

            /*if (age >= 24) bi.fldGrpCVcr19.setVisibility(View.GONE);
            else bi.fldGrpCVcr19.setVisibility(View.VISIBLE);*/
    }

    public void cr14yOnTextChanged(CharSequence s, int start, int before, int count) {
        bi.cr15m.setEnabled(false);
        bi.cr15m.setText(null);
        bi.cr15y.setEnabled(false);
        bi.cr15y.setText(null);
        if (!bi.cr14d.isRangeTextValidate() || !bi.cr14m.isRangeTextValidate() || !bi.cr14y.isRangeTextValidate())
            return;
        if (bi.cr14d.getText().toString().equals("00") && bi.cr14m.getText().toString().equals("00") && bi.cr14y.getText().toString().equals("00")) {
            bi.cr15m.setEnabled(true);
            bi.cr15y.setEnabled(true);
            return;
        }
        int day = bi.cr14d.getText().toString().equals("00") ? 0 : Integer.parseInt(bi.cr14d.getText().toString());
        int month = Integer.parseInt(bi.cr14m.getText().toString());
        int year = Integer.parseInt(bi.cr14y.getText().toString());

        AgeModel age = DateRepository.Companion.getCalculatedAge(year, month, day);
        if (age == null) return;
        bi.cr15m.setText(String.valueOf(age.getMonth()));
        bi.cr15y.setText(String.valueOf(age.getYear()));

    }

    @Override
    public void endSecActivity(boolean flag) {
        SaveDraft();
        if (UpdateDB()) {
            finish();
            startActivity(new Intent(this, EndSectionActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press Not Allowed", Toast.LENGTH_SHORT).show();
    }
}