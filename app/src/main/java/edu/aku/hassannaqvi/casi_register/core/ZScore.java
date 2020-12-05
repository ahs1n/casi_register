package edu.aku.hassannaqvi.casi_register.core;

import android.util.Log;

public class ZScore {

    int t;
    double y;
    double L;
    double M;
    double S;
    int gender;
    String cat;
    double zInd;
    private float SD3pos;
    private float SD3neg;
    private float SD23pos;
    private float SD23neg;
    private float zScore;

    public ZScore(int age, int gender, float measurement, String cat) {

    }

    public ZScore() {

    }

    public double calcZS(int age, int gender, float measurement, String cat) {
        Log.d("ZScore", "calcZS: Starting");

        this.gender = gender;
        t = age;
        y = measurement;
        this.cat = cat;

        // Fetch L, M, & S from database
        populateLMS();

        // Calculate Z(ind)
        calcZind();

        // Calculate SD3pos
        calcSD3pos();

        // Calculate SD3neg
        calcSD3neg();

        // Calculate SD23pos
        calcSD23pos();

        // Calculate SD23neg
        calcSD23neg();

        // Calculate ZIndFinal
        caclZScoreFinal();

        Log.d("TAG", "ZScore: " +
                "\n t: " + t +
                "\n y: " + y +
                "\n L: " + L +
                "\n M: " + M +
                "\n S: " + S +
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

        zInd = (Math.pow(y / (M * t), L * t)) - 1 / (S * t * L * t);

    }


    private void caclZScoreFinal() {


        if (zInd <= 3 && zInd >= -3) {
            zScore = (float) zInd;
        } else if (zInd > 3) {
            zScore = (float) (3 + ((y - SD3pos) / SD23pos));
        } else if (zInd < -3) {
            zScore = (float) (-3 + ((y - SD3neg) / SD23neg));
        }


    }

    private void calcSD3pos() {

        SD3pos = (float) (M * t * (
                Math.pow(1 + (L * t * S * t * 3), (1 / (L * t)))
        )
        );

    }

    private void calcSD3neg() {

        SD3neg = (float) (M * t * (
                Math.pow(1 + (L * t * S * t * -3), (1 / (L * t)))
        )
        );


    }

    private void calcSD23pos() {

        SD23pos = (float) (SD3pos - (M * t * (
                Math.pow(1 + (L * t * S * t * 2), (1 / (L * t)))
        )
        ));
    }

    private void calcSD23neg() {

        SD23neg = (float) ((M * t * (
                Math.pow(1 + (L * t * S * t * -3), (1 / (L * t)))
        )
        ) - SD3neg);
    }


    private void populateLMS() {

        //TODO: Fetch L,M & S from database


        switch (cat) {
            case "HA":
                if (gender == 1) {
                    this.L = 1;
                    this.M = 51.6427;
                    this.S = 0.03693;
                } else {
                    this.L = 1;
                    this.M = 50.8365;
                    this.S = 0.03722;
                }
                break;
            case "WA":
                if (gender == 1) {
                    this.L = 0.2681;
                    this.M = 3.5941;
                    this.S = 0.14339;

                } else {
                    this.L = 1;
                    this.M = 0.2497;
                    this.S = 3.4314;
                }
                break;
            case "WH":
                if (gender == 1) {
                    this.L = -0.3521;
                    this.M = 15.3576;
                    this.S = 0.08229;

                } else {
                    this.L = -0.3833;
                    this.M = 15.2246;
                    this.S = 0.09088;
                }
                break;
            case "WL":
                if (gender == 1) {
                    this.L = -0.3521;
                    this.M = 15.1637;
                    this.S = 0.08198;

                } else {
                    this.L = -0.3833;
                    this.M = 15.0267;
                    this.S = 0.09069;
                }
                break;

        }
    }

}
