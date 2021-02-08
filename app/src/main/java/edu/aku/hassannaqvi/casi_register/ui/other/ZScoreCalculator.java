package edu.aku.hassannaqvi.casi_register.ui.other;

import androidx.appcompat.app.AppCompatActivity;

public class ZScoreCalculator extends AppCompatActivity {
  /*  ActivityZScoreCalculatorBinding bi;
    private int age;
    private int gender;
    private float measurement;
    private String cat;
    private float zScore;
    private ZScore zs;
    private float L;
    private float M;
    private float S;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_z_score_calculator);
        bi.setCallback(this);

    }

    public void calculateZScore(View view) {

        this.age = Integer.parseInt(bi.age.getText().toString());
        this.gender = (bi.male.isChecked() ? 1 : 2);

        this.cat = (bi.ha.isChecked() ? "HA" : bi.wa.isChecked() ? "WA" : bi.wh.isChecked() ? this.age < 24 ? "WL" : "WH" : "");
        this.measurement = (float) (bi.ha.isChecked() ? Double.parseDouble(bi.height.getText().toString()) :
                bi.wa.isChecked() ? Double.parseDouble(bi.height.getText().toString()) :
                        bi.wh.isChecked() ? Double.parseDouble(bi.weight.getText().toString()) : 00.00);

        this.L = Float.parseFloat(bi.txtL.getText().toString());
        this.M = Float.parseFloat(bi.txtM.getText().toString());
        this.S = Float.parseFloat(bi.txtS.getText().toString());

        this.zs = new ZScore();
//        this.zScore = (float) zs.calcZS(this.age, this.gender, this.measurement, this.cat, this.L, this.M, this.S);

        bi.zsResult.setText((String.valueOf(this.zScore)));
    }*/
}