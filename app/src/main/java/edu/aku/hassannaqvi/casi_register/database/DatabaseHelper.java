package edu.aku.hassannaqvi.casi_register.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.casi_register.CONSTANTS;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.casi_register.contracts.ZStandardContract;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup;
import edu.aku.hassannaqvi.casi_register.models.ChildFollowup.ChildTable;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.FormIndicatorsModel;
import edu.aku.hassannaqvi.casi_register.models.HealthFacility;
import edu.aku.hassannaqvi.casi_register.models.Identification;
import edu.aku.hassannaqvi.casi_register.models.Users;
import edu.aku.hassannaqvi.casi_register.models.Users.UsersTable;
import edu.aku.hassannaqvi.casi_register.models.VersionApp;
import edu.aku.hassannaqvi.casi_register.models.VersionApp.VersionAppTable;
import edu.aku.hassannaqvi.casi_register.models.Villages;
import edu.aku.hassannaqvi.casi_register.models.Villages.VillagesTable;
import edu.aku.hassannaqvi.casi_register.models.WraFollowup;
import edu.aku.hassannaqvi.casi_register.models.WraFollowup.WraTable;
import edu.aku.hassannaqvi.casi_register.models.ZStandard;
import edu.aku.hassannaqvi.casi_register.utils.DateUtilsKt;

import static edu.aku.hassannaqvi.casi_register.contracts.ZStandardContract.ZScoreTable;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.DATABASE_NAME;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_CHILD_FOLLOW_UP_LIST;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_FORMS;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_HEALTHFACILITY;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_HEALTHFACILITY01;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_HEALTHFACILITY02;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_VERSIONAPP;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_VILLAGES;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_WRA_FOLLOW_UP_LIST;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_ALTER_WRA_FOLLOW_UP_LIST02;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_ZSTANDARD;


public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TAG = DatabaseHelper.class.getName();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_VILLAGES);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_ZSTANDARD);
        db.execSQL(SQL_CREATE_HEALTHFACILITY);
        db.execSQL(SQL_CREATE_CHILD_FOLLOW_UP_LIST);
        db.execSQL(SQL_CREATE_WRA_FOLLOW_UP_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(SQL_CREATE_HEALTHFACILITY01);
                db.execSQL(SQL_CREATE_HEALTHFACILITY02);
            case 2:
                db.execSQL(SQL_ALTER_WRA_FOLLOW_UP_LIST02);
        }
    }


    //Sync functions
    public int syncUser(JSONArray userList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < userList.length(); i++) {

                JSONObject jsonObjectUser = userList.getJSONObject(i);

                Users user = new Users();
                user.sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersTable.COLUMN_USERNAME, user.getUserName());
                values.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
                values.put(UsersTable.COLUMN_FULLNAME, user.getFullname());
                values.put(UsersTable.COLUMN_COUNTRY_CODE, user.getCountry_code());
                long rowID = db.insert(UsersTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public int syncVersionApp(JSONObject VersionList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VersionAppTable.TABLE_NAME, null, null);
        long count = 0;
        try {
            JSONObject jsonObjectCC = ((JSONArray) VersionList.get(VersionAppTable.COLUMN_VERSION_PATH)).getJSONObject(0);
            VersionApp Vc = new VersionApp();
            Vc.sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionAppTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionAppTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionAppTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            count = db.insert(VersionAppTable.TABLE_NAME, null, values);
            if (count > 0) count = 1;

        } catch (Exception ignored) {
        } finally {
            db.close();
        }

        return (int) count;
    }

    public int syncVillages(JSONArray vilList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VillagesTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < vilList.length(); i++) {

                JSONObject jsonObjectzs = vilList.getJSONObject(i);

                Villages villages = new Villages();
                villages.sync(jsonObjectzs);
                ContentValues values = new ContentValues();

                values.put(VillagesTable.COLUMN_REGION, villages.getRegion());
                values.put(VillagesTable.COLUMN_DISTRICT, villages.getDistrict());
                values.put(VillagesTable.COLUMN_UC, villages.getUc());
                values.put(VillagesTable.COLUMN_VILLAGE, villages.getVillage());
                values.put(VillagesTable.COLUMN_COUNTRY_CODE, villages.getCountry_code());
                values.put(VillagesTable.COLUMN_COUNTRY, villages.getCountry());
                values.put(VillagesTable.COLUMN_DISTRICT_CODE, villages.getDistrict_code());
                values.put(VillagesTable.COLUMN_UC_CODE, villages.getUc_code());
                values.put(VillagesTable.COLUMN_VILLLAGE_CODE, villages.getVillage_code());
                values.put(VillagesTable.COLUMN_REGION_CODE, villages.getRegion_code());
                long rowID = db.insert(VillagesTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncVillages(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public int syncZStandard(JSONArray zsList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ZScoreTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < zsList.length(); i++) {

                JSONObject jsonObjectzs = zsList.getJSONObject(i);

                ZStandard Zstandard = new ZStandard();
                Zstandard.Sync(jsonObjectzs);
                ContentValues values = new ContentValues();

                values.put(ZStandardContract.ZScoreTable.COLUMN_SEX, Zstandard.getSex());
                values.put(ZStandardContract.ZScoreTable.COLUMN_AGE, Zstandard.getAge());
                values.put(ZStandardContract.ZScoreTable.COLUMN_MEASURE, Zstandard.getMeasure());
                values.put(ZStandardContract.ZScoreTable.COLUMN_L, Zstandard.getL());
                values.put(ZStandardContract.ZScoreTable.COLUMN_M, Zstandard.getM());
                values.put(ZStandardContract.ZScoreTable.COLUMN_S, Zstandard.getS());
                values.put(ZStandardContract.ZScoreTable.COLUMN_CAT, Zstandard.getCat());
                long rowID = db.insert(ZScoreTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncZStandard(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public int syncHF(JSONArray hfList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HealthFacility.HealthFacilityTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < hfList.length(); i++) {

                JSONObject jsonObjectzs = hfList.getJSONObject(i);

                HealthFacility facility = new HealthFacility();
                facility.sync(jsonObjectzs);
                ContentValues values = new ContentValues();

                values.put(HealthFacility.HealthFacilityTable.COLUMN_COUNTRY_CODE, facility.getCountry_code());
                values.put(HealthFacility.HealthFacilityTable.COLUMN_REGION_CODE, facility.getRegion_code());
                values.put(HealthFacility.HealthFacilityTable.COLUMN_HF_CODE, facility.getHf_code());
                values.put(HealthFacility.HealthFacilityTable.COLUMN_HEALTH_FACILITY, facility.getHealth_facility());
                values.put(HealthFacility.HealthFacilityTable.COLUMN_FACILITY_TYPE, facility.getFacility_type());
                values.put(HealthFacility.HealthFacilityTable.COLUMN_DIST_CODE, facility.getDist_code());
                values.put(HealthFacility.HealthFacilityTable.COLUMN_UC_CODE, facility.getUc_code());
                long rowID = db.insert(HealthFacility.HealthFacilityTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncHF(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public int syncChildFollowups(JSONArray chList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ChildFollowup.ChildTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < chList.length(); i++) {

                ChildFollowup chFollowup = new ChildFollowup();
                chFollowup.sync(chList.getJSONObject(i));
                ContentValues values = new ContentValues();

                values.put(ChildFollowup.ChildTable.COLUMN_LUID, chFollowup.getLUID());
                values.put(ChildFollowup.ChildTable.COLUMN_CS01, chFollowup.getCs01());
                values.put(ChildFollowup.ChildTable.COLUMN_CS01A, chFollowup.getCs01a());
                values.put(ChildFollowup.ChildTable.COLUMN_CS01B, chFollowup.getCs01b());
                values.put(ChildFollowup.ChildTable.COLUMN_CS09, chFollowup.getCs09());
                values.put(ChildFollowup.ChildTable.COLUMN_CS04, chFollowup.getCs04());
                values.put(ChildFollowup.ChildTable.COLUMN_CS05, chFollowup.getCs05());
                values.put(ChildFollowup.ChildTable.COLUMN_CS08, chFollowup.getCs08());
                values.put(ChildFollowup.ChildTable.COLUMN_CS10, chFollowup.getCs10());
                values.put(ChildFollowup.ChildTable.COLUMN_CS10A, chFollowup.getCs10a());
                values.put(ChildFollowup.ChildTable.COLUMN_CS11, chFollowup.getCs11());
                values.put(ChildFollowup.ChildTable.COLUMN_CS11A, chFollowup.getCs11a());
                values.put(ChildFollowup.ChildTable.COLUMN_CS12, chFollowup.getCs12());
                values.put(ChildFollowup.ChildTable.COLUMN_CS13, chFollowup.getCs13());
                values.put(ChildFollowup.ChildTable.COLUMN_FUPDT, chFollowup.getFupDt());
                values.put(ChildFollowup.ChildTable.COLUMN_FUPNO, chFollowup.getFupNo());
                values.put(ChildTable.COLUMN_DOB, chFollowup.getDob());
                long rowID = db.insert(ChildFollowup.ChildTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncHF(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public int wraFollowups(JSONArray wraList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WraTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < wraList.length(); i++) {

                WraFollowup wraFollowup = new WraFollowup();
                wraFollowup.sync(wraList.getJSONObject(i));
                ContentValues values = new ContentValues();

                values.put(WraTable.COLUMN_LUID, wraFollowup.getLUID());
                values.put(WraTable.COLUMN_WS01, wraFollowup.getWs01());
                values.put(WraTable.COLUMN_WS01A, wraFollowup.getWs01a());
                values.put(WraTable.COLUMN_WS01B, wraFollowup.getWs01b());
                values.put(WraTable.COLUMN_WS09, wraFollowup.getWs09());
                values.put(WraTable.COLUMN_WS04, wraFollowup.getWs04());
                values.put(WraTable.COLUMN_WS05, wraFollowup.getWs05());
                values.put(WraTable.COLUMN_WS08, wraFollowup.getWs08());
                values.put(WraTable.COLUMN_WS10, wraFollowup.getWs10());
                values.put(WraTable.COLUMN_WS11, wraFollowup.getWs11());
                values.put(WraTable.COLUMN_WS12, wraFollowup.getWs12());
                values.put(WraTable.COLUMN_WS13, wraFollowup.getWs13());
                values.put(WraTable.COLUMN_FUPDT, wraFollowup.getFupDt());
                values.put(WraTable.COLUMN_FUPNO, wraFollowup.getFupNo());
                long rowID = db.insert(WraTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncHF(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }


    //Add Functions
    public Long addForm(Form form) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECT_NAME, form.getProjectName());
        values.put(FormsTable.COLUMN_UID, form.get_UID());
        values.put(FormsTable.COLUMN_USERNAME, form.getUsername());
        values.put(FormsTable.COLUMN_SYSDATE, form.getSysdate());
        values.put(FormsTable.COLUMN_COUNTRY_CODE, form.getCountryCode());
        values.put(FormsTable.COLUMN_REG_NO, form.getReg_no());
        values.put(FormsTable.COLUMN_DISTRICT_CODE, form.getDistrictCode());
        values.put(FormsTable.COLUMN_DISTRICT, form.getDistrict());
        values.put(FormsTable.COLUMN_UC_CODE, form.getUcCode());
        values.put(FormsTable.COLUMN_UC, form.getUc());
        values.put(FormsTable.COLUMN_VILLAGE_CODE, form.getVillageCode());
        values.put(FormsTable.COLUMN_VILLAGE, form.getVillage());

        values.put(FormsTable.COLUMN_ISTATUS, form.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS96x, form.getIstatus96x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, form.getEndingdatetime());
        values.put(FormsTable.COLUMN_GPSLAT, form.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, form.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDATE, form.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, form.getGpsAcc());
        values.put(FormsTable.COLUMN_DEVICETAGID, form.getDevicetagID());
        values.put(FormsTable.COLUMN_DEVICEID, form.getDeviceID());
        values.put(FormsTable.COLUMN_APPVERSION, form.getAppversion());
        values.put(FormsTable.COLUMN_FORM_TYPE, form.getFormType());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public VersionApp getVersionApp() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;

        VersionApp allVC = null;
        try {
            c = db.query(
                    VersionAppTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allVC = new VersionApp().hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allVC;
    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.form.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Collection<Form> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_REG_NO,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_CS,
                FormsTable.COLUMN_CSFP,
                FormsTable.COLUMN_WS,
                FormsTable.COLUMN_WSFP,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_FORM_TYPE

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<Form>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                allForms.add(form.Hydrate(c, null));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Form> checkFormExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_REG_NO,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_CS,
                FormsTable.COLUMN_CSFP,
                FormsTable.COLUMN_WS,
                FormsTable.COLUMN_WSFP,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_FORM_TYPE

        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<Form>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                allForms.add(form.Hydrate(c, null));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Form> getTodayForms(String sysdate) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_REG_NO,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_CS,
                FormsTable.COLUMN_CSFP,
                FormsTable.COLUMN_WS,
                FormsTable.COLUMN_WSFP,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_FORM_TYPE

        };
        String whereClause = FormsTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setUsername(c.getString(c.getColumnIndex(FormsTable.COLUMN_USERNAME)));
                form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setCountryCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_COUNTRY_CODE)));
                form.setReg_no(c.getString(c.getColumnIndex(FormsTable.COLUMN_REG_NO)));
                form.setDistrictCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT_CODE)));
                form.setDistrict(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT)));
                form.setUcCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC_CODE)));
                form.setUc(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC)));
                form.setVillageCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE_CODE)));
                form.setVillage(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE)));
                form.setcS(c.getString(c.getColumnIndex(FormsTable.COLUMN_CS)));
                form.setcSFP(c.getString(c.getColumnIndex(FormsTable.COLUMN_CSFP)));
                form.setwS(c.getString(c.getColumnIndex(FormsTable.COLUMN_WS)));
                form.setwSFP(c.getString(c.getColumnIndex(FormsTable.COLUMN_WSFP)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public Collection<Form> getFormsByCluster(String cluster) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_REG_NO,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_CS,
                FormsTable.COLUMN_CSFP,
                FormsTable.COLUMN_WS,
                FormsTable.COLUMN_WSFP,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_FORM_TYPE

        };
        String whereClause = FormsTable.COLUMN_CS + " = ? ";
        String[] whereArgs = new String[]{cluster};
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable.COLUMN_ID + " ASC";

        Collection<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form form = new Form();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setCountryCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_COUNTRY_CODE)));
                form.setReg_no(c.getString(c.getColumnIndex(FormsTable.COLUMN_REG_NO)));
                form.setDistrictCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT_CODE)));
                form.setDistrict(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT)));
                form.setUcCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC_CODE)));
                form.setUc(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC)));
                form.setVillageCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE_CODE)));
                form.setVillage(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE)));
                form.setcS(c.getString(c.getColumnIndex(FormsTable.COLUMN_CS)));
                form.setcSFP(c.getString(c.getColumnIndex(FormsTable.COLUMN_CSFP)));
                form.setwS(c.getString(c.getColumnIndex(FormsTable.COLUMN_WS)));
                form.setwSFP(c.getString(c.getColumnIndex(FormsTable.COLUMN_WSFP)));
                form.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                form.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allForms.add(form);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.form.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS96x, MainApp.form.getIstatus96x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, MainApp.form.getEndingdatetime());

        // Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    /*
     * Get UC, DISTRICT, ENUMBLOCK and COUNTRY
     * */

    public List<Villages> getCountry(String country_id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;
        String whereClause = VillagesTable.COLUMN_COUNTRY_CODE + "=?";
        String[] whereArgs = new String[]{country_id};
        String groupBy = null;
        String having = null;

        String orderBy = VillagesTable.COLUMN_REGION + " ASC";
        List<Villages> allEB = new ArrayList<>();
        try {
            c = db.query(
                    VillagesTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new Villages().hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allEB;
    }

    public List<HealthFacility> getFacility(String country, String region, String district, String uc) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;
        String whereClause;
        String[] whereArgs;
        if (country.equals("3")) {
            whereClause = HealthFacility.HealthFacilityTable.COLUMN_COUNTRY_CODE + "=? AND " +
                    HealthFacility.HealthFacilityTable.COLUMN_REGION_CODE + "=? AND " +
                    HealthFacility.HealthFacilityTable.COLUMN_DIST_CODE + "=? AND " +
                    HealthFacility.HealthFacilityTable.COLUMN_UC_CODE + "=?";
            whereArgs = new String[]{country, region, district, uc};
        } else {
            whereClause = HealthFacility.HealthFacilityTable.COLUMN_COUNTRY_CODE + "=? AND " +
                    HealthFacility.HealthFacilityTable.COLUMN_REGION_CODE + "=? ";
            whereArgs = new String[]{country, region};
        }
        String groupBy = null;
        String having = null;

        String orderBy = HealthFacility.HealthFacilityTable.COLUMN_HEALTH_FACILITY + " ASC";
        List<HealthFacility> allEB = new ArrayList<>();
        try {
            c = db.query(
                    HealthFacility.HealthFacilityTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new HealthFacility().hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allEB;
    }


    //Synced functions
    public JSONArray getUnsyncedForms(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;

        String whereClause = FormsTable.COLUMN_FORM_TYPE + " =? AND " + FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " = ''";
        String[] whereArgs = {type};

        String groupBy = null;
        String having = null;
        String orderBy = FormsTable.COLUMN_ID + " ASC";

        JSONArray allForms = new JSONArray();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allForms.put(new Form().Hydrate(c, type).toJSONObject(type));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }


    //Get Form already exist
    public Form getFilledForm(String district, String refno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_REG_NO,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_CS,
                FormsTable.COLUMN_CSFP,
                FormsTable.COLUMN_WS,
                FormsTable.COLUMN_WSFP,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96x,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION
        };

//        String whereClause = "(" + FormsTable.COLUMN_ISTATUS + " is null OR " + FormsTable.COLUMN_ISTATUS + "='') AND " + FormsTable.COLUMN_CLUSTERCODE + "=? AND " + FormsTable.COLUMN_HHNO + "=?";
        String whereClause = FormsTable.COLUMN_UC + "=? AND " + FormsTable.COLUMN_VILLAGE + "=?";
        String[] whereArgs = {district, refno};
        String groupBy = null;
        String having = null;
        String orderBy = FormsTable._ID + " ASC";
        Form allForms = null;
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allForms = new Form().Hydrate(c, null);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }


    //Generic update FormColumn
    public int updatesFormColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    //Generic update FormColumn
    public int updatesFormsColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.form.get_ID())};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    //Get FamilyMembers data for info activity
    public Form getSelectedForm(String cluster, String subcluster, String hhno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_REG_NO,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_CS,
                FormsTable.COLUMN_CSFP,
                FormsTable.COLUMN_WS,
                FormsTable.COLUMN_WSFP,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96x,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_FORM_TYPE
        };

        String whereClause = FormsTable.COLUMN_UC + "=? AND " + FormsTable.COLUMN_VILLAGE + "=? AND " + FormsTable.COLUMN_DISTRICT + "=? AND " + FormsTable.COLUMN_ISTATUS + "=? ";
        String[] whereArgs = new String[]{cluster, subcluster, hhno, "1"};

        String groupBy = null;
        String having = null;
        String orderBy = FormsTable.COLUMN_ID + " ASC";

        Form allForms = null;
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allForms = new Form().Hydrate(c, null);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }


    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }


    //Generic Un-Synced Forms
    public void updateSyncedWScreening(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

        // Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedCScreening(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

        // Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedWFollowup(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

        // Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedCFollowup(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

        // Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public List<String> getLMS(int age, int gender, String catA, String catB) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT l,m,s " +
                        "FROM " + ZScoreTable.TABLE_NAME + " " +
                        "WHERE " + ZScoreTable.COLUMN_AGE + "=? " +
                        "AND "
                        + ZScoreTable.COLUMN_SEX + "=?" +
                        "AND "
                        + ZScoreTable.COLUMN_CAT + " IN (?,?)"
                ,
                new String[]{String.valueOf(age), String.valueOf(gender), catA, catB});
        List<String> lms = null;
        while (c.moveToNext()) {
            lms = new ArrayList<>();
            lms.add(c.getString(c.getColumnIndex(ZScoreTable.COLUMN_L)));
            lms.add(c.getString(c.getColumnIndex(ZScoreTable.COLUMN_M)));
            lms.add(c.getString(c.getColumnIndex(ZScoreTable.COLUMN_S)));

        }
        return lms;
    }

    public List<String> getWHLMS(Double height, int gender, String catA) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT l,m,s " +
                        "FROM " + ZScoreTable.TABLE_NAME +
                        " WHERE " + ZScoreTable.COLUMN_MEASURE + "=?" +
                        " AND " + ZScoreTable.COLUMN_SEX + "=?" +
                        " AND " + ZScoreTable.COLUMN_CAT + "=?"
                ,
                new String[]{String.valueOf(height), String.valueOf(gender), catA});
        List<String> whlms = new ArrayList<>();
        Log.d(TAG, "getWHLMS: height " + height);
        Log.d(TAG, "getWHLMS: " + c.getCount());
        while (c.moveToNext()) {
            whlms = new ArrayList<>();
            whlms.add(c.getString(c.getColumnIndex(ZScoreTable.COLUMN_L)));
            whlms.add(c.getString(c.getColumnIndex(ZScoreTable.COLUMN_M)));
            whlms.add(c.getString(c.getColumnIndex(ZScoreTable.COLUMN_S)));

        }
        c.close();
        return whlms;
    }


    /*
     * Ali generated functions
     * */
    public Users getLoginUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;
        String whereClause = UsersTable.COLUMN_USERNAME + "=? AND " + UsersTable.COLUMN_PASSWORD + "=?";
        String[] whereArgs = {username, password};
        String groupBy = null;
        String having = null;
        String orderBy = UsersTable.COLUMN_ID + " ASC";

        Users allForms = null;
        try {
            c = db.query(
                    UsersTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allForms = new Users().hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public ArrayList<Form> getFormsByDate(String sysdate) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS96x,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_FORM_TYPE

        };
        String whereClause = FormsTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
        String groupBy = null;
        String having = null;
        String orderBy = FormsTable.COLUMN_ID + " ASC";
        ArrayList<Form> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Form forms = new Form();
                forms.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                forms.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                forms.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                forms.setUsername(c.getString(c.getColumnIndex(FormsTable.COLUMN_USERNAME)));
                allForms.add(forms);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }

    public FormIndicatorsModel getFormStatusCount(String sysdate) {
        SQLiteDatabase db = this.getReadableDatabase();
        FormIndicatorsModel count = new FormIndicatorsModel();
        Cursor mCursor = db.rawQuery(
                String.format("select " +
                        "sum(case when %s = 1 then 1 else 0 end) as completed," +
                        "sum(case when %s != 1 OR %s is null then 1 else 0 end) as notCompleted " +
                        "from %s WHERE %s Like ?", FormsTable.COLUMN_ISTATUS, FormsTable.COLUMN_ISTATUS, FormsTable.COLUMN_ISTATUS, FormsTable.TABLE_NAME, FormsTable.COLUMN_SYSDATE),
                new String[]{"%" + sysdate + " %"}, null);
        if (mCursor != null && mCursor.moveToFirst()) {
            count = count.copy(Integer.parseInt(mCursor.getString(0)),
                    Integer.parseInt(mCursor.getString(1)));
            mCursor.close();
        }
        return count;
    }

    public FormIndicatorsModel getUploadStatusCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        FormIndicatorsModel count = new FormIndicatorsModel();
        Cursor mCursor = db.rawQuery(
                String.format("select " +
                        "sum(case when %s = 1 then 1 else 0 end) as completed," +
                        "sum(case when %s is null OR %s = '' then 1 else 0 end) as notCompleted " +
                        "from %s", FormsTable.COLUMN_SYNCED, FormsTable.COLUMN_SYNCED, FormsTable.COLUMN_SYNCED, FormsTable.TABLE_NAME),
                null, null);
        if (mCursor != null && mCursor.moveToFirst()) {
            count = count.copy(Integer.parseInt(mCursor.getString(0)),
                    Integer.parseInt(mCursor.getString(1)));
            mCursor.close();
        }
        return count;
    }

    public ArrayList<ChildFollowup> getChildrenFollowUpFromDB(@NotNull String country, @NotNull Identification identification) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;
        String whereClause = ChildTable.COLUMN_CS01 + "=? AND " +
                ChildTable.COLUMN_CS01A + "=? AND " +
                ChildTable.COLUMN_CS01B + "=? AND " +
                ChildTable.COLUMN_CS04 + "=? AND " +
                ChildTable.COLUMN_CS05 + "=? ";
        String[] whereArgs = {country, identification.getRegion(), identification.getDistrict(), identification.getUc(), identification.getVillage()};
        String groupBy = null;
        String having = null;

        String orderBy = ChildTable.COLUMN_CS01 + " ASC";
        ArrayList<ChildFollowup> allEB = new ArrayList<>();
        try {
            c = db.query(
                    ChildTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new ChildFollowup().hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                if (!db.isOpen()) db.close();
            }
        }
        return allEB;
    }

    public ArrayList<WraFollowup> getWraFollowUpFromDB(@NotNull String country, @NotNull Identification identification) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;
        String whereClause = WraTable.COLUMN_WS01 + "=? AND " +
                WraTable.COLUMN_WS01A + "=? AND " +
                WraTable.COLUMN_WS01B + "=? AND " +
                WraTable.COLUMN_WS04 + "=? AND " +
                WraTable.COLUMN_WS05 + "=? ";
        String[] whereArgs = {country, identification.getRegion(), identification.getDistrict(), identification.getUc(), identification.getVillage()};
        String groupBy = null;
        String having = null;

        String orderBy = WraTable.COLUMN_WS01 + " ASC";
        ArrayList<WraFollowup> allEB = new ArrayList<>();
        try {
            c = db.query(
                    WraTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new WraFollowup().hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                if (!db.isOpen()) db.close();
            }
        }
        return allEB;
    }

    public ArrayList<ChildFollowup> getChildrenFollowupFromFormDB(@NotNull String country, @NotNull Identification identification) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;
        String whereClause = FormsTable.COLUMN_COUNTRY_CODE + "=? AND " +
//                FormsTable.COLUMN_REGION_CODE + "=? AND " +
                FormsTable.COLUMN_DISTRICT_CODE + "=? AND " +
                FormsTable.COLUMN_UC_CODE + "=? AND " +
                FormsTable.COLUMN_VILLAGE_CODE + "=? AND " +
                FormsTable.COLUMN_FORM_TYPE + "=? AND " +
                FormsTable.COLUMN_ISTATUS + "=? ";
        String[] whereArgs = {country, identification.getDistrict(), identification.getUc(), identification.getVillage(), CONSTANTS.CHILD_TYPE, "1"};
        String groupBy = null;
        String having = null;

        String orderBy = FormsTable.COLUMN_VILLAGE_CODE + " ASC";
        ArrayList<ChildFollowup> allEB = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildFollowup ch = new ChildFollowup().hydrateForm(c);
                ch.setFupDt("0");
                ch.setFupNo("0");

                Form fm = new Form().Hydrate(c, CONSTANTS.CHILD_TYPE);
                if (fm.getCs1403().equals("9999")) {
                    String dt = DateUtilsKt.getDOB("yyyy-MM-dd", Integer.parseInt(fm.getCs1501()), Integer.parseInt(fm.getCs1502()), 15);
                    ch.setDob(dt);
                } else {
                    ch.setDob(
                            fm.getCs1403() + "-" + fm.getCs1402() + "-" + fm.getCs1401()
                    );
                }
                allEB.add(ch);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                if (!db.isOpen()) db.close();
            }
        }
        return allEB;
    }

    public ArrayList<WraFollowup> getWraFollowupFromFormDB(@NotNull String country, @NotNull Identification identification) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = null;
        String whereClause = FormsTable.COLUMN_COUNTRY_CODE + "=? AND " +
//                FormsTable.COLUMN_REGION_CODE + "=? AND " +
                FormsTable.COLUMN_DISTRICT_CODE + "=? AND " +
                FormsTable.COLUMN_UC_CODE + "=? AND " +
                FormsTable.COLUMN_VILLAGE_CODE + "=? AND " +
                FormsTable.COLUMN_FORM_TYPE + "=? AND " +
                FormsTable.COLUMN_ISTATUS + "=? ";
        String[] whereArgs = {country, identification.getDistrict(), identification.getUc(), identification.getVillage(), CONSTANTS.WRA_TYPE, "1"};
        String groupBy = null;
        String having = null;

        String orderBy = FormsTable.COLUMN_VILLAGE_CODE + " ASC";
        ArrayList<WraFollowup> allEB = new ArrayList<>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new WraFollowup().hydrateForm(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                if (!db.isOpen()) db.close();
            }
        }
        return allEB;
    }

    public Form getFollowUpFormStatus(@NotNull String country, @NotNull Identification identification, String reg_no, String followUpType) {
        SQLiteDatabase db = this.getReadableDatabase();
        Form form = null;
        Cursor mCursor = db.rawQuery(
                String.format("select * from %s WHERE %s =? AND %s =? AND %s =? AND %s =? AND %s =? AND %s =? AND %s =?",
                        FormsTable.TABLE_NAME,
                        FormsTable.COLUMN_COUNTRY_CODE,
                        FormsTable.COLUMN_DISTRICT_CODE,
                        FormsTable.COLUMN_UC_CODE,
                        FormsTable.COLUMN_VILLAGE_CODE,
                        FormsTable.COLUMN_REG_NO,
                        FormsTable.COLUMN_FORM_TYPE,
                        FormsTable.COLUMN_ISTATUS
                ),
                new String[]{country, identification.getDistrict(), identification.getUc(), identification.getVillage(), reg_no, followUpType, "1"}, null);
        if (mCursor != null && mCursor.moveToFirst()) {
            form = new Form().Hydrate(mCursor, followUpType);
            mCursor.close();
        }
        return form;
    }
}