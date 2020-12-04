package edu.aku.hassannaqvi.casi_register.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class DistrictContract {

    private static final String TAG = "DISTRICT_CONTRACT";
    String district_code;
    String district_name;
    String district_type;

    public DistrictContract() {
        // Default Constructor
    }

    public DistrictContract Sync(JSONObject jsonObject) throws JSONException {
        this.district_code = jsonObject.getString(DISTRICTSTable.COLUMN_DISTRICT_CODE);
        this.district_name = jsonObject.getString(DISTRICTSTable.COLUMN_DISTRICT_NAME);
        this.district_type = jsonObject.getString(DISTRICTSTable.COLUMN_DISTRICT_TYPE);
        return this;
    }

    public DistrictContract Hydrate(Cursor cursor) {
        this.district_code = cursor.getString(cursor.getColumnIndex(DISTRICTSTable.COLUMN_DISTRICT_CODE));
        this.district_name = cursor.getString(cursor.getColumnIndex(DISTRICTSTable.COLUMN_DISTRICT_NAME));
        this.district_type = cursor.getString(cursor.getColumnIndex(DISTRICTSTable.COLUMN_DISTRICT_TYPE));
        return this;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getDistrict_type() {
        return district_type;
    }

    public void setDistrict_type(String district_type) {
        this.district_type = district_type;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(DISTRICTSTable.COLUMN_DISTRICT_CODE, this.district_code == null ? JSONObject.NULL : this.district_code);
        json.put(DISTRICTSTable.COLUMN_DISTRICT_NAME, this.district_name == null ? JSONObject.NULL : this.district_name);
        json.put(DISTRICTSTable.COLUMN_DISTRICT_TYPE, this.district_type == null ? JSONObject.NULL : this.district_type);
        return json;
    }


    public static abstract class DISTRICTSTable implements BaseColumns {

        public static final String TABLE_NAME = "districts";
        public static final String COLUMN_DISTRICT_CODE = "district_code";
        public static final String COLUMN_DISTRICT_NAME = "district_name";
        public static final String COLUMN_DISTRICT_TYPE = "district_type";
    }
}