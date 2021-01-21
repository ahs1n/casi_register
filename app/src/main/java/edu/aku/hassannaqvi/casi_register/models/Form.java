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

    //    Screening Log U5
    private String cs01;
    private String cs01a;
    private String cs01b;
    private String cs02;
    private String cs03;
    private String cs04;
    private String cs05;
    private String cs05a;
    private String cs06;
    private String cs06096x;
    private String cs07;
    private String cs0801;
    private String cs0802;
    private String cs0803;
    private String cs09;
    private String cs10;
    private String cs10a;
    private String cs11;
    private String cs11a;
    private String cs12;
    private String cs12a;
    private String cs13;
    private String cs1401;
    private String cs1402;
    private String cs1403;
    private String cs1501;
    private String cs1502;
    private String cs16;
    private String cs17;
    private String cs18;
    private String cs19;
    private String cs20a;
    private String cs20b;
    private String cs21;
    private String cs22;
    private String cs23;
    private String cs24;
    private String cs2401;
    private String cs2402;
    private String cs2403;
    private String cs2404;
    private String cs2405;
    private String cs2406;
    private String cs25;
    private String cs25a01;
    private String cs25b01;
    private String cs25c;
    private String cs25c096x;
    private String cs26;
    private String cs2601;
    private String cs2602;
    private String cs2603;
    private String cs2604;
    private String cs2605;
    private String cs2701;
    private String cs2702;
    private String cs2703;
    private String cs2704;
    private String cs28a;
    private String cs28b;
    private String cs28c;
    private String cs28d;
    private String cs28e;
    private String cs28f;
    private String cs28f01x;
    private String cs29;
    private String cs30a;
    private String cs30b;
    private String cs30c;
    private String cs30d;
    private String cs30e;
    private String cs30f;
    private String cs30f096x;
    private String cs31;
    private String cs32a;
    private String cs32b;
    private String cs32c;
    private String cs32d;
    private String cs32e;
    private String cs32f;
    private String cs32g;
    private String cs32h;
    private String cs32h096x;

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

    private String cS = "";
    //For section selection
    private SectionSelection secSelection;

    public Form() {
    }


//    Follow-up U5


//    Screening Log WRAs


//   Follow-up WRAs


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

    //    Follow-up U5
    public String getCs01() {
        return cs01;
    }

    public void setCs01(String cs01) {
        this.cs01 = cs01;
    }

    public String getCs01a() {
        return cs01a;
    }

    public void setCs01a(String cs01a) {
        this.cs01a = cs01a;
    }

    public String getCs01b() {
        return cs01b;
    }

    public void setCs01b(String cs01b) {
        this.cs01b = cs01b;
    }

    public String getCs02() {
        return cs02;
    }

    public void setCs02(String cs02) {
        this.cs02 = cs02;
    }

    public String getCs03() {
        return cs03;
    }

    public void setCs03(String cs03) {
        this.cs03 = cs03;
    }

    public String getCs04() {
        return cs04;
    }

    public void setCs04(String cs04) {
        this.cs04 = cs04;
    }

    public String getCs05() {
        return cs05;
    }

    public void setCs05(String cs05) {
        this.cs05 = cs05;
    }

    public String getCs05a() {
        return cs05a;
    }

    public void setCs05a(String cs05a) {
        this.cs05a = cs05a;
    }

    public String getCs06() {
        return cs06;
    }

    public void setCs06(String cs06) {
        this.cs06 = cs06;
    }

    public String getCs06096x() {
        return cs06096x;
    }

    public void setCs06096x(String cs06096x) {
        this.cs06096x = cs06096x;
    }

    public String getCs07() {
        return cs07;
    }

    public void setCs07(String cs07) {
        this.cs07 = cs07;
    }

    public String getCs0801() {
        return cs0801;
    }

    public void setCs0801(String cs0801) {
        this.cs0801 = cs0801;
    }

    public String getCs0802() {
        return cs0802;
    }

    public void setCs0802(String cs0802) {
        this.cs0802 = cs0802;
    }

    public String getCs0803() {
        return cs0803;
    }

    public void setCs0803(String cs0803) {
        this.cs0803 = cs0803;
    }

    public String getCs09() {
        return cs09;
    }

    public void setCs09(String cs09) {
        this.cs09 = cs09;
    }

    public String getCs10() {
        return cs10;
    }

    public void setCs10(String cs10) {
        this.cs10 = cs10;
    }

    public String getCs10a() {
        return cs10a;
    }

    public void setCs10a(String cs10a) {
        this.cs10a = cs10a;
    }

    public String getCs11() {
        return cs11;
    }

    public void setCs11(String cs11) {
        this.cs11 = cs11;
    }

    public String getCs11a() {
        return cs11a;
    }

    public void setCs11a(String cs11a) {
        this.cs11a = cs11a;
    }

    public String getCs12() {
        return cs12;
    }

    public void setCs12(String cs12) {
        this.cs12 = cs12;
    }

    public String getCs12a() {
        return cs12a;
    }

    public void setCs12a(String cs12a) {
        this.cs12a = cs12a;
    }

    public String getCs13() {
        return cs13;
    }

    public void setCs13(String cs13) {
        this.cs13 = cs13;
    }

    public String getCs1401() {
        return cs1401;
    }

    public void setCs1401(String cs1401) {
        this.cs1401 = cs1401;
    }

    public String getCs1402() {
        return cs1402;
    }

    public void setCs1402(String cs1402) {
        this.cs1402 = cs1402;
    }

    public String getCs1403() {
        return cs1403;
    }

    public void setCs1403(String cs1403) {
        this.cs1403 = cs1403;
    }

    public String getCs1501() {
        return cs1501;
    }

    public void setCs1501(String cs1501) {
        this.cs1501 = cs1501;
    }

    public String getCs1502() {
        return cs1502;
    }

    public void setCs1502(String cs1502) {
        this.cs1502 = cs1502;
    }

    public String getCs16() {
        return cs16;
    }

    public void setCs16(String cs16) {
        this.cs16 = cs16;
    }

    public String getCs17() {
        return cs17;
    }

    public void setCs17(String cs17) {
        this.cs17 = cs17;
    }

    public String getCs18() {
        return cs18;
    }

    public void setCs18(String cs18) {
        this.cs18 = cs18;
    }

    public String getCs19() {
        return cs19;
    }

    public void setCs19(String cs19) {
        this.cs19 = cs19;
    }

    public String getCs20a() {
        return cs20a;
    }

    public void setCs20a(String cs20a) {
        this.cs20a = cs20a;
    }

    public String getCs20b() {
        return cs20b;
    }

    public void setCs20b(String cs20b) {
        this.cs20b = cs20b;
    }

    public String getCs21() {
        return cs21;
    }

    public void setCs21(String cs21) {
        this.cs21 = cs21;
    }

    public String getCs22() {
        return cs22;
    }

    public void setCs22(String cs22) {
        this.cs22 = cs22;
    }

    public String getCs23() {
        return cs23;
    }

    public void setCs23(String cs23) {
        this.cs23 = cs23;
    }

    public String getCs24() {
        return cs24;
    }

    public void setCs24(String cs24) {
        this.cs24 = cs24;
    }

    public String getCs2401() {
        return cs2401;
    }

    public void setCs2401(String cs2401) {
        this.cs2401 = cs2401;
    }

    public String getCs2402() {
        return cs2402;
    }

    public void setCs2402(String cs2402) {
        this.cs2402 = cs2402;
    }

    public String getCs2403() {
        return cs2403;
    }

    public void setCs2403(String cs2403) {
        this.cs2403 = cs2403;
    }

    public String getCs2404() {
        return cs2404;
    }

    public void setCs2404(String cs2404) {
        this.cs2404 = cs2404;
    }

    public String getCs2405() {
        return cs2405;
    }

    public void setCs2405(String cs2405) {
        this.cs2405 = cs2405;
    }

    public String getCs2406() {
        return cs2406;
    }

    public void setCs2406(String cs2406) {
        this.cs2406 = cs2406;
    }

    public String getCs25() {
        return cs25;
    }

    public void setCs25(String cs25) {
        this.cs25 = cs25;
    }

    public String getCs25a01() {
        return cs25a01;
    }

    public void setCs25a01(String cs25a01) {
        this.cs25a01 = cs25a01;
    }

    public String getCs25b01() {
        return cs25b01;
    }

    public void setCs25b01(String cs25b01) {
        this.cs25b01 = cs25b01;
    }

    public String getCs25c() {
        return cs25c;
    }

    public void setCs25c(String cs25c) {
        this.cs25c = cs25c;
    }

    public String getCs25c096x() {
        return cs25c096x;
    }

    public void setCs25c096x(String cs25c096x) {
        this.cs25c096x = cs25c096x;
    }

    public String getCs26() {
        return cs26;
    }

    public void setCs26(String cs26) {
        this.cs26 = cs26;
    }

    public String getCs2601() {
        return cs2601;
    }

    public void setCs2601(String cs2601) {
        this.cs2601 = cs2601;
    }

    public String getCs2602() {
        return cs2602;
    }

    public void setCs2602(String cs2602) {
        this.cs2602 = cs2602;
    }

    public String getCs2603() {
        return cs2603;
    }

    public void setCs2603(String cs2603) {
        this.cs2603 = cs2603;
    }

    public String getCs2604() {
        return cs2604;
    }

    public void setCs2604(String cs2604) {
        this.cs2604 = cs2604;
    }

    public String getCs2605() {
        return cs2605;
    }

    public void setCs2605(String cs2605) {
        this.cs2605 = cs2605;
    }

    public String getCs2701() {
        return cs2701;
    }

    public void setCs2701(String cs2701) {
        this.cs2701 = cs2701;
    }

    public String getCs2702() {
        return cs2702;
    }

    public void setCs2702(String cs2702) {
        this.cs2702 = cs2702;
    }

    public String getCs2703() {
        return cs2703;
    }

    public void setCs2703(String cs2703) {
        this.cs2703 = cs2703;
    }

    public String getCs2704() {
        return cs2704;
    }

    public void setCs2704(String cs2704) {
        this.cs2704 = cs2704;
    }

    public String getCs28a() {
        return cs28a;
    }

    public void setCs28a(String cs28a) {
        this.cs28a = cs28a;
    }

    public String getCs28b() {
        return cs28b;
    }

    public void setCs28b(String cs28b) {
        this.cs28b = cs28b;
    }

    public String getCs28c() {
        return cs28c;
    }

    public void setCs28c(String cs28c) {
        this.cs28c = cs28c;
    }

    public String getCs28d() {
        return cs28d;
    }

    public void setCs28d(String cs28d) {
        this.cs28d = cs28d;
    }

    public String getCs28e() {
        return cs28e;
    }

    public void setCs28e(String cs28e) {
        this.cs28e = cs28e;
    }

    public String getCs28f() {
        return cs28f;
    }

    public void setCs28f(String cs28f) {
        this.cs28f = cs28f;
    }

    public String getCs28f01x() {
        return cs28f01x;
    }

    public void setCs28f01x(String cs28f01x) {
        this.cs28f01x = cs28f01x;
    }

    public String getCs29() {
        return cs29;
    }

    public void setCs29(String cs29) {
        this.cs29 = cs29;
    }

    public String getCs30a() {
        return cs30a;
    }

    public void setCs30a(String cs30a) {
        this.cs30a = cs30a;
    }

    public String getCs30b() {
        return cs30b;
    }

    public void setCs30b(String cs30b) {
        this.cs30b = cs30b;
    }

    public String getCs30c() {
        return cs30c;
    }

    public void setCs30c(String cs30c) {
        this.cs30c = cs30c;
    }

    public String getCs30d() {
        return cs30d;
    }

    public void setCs30d(String cs30d) {
        this.cs30d = cs30d;
    }

    public String getCs30e() {
        return cs30e;
    }

    public void setCs30e(String cs30e) {
        this.cs30e = cs30e;
    }

    public String getCs30f() {
        return cs30f;
    }

    public void setCs30f(String cs30f) {
        this.cs30f = cs30f;
    }

    public String getCs30f096x() {
        return cs30f096x;
    }

    public void setCs30f096x(String cs30f096x) {
        this.cs30f096x = cs30f096x;
    }

    public String getCs31() {
        return cs31;
    }

    public void setCs31(String cs31) {
        this.cs31 = cs31;
    }

    public String getCs32a() {
        return cs32a;
    }

    public void setCs32a(String cs32a) {
        this.cs32a = cs32a;
    }

    public String getCs32b() {
        return cs32b;
    }

    public void setCs32b(String cs32b) {
        this.cs32b = cs32b;
    }

    public String getCs32c() {
        return cs32c;
    }

    public void setCs32c(String cs32c) {
        this.cs32c = cs32c;
    }

    public String getCs32d() {
        return cs32d;
    }

    public void setCs32d(String cs32d) {
        this.cs32d = cs32d;
    }

    public String getCs32e() {
        return cs32e;
    }

    public void setCs32e(String cs32e) {
        this.cs32e = cs32e;
    }

    public String getCs32f() {
        return cs32f;
    }

    public void setCs32f(String cs32f) {
        this.cs32f = cs32f;
    }

    public String getCs32g() {
        return cs32g;
    }

    public void setCs32g(String cs32g) {
        this.cs32g = cs32g;
    }

    public String getCs32h() {
        return cs32h;
    }

    public void setCs32h(String cs32h) {
        this.cs32h = cs32h;
    }

    public String getCs32h096x() {
        return cs32h096x;
    }

    public void setCs32h096x(String cs32h096x) {
        this.cs32h096x = cs32h096x;
    }

    //    Follow-up U5


    //    Screening Log WRAs


    //   Follow-up WRAs


    public String getcS() {
        return cS;
    }

    public void setcS(String cS) {
        this.cS = cS;
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
        this.cS = jsonObject.getString(FormsTable.COLUMN_CS);

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
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus96x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS96x));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        cSHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CS)));
        return this;
    }


    //TODO: Try this instead of toJSONObject
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Form.class);
    }

    public String cStoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("cs01", cs01)
                    .put("cs01a", cs01a)
                    .put("cs01b", cs01b)
                    .put("cs02", cs02)
                    .put("cs03", cs03)
                    .put("cs04", cs04)
                    .put("cs05", cs05)
                    .put("cs05a", cs05a)
                    .put("cs06", cs06)
                    .put("cs06096x", cs06096x)
                    .put("cs07", cs07)
                    .put("cs0801", cs0801)
                    .put("cs0802", cs0802)
                    .put("cs0803", cs0803)
                    .put("cs09", cs09)
                    .put("cs10", cs10)
                    .put("cs10a", cs10a)
                    .put("cs11", cs11)
                    .put("cs11a", cs11a)
                    .put("cs12", cs12)
                    .put("cs12a", cs12a)
                    .put("cs13", cs13)
                    .put("cs1401", cs1401)
                    .put("cs1402", cs1402)
                    .put("cs1403", cs1403)
                    .put("cs1501", cs1501)
                    .put("cs1502", cs1502)
                    .put("cs16", cs16)
                    .put("cs17", cs17)
                    .put("cs18", cs18)
                    .put("cs19", cs19)
                    .put("cs20a", cs20a)
                    .put("cs20b", cs20b)
                    .put("cs21", cs21)
                    .put("cs22", cs22)
                    .put("cs23", cs23)
                    .put("cs24", cs24)
                    .put("cs2401", cs2401)
                    .put("cs2402", cs2402)
                    .put("cs2403", cs2403)
                    .put("cs2404", cs2404)
                    .put("cs2405", cs2405)
                    .put("cs2406", cs2406)
                    .put("cs25", cs25)
                    .put("cs25a01", cs25a01)
                    .put("cs25b01", cs25b01)
                    .put("cs25c", cs25c)
                    .put("cs25c096x", cs25c096x)
                    .put("cs26", cs26)
                    .put("cs2601", cs2601)
                    .put("cs2602", cs2602)
                    .put("cs2603", cs2603)
                    .put("cs2604", cs2604)
                    .put("cs2605", cs2605)
                    .put("cs2701", cs2701)
                    .put("cs2702", cs2702)
                    .put("cs2703", cs2703)
                    .put("cs2704", cs2704)
                    .put("cs28a", cs28a)
                    .put("cs28b", cs28b)
                    .put("cs28c", cs28c)
                    .put("cs28d", cs28d)
                    .put("cs28e", cs28e)
                    .put("cs28f", cs28f)
                    .put("cs28f01x", cs28f01x)
                    .put("cs29", cs29)
                    .put("cs30a", cs30a)
                    .put("cs30b", cs30b)
                    .put("cs30c", cs30c)
                    .put("cs30d", cs30d)
                    .put("cs30e", cs30e)
                    .put("cs30f", cs30f)
                    .put("cs30f096x", cs30f096x)
                    .put("cs31", cs31)
                    .put("cs32a", cs32a)
                    .put("cs32b", cs32b)
                    .put("cs32c", cs32c)
                    .put("cs32d", cs32d)
                    .put("cs32e", cs32e)
                    .put("cs32f", cs32f)
                    .put("cs32g", cs32g)
                    .put("cs32h", cs32h)
                    .put("cs32h096x", cs32h096x);

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

            json.put(FormsTable.COLUMN_CS, new JSONObject(cStoString()));


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

    private void cSHydrate(String string) {
        if (string != null) {

            try {
                this.cs01 = toJSONObject().getString("cs01");
                this.cs01a = toJSONObject().getString("cs01a");
                this.cs01b = toJSONObject().getString("cs01b");
                this.cs02 = toJSONObject().getString("cs02");
                this.cs03 = toJSONObject().getString("cs03");
                this.cs04 = toJSONObject().getString("cs04");
                this.cs05 = toJSONObject().getString("cs05");
                this.cs05a = toJSONObject().getString("cs05a");
                this.cs06 = toJSONObject().getString("cs06");
                this.cs06096x = toJSONObject().getString("cs06096x");
                this.cs07 = toJSONObject().getString("cs07");
                this.cs0801 = toJSONObject().getString("cs0801");
                this.cs0802 = toJSONObject().getString("cs0802");
                this.cs0803 = toJSONObject().getString("cs0803");
                this.cs09 = toJSONObject().getString("cs09");
                this.cs10 = toJSONObject().getString("cs10");
                this.cs10a = toJSONObject().getString("cs10a");
                this.cs11 = toJSONObject().getString("cs11");
                this.cs11a = toJSONObject().getString("cs11a");
                this.cs12 = toJSONObject().getString("cs12");
                this.cs12a = toJSONObject().getString("cs12a");
                this.cs13 = toJSONObject().getString("cs13");
                this.cs1401 = toJSONObject().getString("cs1401");
                this.cs1402 = toJSONObject().getString("cs1402");
                this.cs1403 = toJSONObject().getString("cs1403");
                this.cs1501 = toJSONObject().getString("cs1501");
                this.cs1502 = toJSONObject().getString("cs1502");
                this.cs16 = toJSONObject().getString("cs16");
                this.cs17 = toJSONObject().getString("cs17");
                this.cs18 = toJSONObject().getString("cs18");
                this.cs19 = toJSONObject().getString("cs19");
                this.cs20a = toJSONObject().getString("cs20a");
                this.cs20b = toJSONObject().getString("cs20b");
                this.cs21 = toJSONObject().getString("cs21");
                this.cs22 = toJSONObject().getString("cs22");
                this.cs23 = toJSONObject().getString("cs23");
                this.cs24 = toJSONObject().getString("cs24");
                this.cs2401 = toJSONObject().getString("cs2401");
                this.cs2402 = toJSONObject().getString("cs2402");
                this.cs2403 = toJSONObject().getString("cs2403");
                this.cs2404 = toJSONObject().getString("cs2404");
                this.cs2405 = toJSONObject().getString("cs2405");
                this.cs2406 = toJSONObject().getString("cs2406");
                this.cs25 = toJSONObject().getString("cs25");
                this.cs25a01 = toJSONObject().getString("cs25a01");
                this.cs25b01 = toJSONObject().getString("cs25b01");
                this.cs25c = toJSONObject().getString("cs25c");
                this.cs25c096x = toJSONObject().getString("cs25c096x");
                this.cs26 = toJSONObject().getString("cs26");
                this.cs2601 = toJSONObject().getString("cs2601");
                this.cs2602 = toJSONObject().getString("cs2602");
                this.cs2603 = toJSONObject().getString("cs2603");
                this.cs2604 = toJSONObject().getString("cs2604");
                this.cs2605 = toJSONObject().getString("cs2605");
                this.cs2701 = toJSONObject().getString("cs2701");
                this.cs2702 = toJSONObject().getString("cs2702");
                this.cs2703 = toJSONObject().getString("cs2703");
                this.cs2704 = toJSONObject().getString("cs2704");
                this.cs28a = toJSONObject().getString("cs28a");
                this.cs28b = toJSONObject().getString("cs28b");
                this.cs28c = toJSONObject().getString("cs28c");
                this.cs28d = toJSONObject().getString("cs28d");
                this.cs28e = toJSONObject().getString("cs28e");
                this.cs28f = toJSONObject().getString("cs28f");
                this.cs28f01x = toJSONObject().getString("cs28f01x");
                this.cs29 = toJSONObject().getString("cs29");
                this.cs30a = toJSONObject().getString("cs30a");
                this.cs30b = toJSONObject().getString("cs30b");
                this.cs30c = toJSONObject().getString("cs30c");
                this.cs30d = toJSONObject().getString("cs30d");
                this.cs30e = toJSONObject().getString("cs30e");
                this.cs30f = toJSONObject().getString("cs30f");
                this.cs30f096x = toJSONObject().getString("cs30f096x");
                this.cs31 = toJSONObject().getString("cs31");
                this.cs32a = toJSONObject().getString("cs32a");
                this.cs32b = toJSONObject().getString("cs32b");
                this.cs32c = toJSONObject().getString("cs32c");
                this.cs32d = toJSONObject().getString("cs32d");
                this.cs32e = toJSONObject().getString("cs32e");
                this.cs32f = toJSONObject().getString("cs32f");
                this.cs32g = toJSONObject().getString("cs32g");
                this.cs32h = toJSONObject().getString("cs32h");
                this.cs32h096x = toJSONObject().getString("cs32h096x");


            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error at Hyderate", "cSHydrate: " + e.getMessage());
            }
        }
    }
}
