package edu.aku.hassannaqvi.casi_register.models;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.casi_register.contracts.ZStandardContract;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class ZStandard {

    private static final String TAG = "ZStandard_CONTRACT";
    String sex;
    String age;
    String measure;
    String l;
    String m;
    String s;
    String cat;
//    String REGION_DSS;

    public ZStandard() {
        // Default Constructor
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }


    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }


    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }


    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }


    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }


    public ZStandard Sync(JSONObject jsonObject) throws JSONException {
        this.sex = jsonObject.getString(ZStandardContract.Table.COLUMN_SEX);
        this.age = jsonObject.getString(ZStandardContract.Table.COLUMN_AGE);
        this.measure = jsonObject.getString(ZStandardContract.Table.COLUMN_MEASURE);
        this.l = jsonObject.getString(ZStandardContract.Table.COLUMN_L);
        this.m = jsonObject.getString(ZStandardContract.Table.COLUMN_M);
        this.s = jsonObject.getString(ZStandardContract.Table.COLUMN_S);
        this.cat = jsonObject.getString(ZStandardContract.Table.COLUMN_CAT);
//        this.REGION_DSS = jsonObject.getString(singleUser.REGION_DSS);
        return this;

    }

    public ZStandard Hydrate(Cursor cursor) {
        this.sex = cursor.getString(cursor.getColumnIndex(ZStandardContract.Table.COLUMN_SEX));
        this.age = cursor.getString(cursor.getColumnIndex(ZStandardContract.Table.COLUMN_AGE));
        this.measure = cursor.getString(cursor.getColumnIndex(ZStandardContract.Table.COLUMN_MEASURE));
        this.l = cursor.getString(cursor.getColumnIndex(ZStandardContract.Table.COLUMN_L));
        this.m = cursor.getString(cursor.getColumnIndex(ZStandardContract.Table.COLUMN_M));
        this.s = cursor.getString(cursor.getColumnIndex(ZStandardContract.Table.COLUMN_S));
        this.cat = cursor.getString(cursor.getColumnIndex(ZStandardContract.Table.COLUMN_CAT));
        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(ZStandardContract.Table.COLUMN_SEX, this.sex == null ? JSONObject.NULL : this.sex);
        json.put(ZStandardContract.Table.COLUMN_AGE, this.age == null ? JSONObject.NULL : this.age);
        json.put(ZStandardContract.Table.COLUMN_MEASURE, this.measure == null ? JSONObject.NULL : this.measure);
        json.put(ZStandardContract.Table.COLUMN_L, this.l == null ? JSONObject.NULL : this.l);
        json.put(ZStandardContract.Table.COLUMN_M, this.m == null ? JSONObject.NULL : this.m);
        json.put(ZStandardContract.Table.COLUMN_S, this.s == null ? JSONObject.NULL : this.s);
        json.put(ZStandardContract.Table.COLUMN_CAT, this.cat == null ? JSONObject.NULL : this.cat);
        return json;
    }

}