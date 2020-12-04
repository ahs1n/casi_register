package edu.aku.hassannaqvi.casi_register.models;

import android.database.Cursor;

import androidx.lifecycle.LiveData;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.casi_register.contracts.FormsContract.FormsTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Form extends LiveData<Form> {

    private final String projectName = "casi_register";
    private String _ID = "";
    private String _UID = "";
    private String username;
    private String sysdate = "";

    private String cr01d = "";
    private String cr01m = "";
    private String cr01y = "";
    private String cr02 = "";
    private String cr03 = "";
    private String cr04 = "";
    private String cr05 = "";
    private String cr06 = "";
    private String cr07 = "";
    private String cr08 = "";
    private String cr09 = "";
    private String cr10 = "";
    private String istatus = ""; // Interview Status
    private String istatus96x = ""; // Interview Status
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion = "";
    private String gpslat = "";
    private String gpslng = "";
    private String gpsdate = "";
    private String gpsacc = "";
    private String deviceid = "";
    private String tagid = "";

    //For section selection
    private SectionSelection secSelection;


    public Form() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getSysdate() {
        return sysdate;
    }

    public void setSysdate(String sysdate) {
        this.sysdate = sysdate;
    }


    public String getCr01d() {
        return cr01d;
    }

    public void setCr01d(String cr01d) {
        this.cr01d = cr01d;
    }

    public String getCr01m() {
        return cr01m;
    }

    public void setCr01m(String cr01m) {
        this.cr01m = cr01m;
    }

    public String getCr01y() {
        return cr01y;
    }

    public void setCr01y(String cr01y) {
        this.cr01y = cr01y;
    }

    public String getCr02() {
        return cr02;
    }

    public void setCr02(String cr02) {
        this.cr02 = cr02;
    }

    public String getCr03() {
        return cr03;
    }

    public void setCr03(String cr03) {
        this.cr03 = cr03;
    }

    public String getCr04() {
        return cr04;
    }

    public void setCr04(String cr04) {
        this.cr04 = cr04;
    }


    public String getCr05() {
        return cr05;
    }

    public void setCr05(String cr05) {
        this.cr05 = cr05;
    }


    public String getCr06() {
        return cr06;
    }

    public void setCr06(String cr06) {
        this.cr06 = cr06;
    }


    public String getCr07() {
        return cr07;
    }

    public void setCr07(String cr07) {
        this.cr07 = cr07;
    }


    public String getCr08() {
        return cr08;
    }

    public void setCr08(String cr08) {
        this.cr08 = cr08;
    }


    public String getCr09() {
        return cr09;
    }

    public void setCr09(String cr09) {
        this.cr09 = cr09;
    }


    public String getCr10() {
        return cr10;
    }

    public void setCr10(String cr10) {
        this.cr10 = cr10;
    }



    public String getGpslat() {
        return gpslat;
    }

    public Form setGpslat(String gpslat) {
        this.gpslat = gpslat;
        return this;
    }


    public String getGpslng() {
        return gpslng;
    }

    public Form setGpslng(String gpslng) {
        this.gpslng = gpslng;
        return this;
    }


    public String getGpsdate() {
        return gpsdate;
    }

    public Form setGpsdate(String gpsdate) {
        this.gpsdate = gpsdate;
        return this;
    }


    public String getGpsacc() {
        return gpsacc;
    }

    public Form setGpsacc(String gpsacc) {
        this.gpsacc = gpsacc;
        return this;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public Form setDeviceid(String deviceid) {
        this.deviceid = deviceid;
        return this;
    }

    public String getTagid() {
        return tagid;
    }

    public Form setTagid(String tagid) {
        this.tagid = tagid;
        return this;
    }


    //======================


    //====================


    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }


    public String getProjectName() {
        return projectName;
    }


    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }


    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }


    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }


    public String getIstatus96x() {
        return istatus96x;
    }

    public void setIstatus96x(String istatus96x) {
        this.istatus96x = istatus96x;
    }


    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }


    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }


    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }


    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }


    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }


    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }


    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }


    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;

    }


    public Form Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.username = jsonObject.getString(FormsTable.COLUMN_USERNAME);
        this.sysdate = jsonObject.getString(FormsTable.COLUMN_SYSDATE);
        this.cr01d = jsonObject.getString(FormsTable.COLUMN_CR01D);
        this.cr01m = jsonObject.getString(FormsTable.COLUMN_CR01M);
        this.cr01y = jsonObject.getString(FormsTable.COLUMN_CR01Y);
        this.cr02 = jsonObject.getString(FormsTable.COLUMN_CR02);
        this.cr03 = jsonObject.getString(FormsTable.COLUMN_CR03);
        this.cr04 = jsonObject.getString(FormsTable.COLUMN_CR04);
        this.cr05 = jsonObject.getString(FormsTable.COLUMN_CR05);
        this.cr06 = jsonObject.getString(FormsTable.COLUMN_CR06);
        this.cr07 = jsonObject.getString(FormsTable.COLUMN_CR07);
        this.cr08 = jsonObject.getString(FormsTable.COLUMN_CR08);
        this.cr09 = jsonObject.getString(FormsTable.COLUMN_CR09);
        this.cr10 = jsonObject.getString(FormsTable.COLUMN_CR10);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus96x = jsonObject.getString(FormsTable.COLUMN_ISTATUS96x);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDATE);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_APPVERSION);

        return this;

    }

    public Form Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UID));
        this.username = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USERNAME));
        this.sysdate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYSDATE));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVERSION));
        this.cr01d = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR01D));
        this.cr01m = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR01M));
        this.cr01y = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR01Y));
        this.cr02 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR02));
        this.cr03 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR03));
        this.cr04 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR04));
        this.cr05 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR05));
        this.cr06 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR06));
        this.cr07 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR07));
        this.cr08 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR08));
        this.cr09 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR09));
        this.cr10 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR10));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));

        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Form.class);
    }

    public String sBtoString() {
        JSONObject json = new JSONObject();

        try {
            json/*.put("mf101", mf101)*/
                    /*.put("s2q101x", s2q101x)
                    .put("s2q102", s2q102)
                    .put("s2q102x", s2q102x)
                    .put("s2q103", s2q103)
                    .put("s2q103x", s2q103x)
                    .put("s2q104", s2q104)
                    .put("s2q104x", s2q104x)
                    .put("s2q105", s2q105)
                    .put("s2q105x", s2q105x)
                    .put("s2q106", s2q106)
                    .put("s2q106x", s2q106x)
                    .put("s2q107", s2q107)
                    .put("s2q107x", s2q107x)
                    .put("s2q108", s2q108)
                    .put("s2q108x", s2q108x)
                    .put("s2q109", s2q109)
                    .put("s2q109x", s2q109x)
                    .put("s2q110", s2q110)
                    .put("s2q110x", s2q110x)
                    .put("s2q111", s2q111)
                    .put("s2q111x", s2q111x)
                    .put("s2q112", s2q112)
                    .put("s2q112x", s2q112x)
                    .put("s2q113", s2q113)
                    .put("s2q113x", s2q113x)
                    .put("s2q114", s2q114)
                    .put("s2q114x", s2q114x)
                    .put("s2q115", s2q115)
                    .put("s2q115x", s2q115x)
                    .put("s2q116", s2q116)
                    .put("s2q116x", s2q116x)
                    .put("s2q2", s2q2)
                    .put("s2q3", s2q3)
                    .put("s2q31", s2q31)
                    .put("s2q32", s2q32)
                    .put("s2q33", s2q33)
                    .put("s2q4", s2q4)
                    .put("s2q501", s2q501)
                    .put("s2q502", s2q502)
                    .put("s2q503", s2q503)
                    .put("s2q504", s2q504)
                    .put("s2q505", s2q505)
                    .put("s2q506", s2q506)
                    .put("s2q507", s2q507)
                    .put("s2q508", s2q508)
                    .put("s2q509", s2q509)
                    .put("s2q596", s2q596)
                    .put("s2q596x", s2q596x)
                    .put("s2q6", s2q6)
                    .put("s2q7", s2q7)
                    .put("s2q71", s2q71)
                    .put("s2q72", s2q72)*/
                    .put("gpslat", gpslat)
                    .put("gpslng", gpslng)
                    .put("gpsdate", gpsdate)
                    .put("gpsacc", gpsacc)
                    .put("deviceid", deviceid)
                    .put("tagid", tagid)
                    .put("appversion", appversion);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public JSONObject toJSONObject() {

        JSONObject json = new JSONObject();

        try {
            json.put(FormsTable.COLUMN_ID, this._ID == null ? JSONObject.NULL : this._ID);
            json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
            json.put(FormsTable.COLUMN_USERNAME, this.username == null ? JSONObject.NULL : this.username);
            json.put(FormsTable.COLUMN_SYSDATE, this.sysdate == null ? JSONObject.NULL : this.sysdate);
            json.put(FormsTable.COLUMN_CR05, this.cr05 == null ? JSONObject.NULL : this.cr05);
            json.put(FormsTable.COLUMN_CR06, this.cr06 == null ? JSONObject.NULL : this.cr06);
            json.put(FormsTable.COLUMN_CR07, this.cr07 == null ? JSONObject.NULL : this.cr07);
            json.put(FormsTable.COLUMN_CR08, this.cr08 == null ? JSONObject.NULL : this.cr08);
            json.put(FormsTable.COLUMN_CR09, this.cr09 == null ? JSONObject.NULL : this.cr09);
            json.put(FormsTable.COLUMN_CR10, this.cr10 == null ? JSONObject.NULL : this.cr10);
           /* json.put(FormsTable.COLUMN_ELB7, this.elb7 == null ? JSONObject.NULL : this.elb7);
            json.put(FormsTable.COLUMN_ELB8, this.elb8 == null ? JSONObject.NULL : this.elb8);
            json.put(FormsTable.COLUMN_ELB8a, this.elb8a == null ? JSONObject.NULL : this.elb8a);
            json.put(FormsTable.COLUMN_ELB09, this.elb09 == null ? JSONObject.NULL : this.elb09);
            json.put(FormsTable.COLUMN_ELB10, this.elb10 == null ? JSONObject.NULL : this.elb10);
            json.put(FormsTable.COLUMN_ELB11, this.elb11 == null ? JSONObject.NULL : this.elb11);
            json.put(FormsTable.COLUMN_ELB12, this.elb12 == null ? JSONObject.NULL : this.elb12);

            if (this.sC != null && !this.sC.equals("")) {
                json.put(FormsTable.COLUMN_SC, new JSONObject(this.sC));
            }
            if (this.sD != null && !this.sD.equals("")) {
                json.put(FormsTable.COLUMN_SD, new JSONObject(this.sD));
            }
            if (this.sE != null && !this.sE.equals("")) {
                json.put(FormsTable.COLUMN_SE, new JSONObject(this.sE));
            }
            if (this.sF != null && !this.sF.equals("")) {
                json.put(FormsTable.COLUMN_SF, new JSONObject(this.sF));
            }
            if (this.sG != null && !this.sG.equals("")) {
                json.put(FormsTable.COLUMN_SG, new JSONObject(this.sG));
            }
            if (this.sH != null && !this.sH.equals("")) {
                json.put(FormsTable.COLUMN_SH, new JSONObject(this.sH));
            }
            if (this.sI != null && !this.sI.equals("")) {
                json.put(FormsTable.COLUMN_SI, new JSONObject(this.sI));
            }
            if (this.sJ != null && !this.sJ.equals("")) {
                json.put(FormsTable.COLUMN_SJ, new JSONObject(this.sJ));
            }
            if (this.sK != null && !this.sK.equals("")) {
                json.put(FormsTable.COLUMN_SK, new JSONObject(this.sK));
            }
            if (this.sL != null && !this.sL.equals("")) {
                json.put(FormsTable.COLUMN_SL, new JSONObject(this.sL));
            }
            if (this.sN != null && !this.sN.equals("")) {
                json.put(FormsTable.COLUMN_SN, new JSONObject(this.sN));
            }*/

            json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
            json.put(FormsTable.COLUMN_ISTATUS96x, this.istatus96x == null ? JSONObject.NULL : this.istatus96x);
            json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);
            json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
            json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
            json.put(FormsTable.COLUMN_GPSDATE, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
            json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
            json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
            json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
            json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sBHydrate(String string) {

        if (string != null) {

            try {
                JSONObject json = null;
                json = new JSONObject(string);

                /*this.s2q101 = json.getString("s2q101");
                this.s2q101x = json.getString("s2q101x");
                this.s2q102 = json.getString("s2q102");
                this.s2q102x = json.getString("s2q102x");
                this.s2q103 = json.getString("s2q103");
                this.s2q103x = json.getString("s2q103x");
                this.s2q104 = json.getString("s2q104");
                this.s2q104x = json.getString("s2q104x");
                this.s2q105 = json.getString("s2q105");
                this.s2q105x = json.getString("s2q105x");
                this.s2q106 = json.getString("s2q106");
                this.s2q106x = json.getString("s2q106x");
                this.s2q107 = json.getString("s2q107");
                this.s2q107x = json.getString("s2q107x");
                this.s2q108 = json.getString("s2q108");
                this.s2q108x = json.getString("s2q108x");
                this.s2q109 = json.getString("s2q109");
                this.s2q109x = json.getString("s2q109x");
                this.s2q110 = json.getString("s2q110");
                this.s2q110x = json.getString("s2q110x");
                this.s2q111 = json.getString("s2q111");
                this.s2q111x = json.getString("s2q111x");
                this.s2q112 = json.getString("s2q112");
                this.s2q112x = json.getString("s2q112x");
                this.s2q113 = json.getString("s2q113");
                this.s2q113x = json.getString("s2q113x");
                this.s2q114 = json.getString("s2q114");
                this.s2q114x = json.getString("s2q114x");
                this.s2q115 = json.getString("s2q115");
                this.s2q115x = json.getString("s2q115x");
                this.s2q116 = json.getString("s2q116");
                this.s2q116x = json.getString("s2q116x");
                this.s2q2 = json.getString("s2q2");
                this.s2q3 = json.getString("s2q3");
                this.s2q31 = json.getString("s2q31");
                this.s2q32 = json.getString("s2q32");
                this.s2q33 = json.getString("s2q33");
                this.s2q4 = json.getString("s2q4");
                this.s2q501 = json.getString("s2q501");
                this.s2q502 = json.getString("s2q502");
                this.s2q503 = json.getString("s2q503");
                this.s2q504 = json.getString("s2q504");
                this.s2q505 = json.getString("s2q505");
                this.s2q506 = json.getString("s2q506");
                this.s2q507 = json.getString("s2q507");
                this.s2q508 = json.getString("s2q508");
                this.s2q509 = json.getString("s2q509");
                this.s2q596 = json.getString("s2q596");
                this.s2q596x = json.getString("s2q596x");
                this.s2q6 = json.getString("s2q6");
                this.s2q7 = json.getString("s2q7");
                this.s2q71 = json.getString("s2q71");
                this.s2q72 = json.getString("s2q72");*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}