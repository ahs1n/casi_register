package edu.aku.hassannaqvi.casi_register.models;

import android.database.Cursor;
import android.util.Log;

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
    private String country = "";
    private String countryCode = "";
    private String district = "";
    private String districtCode = "";
    private String uc = "";
    private String ucCode = "";
    private String village = "";
    private String villageCode = "";

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
    private String sA = "";

    //For section selection
    private SectionSelection secSelection;


    public Form() {
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }


    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }


    public String getUcCode() {
        return ucCode;
    }

    public void setUcCode(String ucCode) {
        this.ucCode = ucCode;
    }


    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }


    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
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


    public String getsA() {
        return sA;
    }

    public void setsA(String sA) {
        this.sA = sA;
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
        this.countryCode = jsonObject.getString(FormsTable.COLUMN_COUNTRY_CODE);
        this.country = jsonObject.getString(FormsTable.COLUMN_COUNTRY);
        this.districtCode = jsonObject.getString(FormsTable.COLUMN_DISTRICT_CODE);
        this.district = jsonObject.getString(FormsTable.COLUMN_DISTRICT);
        this.ucCode = jsonObject.getString(FormsTable.COLUMN_UC_CODE);
        this.uc = jsonObject.getString(FormsTable.COLUMN_UC);
        this.villageCode = jsonObject.getString(FormsTable.COLUMN_VILLAGE_CODE);
        this.village = jsonObject.getString(FormsTable.COLUMN_VILLAGE);
        /*this.cr01 = jsonObject.getString(FormsTable.COLUMN_CR01);
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
        this.cr28fx = jsonObject.getString(FormsTable.COLUMN_CR28FX);*/
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
        this.sA = jsonObject.getString(FormsTable.COLUMN_SA);

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
        this.countryCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_COUNTRY_CODE));
        this.country = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_COUNTRY));
        this.districtCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DISTRICT_CODE));
        this.district = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DISTRICT));
        this.ucCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UC_CODE));
        this.uc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_UC));
        this.villageCode = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_VILLAGE_CODE));
        this.village = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_VILLAGE));
        /*this.cr01 = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR01));
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
        this.cr28fx = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CR28FX));*/
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        sAHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SA)));
        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Form.class);
    }

    public String sAtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("cr01", cr01)
                    .put("cr02", cr02)
                    .put("cr03", cr03)
                    .put("cr04", cr04)
                    .put("cr05", cr05)
                    .put("cr06", cr06)
                    .put("cr06x", cr06x)
                    .put("cr07", cr07)
                    .put("cr08d", cr08d)
                    .put("cr08m", cr08m)
                    .put("cr08y", cr08y)
                    .put("cr09", cr09)
                    .put("cr10", cr10)
                    .put("cr11", cr11)
                    .put("cr12", cr12)
                    .put("cr13", cr13)
                    .put("cr14d", cr14d)
                    .put("cr14m", cr14m)
                    .put("cr14y", cr14y)
                    .put("cr15m", cr15m)
                    .put("cr15y", cr15y)
                    .put("cr16", cr16)
                    .put("cr17", cr17)
                    .put("cr18", cr18)
                    .put("cr19", cr19)
                    .put("cr20", cr20)
                    .put("cr21", cr21)
                    .put("cr22", cr22)
                    .put("cr23", cr23)
                    .put("cr24a", cr24a)
                    .put("cr24b", cr24b)
                    .put("cr24c", cr24c)
                    .put("cr24d", cr24d)
                    .put("cr24e", cr24e)
                    .put("cr24f", cr24f)
                    .put("cr25", cr25)
                    .put("cr26", cr26)
                    .put("cr27a", cr27a)
                    .put("cr27b", cr27b)
                    .put("cr27c", cr27c)
                    .put("cr28a", cr28a)
                    .put("cr28b", cr28b)
                    .put("cr28c", cr28c)
                    .put("cr28d", cr28d)
                    .put("cr28e", cr28e)
                    .put("cr28f", cr28f)
                    .put("cr28fx", cr28fx);
/*                    .put("gpslat", gpslat)
                    .put("gpslng", gpslng)
                    .put("gpsdate", gpsdate)
                    .put("gpsacc", gpsacc)
                    .put("deviceid", deviceid)
                    .put("tagid", tagid)
                    .put("appversion", appversion);*/

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\"" + e.getMessage() + "\"";
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

            json.put(FormsTable.COLUMN_SA, new JSONObject(sAtoString()));
            /*json.put(FormsTable.COLUMN_CR01, this.cr01 == null ? JSONObject.NULL : this.cr01);
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
            json.put(FormsTable.COLUMN_CR28FX, this.cr28fx == null ? JSONObject.NULL : this.cr28fx);*/
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

    private void sAHydrate(String string) {
        if (string != null) {

            try {
                this.cr01 = toJSONObject().getString("cr01");
                this.cr02 = toJSONObject().getString("cr02");
                this.cr03 = toJSONObject().getString("cr03");
                this.cr04 = toJSONObject().getString("cr04");
                this.cr05 = toJSONObject().getString("cr05");
                this.cr06 = toJSONObject().getString("cr06");
                this.cr06x = toJSONObject().getString("cr06x");
                this.cr07 = toJSONObject().getString("cr07");
                this.cr08d = toJSONObject().getString("cr08d");
                this.cr08m = toJSONObject().getString("cr08m");
                this.cr08y = toJSONObject().getString("cr08y");
                this.cr09 = toJSONObject().getString("cr09");
                this.cr10 = toJSONObject().getString("cr10");
                this.cr11 = toJSONObject().getString("cr11");
                this.cr12 = toJSONObject().getString("cr12");
                this.cr13 = toJSONObject().getString("cr13");
                this.cr14d = toJSONObject().getString("cr14d");
                this.cr14m = toJSONObject().getString("cr14m");
                this.cr14y = toJSONObject().getString("cr14y");
                this.cr15m = toJSONObject().getString("cr15m");
                this.cr15y = toJSONObject().getString("cr15y");
                this.cr16 = toJSONObject().getString("cr16");
                this.cr17 = toJSONObject().getString("cr17");
                this.cr18 = toJSONObject().getString("cr18");
                this.cr19 = toJSONObject().getString("cr19");
                this.cr20 = toJSONObject().getString("cr20");
                this.cr21 = toJSONObject().getString("cr21");
                this.cr22 = toJSONObject().getString("cr22");
                this.cr23 = toJSONObject().getString("cr23");
                this.cr24a = toJSONObject().getString("cr24a");
                this.cr24b = toJSONObject().getString("cr24b");
                this.cr24c = toJSONObject().getString("cr24c");
                this.cr24d = toJSONObject().getString("cr24d");
                this.cr24e = toJSONObject().getString("cr24e");
                this.cr24f = toJSONObject().getString("cr24f");
                this.cr25 = toJSONObject().getString("cr25");
                this.cr26 = toJSONObject().getString("cr26");
                this.cr27a = toJSONObject().getString("cr27a");
                this.cr27b = toJSONObject().getString("cr27b");
                this.cr27c = toJSONObject().getString("cr27c");
                this.cr28a = toJSONObject().getString("cr28a");
                this.cr28b = toJSONObject().getString("cr28b");
                this.cr28c = toJSONObject().getString("cr28c");
                this.cr28d = toJSONObject().getString("cr28d");
                this.cr28e = toJSONObject().getString("cr28e");
                this.cr28f = toJSONObject().getString("cr28f");
                this.cr28fx = toJSONObject().getString("cr28fx");
                /*this.gpslat = toJSONObject().getString("gpslat");
                this.gpslng = toJSONObject().getString("gpslng");
                this.gpsdate = toJSONObject().getString("gpsdate");
                this.gpsacc = toJSONObject().getString("gpsacc");
                this.deviceid = toJSONObject().getString("deviceid");
                this.tagid = toJSONObject().getString("tagid");
                this.appversion = toJSONObject().getString("appversion");*/


            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error at Hyderate", "sAHydrate: " + e.getMessage());
            }
        }
    }


}
