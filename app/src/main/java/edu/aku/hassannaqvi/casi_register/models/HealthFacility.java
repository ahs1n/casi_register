package edu.aku.hassannaqvi.casi_register.models;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.casi_register.contracts.HFContract;

/**
 * @author abdul.sajid.
 */

public class HealthFacility {

    private static final String TAG = "HFCONTRACT";
    String countryCode;
    String countryName;
    String regionCode;
    String region;
    String districtCode;
    String district;
    String ucCode;
    String uc;
    String villageCode;
    String village;


    public HealthFacility() {
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public String getUcCode() {
        return ucCode;
    }

    public void setUcCode(String ucCode) {
        this.ucCode = ucCode;
    }


    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }


    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }


    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }


    public HealthFacility Sync(JSONObject jsonObject) throws JSONException {
        this.countryCode = jsonObject.getString(HFContract.HFTable.COLUMN_COUNTRY_CODE);
        this.countryName = jsonObject.getString(HFContract.HFTable.COLUMN_COUNTRY_NAME);
        this.regionCode = jsonObject.getString(HFContract.HFTable.COLUMN_REGION_CODE);
        this.region = jsonObject.getString(HFContract.HFTable.COLUMN_REGION);
        this.districtCode = jsonObject.getString(HFContract.HFTable.COLUMN_DISTRICT_CODE);
        this.district = jsonObject.getString(HFContract.HFTable.COLUMN_DISTRICT);
        this.ucCode = jsonObject.getString(HFContract.HFTable.COLUMN_UC_CODE);
        this.uc = jsonObject.getString(HFContract.HFTable.COLUMN_UC);
        this.villageCode = jsonObject.getString(HFContract.HFTable.COLUMN_VILLAGE_CODE);
        this.village = jsonObject.getString(HFContract.HFTable.COLUMN_VILLAGE);
        return this;

    }


    public HealthFacility Hydrate(Cursor cursor) {
        this.countryCode = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_COUNTRY_CODE));
        this.countryName = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_COUNTRY_NAME));
        this.regionCode = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_REGION_CODE));
        this.region = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_REGION));
        this.districtCode = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_DISTRICT_CODE));
        this.district = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_DISTRICT));
        this.ucCode = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_UC_CODE));
        this.uc = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_UC));
        this.villageCode = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_VILLAGE_CODE));
        this.village = cursor.getString(cursor.getColumnIndex(HFContract.HFTable.COLUMN_VILLAGE));
        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(HFContract.HFTable.COLUMN_COUNTRY_CODE, this.countryCode == null ? JSONObject.NULL : this.countryCode);
        json.put(HFContract.HFTable.COLUMN_COUNTRY_NAME, this.countryName == null ? JSONObject.NULL : this.countryName);
        json.put(HFContract.HFTable.COLUMN_REGION_CODE, this.regionCode == null ? JSONObject.NULL : this.regionCode);
        json.put(HFContract.HFTable.COLUMN_REGION, this.region == null ? JSONObject.NULL : this.region);
        json.put(HFContract.HFTable.COLUMN_DISTRICT_CODE, this.districtCode == null ? JSONObject.NULL : this.districtCode);
        json.put(HFContract.HFTable.COLUMN_DISTRICT, this.district == null ? JSONObject.NULL : this.district);
        json.put(HFContract.HFTable.COLUMN_UC_CODE, this.ucCode == null ? JSONObject.NULL : this.ucCode);
        json.put(HFContract.HFTable.COLUMN_UC, this.uc == null ? JSONObject.NULL : this.uc);
        json.put(HFContract.HFTable.COLUMN_VILLAGE_CODE, this.villageCode == null ? JSONObject.NULL : this.villageCode);
        json.put(HFContract.HFTable.COLUMN_VILLAGE, this.village == null ? JSONObject.NULL : this.village);
        return json;
    }

}