package edu.aku.hassannaqvi.casi_register.core;

import android.util.Log;

import java.util.List;

import static java.lang.Math.abs;

public class ZScore {


    int t;
    double y;
    double Lt;
    double Mt;
    double St;
    int gender;
    String cat;
    double zInd;
    private double SD3pos;
    private double SD3neg;
    private double SD23pos;
    private double SD23neg;
    private double zScore;
    private boolean flag;
    private double SD;
    private DatabaseHelper db;
    private String catA;
    private String catB;
    private List<String> lms;
    private List<String> whlms;
    private String height;

    public ZScore(int age, int gender, float measurement, String cat) {

    }

    public ZScore() {

        flag = false;

    }

    public ZScore(int ageindays, int gender) {
        this.t = ageindays;
        this.gender = gender;
    }

    public double calcZS(int age, int gender, float measurement, String cat, float L, float M, float S) {
        Log.d("ZScore", "calcZS: Starting");


        this.gender = gender;
        t = age;
        y = measurement;
        this.cat = cat;

        this.Lt = (double) L * t;
        this.Mt = (double) M * t;
        this.St = (double) S * t;

        // Fetch L, M, & S from database
        populateLMS();

        // Calculate Z(ind)
        calcZind();

        Log.d("TAG", "ZScore: " +
                "\n t: " + t +
                "\n y: " + y +
                "\n L: " + Lt +
                "\n M: " + Mt +
                "\n S: " + St +
                "\n zInd: " + zInd +
                "\n SD3pos: " + SD3pos +
                "\n SD3neg: " + SD3neg +
                "\n SD23pos: " + SD23pos +
                "\n SD23neg: " + SD23neg +
                "\n zScore: " + zScore

        );

        return zScore;
    }


    private void calcZind() {

        zInd = (Math.pow(y / Mt, Lt)) - 1 / (St * Lt);

        // Calculate SD3neg
        calcSD3neg();
        // Calculate SD3pos
        calcSD3pos();

    }

    private double calcSD(int num) {

        double sd01 = 1 + Lt * St;

        return num > 0 ? Mt * Math.pow((sd01 * num), (1 / Lt)) : (Mt * Math.pow((sd01 * abs(num)), (1 / Lt))) * -1;

    }

    private void calcSD3pos() {

        SD3pos = (Mt * Math.pow(1 + (Lt * St * 3), (1 / (Lt))));

        // Calculate SD23pos
        calcSD23pos();
    }

    private void calcSD3neg() {

        double sd3neg01 = (1 + Lt * St * -3);

        Log.d("TAG", "sd3neg01: " + sd3neg01);
        //SD3neg = powa;
        //SD3neg = (Mt * Math.pow(1 + (Lt  * St * -3), (1 / (Lt ))));
        SD3neg = (Mt * Math.pow(abs(sd3neg01), (1 / Lt)) * -1);
        Log.d("TAG", "SD3neg: " + SD3neg);
        // Calculate SD23neg
        calcSD23neg();
    }

    private void calcSD23pos() {

        SD23pos = (calcSD(3) - calcSD(2));
        // Calculate ZIndFinal
        caclZScoreFinal();
    }

    private void calcSD23neg() {

        SD23neg = calcSD(-2) - calcSD(-3);


        // Calculate ZIndFinal
        caclZScoreFinal();

    }


    private void caclZScoreFinal() {
        if (flag) {

            if (zInd <= 3 && zInd >= -3) {
                zScore = zInd;
            } else if (zInd > 3) {
                zScore = (3 + ((y - calcSD(3)) / SD23pos));
            } else if (zInd < -3) {
                zScore = (-3 + ((y - SD3neg) / SD23neg));
            }
            flag = false;
        } else {
            flag = true;
        }

    }

    private void populateWHLMS() {

        //TODO: Fetch L,M & S from database
        db = MainApp.appInfo.getDbHelper();


        whlms = db.getWHLMS(Double.valueOf(height), gender, catA);
        if (lms.size() > 0) {
            this.Lt = Double.parseDouble(lms.get(0));
            this.Mt = Double.parseDouble(lms.get(1));
        }
    }

    private void populateLMS() {

        //TODO: Fetch L,M & S from database
        db = MainApp.appInfo.getDbHelper();


        lms = db.getLMS(t, gender, catA, catB);
        if (lms.size() > 0) {
            this.Lt = Double.parseDouble(lms.get(0));
            this.Mt = Double.parseDouble(lms.get(1));
            this.St = Double.parseDouble(lms.get(2));
        }

      /*  switch (cat) {
            case "HA":
                if (gender == 1) {
                    this.Lt = 1*t;
                    this.Mt = 51.6427*t;
                    this.St = 0.03693*t;
                } else {
                    this.Lt = 1*t;
                    this.Mt = 50.8365*t;
                    this.St = 0.03722*t;
                }
                break;
            case "WA":
                if (gender == 1) {
                    this.Lt = 0.2681*t;
                    this.Mt = 3.5941*t;
                    this.St = 0.14339*t;

                } else {
                    this.Lt = 1*t;
                    this.Mt = 0.2497*t;
                    this.St = 3.4314*t;
                }
                break;
            case "WH":
                if (gender == 1) {
                    this.Lt = -0.3521*t;
                    this.Mt = 15.3576*t;
                    this.St = 0.08229*t;

                } else {
                    this.Lt = -0.3833*t;
                    this.Mt = 15.2246*t;
                    this.St = 0.09088*t;
                }
                break;
            case "WL":
                if (gender == 1) {
                    this.Lt = -0.3521*t;
                    this.Mt = 15.1637*t;
                    this.St = 0.08198*t;

                } else {
                    this.Lt = -0.3833*t;
                    this.Mt = 15.0267*t;
                    this.St = 0.09069*t;
                }
                break;

        }*/
    }

    public double getZScore_HLAZ(String measurment) {

        this.catA = "HA";
        this.catB = "LA";
        this.y = Double.parseDouble(measurment);

        populateLMS();

        return ((Math.pow(this.y / Mt, Lt)) - 1 / (St * Lt));

    }

    public double getZScore_WAZ(String measurment) {

        this.catA = "WA";
        this.catB = "WA";
        this.y = Double.parseDouble(measurment);

        populateLMS();

        return ((Math.pow(this.y / Mt, Lt)) - 1 / (St * Lt));
    }

    public double getZScore_WHZ(String weight, String height) {
        this.height = height;
        this.catA = "WH";
        this.catB = "WH";
        this.y = Double.parseDouble(weight);

        populateWHLMS();

        return ((Math.pow(this.y / Mt, Lt)) - 1 / (St * Lt));
    }
}
