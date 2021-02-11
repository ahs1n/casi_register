package edu.aku.hassannaqvi.casi_register.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.databinding.ActivitySection01Cs2Binding;
import edu.aku.hassannaqvi.casi_register.ui.MainActivity;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;

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
        saveDraft();
        if (updateDB()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean updateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        int updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_CS, MainApp.form.cS02toString());
        return updcount == 1;
    }


    private void saveDraft() {

        form.setCs28a(bi.cs28a01.isChecked() ? "1"
                : bi.cs28a02.isChecked() ? "2"
                : "-1");

        form.setCs28b(bi.cs28b01.isChecked() ? "1"
                : bi.cs28b02.isChecked() ? "2"
                : "-1");

        form.setCs28c(bi.cs28c01.isChecked() ? "1"
                : bi.cs28c02.isChecked() ? "2"
                : "-1");

        form.setCs28d(bi.cs28d01.isChecked() ? "1"
                : bi.cs28d02.isChecked() ? "2"
                : "-1");

        form.setCs28e(bi.cs28e01.isChecked() ? "1"
                : bi.cs28e02.isChecked() ? "2"
                : "-1");

        form.setCs28f(bi.cs28f01.isChecked() ? "1"
                : bi.cs28f02.isChecked() ? "2"
                : "-1");
        form.setCs28f01x(bi.cs28f01x.getText().toString());

        form.setCs29(bi.cs2901.isChecked() ? "1"
                : bi.cs2902.isChecked() ? "2"
                : "-1");

        form.setCs30a(bi.cs30a01.isChecked() ? "1"
                : bi.cs30a02.isChecked() ? "2"
                : "-1");

        form.setCs30b(bi.cs30b01.isChecked() ? "1"
                : bi.cs30b02.isChecked() ? "2"
                : "-1");

        form.setCs30c(bi.cs30c01.isChecked() ? "1"
                : bi.cs30c02.isChecked() ? "2"
                : "-1");

        form.setCs30d(bi.cs30d01.isChecked() ? "1"
                : bi.cs30d02.isChecked() ? "2"
                : "-1");

        form.setCs30e(bi.cs30e01.isChecked() ? "1"
                : bi.cs30e02.isChecked() ? "2"
                : "-1");

        form.setCs30f(bi.cs30f096.isChecked() ? "96"
                : bi.cs30f02.isChecked() ? "2"
                : "-1");
        form.setCs30f096x(bi.cs30f096x.getText().toString());

        form.setCs31(bi.cs3101.isChecked() ? "1"
                : bi.cs3102.isChecked() ? "2"
                : "-1");

        form.setCs32a(bi.cs32a01.isChecked() ? "1"
                : bi.cs32a02.isChecked() ? "2"
                : "-1");

        form.setCs32b(bi.cs32b01.isChecked() ? "1"
                : bi.cs32b02.isChecked() ? "2"
                : "-1");

        form.setCs32c(bi.cs32c01.isChecked() ? "1"
                : bi.cs32c02.isChecked() ? "2"
                : "-1");

        form.setCs32d(bi.cs32d01.isChecked() ? "1"
                : bi.cs32d02.isChecked() ? "2"
                : "-1");

        form.setCs32e(bi.cs32e01.isChecked() ? "1"
                : bi.cs32e02.isChecked() ? "2"
                : "-1");

        form.setCs32f(bi.cs32f01.isChecked() ? "1"
                : bi.cs32f02.isChecked() ? "2"
                : "-1");

        form.setCs32g(bi.cs32g01.isChecked() ? "1"
                : bi.cs32g02.isChecked() ? "2"
                : "-1");

        form.setCs32h(bi.cs32h096.isChecked() ? "96"
                : bi.cs32h02.isChecked() ? "2"
                : "-1");
        form.setCs32h096x(bi.cs32h096x.getText().toString());

    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    public void BtnEnd() {
        AppUtilsKt.contextEndActivity(this);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Press Not Allowed", Toast.LENGTH_SHORT).show();
    }

}