package edu.aku.hassannaqvi.casi_register.models;

import android.database.Cursor;
import android.util.Log;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.LocalDate;

import androidx.lifecycle.LiveData;

import edu.aku.hassannaqvi.casi_register.contracts.FormsContract.FormsTable;

/**
 * @author hassan.naqvi.
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
    private String cs07User;
    private String cs08;
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
    private String cs1698;
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
    private String cSFP = "";
    private String wS = "";
    private String wSFP = "";


    //    Follow-up U5
    private String fc01;
    private String fc01a;
    private String fc01b;
    private String fc02;
    private String fc03;
    private String fc04;
    private String fc05;
    private String fc05a;
    private String fc06;
    private String fc0696x;
    private String fc07;
    private String fc08;
    private String fc0801;
    private String fc0802;
    private String fc0803;
    private String fc09;
    private String fc10;
    private String fc10a;
    private String fc15;
    private String fc15a;
    private String fc16;
    private String fc16a;
    private String fc16b;
    private String fc16b96x;
    private String fc1701;
    private String fc1702;
    private String fc18;
    private String fc19;
    private String fc20;
    private String fc21;
    private String fc22;
    private String fc23;
    private String fc24;
    private String fc25;
    private String fc2501;
    private String fc2502;
    private String fc2503;
    private String fc2504;
    private String fc2505;
    private String fc2506;
    private String fc26;
    private String fc2601;
    private String fc2602;
    private String fc2603;
    private String fc2604;
    private String fc2605;
    private String fc2701;
    private String fc2702;
    private String fc2703;
    private String fc2704;
    private String fc28;
    private String fc29;
    private String fc2901;
    private String fc2902;
    private String fc2903;
    private String fc2904;
    private String fc2905;
    private String fc2996;
    private String fc2996x;
    private String fc30;
    private String fc31;
    private String fc3201;
    private String fc3202;
    private String fc3203;
    private String fc3301;
    private String fc3302;
    private String fc3303;
    private String fc34;
    private String fc3501;
    private String fc3601;
    private String fc37;
    private String fc3796x;
    private String fc38;


    //    Screening Log WRAs
    private String ws01;
    private String ws01a;
    private String ws01b;
    private String ws02;
    private String ws03;
    private String ws04;
    private String ws05;
    private String ws05a;
    private String ws06;
    private String ws0696x;
    private String ws07;
    private String ws08;
    private String ws0801;
    private String ws0802;
    private String ws0803;
    private String ws09;
    private String ws10;
    private String ws11;
    private String ws12;
    private String ws12a;
    private String ws13;
    private String ws14;
    private String ws15;
    private String ws16;
    private String ws17;
    private String ws18;
    private String ws19;
    private String ws20;
    private String ws2001;
    private String ws2002;
    private String ws2003;
    private String ws2004;
    private String ws2005;
    private String ws2006;
    private String ws21;
    private String ws22;
    private String ws2201;
    private String ws2202;
    private String ws2203;
    private String ws2296;
    private String ws2296x;
    private String ws2301;
    private String ws2302;
    private String ws2303;
    private String ws25a;
    private String ws25b;
    private String ws25c;
    private String ws25d;
    private String ws25e;
    private String ws25f;
    private String ws25g;
    private String ws25h;
    private String ws25i;
    private String ws25j;
    private String ws25k;
    private String ws25k01x;


    //   Follow-up WRAs
    private String fw01;
    private String fw01a;
    private String fw01b;
    private String fw02;
    private String fw03;
    private String fw04;
    private String fw05;
    private String fw05a;
    private String fw06;
    private String fw0696x;
    private String fw07;
    private String fw08;
    private String fw0801;
    private String fw0802;
    private String fw0803;
    private String fw09;
    private String fw10;
    private String fw11;
    private String fw12;
    private String fw12a;
    private String fw12b;
    private String fw12b96x;
    private String fw13;
    private String fw14;
    private String fw15;
    private String fw16;
    private String fw17;
    private String fw18;
    private String fw1801;
    private String fw1802;
    private String fw1803;
    private String fw1804;
    private String fw1805;
    private String fw1806;
    private String fw19;
    private String fw2001;
    private String fw21;
    private String fw22;
    private String fw2301;
    private String fw24;
    private String fw25;
    private String fw26;
    private String fw27;
    private String fw28;
    private String fw2801;
    private String fw2802;
    private String fw2803;
    private String fw2804;
    private String fw2896;
    private String fw2896x;
    private String fw29a;
    private String fw29b;
    private String fw29c;
    private String fw29d;
    private String fw29e;
    private String fw29f;
    private String fw29g;
    private String fw29h;
    private String fw29i;
    private String fw29j;
    private String fw29k;
    private String fw29k96x;


    //Date Settings
    private LocalDate localDate = null, calculatedDOB = null;


    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    public LocalDate getCalculatedDOB() {
        return calculatedDOB;
    }

    public void setCalculatedDOB(LocalDate calculatedDOB) {
        this.calculatedDOB = calculatedDOB;
    }

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


    public String getCs07User() {
        return cs07User;
    }

    public void setCs07User(String cs07User) {
        this.cs07User = cs07User;
    }


    public String getCs08() {
        return cs08;
    }

    public void setCs08(String cs08) {
        this.cs08 = cs08;
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

    public String getCs1698() {
        return cs1698;
    }

    public void setCs1698(String cs1698) {
        this.cs1698 = cs1698;
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
    public String getFc01() {
        return fc01;
    }

    public void setFc01(String fc01) {
        this.fc01 = fc01;
    }

    public String getFc01a() {
        return fc01a;
    }

    public void setFc01a(String fc01a) {
        this.fc01a = fc01a;
    }

    public String getFc01b() {
        return fc01b;
    }

    public void setFc01b(String fc01b) {
        this.fc01b = fc01b;
    }

    public String getFc02() {
        return fc02;
    }

    public void setFc02(String fc02) {
        this.fc02 = fc02;
    }

    public String getFc03() {
        return fc03;
    }

    public void setFc03(String fc03) {
        this.fc03 = fc03;
    }

    public String getFc04() {
        return fc04;
    }

    public void setFc04(String fc04) {
        this.fc04 = fc04;
    }

    public String getFc05() {
        return fc05;
    }

    public void setFc05(String fc05) {
        this.fc05 = fc05;
    }

    public String getFc05a() {
        return fc05a;
    }

    public void setFc05a(String fc05a) {
        this.fc05a = fc05a;
    }

    public String getFc06() {
        return fc06;
    }

    public void setFc06(String fc06) {
        this.fc06 = fc06;
    }

    public String getFc0696x() {
        return fc0696x;
    }

    public void setFc0696x(String fc0696x) {
        this.fc0696x = fc0696x;
    }

    public String getFc07() {
        return fc07;
    }

    public void setFc07(String fc07) {
        this.fc07 = fc07;
    }

    public String getFc08() {
        return fc08;
    }

    public void setFc08(String fc08) {
        this.fc08 = fc08;
    }

    public String getFc0801() {
        return fc0801;
    }

    public void setFc0801(String fc0801) {
        this.fc0801 = fc0801;
    }

    public String getFc0802() {
        return fc0802;
    }

    public void setFc0802(String fc0802) {
        this.fc0802 = fc0802;
    }

    public String getFc0803() {
        return fc0803;
    }

    public void setFc0803(String fc0803) {
        this.fc0803 = fc0803;
    }

    public String getFc09() {
        return fc09;
    }

    public void setFc09(String fc09) {
        this.fc09 = fc09;
    }

    public String getFc10() {
        return fc10;
    }

    public void setFc10(String fc10) {
        this.fc10 = fc10;
    }

    public String getFc10a() {
        return fc10a;
    }

    public void setFc10a(String fc10a) {
        this.fc10a = fc10a;
    }

    public String getFc15() {
        return fc15;
    }

    public void setFc15(String fc15) {
        this.fc15 = fc15;
    }

    public String getFc15a() {
        return fc15a;
    }

    public void setFc15a(String fc15a) {
        this.fc15a = fc15a;
    }

    public String getFc16() {
        return fc16;
    }

    public void setFc16(String fc16) {
        this.fc16 = fc16;
    }

    public String getFc16a() {
        return fc16a;
    }

    public void setFc16a(String fc16a) {
        this.fc16a = fc16a;
    }

    public String getFc16b() {
        return fc16b;
    }

    public void setFc16b(String fc16b) {
        this.fc16b = fc16b;
    }

    public String getFc16b96x() {
        return fc16b96x;
    }

    public void setFc16b96x(String fc16b96x) {
        this.fc16b96x = fc16b96x;
    }

    public String getFc1701() {
        return fc1701;
    }

    public void setFc1701(String fc1701) {
        this.fc1701 = fc1701;
    }

    public String getFc1702() {
        return fc1702;
    }

    public void setFc1702(String fc1702) {
        this.fc1702 = fc1702;
    }

    public String getFc18() {
        return fc18;
    }

    public void setFc18(String fc18) {
        this.fc18 = fc18;
    }

    public String getFc19() {
        return fc19;
    }

    public void setFc19(String fc19) {
        this.fc19 = fc19;
    }

    public String getFc20() {
        return fc20;
    }

    public void setFc20(String fc20) {
        this.fc20 = fc20;
    }

    public String getFc21() {
        return fc21;
    }

    public void setFc21(String fc21) {
        this.fc21 = fc21;
    }

    public String getFc22() {
        return fc22;
    }

    public void setFc22(String fc22) {
        this.fc22 = fc22;
    }

    public String getFc23() {
        return fc23;
    }

    public void setFc23(String fc23) {
        this.fc23 = fc23;
    }

    public String getFc24() {
        return fc24;
    }

    public void setFc24(String fc24) {
        this.fc24 = fc24;
    }

    public String getFc25() {
        return fc25;
    }

    public void setFc25(String fc25) {
        this.fc25 = fc25;
    }

    public String getFc2501() {
        return fc2501;
    }

    public void setFc2501(String fc2501) {
        this.fc2501 = fc2501;
    }

    public String getFc2502() {
        return fc2502;
    }

    public void setFc2502(String fc2502) {
        this.fc2502 = fc2502;
    }

    public String getFc2503() {
        return fc2503;
    }

    public void setFc2503(String fc2503) {
        this.fc2503 = fc2503;
    }

    public String getFc2504() {
        return fc2504;
    }

    public void setFc2504(String fc2504) {
        this.fc2504 = fc2504;
    }

    public String getFc2505() {
        return fc2505;
    }

    public void setFc2505(String fc2505) {
        this.fc2505 = fc2505;
    }

    public String getFc2506() {
        return fc2506;
    }

    public void setFc2506(String fc2506) {
        this.fc2506 = fc2506;
    }

    public String getFc26() {
        return fc26;
    }

    public void setFc26(String fc26) {
        this.fc26 = fc26;
    }

    public String getFc2601() {
        return fc2601;
    }

    public void setFc2601(String fc2601) {
        this.fc2601 = fc2601;
    }

    public String getFc2602() {
        return fc2602;
    }

    public void setFc2602(String fc2602) {
        this.fc2602 = fc2602;
    }

    public String getFc2603() {
        return fc2603;
    }

    public void setFc2603(String fc2603) {
        this.fc2603 = fc2603;
    }

    public String getFc2604() {
        return fc2604;
    }

    public void setFc2604(String fc2604) {
        this.fc2604 = fc2604;
    }

    public String getFc2605() {
        return fc2605;
    }

    public void setFc2605(String fc2605) {
        this.fc2605 = fc2605;
    }

    public String getFc2701() {
        return fc2701;
    }

    public void setFc2701(String fc2701) {
        this.fc2701 = fc2701;
    }

    public String getFc2702() {
        return fc2702;
    }

    public void setFc2702(String fc2702) {
        this.fc2702 = fc2702;
    }

    public String getFc2703() {
        return fc2703;
    }

    public void setFc2703(String fc2703) {
        this.fc2703 = fc2703;
    }

    public String getFc2704() {
        return fc2704;
    }

    public void setFc2704(String fc2704) {
        this.fc2704 = fc2704;
    }

    public String getFc28() {
        return fc28;
    }

    public void setFc28(String fc28) {
        this.fc28 = fc28;
    }

    public String getFc29() {
        return fc29;
    }

    public void setFc29(String fc29) {
        this.fc29 = fc29;
    }

    public String getFc2901() {
        return fc2901;
    }

    public void setFc2901(String fc2901) {
        this.fc2901 = fc2901;
    }

    public String getFc2902() {
        return fc2902;
    }

    public void setFc2902(String fc2902) {
        this.fc2902 = fc2902;
    }

    public String getFc2903() {
        return fc2903;
    }

    public void setFc2903(String fc2903) {
        this.fc2903 = fc2903;
    }

    public String getFc2904() {
        return fc2904;
    }

    public void setFc2904(String fc2904) {
        this.fc2904 = fc2904;
    }

    public String getFc2905() {
        return fc2905;
    }

    public void setFc2905(String fc2905) {
        this.fc2905 = fc2905;
    }

    public String getFc2996() {
        return fc2996;
    }

    public void setFc2996(String fc2996) {
        this.fc2996 = fc2996;
    }

    public String getFc2996x() {
        return fc2996x;
    }

    public void setFc2996x(String fc2996x) {
        this.fc2996x = fc2996x;
    }

    public String getFc30() {
        return fc30;
    }

    public void setFc30(String fc30) {
        this.fc30 = fc30;
    }

    public String getFc31() {
        return fc31;
    }

    public void setFc31(String fc31) {
        this.fc31 = fc31;
    }

    public String getFc3201() {
        return fc3201;
    }

    public void setFc3201(String fc3201) {
        this.fc3201 = fc3201;
    }

    public String getFc3202() {
        return fc3202;
    }

    public void setFc3202(String fc3202) {
        this.fc3202 = fc3202;
    }

    public String getFc3203() {
        return fc3203;
    }

    public void setFc3203(String fc3203) {
        this.fc3203 = fc3203;
    }

    public String getFc3301() {
        return fc3301;
    }

    public void setFc3301(String fc3301) {
        this.fc3301 = fc3301;
    }

    public String getFc3302() {
        return fc3302;
    }

    public void setFc3302(String fc3302) {
        this.fc3302 = fc3302;
    }

    public String getFc3303() {
        return fc3303;
    }

    public void setFc3303(String fc3303) {
        this.fc3303 = fc3303;
    }

    public String getFc34() {
        return fc34;
    }

    public void setFc34(String fc34) {
        this.fc34 = fc34;
    }

    public String getFc3501() {
        return fc3501;
    }

    public void setFc3501(String fc3501) {
        this.fc3501 = fc3501;
    }

    public String getFc3601() {
        return fc3601;
    }

    public void setFc3601(String fc3601) {
        this.fc3601 = fc3601;
    }

    public String getFc37() {
        return fc37;
    }

    public void setFc37(String fc37) {
        this.fc37 = fc37;
    }

    public String getFc3796x() {
        return fc3796x;
    }

    public void setFc3796x(String fc3796x) {
        this.fc3796x = fc3796x;
    }

    public String getFc38() {
        return fc38;
    }

    public void setFc38(String fc38) {
        this.fc38 = fc38;
    }


    //    Screening Log WRAs
    public String getWs01() {
        return ws01;
    }

    public void setWs01(String ws01) {
        this.ws01 = ws01;
    }

    public String getWs01a() {
        return ws01a;
    }

    public void setWs01a(String ws01a) {
        this.ws01a = ws01a;
    }

    public String getWs01b() {
        return ws01b;
    }

    public void setWs01b(String ws01b) {
        this.ws01b = ws01b;
    }

    public String getWs02() {
        return ws02;
    }

    public void setWs02(String ws02) {
        this.ws02 = ws02;
    }

    public String getWs03() {
        return ws03;
    }

    public void setWs03(String ws03) {
        this.ws03 = ws03;
    }

    public String getWs04() {
        return ws04;
    }

    public void setWs04(String ws04) {
        this.ws04 = ws04;
    }

    public String getWs05() {
        return ws05;
    }

    public void setWs05(String ws05) {
        this.ws05 = ws05;
    }

    public String getWs05a() {
        return ws05a;
    }

    public void setWs05a(String ws05a) {
        this.ws05a = ws05a;
    }

    public String getWs06() {
        return ws06;
    }

    public void setWs06(String ws06) {
        this.ws06 = ws06;
    }

    public String getWs0696x() {
        return ws0696x;
    }

    public void setWs0696x(String ws0696x) {
        this.ws0696x = ws0696x;
    }

    public String getWs07() {
        return ws07;
    }

    public void setWs07(String ws07) {
        this.ws07 = ws07;
    }

    public String getWs08() {
        return ws08;
    }

    public void setWs08(String ws08) {
        this.ws08 = ws08;
    }

    public String getWs0801() {
        return ws0801;
    }

    public void setWs0801(String ws0801) {
        this.ws0801 = ws0801;
    }

    public String getWs0802() {
        return ws0802;
    }

    public void setWs0802(String ws0802) {
        this.ws0802 = ws0802;
    }

    public String getWs0803() {
        return ws0803;
    }

    public void setWs0803(String ws0803) {
        this.ws0803 = ws0803;
    }

    public String getWs09() {
        return ws09;
    }

    public void setWs09(String ws09) {
        this.ws09 = ws09;
    }

    public String getWs10() {
        return ws10;
    }

    public void setWs10(String ws10) {
        this.ws10 = ws10;
    }

    public String getWs11() {
        return ws11;
    }

    public void setWs11(String ws11) {
        this.ws11 = ws11;
    }

    public String getWs12() {
        return ws12;
    }

    public void setWs12(String ws12) {
        this.ws12 = ws12;
    }

    public String getWs12a() {
        return ws12a;
    }

    public void setWs12a(String ws12a) {
        this.ws12a = ws12a;
    }

    public String getWs13() {
        return ws13;
    }

    public void setWs13(String ws13) {
        this.ws13 = ws13;
    }

    public String getWs14() {
        return ws14;
    }

    public void setWs14(String ws14) {
        this.ws14 = ws14;
    }

    public String getWs15() {
        return ws15;
    }

    public void setWs15(String ws15) {
        this.ws15 = ws15;
    }

    public String getWs16() {
        return ws16;
    }

    public void setWs16(String ws16) {
        this.ws16 = ws16;
    }

    public String getWs17() {
        return ws17;
    }

    public void setWs17(String ws17) {
        this.ws17 = ws17;
    }

    public String getWs18() {
        return ws18;
    }

    public void setWs18(String ws18) {
        this.ws18 = ws18;
    }

    public String getWs19() {
        return ws19;
    }

    public void setWs19(String ws19) {
        this.ws19 = ws19;
    }

    public String getWs20() {
        return ws20;
    }

    public void setWs20(String ws20) {
        this.ws20 = ws20;
    }

    public String getWs2001() {
        return ws2001;
    }

    public void setWs2001(String ws2001) {
        this.ws2001 = ws2001;
    }

    public String getWs2002() {
        return ws2002;
    }

    public void setWs2002(String ws2002) {
        this.ws2002 = ws2002;
    }

    public String getWs2003() {
        return ws2003;
    }

    public void setWs2003(String ws2003) {
        this.ws2003 = ws2003;
    }

    public String getWs2004() {
        return ws2004;
    }

    public void setWs2004(String ws2004) {
        this.ws2004 = ws2004;
    }

    public String getWs2005() {
        return ws2005;
    }

    public void setWs2005(String ws2005) {
        this.ws2005 = ws2005;
    }

    public String getWs2006() {
        return ws2006;
    }

    public void setWs2006(String ws2006) {
        this.ws2006 = ws2006;
    }

    public String getWs21() {
        return ws21;
    }

    public void setWs21(String ws21) {
        this.ws21 = ws21;
    }

    public String getWs22() {
        return ws22;
    }

    public void setWs22(String ws22) {
        this.ws22 = ws22;
    }

    public String getWs2201() {
        return ws2201;
    }

    public void setWs2201(String ws2201) {
        this.ws2201 = ws2201;
    }

    public String getWs2202() {
        return ws2202;
    }

    public void setWs2202(String ws2202) {
        this.ws2202 = ws2202;
    }

    public String getWs2203() {
        return ws2203;
    }

    public void setWs2203(String ws2203) {
        this.ws2203 = ws2203;
    }

    public String getWs2296() {
        return ws2296;
    }

    public void setWs2296(String ws2296) {
        this.ws2296 = ws2296;
    }

    public String getWs2296x() {
        return ws2296x;
    }

    public void setWs2296x(String ws2296x) {
        this.ws2296x = ws2296x;
    }

    public String getWs2301() {
        return ws2301;
    }

    public void setWs2301(String ws2301) {
        this.ws2301 = ws2301;
    }

    public String getWs2302() {
        return ws2302;
    }

    public void setWs2302(String ws2302) {
        this.ws2302 = ws2302;
    }

    public String getWs2303() {
        return ws2303;
    }

    public void setWs2303(String ws2303) {
        this.ws2303 = ws2303;
    }

    public String getWs25a() {
        return ws25a;
    }

    public void setWs25a(String ws25a) {
        this.ws25a = ws25a;
    }

    public String getWs25b() {
        return ws25b;
    }

    public void setWs25b(String ws25b) {
        this.ws25b = ws25b;
    }

    public String getWs25c() {
        return ws25c;
    }

    public void setWs25c(String ws25c) {
        this.ws25c = ws25c;
    }

    public String getWs25d() {
        return ws25d;
    }

    public void setWs25d(String ws25d) {
        this.ws25d = ws25d;
    }

    public String getWs25e() {
        return ws25e;
    }

    public void setWs25e(String ws25e) {
        this.ws25e = ws25e;
    }

    public String getWs25f() {
        return ws25f;
    }

    public void setWs25f(String ws25f) {
        this.ws25f = ws25f;
    }

    public String getWs25g() {
        return ws25g;
    }

    public void setWs25g(String ws25g) {
        this.ws25g = ws25g;
    }

    public String getWs25h() {
        return ws25h;
    }

    public void setWs25h(String ws25h) {
        this.ws25h = ws25h;
    }

    public String getWs25i() {
        return ws25i;
    }

    public void setWs25i(String ws25i) {
        this.ws25i = ws25i;
    }

    public String getWs25j() {
        return ws25j;
    }

    public void setWs25j(String ws25j) {
        this.ws25j = ws25j;
    }

    public String getWs25k() {
        return ws25k;
    }

    public void setWs25k(String ws25k) {
        this.ws25k = ws25k;
    }

    public String getWs25k01x() {
        return ws25k01x;
    }

    public void setWs25k01x(String ws25k01x) {
        this.ws25k01x = ws25k01x;
    }

    //   Follow-up WRAs
    public String getFw01() {
        return fw01;
    }

    public void setFw01(String fw01) {
        this.fw01 = fw01;
    }

    public String getFw01a() {
        return fw01a;
    }

    public void setFw01a(String fw01a) {
        this.fw01a = fw01a;
    }

    public String getFw01b() {
        return fw01b;
    }

    public void setFw01b(String fw01b) {
        this.fw01b = fw01b;
    }

    public String getFw02() {
        return fw02;
    }

    public void setFw02(String fw02) {
        this.fw02 = fw02;
    }

    public String getFw03() {
        return fw03;
    }

    public void setFw03(String fw03) {
        this.fw03 = fw03;
    }

    public String getFw04() {
        return fw04;
    }

    public void setFw04(String fw04) {
        this.fw04 = fw04;
    }

    public String getFw05() {
        return fw05;
    }

    public void setFw05(String fw05) {
        this.fw05 = fw05;
    }

    public String getFw05a() {
        return fw05a;
    }

    public void setFw05a(String fw05a) {
        this.fw05a = fw05a;
    }

    public String getFw06() {
        return fw06;
    }

    public void setFw06(String fw06) {
        this.fw06 = fw06;
    }

    public String getFw0696x() {
        return fw0696x;
    }

    public void setFw0696x(String fw0696x) {
        this.fw0696x = fw0696x;
    }

    public String getFw07() {
        return fw07;
    }

    public void setFw07(String fw07) {
        this.fw07 = fw07;
    }

    public String getFw08() {
        return fw08;
    }

    public void setFw08(String fw08) {
        this.fw08 = fw08;
    }

    public String getFw0801() {
        return fw0801;
    }

    public void setFw0801(String fw0801) {
        this.fw0801 = fw0801;
    }

    public String getFw0802() {
        return fw0802;
    }

    public void setFw0802(String fw0802) {
        this.fw0802 = fw0802;
    }

    public String getFw0803() {
        return fw0803;
    }

    public void setFw0803(String fw0803) {
        this.fw0803 = fw0803;
    }

    public String getFw09() {
        return fw09;
    }

    public void setFw09(String fw09) {
        this.fw09 = fw09;
    }

    public String getFw10() {
        return fw10;
    }

    public void setFw10(String fw10) {
        this.fw10 = fw10;
    }

    public String getFw11() {
        return fw11;
    }

    public void setFw11(String fw11) {
        this.fw11 = fw11;
    }

    public String getFw12() {
        return fw12;
    }

    public void setFw12(String fw12) {
        this.fw12 = fw12;
    }

    public String getFw12a() {
        return fw12a;
    }

    public void setFw12a(String fw12a) {
        this.fw12a = fw12a;
    }

    public String getFw12b() {
        return fw12b;
    }

    public void setFw12b(String fw12b) {
        this.fw12b = fw12b;
    }

    public String getFw12b96x() {
        return fw12b96x;
    }

    public void setFw12b96x(String fw12b96x) {
        this.fw12b96x = fw12b96x;
    }

    public String getFw13() {
        return fw13;
    }

    public void setFw13(String fw13) {
        this.fw13 = fw13;
    }

    public String getFw14() {
        return fw14;
    }

    public void setFw14(String fw14) {
        this.fw14 = fw14;
    }

    public String getFw15() {
        return fw15;
    }

    public void setFw15(String fw15) {
        this.fw15 = fw15;
    }

    public String getFw16() {
        return fw16;
    }

    public void setFw16(String fw16) {
        this.fw16 = fw16;
    }

    public String getFw17() {
        return fw17;
    }

    public void setFw17(String fw17) {
        this.fw17 = fw17;
    }

    public String getFw18() {
        return fw18;
    }

    public void setFw18(String fw18) {
        this.fw18 = fw18;
    }

    public String getFw1801() {
        return fw1801;
    }

    public void setFw1801(String fw1801) {
        this.fw1801 = fw1801;
    }

    public String getFw1802() {
        return fw1802;
    }

    public void setFw1802(String fw1802) {
        this.fw1802 = fw1802;
    }

    public String getFw1803() {
        return fw1803;
    }

    public void setFw1803(String fw1803) {
        this.fw1803 = fw1803;
    }

    public String getFw1804() {
        return fw1804;
    }

    public void setFw1804(String fw1804) {
        this.fw1804 = fw1804;
    }

    public String getFw1805() {
        return fw1805;
    }

    public void setFw1805(String fw1805) {
        this.fw1805 = fw1805;
    }

    public String getFw1806() {
        return fw1806;
    }

    public void setFw1806(String fw1806) {
        this.fw1806 = fw1806;
    }

    public String getFw19() {
        return fw19;
    }

    public void setFw19(String fw19) {
        this.fw19 = fw19;
    }

    public String getFw2001() {
        return fw2001;
    }

    public void setFw2001(String fw2001) {
        this.fw2001 = fw2001;
    }

    public String getFw21() {
        return fw21;
    }

    public void setFw21(String fw21) {
        this.fw21 = fw21;
    }

    public String getFw22() {
        return fw22;
    }

    public void setFw22(String fw22) {
        this.fw22 = fw22;
    }

    public String getFw2301() {
        return fw2301;
    }

    public void setFw2301(String fw2301) {
        this.fw2301 = fw2301;
    }

    public String getFw24() {
        return fw24;
    }

    public void setFw24(String fw24) {
        this.fw24 = fw24;
    }

    public String getFw25() {
        return fw25;
    }

    public void setFw25(String fw25) {
        this.fw25 = fw25;
    }

    public String getFw26() {
        return fw26;
    }

    public void setFw26(String fw26) {
        this.fw26 = fw26;
    }

    public String getFw27() {
        return fw27;
    }

    public void setFw27(String fw27) {
        this.fw27 = fw27;
    }

    public String getFw28() {
        return fw28;
    }

    public void setFw28(String fw28) {
        this.fw28 = fw28;
    }

    public String getFw2801() {
        return fw2801;
    }

    public void setFw2801(String fw2801) {
        this.fw2801 = fw2801;
    }

    public String getFw2802() {
        return fw2802;
    }

    public void setFw2802(String fw2802) {
        this.fw2802 = fw2802;
    }

    public String getFw2803() {
        return fw2803;
    }

    public void setFw2803(String fw2803) {
        this.fw2803 = fw2803;
    }

    public String getFw2804() {
        return fw2804;
    }

    public void setFw2804(String fw2804) {
        this.fw2804 = fw2804;
    }

    public String getFw2896() {
        return fw2896;
    }

    public void setFw2896(String fw2896) {
        this.fw2896 = fw2896;
    }

    public String getFw2896x() {
        return fw2896x;
    }

    public void setFw2896x(String fw2896x) {
        this.fw2896x = fw2896x;
    }

    public String getFw29a() {
        return fw29a;
    }

    public void setFw29a(String fw29a) {
        this.fw29a = fw29a;
    }

    public String getFw29b() {
        return fw29b;
    }

    public void setFw29b(String fw29b) {
        this.fw29b = fw29b;
    }

    public String getFw29c() {
        return fw29c;
    }

    public void setFw29c(String fw29c) {
        this.fw29c = fw29c;
    }

    public String getFw29d() {
        return fw29d;
    }

    public void setFw29d(String fw29d) {
        this.fw29d = fw29d;
    }

    public String getFw29e() {
        return fw29e;
    }

    public void setFw29e(String fw29e) {
        this.fw29e = fw29e;
    }

    public String getFw29f() {
        return fw29f;
    }

    public void setFw29f(String fw29f) {
        this.fw29f = fw29f;
    }

    public String getFw29g() {
        return fw29g;
    }

    public void setFw29g(String fw29g) {
        this.fw29g = fw29g;
    }

    public String getFw29h() {
        return fw29h;
    }

    public void setFw29h(String fw29h) {
        this.fw29h = fw29h;
    }

    public String getFw29i() {
        return fw29i;
    }

    public void setFw29i(String fw29i) {
        this.fw29i = fw29i;
    }

    public String getFw29j() {
        return fw29j;
    }

    public void setFw29j(String fw29j) {
        this.fw29j = fw29j;
    }

    public String getFw29k() {
        return fw29k;
    }

    public void setFw29k(String fw29k) {
        this.fw29k = fw29k;
    }

    public String getFw29k96x() {
        return fw29k96x;
    }

    public void setFw29k96x(String fw29k96x) {
        this.fw29k96x = fw29k96x;
    }


    // Sections
    public String getcS() {
        return cS;
    }

    public void setcS(String cS) {
        this.cS = cS;
    }

    public String getcSFP() {
        return cSFP;
    }

    public void setcSFP(String cSFP) {
        this.cSFP = cSFP;
    }

    public String getwS() {
        return wS;
    }

    public void setwS(String wS) {
        this.wS = wS;
    }

    public String getwSFP() {
        return wSFP;
    }

    public void setwSFP(String wSFP) {
        this.wSFP = wSFP;
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
        this.cSFP = jsonObject.getString(FormsTable.COLUMN_CSFP);
        this.wS = jsonObject.getString(FormsTable.COLUMN_WS);
        this.wSFP = jsonObject.getString(FormsTable.COLUMN_WSFP);

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
        cSFPHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CSFP)));
        wSHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_WS)));
        wSFPHydrate(cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_WSFP)));
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
                    .put("cs07User", cs07User)
                    .put("cs08", cs08)
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
                    .put("cs1698", cs1698)
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

    public String cS02toString() {
        JSONObject json = new JSONObject();

        try {
            json
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

    public String cSFPtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("fc01", fc01)
                    .put("fc01a", fc01a)
                    .put("fc01b", fc01b)
                    .put("fc02", fc02)
                    .put("fc03", fc03)
                    .put("fc04", fc04)
                    .put("fc05", fc05)
                    .put("fc05a", fc05a)
                    .put("fc06", fc06)
                    .put("fc0696x", fc0696x)
                    .put("fc07", fc07)
                    .put("fc08", fc08)
                    .put("fc0801", fc0801)
                    .put("fc0802", fc0802)
                    .put("fc0803", fc0803)
                    .put("fc09", fc09)
                    .put("fc10", fc10)
                    .put("fc10a", fc10a)
                    .put("fc15", fc15)
                    .put("fc15a", fc15a)
                    .put("fc16", fc16)
                    .put("fc16a", fc16a)
                    .put("fc16b", fc16b)
                    .put("fc16b96x", fc16b96x)
                    .put("fc1701", fc1701)
                    .put("fc1702", fc1702)
                    .put("fc18", fc18)
                    .put("fc19", fc19)
                    .put("fc20", fc20)
                    .put("fc21", fc21)
                    .put("fc22", fc22)
                    .put("fc23", fc23)
                    .put("fc24", fc24)
                    .put("fc25", fc25)
                    .put("fc2501", fc2501)
                    .put("fc2502", fc2502)
                    .put("fc2503", fc2503)
                    .put("fc2504", fc2504)
                    .put("fc2505", fc2505)
                    .put("fc2506", fc2506)
                    .put("fc26", fc26)
                    .put("fc2601", fc2601)
                    .put("fc2602", fc2602)
                    .put("fc2603", fc2603)
                    .put("fc2604", fc2604)
                    .put("fc2605", fc2605)
                    .put("fc2701", fc2701)
                    .put("fc2702", fc2702)
                    .put("fc2703", fc2703)
                    .put("fc2704", fc2704)
                    .put("fc28", fc28)
                    .put("fc29", fc29)
                    .put("fc2901", fc2901)
                    .put("fc2902", fc2902)
                    .put("fc2903", fc2903)
                    .put("fc2904", fc2904)
                    .put("fc2905", fc2905)
                    .put("fc2996", fc2996)
                    .put("fc2996x", fc2996x)
                    .put("fc30", fc30)
                    .put("fc31", fc31)
                    .put("fc3201", fc3201)
                    .put("fc3202", fc3202)
                    .put("fc3203", fc3203)
                    .put("fc3301", fc3301)
                    .put("fc3302", fc3302)
                    .put("fc3303", fc3303)
                    .put("fc34", fc34)
                    .put("fc3501", fc3501)
                    .put("fc3601", fc3601)
                    .put("fc37", fc37)
                    .put("fc3796x", fc3796x)
                    .put("fc38", fc38);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\"" + e.getMessage() + "\"";
        }
        return json.toString();
    }

    public String wStoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("ws01", ws01)
                    .put("ws01a", ws01a)
                    .put("ws01b", ws01b)
                    .put("ws02", ws02)
                    .put("ws03", ws03)
                    .put("ws04", ws04)
                    .put("ws05", ws05)
                    .put("ws05a", ws05a)
                    .put("ws06", ws06)
                    .put("ws0696x", ws0696x)
                    .put("ws07", ws07)
                    .put("ws08", ws08)
                    .put("ws0801", ws0801)
                    .put("ws0802", ws0802)
                    .put("ws0803", ws0803)
                    .put("ws09", ws09)
                    .put("ws10", ws10)
                    .put("ws11", ws11)
                    .put("ws12", ws12)
                    .put("ws12a", ws12a)
                    .put("ws13", ws13)
                    .put("ws14", ws14)
                    .put("ws15", ws15)
                    .put("ws16", ws16)
                    .put("ws17", ws17)
                    .put("ws18", ws18)
                    .put("ws19", ws19)
                    .put("ws20", ws20)
                    .put("ws2001", ws2001)
                    .put("ws2002", ws2002)
                    .put("ws2003", ws2003)
                    .put("ws2004", ws2004)
                    .put("ws2005", ws2005)
                    .put("ws2006", ws2006)
                    .put("ws21", ws21)
                    .put("ws22", ws22)
                    .put("ws2201", ws2201)
                    .put("ws2202", ws2202)
                    .put("ws2203", ws2203)
                    .put("ws2296", ws2296)
                    .put("ws2296x", ws2296x)
                    .put("ws2301", ws2301)
                    .put("ws2302", ws2302)
                    .put("ws2303", ws2303)
                    .put("ws25a", ws25a)
                    .put("ws25b", ws25b)
                    .put("ws25c", ws25c)
                    .put("ws25d", ws25d)
                    .put("ws25e", ws25e)
                    .put("ws25f", ws25f)
                    .put("ws25g", ws25g)
                    .put("ws25h", ws25h)
                    .put("ws25i", ws25i)
                    .put("ws25j", ws25j)
                    .put("ws25k", ws25k)
                    .put("ws25k01x", ws25k01x);

        } catch (JSONException e) {
            e.printStackTrace();
            return "\"error\"" + e.getMessage() + "\"";
        }
        return json.toString();
    }

    public String wSFPtoString() {
        JSONObject json = new JSONObject();

        try {
            json.put("fw01", fw01)
                    .put("fw01a", fw01a)
                    .put("fw01b", fw01b)
                    .put("fw02", fw02)
                    .put("fw03", fw03)
                    .put("fw04", fw04)
                    .put("fw05", fw05)
                    .put("fw05a", fw05a)
                    .put("fw06", fw06)
                    .put("fw0696x", fw0696x)
                    .put("fw07", fw07)
                    .put("fw08", fw08)
                    .put("fw0801", fw0801)
                    .put("fw0802", fw0802)
                    .put("fw0803", fw0803)
                    .put("fw09", fw09)
                    .put("fw10", fw10)
                    .put("fw11", fw11)
                    .put("fw12", fw12)
                    .put("fw12a", fw12a)
                    .put("fw12b", fw12b)
                    .put("fw12b96x", fw12b96x)
                    .put("fw13", fw13)
                    .put("fw14", fw14)
                    .put("fw15", fw15)
                    .put("fw16", fw16)
                    .put("fw17", fw17)
                    .put("fw18", fw18)
                    .put("fw1801", fw1801)
                    .put("fw1802", fw1802)
                    .put("fw1803", fw1803)
                    .put("fw1804", fw1804)
                    .put("fw1805", fw1805)
                    .put("fw1806", fw1806)
                    .put("fw19", fw19)
                    .put("fw2001", fw2001)
                    .put("fw21", fw21)
                    .put("fw22", fw22)
                    .put("fw2301", fw2301)
                    .put("fw24", fw24)
                    .put("fw25", fw25)
                    .put("fw26", fw26)
                    .put("fw27", fw27)
                    .put("fw28", fw28)
                    .put("fw2801", fw2801)
                    .put("fw2802", fw2802)
                    .put("fw2803", fw2803)
                    .put("fw2804", fw2804)
                    .put("fw2896", fw2896)
                    .put("fw2896x", fw2896x)
                    .put("fw29a", fw29a)
                    .put("fw29b", fw29b)
                    .put("fw29c", fw29c)
                    .put("fw29d", fw29d)
                    .put("fw29e", fw29e)
                    .put("fw29f", fw29f)
                    .put("fw29g", fw29g)
                    .put("fw29h", fw29h)
                    .put("fw29i", fw29i)
                    .put("fw29j", fw29j)
                    .put("fw29k", fw29k)
                    .put("fw29k96x", fw29k96x);


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
            json.put(FormsTable.COLUMN_CSFP, new JSONObject(cSFPtoString()));
            json.put(FormsTable.COLUMN_WS, new JSONObject(wStoString()));
            json.put(FormsTable.COLUMN_WSFP, new JSONObject(wSFPtoString()));


            if (this.cS != null && !this.cS.equals("")) {
                json.put(FormsTable.COLUMN_CS, new JSONObject(this.cS));
            }

            if (this.cSFP != null && !this.cSFP.equals("")) {
                json.put(FormsTable.COLUMN_CSFP, new JSONObject(this.cSFP));
            }

            if (this.wS != null && !this.wS.equals("")) {
                json.put(FormsTable.COLUMN_WS, new JSONObject(this.wS));
            }

            if (this.wSFP != null && !this.wSFP.equals("")) {
                json.put(FormsTable.COLUMN_WSFP, new JSONObject(this.wSFP));
            }


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
                this.cs07User = toJSONObject().getString("cs07User");
                this.cs08 = toJSONObject().getString("cs08");
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
                this.cs1698 = toJSONObject().getString("cs1698");
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

    private void cSFPHydrate(String string) {
        if (string != null) {

            try {
                this.fc01 = toJSONObject().getString("fc01");
                this.fc01a = toJSONObject().getString("fc01a");
                this.fc01b = toJSONObject().getString("fc01b");
                this.fc02 = toJSONObject().getString("fc02");
                this.fc03 = toJSONObject().getString("fc03");
                this.fc04 = toJSONObject().getString("fc04");
                this.fc05 = toJSONObject().getString("fc05");
                this.fc05a = toJSONObject().getString("fc05a");
                this.fc06 = toJSONObject().getString("fc06");
                this.fc0696x = toJSONObject().getString("fc0696x");
                this.fc07 = toJSONObject().getString("fc07");
                this.fc08 = toJSONObject().getString("fc08");
                this.fc0801 = toJSONObject().getString("fc0801");
                this.fc0802 = toJSONObject().getString("fc0802");
                this.fc0803 = toJSONObject().getString("fc0803");
                this.fc09 = toJSONObject().getString("fc09");
                this.fc10 = toJSONObject().getString("fc10");
                this.fc10a = toJSONObject().getString("fc10a");
                this.fc15 = toJSONObject().getString("fc15");
                this.fc15a = toJSONObject().getString("fc15a");
                this.fc16 = toJSONObject().getString("fc16");
                this.fc16a = toJSONObject().getString("fc16a");
                this.fc16b = toJSONObject().getString("fc16b");
                this.fc16b96x = toJSONObject().getString("fc16b96x");
                this.fc1701 = toJSONObject().getString("fc1701");
                this.fc1702 = toJSONObject().getString("fc1702");
                this.fc18 = toJSONObject().getString("fc18");
                this.fc19 = toJSONObject().getString("fc19");
                this.fc20 = toJSONObject().getString("fc20");
                this.fc21 = toJSONObject().getString("fc21");
                this.fc22 = toJSONObject().getString("fc22");
                this.fc23 = toJSONObject().getString("fc23");
                this.fc24 = toJSONObject().getString("fc24");
                this.fc25 = toJSONObject().getString("fc25");
                this.fc2501 = toJSONObject().getString("fc2501");
                this.fc2502 = toJSONObject().getString("fc2502");
                this.fc2503 = toJSONObject().getString("fc2503");
                this.fc2504 = toJSONObject().getString("fc2504");
                this.fc2505 = toJSONObject().getString("fc2505");
                this.fc2506 = toJSONObject().getString("fc2506");
                this.fc26 = toJSONObject().getString("fc26");
                this.fc2601 = toJSONObject().getString("fc2601");
                this.fc2602 = toJSONObject().getString("fc2602");
                this.fc2603 = toJSONObject().getString("fc2603");
                this.fc2604 = toJSONObject().getString("fc2604");
                this.fc2605 = toJSONObject().getString("fc2605");
                this.fc2701 = toJSONObject().getString("fc2701");
                this.fc2702 = toJSONObject().getString("fc2702");
                this.fc2703 = toJSONObject().getString("fc2703");
                this.fc2704 = toJSONObject().getString("fc2704");
                this.fc28 = toJSONObject().getString("fc28");
                this.fc29 = toJSONObject().getString("fc29");
                this.fc2901 = toJSONObject().getString("fc2901");
                this.fc2902 = toJSONObject().getString("fc2902");
                this.fc2903 = toJSONObject().getString("fc2903");
                this.fc2904 = toJSONObject().getString("fc2904");
                this.fc2905 = toJSONObject().getString("fc2905");
                this.fc2996 = toJSONObject().getString("fc2996");
                this.fc2996x = toJSONObject().getString("fc2996x");
                this.fc30 = toJSONObject().getString("fc30");
                this.fc31 = toJSONObject().getString("fc31");
                this.fc3201 = toJSONObject().getString("fc3201");
                this.fc3202 = toJSONObject().getString("fc3202");
                this.fc3203 = toJSONObject().getString("fc3203");
                this.fc3301 = toJSONObject().getString("fc3301");
                this.fc3302 = toJSONObject().getString("fc3302");
                this.fc3303 = toJSONObject().getString("fc3303");
                this.fc34 = toJSONObject().getString("fc34");
                this.fc3501 = toJSONObject().getString("fc3501");
                this.fc3601 = toJSONObject().getString("fc3601");
                this.fc37 = toJSONObject().getString("fc37");
                this.fc3796x = toJSONObject().getString("fc3796x");
                this.fc38 = toJSONObject().getString("fc38");

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error at Hyderate", "cSHydrate: " + e.getMessage());
            }
        }
    }

    private void wSHydrate(String string) {
        if (string != null) {

            try {
                this.ws01 = toJSONObject().getString("ws01");
                this.ws01a = toJSONObject().getString("ws01a");
                this.ws01b = toJSONObject().getString("ws01b");
                this.ws02 = toJSONObject().getString("ws02");
                this.ws03 = toJSONObject().getString("ws03");
                this.ws04 = toJSONObject().getString("ws04");
                this.ws05 = toJSONObject().getString("ws05");
                this.ws05a = toJSONObject().getString("ws05a");
                this.ws06 = toJSONObject().getString("ws06");
                this.ws0696x = toJSONObject().getString("ws0696x");
                this.ws07 = toJSONObject().getString("ws07");
                this.ws08 = toJSONObject().getString("ws08");
                this.ws0801 = toJSONObject().getString("ws0801");
                this.ws0802 = toJSONObject().getString("ws0802");
                this.ws0803 = toJSONObject().getString("ws0803");
                this.ws09 = toJSONObject().getString("ws09");
                this.ws10 = toJSONObject().getString("ws10");
                this.ws11 = toJSONObject().getString("ws11");
                this.ws12 = toJSONObject().getString("ws12");
                this.ws12a = toJSONObject().getString("ws12a");
                this.ws13 = toJSONObject().getString("ws13");
                this.ws14 = toJSONObject().getString("ws14");
                this.ws15 = toJSONObject().getString("ws15");
                this.ws16 = toJSONObject().getString("ws16");
                this.ws17 = toJSONObject().getString("ws17");
                this.ws18 = toJSONObject().getString("ws18");
                this.ws19 = toJSONObject().getString("ws19");
                this.ws20 = toJSONObject().getString("ws20");
                this.ws2001 = toJSONObject().getString("ws2001");
                this.ws2002 = toJSONObject().getString("ws2002");
                this.ws2003 = toJSONObject().getString("ws2003");
                this.ws2004 = toJSONObject().getString("ws2004");
                this.ws2005 = toJSONObject().getString("ws2005");
                this.ws2006 = toJSONObject().getString("ws2006");
                this.ws21 = toJSONObject().getString("ws21");
                this.ws22 = toJSONObject().getString("ws22");
                this.ws2201 = toJSONObject().getString("ws2201");
                this.ws2202 = toJSONObject().getString("ws2202");
                this.ws2203 = toJSONObject().getString("ws2203");
                this.ws2296 = toJSONObject().getString("ws2296");
                this.ws2296x = toJSONObject().getString("ws2296x");
                this.ws2301 = toJSONObject().getString("ws2301");
                this.ws2302 = toJSONObject().getString("ws2302");
                this.ws2303 = toJSONObject().getString("ws2303");
                this.ws25a = toJSONObject().getString("ws25a");
                this.ws25b = toJSONObject().getString("ws25b");
                this.ws25c = toJSONObject().getString("ws25c");
                this.ws25d = toJSONObject().getString("ws25d");
                this.ws25e = toJSONObject().getString("ws25e");
                this.ws25f = toJSONObject().getString("ws25f");
                this.ws25g = toJSONObject().getString("ws25g");
                this.ws25h = toJSONObject().getString("ws25h");
                this.ws25i = toJSONObject().getString("ws25i");
                this.ws25j = toJSONObject().getString("ws25j");
                this.ws25k = toJSONObject().getString("ws25k");
                this.ws25k01x = toJSONObject().getString("ws25k01x");

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error at Hyderate", "cSHydrate: " + e.getMessage());
            }
        }
    }

    private void wSFPHydrate(String string) {
        if (string != null) {

            try {
                this.fw01 = toJSONObject().getString("fw01");
                this.fw01a = toJSONObject().getString("fw01a");
                this.fw01b = toJSONObject().getString("fw01b");
                this.fw02 = toJSONObject().getString("fw02");
                this.fw03 = toJSONObject().getString("fw03");
                this.fw04 = toJSONObject().getString("fw04");
                this.fw05 = toJSONObject().getString("fw05");
                this.fw05a = toJSONObject().getString("fw05a");
                this.fw06 = toJSONObject().getString("fw06");
                this.fw0696x = toJSONObject().getString("fw0696x");
                this.fw07 = toJSONObject().getString("fw07");
                this.fw08 = toJSONObject().getString("fw08");
                this.fw0801 = toJSONObject().getString("fw0801");
                this.fw0802 = toJSONObject().getString("fw0802");
                this.fw0803 = toJSONObject().getString("fw0803");
                this.fw09 = toJSONObject().getString("fw09");
                this.fw10 = toJSONObject().getString("fw10");
                this.fw11 = toJSONObject().getString("fw11");
                this.fw12 = toJSONObject().getString("fw12");
                this.fw12a = toJSONObject().getString("fw12a");
                this.fw12b = toJSONObject().getString("fw12b");
                this.fw12b96x = toJSONObject().getString("fw12b96x");
                this.fw13 = toJSONObject().getString("fw13");
                this.fw14 = toJSONObject().getString("fw14");
                this.fw15 = toJSONObject().getString("fw15");
                this.fw16 = toJSONObject().getString("fw16");
                this.fw17 = toJSONObject().getString("fw17");
                this.fw18 = toJSONObject().getString("fw18");
                this.fw1801 = toJSONObject().getString("fw1801");
                this.fw1802 = toJSONObject().getString("fw1802");
                this.fw1803 = toJSONObject().getString("fw1803");
                this.fw1804 = toJSONObject().getString("fw1804");
                this.fw1805 = toJSONObject().getString("fw1805");
                this.fw1806 = toJSONObject().getString("fw1806");
                this.fw19 = toJSONObject().getString("fw19");
                this.fw2001 = toJSONObject().getString("fw2001");
                this.fw21 = toJSONObject().getString("fw21");
                this.fw22 = toJSONObject().getString("fw22");
                this.fw2301 = toJSONObject().getString("fw2301");
                this.fw24 = toJSONObject().getString("fw24");
                this.fw25 = toJSONObject().getString("fw25");
                this.fw26 = toJSONObject().getString("fw26");
                this.fw27 = toJSONObject().getString("fw27");
                this.fw28 = toJSONObject().getString("fw28");
                this.fw2801 = toJSONObject().getString("fw2801");
                this.fw2802 = toJSONObject().getString("fw2802");
                this.fw2803 = toJSONObject().getString("fw2803");
                this.fw2804 = toJSONObject().getString("fw2804");
                this.fw2896 = toJSONObject().getString("fw2896");
                this.fw2896x = toJSONObject().getString("fw2896x");
                this.fw29a = toJSONObject().getString("fw29a");
                this.fw29b = toJSONObject().getString("fw29b");
                this.fw29c = toJSONObject().getString("fw29c");
                this.fw29d = toJSONObject().getString("fw29d");
                this.fw29e = toJSONObject().getString("fw29e");
                this.fw29f = toJSONObject().getString("fw29f");
                this.fw29g = toJSONObject().getString("fw29g");
                this.fw29h = toJSONObject().getString("fw29h");
                this.fw29i = toJSONObject().getString("fw29i");
                this.fw29j = toJSONObject().getString("fw29j");
                this.fw29k = toJSONObject().getString("fw29k");
                this.fw29k96x = toJSONObject().getString("fw29k96x");

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Error at Hyderate", "cSHydrate: " + e.getMessage());
            }
        }
    }
}
