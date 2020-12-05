package edu.aku.hassannaqvi.casi_register.models;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.casi_register.contracts.VillagesContract;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */


public class Villages {

    private static final String TAG = "Villages_CONTRACT";
    String country;
    String district;
    String uc;
    String village;
    String country_code;
    String district_code;
    String uc_code;
    String villlage_code;
    String cluster_no;


    public Villages() {
        // Default Constructor
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }


    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }


    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }


    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }


    public String getUc_code() {
        return uc_code;
    }

    public void setUc_code(String uc_code) {
        this.uc_code = uc_code;
    }


    public String getVilllage_code() {
        return villlage_code;
    }

    public void setVilllage_code(String villlage_code) {
        this.villlage_code = villlage_code;
    }


    public String getCluster_no() {
        return cluster_no;
    }

    public void setCluster_no(String cluster_no) {
        this.cluster_no = cluster_no;
    }


    public Villages Sync(JSONObject jsonObject) throws JSONException {
        this.country = jsonObject.getString(VillagesContract.Table.COLUMN_COUNTRY);
        this.district = jsonObject.getString(VillagesContract.Table.COLUMN_DISTRICT);
        this.uc = jsonObject.getString(VillagesContract.Table.COLUMN_UC);
        this.village = jsonObject.getString(VillagesContract.Table.COLUMN_VILLAGE);
        this.country_code = jsonObject.getString(VillagesContract.Table.COLUMN_COUNTRY_CODE);
        this.district_code = jsonObject.getString(VillagesContract.Table.COLUMN_DISTRICT_CODE);
        this.uc_code = jsonObject.getString(VillagesContract.Table.COLUMN_UC_CODE);
        this.villlage_code = jsonObject.getString(VillagesContract.Table.COLUMN_VILLLAGE_CODE);
        this.cluster_no = jsonObject.getString(VillagesContract.Table.COLUMN_CLUSTER_NO);
        return this;

    }

    public Villages Hydrate(Cursor cursor) {
        this.country = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_COUNTRY));
        this.district = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_DISTRICT));
        this.uc = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_UC));
        this.village = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_VILLAGE));
        this.country_code = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_COUNTRY_CODE));
        this.district_code = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_DISTRICT_CODE));
        this.uc_code = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_UC_CODE));
        this.villlage_code = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_VILLLAGE_CODE));
        this.cluster_no = cursor.getString(cursor.getColumnIndex(VillagesContract.Table.COLUMN_CLUSTER_NO));
        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(VillagesContract.Table.COLUMN_COUNTRY, this.country == null ? JSONObject.NULL : this.country);
        json.put(VillagesContract.Table.COLUMN_DISTRICT, this.district == null ? JSONObject.NULL : this.district);
        json.put(VillagesContract.Table.COLUMN_UC, this.uc == null ? JSONObject.NULL : this.uc);
        json.put(VillagesContract.Table.COLUMN_VILLAGE, this.village == null ? JSONObject.NULL : this.village);
        json.put(VillagesContract.Table.COLUMN_COUNTRY_CODE, this.country_code == null ? JSONObject.NULL : this.country_code);
        json.put(VillagesContract.Table.COLUMN_DISTRICT_CODE, this.district_code == null ? JSONObject.NULL : this.district_code);
        json.put(VillagesContract.Table.COLUMN_UC_CODE, this.uc_code == null ? JSONObject.NULL : this.uc_code);
        json.put(VillagesContract.Table.COLUMN_VILLLAGE_CODE, this.villlage_code == null ? JSONObject.NULL : this.villlage_code);
        json.put(VillagesContract.Table.COLUMN_CLUSTER_NO, this.cluster_no == null ? JSONObject.NULL : this.cluster_no);
        return json;
    }

}