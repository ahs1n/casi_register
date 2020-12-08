package edu.aku.hassannaqvi.casi_register.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.casi_register.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.casi_register.contracts.UsersContract;
import edu.aku.hassannaqvi.casi_register.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.casi_register.contracts.VersionAppContract;
import edu.aku.hassannaqvi.casi_register.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.casi_register.contracts.VillagesContract;
import edu.aku.hassannaqvi.casi_register.contracts.ZStandardContract;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.Users;
import edu.aku.hassannaqvi.casi_register.models.VersionApp;
import edu.aku.hassannaqvi.casi_register.models.Villages;
import edu.aku.hassannaqvi.casi_register.models.ZStandard;

import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.DATABASE_NAME;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_FORMS;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_VERSIONAPP;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_VILLAGES;
import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.SQL_CREATE_ZSTANDARD;


public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TAG = "DatabaseHelper";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersTable.COLUMN_USERNAME, user.getUserName());
                values.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
                values.put(UsersTable.COLUMN_FULL_NAME, user.getFull_name());
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
        db.delete(VersionAppContract.VersionAppTable.TABLE_NAME, null, null);
        long count = 0;
        try {
            JSONObject jsonObjectCC = ((JSONArray) VersionList.get(VersionAppContract.VersionAppTable.COLUMN_VERSION_PATH)).getJSONObject(0);
            VersionApp Vc = new VersionApp();
            Vc.Sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionAppContract.VersionAppTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionAppContract.VersionAppTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            count = db.insert(VersionAppContract.VersionAppTable.TABLE_NAME, null, values);
            if (count > 0) count = 1;

        } catch (Exception ignored) {
        } finally {
            db.close();
        }

        return (int) count;
    }


    public int syncVillages(JSONArray vilList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VillagesContract.Table.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < vilList.length(); i++) {

                JSONObject jsonObjectzs = vilList.getJSONObject(i);

                Villages villages = new Villages();
                villages.Sync(jsonObjectzs);
                ContentValues values = new ContentValues();

                values.put(VillagesContract.Table.COLUMN_COUNTRY, villages.getCountry());
                values.put(VillagesContract.Table.COLUMN_DISTRICT, villages.getDistrict());
                values.put(VillagesContract.Table.COLUMN_UC, villages.getUc());
                values.put(VillagesContract.Table.COLUMN_VILLAGE, villages.getVillage());
                values.put(VillagesContract.Table.COLUMN_COUNTRY_CODE, villages.getCountry_code());
                values.put(VillagesContract.Table.COLUMN_DISTRICT_CODE, villages.getDistrict_code());
                values.put(VillagesContract.Table.COLUMN_UC_CODE, villages.getUc_code());
                values.put(VillagesContract.Table.COLUMN_VILLLAGE_CODE, villages.getVilllage_code());
                values.put(VillagesContract.Table.COLUMN_CLUSTER_NO, villages.getCluster_no());
                long rowID = db.insert(VillagesContract.Table.TABLE_NAME, null, values);
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


    public int syncZStandard(JSONArray zsList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ZStandardContract.Table.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < zsList.length(); i++) {

                JSONObject jsonObjectzs = zsList.getJSONObject(i);

                ZStandard Zstandard = new ZStandard();
                Zstandard.Sync(jsonObjectzs);
                ContentValues values = new ContentValues();

                values.put(ZStandardContract.Table.COLUMN_SEX, Zstandard.getSex());
                values.put(ZStandardContract.Table.COLUMN_AGE, Zstandard.getAge());
                values.put(ZStandardContract.Table.COLUMN_MEASURE, Zstandard.getMeasure());
                values.put(ZStandardContract.Table.COLUMN_L, Zstandard.getL());
                values.put(ZStandardContract.Table.COLUMN_M, Zstandard.getM());
                values.put(ZStandardContract.Table.COLUMN_S, Zstandard.getS());
                values.put(ZStandardContract.Table.COLUMN_CAT, Zstandard.getCat());
                long rowID = db.insert(ZStandardContract.Table.TABLE_NAME, null, values);
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
        values.put(FormsTable.COLUMN_COUNTRY, form.getCountry());
        values.put(FormsTable.COLUMN_DISTRICT_CODE, form.getDistrictCode());
        values.put(FormsTable.COLUMN_DISTRICT, form.getDistrict());
        values.put(FormsTable.COLUMN_UC_CODE, form.getUcCode());
        values.put(FormsTable.COLUMN_UC, form.getUc());
        values.put(FormsTable.COLUMN_VILLAGE_CODE, form.getVillageCode());
        values.put(FormsTable.COLUMN_VILLAGE, form.getVillage());
        values.put(FormsTable.COLUMN_SA, form.getsA());
        /*values.put(FormsTable.COLUMN_CR01, form.getCr01());
        values.put(FormsTable.COLUMN_CR02, form.getCr02());
        values.put(FormsTable.COLUMN_CR03, form.getCr03());
        values.put(FormsTable.COLUMN_CR04, form.getCr04());
        values.put(FormsTable.COLUMN_CR05, form.getCr05());
        values.put(FormsTable.COLUMN_CR06, form.getCr06());
        values.put(FormsTable.COLUMN_CR06X, form.getCr06x());
        values.put(FormsTable.COLUMN_CR07, form.getCr07());
        values.put(FormsTable.COLUMN_CR08D, form.getCr08d());
        values.put(FormsTable.COLUMN_CR08M, form.getCr08m());
        values.put(FormsTable.COLUMN_CR08Y, form.getCr08y());
        values.put(FormsTable.COLUMN_CR09, form.getCr09());
        values.put(FormsTable.COLUMN_CR10, form.getCr10());
        values.put(FormsTable.COLUMN_CR11, form.getCr11());
        values.put(FormsTable.COLUMN_CR12, form.getCr12());
        values.put(FormsTable.COLUMN_CR13, form.getCr13());
        values.put(FormsTable.COLUMN_CR14D, form.getCr14d());
        values.put(FormsTable.COLUMN_CR14M, form.getCr14m());
        values.put(FormsTable.COLUMN_CR14Y, form.getCr14y());
        values.put(FormsTable.COLUMN_CR15M, form.getCr15m());
        values.put(FormsTable.COLUMN_CR15Y, form.getCr15y());
        values.put(FormsTable.COLUMN_CR16, form.getCr16());
        values.put(FormsTable.COLUMN_CR17, form.getCr17());
        values.put(FormsTable.COLUMN_CR18, form.getCr18());
        values.put(FormsTable.COLUMN_CR19, form.getCr19());
        values.put(FormsTable.COLUMN_CR20, form.getCr20());
        values.put(FormsTable.COLUMN_CR21, form.getCr21());
        values.put(FormsTable.COLUMN_CR22, form.getCr22());
        values.put(FormsTable.COLUMN_CR23, form.getCr23());
        values.put(FormsTable.COLUMN_CR24A, form.getCr24a());
        values.put(FormsTable.COLUMN_CR24B, form.getCr24b());
        values.put(FormsTable.COLUMN_CR24C, form.getCr24c());
        values.put(FormsTable.COLUMN_CR24D, form.getCr24d());
        values.put(FormsTable.COLUMN_CR24E, form.getCr24e());
        values.put(FormsTable.COLUMN_CR24F, form.getCr24f());
        values.put(FormsTable.COLUMN_CR25, form.getCr25());
        values.put(FormsTable.COLUMN_CR26, form.getCr26());
        values.put(FormsTable.COLUMN_CR27A, form.getCr27a());
        values.put(FormsTable.COLUMN_CR27B, form.getCr27b());
        values.put(FormsTable.COLUMN_CR27C, form.getCr27c());
        values.put(FormsTable.COLUMN_CR28A, form.getCr28a());
        values.put(FormsTable.COLUMN_CR28B, form.getCr28b());
        values.put(FormsTable.COLUMN_CR28C, form.getCr28c());
        values.put(FormsTable.COLUMN_CR28D, form.getCr28d());
        values.put(FormsTable.COLUMN_CR28E, form.getCr28e());
        values.put(FormsTable.COLUMN_CR28F, form.getCr28f());
        values.put(FormsTable.COLUMN_CR28FX, form.getCr28fx());*/
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
        String[] columns = {
                VersionAppTable._ID,
                VersionAppTable.COLUMN_VERSION_CODE,
                VersionAppTable.COLUMN_VERSION_NAME,
                VersionAppTable.COLUMN_PATH_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = null;

        VersionApp allVC = new VersionApp();
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
                allVC.hydrate(c);
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


    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME + " WHERE " + UsersTable.COLUMN_USERNAME + "=? AND " + UsersTable.COLUMN_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {

                if (mCursor.moveToFirst()) {
//                    MainApp.DIST_ID = mCursor.getString(mCursor.getColumnIndex(Users.UsersTable.ROW_USERNAME));
                }
                return true;
            }
        }
        return false;
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
                FormsTable.COLUMN_COUNTRY,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_SA,
                /*FormsTable.COLUMN_CR01,
                FormsTable.COLUMN_CR02,
                FormsTable.COLUMN_CR03,
                FormsTable.COLUMN_CR04,
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR06X,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08D,
                FormsTable.COLUMN_CR08M,
                FormsTable.COLUMN_CR08Y,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                FormsTable.COLUMN_CR11,
                FormsTable.COLUMN_CR12,
                FormsTable.COLUMN_CR13,
                FormsTable.COLUMN_CR14D,
                FormsTable.COLUMN_CR14M,
                FormsTable.COLUMN_CR14Y,
                FormsTable.COLUMN_CR15M,
                FormsTable.COLUMN_CR15Y,
                FormsTable.COLUMN_CR16,
                FormsTable.COLUMN_CR17,
                FormsTable.COLUMN_CR18,
                FormsTable.COLUMN_CR19,
                FormsTable.COLUMN_CR20,
                FormsTable.COLUMN_CR21,
                FormsTable.COLUMN_CR22,
                FormsTable.COLUMN_CR23,
                FormsTable.COLUMN_CR24A,
                FormsTable.COLUMN_CR24B,
                FormsTable.COLUMN_CR24C,
                FormsTable.COLUMN_CR24D,
                FormsTable.COLUMN_CR24E,
                FormsTable.COLUMN_CR24F,
                FormsTable.COLUMN_CR25,
                FormsTable.COLUMN_CR26,
                FormsTable.COLUMN_CR27A,
                FormsTable.COLUMN_CR27B,
                FormsTable.COLUMN_CR27C,
                FormsTable.COLUMN_CR28A,
                FormsTable.COLUMN_CR28B,
                FormsTable.COLUMN_CR28C,
                FormsTable.COLUMN_CR28D,
                FormsTable.COLUMN_CR28E,
                FormsTable.COLUMN_CR28F,
                FormsTable.COLUMN_CR28FX,*/
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,

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
                allForms.add(form.Hydrate(c));
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
                FormsTable.COLUMN_COUNTRY,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_SA,
                /*FormsTable.COLUMN_CR01,
                FormsTable.COLUMN_CR02,
                FormsTable.COLUMN_CR03,
                FormsTable.COLUMN_CR04,
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR06X,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08D,
                FormsTable.COLUMN_CR08M,
                FormsTable.COLUMN_CR08Y,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                FormsTable.COLUMN_CR11,
                FormsTable.COLUMN_CR12,
                FormsTable.COLUMN_CR13,
                FormsTable.COLUMN_CR14D,
                FormsTable.COLUMN_CR14M,
                FormsTable.COLUMN_CR14Y,
                FormsTable.COLUMN_CR15M,
                FormsTable.COLUMN_CR15Y,
                FormsTable.COLUMN_CR16,
                FormsTable.COLUMN_CR17,
                FormsTable.COLUMN_CR18,
                FormsTable.COLUMN_CR19,
                FormsTable.COLUMN_CR20,
                FormsTable.COLUMN_CR21,
                FormsTable.COLUMN_CR22,
                FormsTable.COLUMN_CR23,
                FormsTable.COLUMN_CR24A,
                FormsTable.COLUMN_CR24B,
                FormsTable.COLUMN_CR24C,
                FormsTable.COLUMN_CR24D,
                FormsTable.COLUMN_CR24E,
                FormsTable.COLUMN_CR24F,
                FormsTable.COLUMN_CR25,
                FormsTable.COLUMN_CR26,
                FormsTable.COLUMN_CR27A,
                FormsTable.COLUMN_CR27B,
                FormsTable.COLUMN_CR27C,
                FormsTable.COLUMN_CR28A,
                FormsTable.COLUMN_CR28B,
                FormsTable.COLUMN_CR28C,
                FormsTable.COLUMN_CR28D,
                FormsTable.COLUMN_CR28E,
                FormsTable.COLUMN_CR28F,
                FormsTable.COLUMN_CR28FX,*/
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDATE,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICETAGID,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,

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
                allForms.add(form.Hydrate(c));
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
                FormsTable.COLUMN_COUNTRY,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_SA,
                /*FormsTable.COLUMN_CR01,
                FormsTable.COLUMN_CR02,
                FormsTable.COLUMN_CR03,
                FormsTable.COLUMN_CR04,
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR06X,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08D,
                FormsTable.COLUMN_CR08M,
                FormsTable.COLUMN_CR08Y,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                FormsTable.COLUMN_CR11,
                FormsTable.COLUMN_CR12,
                FormsTable.COLUMN_CR13,
                FormsTable.COLUMN_CR14D,
                FormsTable.COLUMN_CR14M,
                FormsTable.COLUMN_CR14Y,
                FormsTable.COLUMN_CR15M,
                FormsTable.COLUMN_CR15Y,
                FormsTable.COLUMN_CR16,
                FormsTable.COLUMN_CR17,
                FormsTable.COLUMN_CR18,
                FormsTable.COLUMN_CR19,
                FormsTable.COLUMN_CR20,
                FormsTable.COLUMN_CR21,
                FormsTable.COLUMN_CR22,
                FormsTable.COLUMN_CR23,
                FormsTable.COLUMN_CR24A,
                FormsTable.COLUMN_CR24B,
                FormsTable.COLUMN_CR24C,
                FormsTable.COLUMN_CR24D,
                FormsTable.COLUMN_CR24E,
                FormsTable.COLUMN_CR24F,
                FormsTable.COLUMN_CR25,
                FormsTable.COLUMN_CR26,
                FormsTable.COLUMN_CR27A,
                FormsTable.COLUMN_CR27B,
                FormsTable.COLUMN_CR27C,
                FormsTable.COLUMN_CR28A,
                FormsTable.COLUMN_CR28B,
                FormsTable.COLUMN_CR28C,
                FormsTable.COLUMN_CR28D,
                FormsTable.COLUMN_CR28E,
                FormsTable.COLUMN_CR28F,
                FormsTable.COLUMN_CR28FX,*/
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

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
                form.setCountry(c.getString(c.getColumnIndex(FormsTable.COLUMN_COUNTRY)));
                form.setDistrictCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT_CODE)));
                form.setDistrict(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT)));
                form.setUcCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC_CODE)));
                form.setUc(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC)));
                form.setVillageCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE_CODE)));
                form.setVillage(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE)));
                form.setsA(c.getString(c.getColumnIndex(FormsTable.COLUMN_SA)));
                /*form.setCr01(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR01)));
                form.setCr02(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR02)));
                form.setCr03(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR03)));
                form.setCr04(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR04)));
                form.setCr05(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR05)));
                form.setCr06(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR06)));
                form.setCr06x(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR06X)));
                form.setCr07(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR07)));
                form.setCr08d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08D)));
                form.setCr08m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08M)));
                form.setCr08y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08Y)));
                form.setCr09(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR09)));
                form.setCr10(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR10)));
                form.setCr11(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR11)));
                form.setCr12(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR12)));
                form.setCr13(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR13)));
                form.setCr14d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14D)));
                form.setCr14m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14M)));
                form.setCr14y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14Y)));
                form.setCr15m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR15M)));
                form.setCr15y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR15Y)));
                form.setCr16(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR16)));
                form.setCr17(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR17)));
                form.setCr18(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR18)));
                form.setCr19(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR19)));
                form.setCr20(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR20)));
                form.setCr21(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR21)));
                form.setCr22(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR22)));
                form.setCr23(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR23)));
                form.setCr24a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24A)));
                form.setCr24b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24B)));
                form.setCr24c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24C)));
                form.setCr24d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24D)));
                form.setCr24e(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24E)));
                form.setCr24f(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24F)));
                form.setCr25(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR25)));
                form.setCr26(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR26)));
                form.setCr27a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27A)));
                form.setCr27b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27B)));
                form.setCr27c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27C)));
                form.setCr28a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28A)));
                form.setCr28b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28B)));
                form.setCr28c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28C)));
                form.setCr28d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28D)));
                form.setCr28e(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28E)));
                form.setCr28f(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28F)));
                form.setCr28fx(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28FX)));*/
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


    public Collection<ZStandard> getZStandard() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ZStandardContract.Table.COLUMN_SEX,
                ZStandardContract.Table.COLUMN_AGE,
                ZStandardContract.Table.COLUMN_MEASURE,
                ZStandardContract.Table.COLUMN_L,
                ZStandardContract.Table.COLUMN_M,
                ZStandardContract.Table.COLUMN_S,
                ZStandardContract.Table.COLUMN_CAT
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                ZStandardContract.Table.COLUMN_SEX + " ASC";

        Collection<ZStandard> allZs = new ArrayList<ZStandard>();
        try {
            c = db.query(
                    ZStandardContract.Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ZStandard zs = new ZStandard();
                allZs.add(zs.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allZs;
    }


    public Collection<ZStandard> getZStandardL() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                "DISTINCT " + ZStandardContract.Table.COLUMN_SEX,
                ZStandardContract.Table.COLUMN_AGE,
                ZStandardContract.Table.COLUMN_MEASURE,
                ZStandardContract.Table.COLUMN_L,
                ZStandardContract.Table.COLUMN_M,
                ZStandardContract.Table.COLUMN_S,
                ZStandardContract.Table.COLUMN_CAT
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = ZStandardContract.Table.COLUMN_SEX;
        String having = null;

        String orderBy =
                ZStandardContract.Table.COLUMN_SEX + " ASC";

        Collection<ZStandard> allzs = new ArrayList<ZStandard>();
        try {
            c = db.query(
                    ZStandardContract.Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ZStandard zs = new ZStandard();
                allzs.add(zs.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allzs;
    }


    public Collection<Villages> getCountri() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                "DISTINCT " + VillagesContract.Table.COLUMN_COUNTRY,
                VillagesContract.Table.COLUMN_COUNTRY_CODE
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = VillagesContract.Table.COLUMN_COUNTRY;
        String having = null;

        String orderBy =
                VillagesContract.Table.COLUMN_COUNTRY + " ASC";

        Collection<Villages> allvil = new ArrayList<Villages>();
        try {
            c = db.query(
                    VillagesContract.Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Villages zs = new Villages();
                allvil.add(zs.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allvil;
    }


    public Collection<ZStandard> getZStandardByL(String uc) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ZStandardContract.Table.COLUMN_SEX,
                ZStandardContract.Table.COLUMN_MEASURE
        };

        String whereClause = ZStandardContract.Table.COLUMN_SEX + "=?";
        String[] whereArgs = new String[]{uc};
        String groupBy = null;
        String having = null;

        String orderBy =
                ZStandardContract.Table.COLUMN_SEX + " ASC";

        Collection<ZStandard> allzs = new ArrayList<ZStandard>();
        try {
            c = db.query(
                    ZStandardContract.Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ZStandard zs = new ZStandard();
                allzs.add(zs.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allzs;
    }


    //TODO: Commented for TESTING APP

    public Collection<Form> getFormsByCluster(String cluster) {

        // String sysdate =  spDateT.substring(0, 8).trim()
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_COUNTRY,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_SA,
                /* FormsTable.COLUMN_CR01,
                 FormsTable.COLUMN_CR02,
                 FormsTable.COLUMN_CR03,
                 FormsTable.COLUMN_CR04,
                 FormsTable.COLUMN_CR05,
                 FormsTable.COLUMN_CR06,
                 FormsTable.COLUMN_CR06X,
                 FormsTable.COLUMN_CR07,
                 FormsTable.COLUMN_CR08D,
                 FormsTable.COLUMN_CR08M,
                 FormsTable.COLUMN_CR08Y,
                 FormsTable.COLUMN_CR09,
                 FormsTable.COLUMN_CR10,
                 FormsTable.COLUMN_CR11,
                 FormsTable.COLUMN_CR12,
                 FormsTable.COLUMN_CR13,
                 FormsTable.COLUMN_CR14D,
                 FormsTable.COLUMN_CR14M,
                 FormsTable.COLUMN_CR14Y,
                 FormsTable.COLUMN_CR15M,
                 FormsTable.COLUMN_CR15Y,
                 FormsTable.COLUMN_CR16,
                 FormsTable.COLUMN_CR17,
                 FormsTable.COLUMN_CR18,
                 FormsTable.COLUMN_CR19,
                 FormsTable.COLUMN_CR20,
                 FormsTable.COLUMN_CR21,
                 FormsTable.COLUMN_CR22,
                 FormsTable.COLUMN_CR23,
                 FormsTable.COLUMN_CR24A,
                 FormsTable.COLUMN_CR24B,
                 FormsTable.COLUMN_CR24C,
                 FormsTable.COLUMN_CR24D,
                 FormsTable.COLUMN_CR24E,
                 FormsTable.COLUMN_CR24F,
                 FormsTable.COLUMN_CR25,
                 FormsTable.COLUMN_CR26,
                 FormsTable.COLUMN_CR27A,
                 FormsTable.COLUMN_CR27B,
                 FormsTable.COLUMN_CR27C,
                 FormsTable.COLUMN_CR28A,
                 FormsTable.COLUMN_CR28B,
                 FormsTable.COLUMN_CR28C,
                 FormsTable.COLUMN_CR28D,
                 FormsTable.COLUMN_CR28E,
                 FormsTable.COLUMN_CR28F,
                 FormsTable.COLUMN_CR28FX,*/
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_SA + " = ? ";
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
                form.setCountry(c.getString(c.getColumnIndex(FormsTable.COLUMN_COUNTRY)));
                form.setDistrictCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT_CODE)));
                form.setDistrict(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT)));
                form.setUcCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC_CODE)));
                form.setUc(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC)));
                form.setVillageCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE_CODE)));
                form.setVillage(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE)));
                form.setsA(c.getString(c.getColumnIndex(FormsTable.COLUMN_SA)));
/*                form.setCr01(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR01)));
                form.setCr02(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR02)));
                form.setCr03(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR03)));
                form.setCr04(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR04)));
                form.setCr05(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR05)));
                form.setCr06(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR06)));
                form.setCr06x(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR06X)));
                form.setCr07(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR07)));
                form.setCr08d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08D)));
                form.setCr08m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08M)));
                form.setCr08y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08Y)));
                form.setCr09(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR09)));
                form.setCr10(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR10)));
                form.setCr11(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR11)));
                form.setCr12(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR12)));
                form.setCr13(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR13)));
                form.setCr14d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14D)));
                form.setCr14m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14M)));
                form.setCr14y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14Y)));
                form.setCr15m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR15M)));
                form.setCr15y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR15Y)));
                form.setCr16(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR16)));
                form.setCr17(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR17)));
                form.setCr18(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR18)));
                form.setCr19(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR19)));
                form.setCr20(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR20)));
                form.setCr21(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR21)));
                form.setCr22(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR22)));
                form.setCr23(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR23)));
                form.setCr24a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24A)));
                form.setCr24b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24B)));
                form.setCr24c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24C)));
                form.setCr24d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24D)));
                form.setCr24e(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24E)));
                form.setCr24f(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24F)));
                form.setCr25(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR25)));
                form.setCr26(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR26)));
                form.setCr27a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27A)));
                form.setCr27b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27B)));
                form.setCr27c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27C)));
                form.setCr28a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28A)));
                form.setCr28b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28B)));
                form.setCr28c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28C)));
                form.setCr28d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28D)));
                form.setCr28e(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28E)));
                form.setCr28f(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28F)));
                form.setCr28fx(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28FX)));*/
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

    public ArrayList<Form> getUnclosedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_COUNTRY,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_SA,
/*                FormsTable.COLUMN_CR01,
                FormsTable.COLUMN_CR02,
                FormsTable.COLUMN_CR03,
                FormsTable.COLUMN_CR04,
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR06X,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08D,
                FormsTable.COLUMN_CR08M,
                FormsTable.COLUMN_CR08Y,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                FormsTable.COLUMN_CR11,
                FormsTable.COLUMN_CR12,
                FormsTable.COLUMN_CR13,
                FormsTable.COLUMN_CR14D,
                FormsTable.COLUMN_CR14M,
                FormsTable.COLUMN_CR14Y,
                FormsTable.COLUMN_CR15M,
                FormsTable.COLUMN_CR15Y,
                FormsTable.COLUMN_CR16,
                FormsTable.COLUMN_CR17,
                FormsTable.COLUMN_CR18,
                FormsTable.COLUMN_CR19,
                FormsTable.COLUMN_CR20,
                FormsTable.COLUMN_CR21,
                FormsTable.COLUMN_CR22,
                FormsTable.COLUMN_CR23,
                FormsTable.COLUMN_CR24A,
                FormsTable.COLUMN_CR24B,
                FormsTable.COLUMN_CR24C,
                FormsTable.COLUMN_CR24D,
                FormsTable.COLUMN_CR24E,
                FormsTable.COLUMN_CR24F,
                FormsTable.COLUMN_CR25,
                FormsTable.COLUMN_CR26,
                FormsTable.COLUMN_CR27A,
                FormsTable.COLUMN_CR27B,
                FormsTable.COLUMN_CR27C,
                FormsTable.COLUMN_CR28A,
                FormsTable.COLUMN_CR28B,
                FormsTable.COLUMN_CR28C,
                FormsTable.COLUMN_CR28D,
                FormsTable.COLUMN_CR28E,
                FormsTable.COLUMN_CR28F,
                FormsTable.COLUMN_CR28FX,*/
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,
        };
        String whereClause = FormsTable.COLUMN_ISTATUS + " = ''";
        String[] whereArgs = null;
//        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
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
                Form form = new Form();
                form.set_ID(c.getString(c.getColumnIndex(FormsTable.COLUMN_ID)));
                form.set_UID(c.getString(c.getColumnIndex(FormsTable.COLUMN_UID)));
                form.setSysdate(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYSDATE)));
                form.setCountryCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_COUNTRY_CODE)));
                form.setCountry(c.getString(c.getColumnIndex(FormsTable.COLUMN_COUNTRY)));
                form.setDistrictCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT_CODE)));
                form.setDistrict(c.getString(c.getColumnIndex(FormsTable.COLUMN_DISTRICT)));
                form.setUcCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC_CODE)));
                form.setUc(c.getString(c.getColumnIndex(FormsTable.COLUMN_UC)));
                form.setVillageCode(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE_CODE)));
                form.setVillage(c.getString(c.getColumnIndex(FormsTable.COLUMN_VILLAGE)));
                form.setsA(c.getString(c.getColumnIndex(FormsTable.COLUMN_SA)));
/*                form.setCr01(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR01)));
                form.setCr02(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR02)));
                form.setCr03(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR03)));
                form.setCr04(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR04)));
                form.setCr05(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR05)));
                form.setCr06(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR06)));
                form.setCr06x(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR06X)));
                form.setCr07(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR07)));
                form.setCr08d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08D)));
                form.setCr08m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08M)));
                form.setCr08y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08Y)));
                form.setCr09(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR09)));
                form.setCr10(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR10)));
                form.setCr11(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR11)));
                form.setCr12(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR12)));
                form.setCr13(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR13)));
                form.setCr14d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14D)));
                form.setCr14m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14M)));
                form.setCr14y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR14Y)));
                form.setCr15m(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR15M)));
                form.setCr15y(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR15Y)));
                form.setCr16(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR16)));
                form.setCr17(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR17)));
                form.setCr18(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR18)));
                form.setCr19(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR19)));
                form.setCr20(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR20)));
                form.setCr21(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR21)));
                form.setCr22(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR22)));
                form.setCr23(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR23)));
                form.setCr24a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24A)));
                form.setCr24b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24B)));
                form.setCr24c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24C)));
                form.setCr24d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24D)));
                form.setCr24e(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24E)));
                form.setCr24f(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR24F)));
                form.setCr25(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR25)));
                form.setCr26(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR26)));
                form.setCr27a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27A)));
                form.setCr27b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27B)));
                form.setCr27c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR27C)));
                form.setCr28a(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28A)));
                form.setCr28b(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28B)));
                form.setCr28c(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28C)));
                form.setCr28d(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28D)));
                form.setCr28e(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28E)));
                form.setCr28f(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28F)));
                form.setCr28fx(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR28FX)));*/
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


    public Collection<Users> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                UsersTable.COLUMN_USERNAME,
                UsersTable.COLUMN_FULL_NAME
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = UsersTable.COLUMN_USERNAME + " ASC";

        Collection<Users> alluser = new ArrayList<>();
        try {
            c = db.query(
                    UsersContract.UsersTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                alluser.add(new Users().Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return alluser;
    }


    //Get All UC
    //TODO: Need list of Districts, UCs, Villages

    public List<Villages> getUCs() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillagesContract.Table._ID,
                VillagesContract.Table.COLUMN_UC_CODE,
                VillagesContract.Table.COLUMN_UC,
                VillagesContract.Table.COLUMN_DISTRICT_CODE
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = VillagesContract.Table.COLUMN_UC_CODE + " ASC";
        List<Villages> allEB = new ArrayList<>();
        try {
            c = db.query(
                    VillagesContract.Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new Villages().Hydrate(c));
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

    //Get All DISTRICTS
    public List<Villages> getDistrics() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillagesContract.Table._ID,
                VillagesContract.Table.COLUMN_DISTRICT_CODE,
                VillagesContract.Table.COLUMN_DISTRICT,
                VillagesContract.Table.COLUMN_COUNTRY_CODE
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = VillagesContract.Table.COLUMN_COUNTRY_CODE + " ASC";
        List<Villages> allEB = new ArrayList<>();
        try {
            c = db.query(
                    VillagesContract.Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new Villages().Hydrate(c));
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


    //Get All EnumBlock
    public List<Villages> getEnumBlock(String dist_id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillagesContract.Table._ID,
                VillagesContract.Table.COLUMN_COUNTRY,
                VillagesContract.Table.COLUMN_DISTRICT,
                VillagesContract.Table.COLUMN_UC,
                VillagesContract.Table.COLUMN_VILLAGE,
                VillagesContract.Table.COLUMN_COUNTRY_CODE,
                VillagesContract.Table.COLUMN_DISTRICT_CODE,
                VillagesContract.Table.COLUMN_UC_CODE,
                VillagesContract.Table.COLUMN_VILLLAGE_CODE,
                VillagesContract.Table.COLUMN_CLUSTER_NO
        };

        String whereClause = VillagesContract.Table.COLUMN_DISTRICT_CODE + " LIKE ? ";
        String[] whereArgs = {"" + dist_id + "%"};
        String groupBy = null;
        String having = null;

        String orderBy = VillagesContract.Table.COLUMN_DISTRICT_CODE + " ASC";
        List<Villages> allEB = new ArrayList<>();
        try {
            c = db.query(
                    VillagesContract.Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new Villages().Hydrate(c));
                //allEB.add(new Villages.HydrateEnum(c));
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


    public List<Villages> getCountry() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                "DISTINCT " + VillagesContract.Table._ID,
                VillagesContract.Table.COLUMN_COUNTRY,
                VillagesContract.Table.COLUMN_DISTRICT,
                VillagesContract.Table.COLUMN_UC,
                VillagesContract.Table.COLUMN_VILLAGE,
                VillagesContract.Table.COLUMN_COUNTRY_CODE,
                VillagesContract.Table.COLUMN_DISTRICT_CODE,
                VillagesContract.Table.COLUMN_UC_CODE,
                VillagesContract.Table.COLUMN_VILLLAGE_CODE,
                VillagesContract.Table.COLUMN_CLUSTER_NO
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = VillagesContract.Table._ID + " ASC";
        List<Villages> allEB = new ArrayList<>();
        try {
            c = db.query(
                    VillagesContract.Table.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new Villages().Hydrate(c));
                //allEB.add(new Villages.HydrateEnum(c));
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
    public Collection<Form> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_COUNTRY_CODE,
                FormsTable.COLUMN_COUNTRY,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_SA,
/*                FormsTable.COLUMN_CR01,
                FormsTable.COLUMN_CR02,
                FormsTable.COLUMN_CR03,
                FormsTable.COLUMN_CR04,
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR06X,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08D,
                FormsTable.COLUMN_CR08M,
                FormsTable.COLUMN_CR08Y,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                FormsTable.COLUMN_CR11,
                FormsTable.COLUMN_CR12,
                FormsTable.COLUMN_CR13,
                FormsTable.COLUMN_CR14D,
                FormsTable.COLUMN_CR14M,
                FormsTable.COLUMN_CR14Y,
                FormsTable.COLUMN_CR15M,
                FormsTable.COLUMN_CR15Y,
                FormsTable.COLUMN_CR16,
                FormsTable.COLUMN_CR17,
                FormsTable.COLUMN_CR18,
                FormsTable.COLUMN_CR19,
                FormsTable.COLUMN_CR20,
                FormsTable.COLUMN_CR21,
                FormsTable.COLUMN_CR22,
                FormsTable.COLUMN_CR23,
                FormsTable.COLUMN_CR24A,
                FormsTable.COLUMN_CR24B,
                FormsTable.COLUMN_CR24C,
                FormsTable.COLUMN_CR24D,
                FormsTable.COLUMN_CR24E,
                FormsTable.COLUMN_CR24F,
                FormsTable.COLUMN_CR25,
                FormsTable.COLUMN_CR26,
                FormsTable.COLUMN_CR27A,
                FormsTable.COLUMN_CR27B,
                FormsTable.COLUMN_CR27C,
                FormsTable.COLUMN_CR28A,
                FormsTable.COLUMN_CR28B,
                FormsTable.COLUMN_CR28C,
                FormsTable.COLUMN_CR28D,
                FormsTable.COLUMN_CR28E,
                FormsTable.COLUMN_CR28F,
                FormsTable.COLUMN_CR28FX,*/
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
        };

        String whereClause;
        String[] whereArgs;

        whereClause = FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " = ''";
        whereArgs = null;

        String groupBy = null;
        String having = null;
        String orderBy = FormsTable.COLUMN_ID + " ASC";

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
                Log.d(TAG, "getUnsyncedForms: " + c.getCount());
                Form form = new Form();
                allForms.add(form.Hydrate(c));
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
                FormsTable.COLUMN_COUNTRY,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_SA,
/*                FormsTable.COLUMN_CR01,
                FormsTable.COLUMN_CR02,
                FormsTable.COLUMN_CR03,
                FormsTable.COLUMN_CR04,
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR06X,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08D,
                FormsTable.COLUMN_CR08M,
                FormsTable.COLUMN_CR08Y,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                FormsTable.COLUMN_CR11,
                FormsTable.COLUMN_CR12,
                FormsTable.COLUMN_CR13,
                FormsTable.COLUMN_CR14D,
                FormsTable.COLUMN_CR14M,
                FormsTable.COLUMN_CR14Y,
                FormsTable.COLUMN_CR15M,
                FormsTable.COLUMN_CR15Y,
                FormsTable.COLUMN_CR16,
                FormsTable.COLUMN_CR17,
                FormsTable.COLUMN_CR18,
                FormsTable.COLUMN_CR19,
                FormsTable.COLUMN_CR20,
                FormsTable.COLUMN_CR21,
                FormsTable.COLUMN_CR22,
                FormsTable.COLUMN_CR23,
                FormsTable.COLUMN_CR24A,
                FormsTable.COLUMN_CR24B,
                FormsTable.COLUMN_CR24C,
                FormsTable.COLUMN_CR24D,
                FormsTable.COLUMN_CR24E,
                FormsTable.COLUMN_CR24F,
                FormsTable.COLUMN_CR25,
                FormsTable.COLUMN_CR26,
                FormsTable.COLUMN_CR27A,
                FormsTable.COLUMN_CR27B,
                FormsTable.COLUMN_CR27C,
                FormsTable.COLUMN_CR28A,
                FormsTable.COLUMN_CR28B,
                FormsTable.COLUMN_CR28C,
                FormsTable.COLUMN_CR28D,
                FormsTable.COLUMN_CR28E,
                FormsTable.COLUMN_CR28F,
                FormsTable.COLUMN_CR28FX,*/
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
                allForms = new Form().Hydrate(c);
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
                FormsTable.COLUMN_COUNTRY,
                FormsTable.COLUMN_DISTRICT_CODE,
                FormsTable.COLUMN_DISTRICT,
                FormsTable.COLUMN_UC_CODE,
                FormsTable.COLUMN_UC,
                FormsTable.COLUMN_VILLAGE_CODE,
                FormsTable.COLUMN_VILLAGE,
                FormsTable.COLUMN_SA,
/*                FormsTable.COLUMN_CR01,
                FormsTable.COLUMN_CR02,
                FormsTable.COLUMN_CR03,
                FormsTable.COLUMN_CR04,
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR06X,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08D,
                FormsTable.COLUMN_CR08M,
                FormsTable.COLUMN_CR08Y,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                FormsTable.COLUMN_CR11,
                FormsTable.COLUMN_CR12,
                FormsTable.COLUMN_CR13,
                FormsTable.COLUMN_CR14D,
                FormsTable.COLUMN_CR14M,
                FormsTable.COLUMN_CR14Y,
                FormsTable.COLUMN_CR15M,
                FormsTable.COLUMN_CR15Y,
                FormsTable.COLUMN_CR16,
                FormsTable.COLUMN_CR17,
                FormsTable.COLUMN_CR18,
                FormsTable.COLUMN_CR19,
                FormsTable.COLUMN_CR20,
                FormsTable.COLUMN_CR21,
                FormsTable.COLUMN_CR22,
                FormsTable.COLUMN_CR23,
                FormsTable.COLUMN_CR24A,
                FormsTable.COLUMN_CR24B,
                FormsTable.COLUMN_CR24C,
                FormsTable.COLUMN_CR24D,
                FormsTable.COLUMN_CR24E,
                FormsTable.COLUMN_CR24F,
                FormsTable.COLUMN_CR25,
                FormsTable.COLUMN_CR26,
                FormsTable.COLUMN_CR27A,
                FormsTable.COLUMN_CR27B,
                FormsTable.COLUMN_CR27C,
                FormsTable.COLUMN_CR28A,
                FormsTable.COLUMN_CR28B,
                FormsTable.COLUMN_CR28C,
                FormsTable.COLUMN_CR28D,
                FormsTable.COLUMN_CR28E,
                FormsTable.COLUMN_CR28F,
                FormsTable.COLUMN_CR28FX,*/
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
                allForms = new Form().Hydrate(c);
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
    public void updateSyncedForms(String id) {
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
}