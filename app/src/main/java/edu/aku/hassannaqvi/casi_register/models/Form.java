package edu.aku.hassannaqvi.casi_register.models;

import android.database.Cursor;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.lifecycle.LiveData;
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

    private String cr01 = "";
    private String cr02 = "";
    private String cr03 = "";
    private String cr04 = "";
    private String cr05 = "";
    private String cr06 = "";
    private String cr06x = "";
    private String cr07 = "";
    private String cr08d = "";
    private String cr08m = "";
    private String cr08y = "";
    private String cr09 = "";
    private String cr10 = "";
    private String cr11 = "";
    private String cr12 = "";
    private String cr13 = "";
    private String cr14d = "";
    private String cr14m = "";
    private String cr14y = "";
    private String cr15m = "";
    private String cr15y = "";
    private String cr16 = "";
    private String cr17 = "";
    private String cr18 = "";
    private String cr19 = "";
    private String cr20 = "";
    private String cr21 = "";
    private String cr22 = "";
    private String cr23 = "";
    private String cr24a = "";
    private String cr24b = "";
    private String cr24c = "";
    private String cr24d = "";
    private String cr24e = "";
    private String cr24f = "";
    private String cr25 = "";
    private String cr26 = "";
    private String cr27a = "";
    private String cr27b = "";
    private String cr27c = "";
    private String cr28a = "";
    private String cr28b = "";
    private String cr28c = "";
    private String cr28d = "";
    private String cr28e = "";
    private String cr28f = "";
    private String cr28fx = "";
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

    public String getCr01() {
        return cr01;
    }

    public void setCr01(String cr01) {
        this.cr01 = cr01;
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


    public String getCr06x() {
        return cr06x;
    }

    public void setCr06x(String cr06x) {
        this.cr06x = cr06x;
    }


    public String getCr07() {
        return cr07;
    }

    public void setCr07(String cr07) {
        this.cr07 = cr07;
    }

    public String getCr08d() {
        return cr08d;
    }

    public void setCr08d(String cr08d) {
        this.cr08d = cr08d;
    }

    public String getCr08m() {
        return cr08m;
    }

    public void setCr08m(String cr08m) {
        this.cr08m = cr08m;
    }

    public String getCr08y() {
        return cr08y;
    }

    public void setCr08y(String cr08y) {
        this.cr08y = cr08y;
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

    public String getCr11() {
        return cr11;
    }

    public void setCr11(String cr11) {
        this.cr11 = cr11;
    }

    public String getCr12() {
        return cr12;
    }

    public void setCr12(String cr12) {
        this.cr12 = cr12;
    }

    public String getCr13() {
        return cr13;
    }

    public void setCr13(String cr13) {
        this.cr13 = cr13;
    }

    public String getCr14d() {
        return cr14d;
    }

    public void setCr14d(String cr14d) {
        this.cr14d = cr14d;
    }

    public String getCr14m() {
        return cr14m;
    }

    public void setCr14m(String cr14m) {
        this.cr14m = cr14m;
    }

    public String getCr14y() {
        return cr14y;
    }

    public void setCr14y(String cr14y) {
        this.cr14y = cr14y;
    }

    public String getCr15m() {
        return cr15m;
    }

    public void setCr15m(String cr15m) {
        this.cr15m = cr15m;
    }

    public String getCr15y() {
        return cr15y;
    }

    public void setCr15y(String cr15y) {
        this.cr15y = cr15y;
    }

    public String getCr16() {
        return cr16;
    }

    public void setCr16(String cr16) {
        this.cr16 = cr16;
    }

    public String getCr17() {
        return cr17;
    }

    public void setCr17(String cr17) {
        this.cr17 = cr17;
    }

    public String getCr18() {
        return cr18;
    }

    public void setCr18(String cr18) {
        this.cr18 = cr18;
    }

    public String getCr19() {
        return cr19;
    }

    public void setCr19(String cr19) {
        this.cr19 = cr19;
    }

    public String getCr20() {
        return cr20;
    }

    public void setCr20(String cr20) {
        this.cr20 = cr20;
    }

    public String getCr21() {
        return cr21;
    }

    public void setCr21(String cr21) {
        this.cr21 = cr21;
    }

    public String getCr22() {
        return cr22;
    }

    public void setCr22(String cr22) {
        this.cr22 = cr22;
    }

    public String getCr23() {
        return cr23;
    }

    public void setCr23(String cr23) {
        this.cr23 = cr23;
    }


    public String getCr24a() {
        return cr24a;
    }

    public void setCr24a(String cr24a) {
        this.cr24a = cr24a;
    }


    public String getCr24b() {
        return cr24b;
    }

    public void setCr24b(String cr24b) {
        this.cr24b = cr24b;
    }


    public String getCr24c() {
        return cr24c;
    }

    public void setCr24c(String cr24c) {
        this.cr24c = cr24c;
    }


    public String getCr24d() {
        return cr24d;
    }

    public void setCr24d(String cr24d) {
        this.cr24d = cr24d;
    }


    public String getCr24e() {
        return cr24e;
    }

    public void setCr24e(String cr24e) {
        this.cr24e = cr24e;
    }


    public String getCr24f() {
        return cr24f;
    }

    public void setCr24f(String cr24f) {
        this.cr24f = cr24f;
    }


    public String getCr25() {
        return cr25;
    }

    public void setCr25(String cr25) {
        this.cr25 = cr25;
    }

    public String getCr26() {
        return cr26;
    }

    public void setCr26(String cr26) {
        this.cr26 = cr26;
    }

    public String getCr27a() {
        return cr27a;
    }

    public void setCr27a(String cr27a) {
        this.cr27a = cr27a;
    }

    public String getCr27b() {
        return cr27b;
    }

    public void setCr27b(String cr27b) {
        this.cr27b = cr27b;
    }

    public String getCr27c() {
        return cr27c;
    }

    public void setCr27c(String cr27c) {
        this.cr27c = cr27c;
    }

    public String getCr28a() {
        return cr28a;
    }

    public void setCr28a(String cr28a) {
        this.cr28a = cr28a;
    }

    public String getCr28b() {
        return cr28b;
    }

    public void setCr28b(String cr28b) {
        this.cr28b = cr28b;
    }

    public String getCr28c() {
        return cr28c;
    }

    public void setCr28c(String cr28c) {
        this.cr28c = cr28c;
    }

    public String getCr28d() {
        return cr28d;
    }

    public void setCr28d(String cr28d) {
        this.cr28d = cr28d;
    }

    public String getCr28e() {
        return cr28e;
    }

    public void setCr28e(String cr28e) {
        this.cr28e = cr28e;
    }

    public String getCr28f() {
        return cr28f;
    }

    public void setCr28f(String cr28f) {
        this.cr28f = cr28f;
    }

    public String getCr28fx() {
        return cr28fx;
    }

    public void setCr28fx(String cr28fx) {
        this.cr28fx = cr28fx;
    }


    public SectionSelection getSecSelection() {
        return secSelection;
    }

    public void setSecSelection(SectionSelection secSelection) {
        this.secSelection = secSelection;
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
        this.cr01 = jsonObject.getString(FormsTable.COLUMN_CR01);
        this.cr02 = jsonObject.getString(FormsTable.COLUMN_CR02);
        this.cr03 = jsonObject.getString(FormsTable.COLUMN_CR03);
        this.cr04 = jsonObject.getString(FormsTable.COLUMN_CR04);
        this.cr05 = jsonObject.getString(FormsTable.COLUMN_CR05);
        this.cr06 = jsonObject.getString(FormsTable.COLUMN_CR06);
        this.cr06x = jsonObject.getString(FormsTable.COLUMN_CR06X);
        this.cr07 = jsonObject.getString(FormsTable.COLUMN_CR07);
        this.cr08d = jsonObject.getString(FormsTable.COLUMN_CR08D);
        this.cr08m = jsonObject.getString(FormsTable.COLUMN_CR08M);
        this.cr08y = jsonObject.getString(FormsTable.COLUMN_CR08Y);
        this.cr09 = jsonObject.getString(FormsTable.COLUMN_CR09);
        this.cr10 = jsonObject.getString(FormsTable.COLUMN_CR10);
        this.cr11 = jsonObject.getString(FormsTable.COLUMN_CR11);
        this.cr12 = jsonObject.getString(FormsTable.COLUMN_CR12);
        this.cr13 = jsonObject.getString(FormsTable.COLUMN_CR13);
        this.cr14d = jsonObject.getString(FormsTable.COLUMN_CR14D);
        this.cr15m = jsonObject.getString(FormsTable.COLUMN_CR15M);
        this.cr15y = jsonObject.getString(FormsTable.COLUMN_CR15Y);
        this.cr16 = jsonObject.getString(FormsTable.COLUMN_CR16);
        this.cr17 = jsonObject.getString(FormsTable.COLUMN_CR17);
        this.cr18 = jsonObject.getString(FormsTable.COLUMN_CR18);
        this.cr19 = jsonObject.getString(FormsTable.COLUMN_CR19);
        this.cr20 = jsonObject.getString(FormsTable.COLUMN_CR20);
        this.cr21 = jsonObject.getString(FormsTable.COLUMN_CR21);
        this.cr22 = jsonObject.getString(FormsTable.COLUMN_CR22);
        this.cr23 = jsonObject.getString(FormsTable.COLUMN_CR23);
        this.cr24a = jsonObject.getString(FormsTable.COLUMN_CR24A);
        this.cr24b = jsonObject.getString(FormsTable.COLUMN_CR24B);
        this.cr24c = jsonObject.getString(FormsTable.COLUMN_CR24C);
        this.cr24d = jsonObject.getString(FormsTable.COLUMN_CR24D);
        this.cr24e = jsonObject.getString(FormsTable.COLUMN_CR24E);
        this.cr24f = jsonObject.getString(FormsTable.COLUMN_CR24F);
        this.cr25 = jsonObject.getString(FormsTable.COLUMN_CR25);
        this.cr26 = jsonObject.getString(FormsTable.COLUMN_CR26);
        this.cr27a = jsonObject.getString(FormsTable.COLUMN_CR27A);
        this.cr27b = jsonObject.getString(FormsTable.COLUMN_CR27B);
        this.cr27c = jsonObject.getString(FormsTable.COLUMN_CR27C);
        this.cr28a = jsonObject.getString(FormsTable.COLUMN_CR28A);
        this.cr28b = jsonObject.getString(FormsTable.COLUMN_CR28B);
        this.cr28c = jsonObject.getString(FormsTable.COLUMN_CR28C);
        this.cr28d = jsonObject.getString(FormsTable.COLUMN_CR28D);
        this.cr28e = jsonObject.getString(FormsTable.COLUMN_CR28E);
        this.cr28f = jsonObject.getString(FormsTable.COLUMN_CR28F);
        this.cr28fx = jsonObject.getString(FormsTable.COLUMN_CR28FX);
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
        this.cr01 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR01));
        this.cr02 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR02));
        this.cr03 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR03));
        this.cr04 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR04));
        this.cr05 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR05));
        this.cr06 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR06));
        this.cr06x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR06X));
        this.cr07 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR07));
        this.cr08d = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR08D));
        this.cr08m = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR08M));
        this.cr08y = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR08Y));
        this.cr09 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR09));
        this.cr10 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR10));
        this.cr11 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR11));
        this.cr12 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR12));
        this.cr13 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR13));
        this.cr14d = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR14D));
        this.cr15m = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR15M));
        this.cr15y = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR15Y));
        this.cr16 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR16));
        this.cr17 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR17));
        this.cr18 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR18));
        this.cr19 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR19));
        this.cr20 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR20));
        this.cr21 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR21));
        this.cr22 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR22));
        this.cr23 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR23));
        this.cr24a = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR24A));
        this.cr24b = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR24B));
        this.cr24c = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR24C));
        this.cr24d = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR24D));
        this.cr24e = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR24E));
        this.cr24f = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR24F));
        this.cr25 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR25));
        this.cr26 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR26));
        this.cr27a = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR27A));
        this.cr27b = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR27B));
        this.cr27c = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR27C));
        this.cr28a = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR28A));
        this.cr28b = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR28B));
        this.cr28c = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR28C));
        this.cr28d = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR28D));
        this.cr28e = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR28E));
        this.cr28f = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR28F));
        this.cr28fx = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR28FX));

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
            json.put(FormsTable.COLUMN_CR01, this.cr01 == null ? JSONObject.NULL : this.cr01);
            json.put(FormsTable.COLUMN_CR02, this.cr02 == null ? JSONObject.NULL : this.cr02);
            json.put(FormsTable.COLUMN_CR03, this.cr03 == null ? JSONObject.NULL : this.cr03);
            json.put(FormsTable.COLUMN_CR04, this.cr04 == null ? JSONObject.NULL : this.cr04);
            json.put(FormsTable.COLUMN_CR05, this.cr05 == null ? JSONObject.NULL : this.cr05);
            json.put(FormsTable.COLUMN_CR06X, this.cr06x == null ? JSONObject.NULL : this.cr06x);
            json.put(FormsTable.COLUMN_CR07, this.cr07 == null ? JSONObject.NULL : this.cr07);
            json.put(FormsTable.COLUMN_CR08D, this.cr08d == null ? JSONObject.NULL : this.cr08d);
            json.put(FormsTable.COLUMN_CR08M, this.cr08m == null ? JSONObject.NULL : this.cr08m);
            json.put(FormsTable.COLUMN_CR08Y, this.cr08y == null ? JSONObject.NULL : this.cr08y);
            json.put(FormsTable.COLUMN_CR09, this.cr09 == null ? JSONObject.NULL : this.cr09);
            json.put(FormsTable.COLUMN_CR10, this.cr10 == null ? JSONObject.NULL : this.cr10);
            json.put(FormsTable.COLUMN_CR11, this.cr11 == null ? JSONObject.NULL : this.cr11);
            json.put(FormsTable.COLUMN_CR12, this.cr12 == null ? JSONObject.NULL : this.cr12);
            json.put(FormsTable.COLUMN_CR13, this.cr13 == null ? JSONObject.NULL : this.cr13);
            json.put(FormsTable.COLUMN_CR14D, this.cr14d == null ? JSONObject.NULL : this.cr14d);
            json.put(FormsTable.COLUMN_CR15M, this.cr15m == null ? JSONObject.NULL : this.cr15m);
            json.put(FormsTable.COLUMN_CR15Y, this.cr15y == null ? JSONObject.NULL : this.cr15y);
            json.put(FormsTable.COLUMN_CR16, this.cr16 == null ? JSONObject.NULL : this.cr16);
            json.put(FormsTable.COLUMN_CR17, this.cr17 == null ? JSONObject.NULL : this.cr17);
            json.put(FormsTable.COLUMN_CR18, this.cr18 == null ? JSONObject.NULL : this.cr18);
            json.put(FormsTable.COLUMN_CR19, this.cr19 == null ? JSONObject.NULL : this.cr19);
            json.put(FormsTable.COLUMN_CR20, this.cr20 == null ? JSONObject.NULL : this.cr20);
            json.put(FormsTable.COLUMN_CR21, this.cr21 == null ? JSONObject.NULL : this.cr21);
            json.put(FormsTable.COLUMN_CR22, this.cr22 == null ? JSONObject.NULL : this.cr22);
            json.put(FormsTable.COLUMN_CR23, this.cr23 == null ? JSONObject.NULL : this.cr23);
            json.put(FormsTable.COLUMN_CR24A, this.cr24a == null ? JSONObject.NULL : this.cr24a);
            json.put(FormsTable.COLUMN_CR24B, this.cr24b == null ? JSONObject.NULL : this.cr24b);
            json.put(FormsTable.COLUMN_CR24C, this.cr24c == null ? JSONObject.NULL : this.cr24c);
            json.put(FormsTable.COLUMN_CR24D, this.cr24d == null ? JSONObject.NULL : this.cr24d);
            json.put(FormsTable.COLUMN_CR24E, this.cr24e == null ? JSONObject.NULL : this.cr24e);
            json.put(FormsTable.COLUMN_CR24F, this.cr24f == null ? JSONObject.NULL : this.cr24f);
            json.put(FormsTable.COLUMN_CR25, this.cr25 == null ? JSONObject.NULL : this.cr25);
            json.put(FormsTable.COLUMN_CR26, this.cr26 == null ? JSONObject.NULL : this.cr26);
            json.put(FormsTable.COLUMN_CR27A, this.cr27a == null ? JSONObject.NULL : this.cr27a);
            json.put(FormsTable.COLUMN_CR27B, this.cr27b == null ? JSONObject.NULL : this.cr27b);
            json.put(FormsTable.COLUMN_CR27C, this.cr27c == null ? JSONObject.NULL : this.cr27c);
            json.put(FormsTable.COLUMN_CR28A, this.cr28a == null ? JSONObject.NULL : this.cr28a);
            json.put(FormsTable.COLUMN_CR28B, this.cr28b == null ? JSONObject.NULL : this.cr28b);
            json.put(FormsTable.COLUMN_CR28C, this.cr28c == null ? JSONObject.NULL : this.cr28c);
            json.put(FormsTable.COLUMN_CR28D, this.cr28d == null ? JSONObject.NULL : this.cr28d);
            json.put(FormsTable.COLUMN_CR28E, this.cr28e == null ? JSONObject.NULL : this.cr28e);
            json.put(FormsTable.COLUMN_CR28F, this.cr28f == null ? JSONObject.NULL : this.cr28f);
            json.put(FormsTable.COLUMN_CR28FX, this.cr28fx == null ? JSONObject.NULL : this.cr28fx);
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


}
