package edu.aku.hassannaqvi.casi_register.core;

import android.util.Log;

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

    public ZScore(int age, int gender, float measurement, String cat) {

    }

    public ZScore() {

        flag = false;

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

/*        // Calculate SD3neg
        calcSD3neg();
        // Calculate SD3pos
        calcSD3pos();*/

    }

    private double calcSD(int num) {

        return Mt * Math.pow((1 + Lt * St * num), (1 / Lt));

    }


    private void calcSD3neg() {

        double a = 1.0 + (Lt * St * -3.0);
        Log.d("calcSD3neg", "a: " + a);
        double Mta = Mt * a;
        Log.d("calcSD3neg", "Mta: " + Mta);

        double p = 1.0 / Lt;
        Log.d("calcSD3neg", "p: " + p);
        p = Math.round(p * 1000000000000000000.0) / 1000000000000000000.0;
        double apow = Math.pow(a, p);
        apow = Math.pow(-25045.780485516327, 0.04024792612003285);

        // Math.exp( p * (log(a) + i*pi) );

        Log.d("calcSD3neg", "apow: " + apow);

        double powc = Mt * apow;
        Log.d("calcSD3neg", "powa: " + powc);
        double powa = Mt * Math.pow(a, p);
        Log.d("calcSD3neg", "powa: " + powa);
        double powb = Math.pow(Mta, p);
        Log.d("calcSD3neg", "powb: " + powb);


        //SD3neg = powa;
        //SD3neg = (Mt * Math.pow(1 + (Lt  * St * -3), (1 / (Lt ))));
        SD3neg = Mt * Math.pow((1 + Lt * St * -3), (1 / Lt));

        // Calculate SD23neg
        calcSD23neg();
    }

    private void calcSD23pos() {

        SD23pos = (calcSD(3) - calcSD(2));
        // Calculate ZIndFinal
        caclZScoreFinal();
    }

    private void calcSD23neg() {

        SD23neg = (calcSD(-2) - calcSD(-3));


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

    private void populateLMS() {

        //TODO: Fetch L,M & S from database


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

}
