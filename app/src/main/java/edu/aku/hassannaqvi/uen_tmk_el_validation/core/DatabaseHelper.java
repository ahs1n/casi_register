package edu.aku.hassannaqvi.uen_tmk_el_validation.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.DistrictContract;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.UCContract;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.UCContract.UCTable;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.UsersContract;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.VersionAppContract;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.VillageContract;
import edu.aku.hassannaqvi.uen_tmk_el_validation.contracts.VillageContract.VillageTable;
import edu.aku.hassannaqvi.uen_tmk_el_validation.models.Form;
import edu.aku.hassannaqvi.uen_tmk_el_validation.models.Users;
import edu.aku.hassannaqvi.uen_tmk_el_validation.models.VersionApp;

import static edu.aku.hassannaqvi.uen_tmk_el_validation.utils.CreateTable.DATABASE_NAME;
import static edu.aku.hassannaqvi.uen_tmk_el_validation.utils.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.uen_tmk_el_validation.utils.CreateTable.SQL_CREATE_DISTRICTS;
import static edu.aku.hassannaqvi.uen_tmk_el_validation.utils.CreateTable.SQL_CREATE_FORMS;
import static edu.aku.hassannaqvi.uen_tmk_el_validation.utils.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.uen_tmk_el_validation.utils.CreateTable.SQL_CREATE_VERSIONAPP;
import static edu.aku.hassannaqvi.uen_tmk_el_validation.utils.CreateTable.SQL_CREATE_VILLAGE_TABLE;


public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_VILLAGE_TABLE);
        db.execSQL(SQL_CREATE_DISTRICTS);
        db.execSQL(SQL_CREATE_VERSIONAPP);
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

    public int syncUCs(JSONArray distList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UCTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < distList.length(); i++) {

                JSONObject jsonObjectUser = distList.getJSONObject(i);

                UCContract dist = new UCContract();
                dist.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UCTable.COLUMN_UC_CODE, dist.getUc_code());
                values.put(UCTable.COLUMN_UC_NAME, dist.getUc_name());
                values.put(UCTable.COLUMN_TEHSIL_CODE, dist.getTehsil_code());
                long rowID = db.insert(UCTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncDist(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public int syncVillages(JSONArray enumList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VillageTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < enumList.length(); i++) {
                JSONObject jsonObjectCC;
                try {
                    jsonObjectCC = enumList.getJSONObject(i);
                    VillageContract Vc = new VillageContract();
                    Vc.Sync(jsonObjectCC);

                    ContentValues values = new ContentValues();

                    values.put(VillageTable.COLUMN_VILLAGE_CODE, Vc.getVillage_code());
                    values.put(VillageTable.COLUMN_VILLAGE_NAME, Vc.getVillage_name());
                    values.put(VillageTable.COLUMN_AREA_CODE, Vc.getArea_code());
                    values.put(VillageTable.COLUMN_CLUSTER_CODE, Vc.getCluster_code());

                    long rowID = db.insert(VillageTable.TABLE_NAME, null, values);
                    if (rowID != -1) insertCount++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            Log.d(TAG, "syncEnumBlocks(e): " + e);
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
        values.put(FormsTable.COLUMN_CR05, form.getCr05());
        values.put(FormsTable.COLUMN_CR06, form.getCr06());
        values.put(FormsTable.COLUMN_CR07, form.getCr07());
        values.put(FormsTable.COLUMN_CR08, form.getCr08());
        values.put(FormsTable.COLUMN_CR09, form.getCr09());
        values.put(FormsTable.COLUMN_CR10, form.getCr10());
        /*values.put(FormsTable.COLUMN_ELB7, form.getElb7());
        values.put(FormsTable.COLUMN_ELB8, form.getElb8());
        values.put(FormsTable.COLUMN_ELB8a, form.getElb8a());
        values.put(FormsTable.COLUMN_ELB09, form.getElb09());
        values.put(FormsTable.COLUMN_ELB10, form.getElb10());
        values.put(FormsTable.COLUMN_ELB11, form.getElb11());
        values.put(FormsTable.COLUMN_ELB12, form.getElb12());
        values.put(FormsTable.COLUMN_SC, form.getsC());
        values.put(FormsTable.COLUMN_SD, form.getsD());
        values.put(FormsTable.COLUMN_SE, form.getsE());
        values.put(FormsTable.COLUMN_SF, form.getsF());
        values.put(FormsTable.COLUMN_SG, form.getsG());
        values.put(FormsTable.COLUMN_SH, form.getsH());
        values.put(FormsTable.COLUMN_SI, form.getsI());
        values.put(FormsTable.COLUMN_SJ, form.getsJ());
        values.put(FormsTable.COLUMN_SK, form.getsK());
        values.put(FormsTable.COLUMN_SL, form.getsL());
        values.put(FormsTable.COLUMN_SN, form.getsN());*/
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
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,

                /* FormsTable.COLUMN_ELB7,
                 FormsTable.COLUMN_ELB8,
                 FormsTable.COLUMN_ELB8a,
                 FormsTable.COLUMN_ELB09,
                 FormsTable.COLUMN_ELB10,
                 FormsTable.COLUMN_ELB11,
                 FormsTable.COLUMN_ELB12,
                 FormsTable.COLUMN_SC,
                 FormsTable.COLUMN_SD,
                 FormsTable.COLUMN_SE,
                 FormsTable.COLUMN_SF,
                 FormsTable.COLUMN_SG,
                 FormsTable.COLUMN_SH,
                 FormsTable.COLUMN_SI,
                 FormsTable.COLUMN_SJ,
                 FormsTable.COLUMN_SK,
                 FormsTable.COLUMN_SL,
                 FormsTable.COLUMN_SN,*/
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
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                /*FormsTable.COLUMN_ELB7,
                FormsTable.COLUMN_ELB8,
                FormsTable.COLUMN_ELB8a,
                FormsTable.COLUMN_ELB09,
                FormsTable.COLUMN_ELB10,
                FormsTable.COLUMN_ELB11,
                FormsTable.COLUMN_ELB12,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_SL,
                FormsTable.COLUMN_SN,*/
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
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                /*FormsTable.COLUMN_ELB7,
                FormsTable.COLUMN_ELB8,
                FormsTable.COLUMN_ELB8a,
                FormsTable.COLUMN_ELB09,
                FormsTable.COLUMN_ELB10,
                FormsTable.COLUMN_ELB11,
                FormsTable.COLUMN_ELB12,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_SL,
                FormsTable.COLUMN_SN,*/
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
                form.setCr05(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR05)));
                form.setCr06(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR06)));
                form.setCr07(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR07)));
                form.setCr08(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR08)));
                form.setCr09(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR09)));
                form.setCr10(c.getString(c.getColumnIndex(FormsTable.COLUMN_CR10)));
                /*form.setElb7(c.getString(c.getColumnIndex(FormsTable.COLUMN_ELB7)));
                form.setElb8(c.getString(c.getColumnIndex(FormsTable.COLUMN_ELB8)));
                form.setElb8a(c.getString(c.getColumnIndex(FormsTable.COLUMN_ELB8a)));
                form.setElb09(c.getString(c.getColumnIndex(FormsTable.COLUMN_ELB09)));
                form.setElb10(c.getString(c.getColumnIndex(FormsTable.COLUMN_ELB10)));
                form.setElb11(c.getString(c.getColumnIndex(FormsTable.COLUMN_ELB11)));
                form.setElb12(c.getString(c.getColumnIndex(FormsTable.COLUMN_ELB12)));
                form.setsC(c.getString(c.getColumnIndex(FormsTable.COLUMN_SC)));
                form.setsD(c.getString(c.getColumnIndex(FormsTable.COLUMN_SD)));
                form.setsE(c.getString(c.getColumnIndex(FormsTable.COLUMN_SE)));
                form.setsF(c.getString(c.getColumnIndex(FormsTable.COLUMN_SF)));
                form.setsG(c.getString(c.getColumnIndex(FormsTable.COLUMN_SG)));
                form.setsH(c.getString(c.getColumnIndex(FormsTable.COLUMN_SH)));
                form.setsI(c.getString(c.getColumnIndex(FormsTable.COLUMN_SI)));
                form.setsJ(c.getString(c.getColumnIndex(FormsTable.COLUMN_SJ)));
                form.setsK(c.getString(c.getColumnIndex(FormsTable.COLUMN_SK)));
                form.setsL(c.getString(c.getColumnIndex(FormsTable.COLUMN_SL)));
                form.setsN(c.getString(c.getColumnIndex(FormsTable.COLUMN_SN)));*/
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
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_SC + " = ? ";
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
                form.setsC(c.getString(c.getColumnIndex(FormsTable.COLUMN_SC)));
                form.setsD(c.getString(c.getColumnIndex(FormsTable.COLUMN_SD)));
                form.setsE(c.getString(c.getColumnIndex(FormsTable.COLUMN_SE)));
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
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SC,
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
                form.setsC(c.getString(c.getColumnIndex(FormsTable.COLUMN_SC)));
                form.setsD(c.getString(c.getColumnIndex(FormsTable.COLUMN_SD)));
                form.setsE(c.getString(c.getColumnIndex(FormsTable.COLUMN_SE)));
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
    public List<UCContract> getUCs() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                UCTable._ID,
                UCTable.COLUMN_UC_CODE,
                UCTable.COLUMN_UC_NAME,
                UCTable.COLUMN_TEHSIL_CODE
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = UCTable._ID + " ASC";
        List<UCContract> allEB = new ArrayList<>();
        try {
            c = db.query(
                    UCTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new UCContract().Hydrate(c));
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
    public List<UCContract> getDistrics() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                DistrictContract.DISTRICTSTable._ID,
                DistrictContract.DISTRICTSTable.COLUMN_DISTRICT_CODE,
                DistrictContract.DISTRICTSTable.COLUMN_DISTRICT_NAME,
                DistrictContract.DISTRICTSTable.COLUMN_DISTRICT_TYPE
        };

        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = DistrictContract.DISTRICTSTable._ID + " ASC";
        List<UCContract> allEB = new ArrayList<>();
        try {
            c = db.query(
                    DistrictContract.DISTRICTSTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new UCContract().Hydrate(c));
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
    public List<VillageContract> getEnumBlock(String uc_id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                VillageTable._ID,
                VillageTable.COLUMN_VILLAGE_CODE,
                VillageTable.COLUMN_VILLAGE_NAME,
                VillageTable.COLUMN_AREA_CODE,
                VillageTable.COLUMN_CLUSTER_CODE,
        };

        String whereClause = VillageTable.COLUMN_AREA_CODE + " LIKE ? ";
        String[] whereArgs = {"" + uc_id + "%"};
        String groupBy = null;
        String having = null;

        String orderBy = VillageTable._ID + " ASC";
        List<VillageContract> allEB = new ArrayList<>();
        try {
            c = db.query(
                    VillageTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allEB.add(new VillageContract().HydrateEnum(c));
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
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                /*FormsTable.COLUMN_ELB7,
                FormsTable.COLUMN_ELB8,
                FormsTable.COLUMN_ELB8a,
                FormsTable.COLUMN_ELB09,
                FormsTable.COLUMN_ELB10,
                FormsTable.COLUMN_ELB11,
                FormsTable.COLUMN_ELB12,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_SL,
                FormsTable.COLUMN_SN,*/
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
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                /*FormsTable.COLUMN_ELB7,
                FormsTable.COLUMN_ELB8,
                FormsTable.COLUMN_ELB8a,
                FormsTable.COLUMN_ELB09,
                FormsTable.COLUMN_ELB10,
                FormsTable.COLUMN_ELB11,
                FormsTable.COLUMN_ELB12,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_SL,
                FormsTable.COLUMN_SN,*/
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
        String whereClause = FormsTable.COLUMN_SD + "=? AND " + FormsTable.COLUMN_SC + "=?";
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


    //Get FamilyMembers data for info activity
    public Form getSelectedForm(String cluster, String subcluster, String hhno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                FormsTable.COLUMN_UID,
                FormsTable.COLUMN_USERNAME,
                FormsTable.COLUMN_SYSDATE,
                FormsTable.COLUMN_CR05,
                FormsTable.COLUMN_CR06,
                FormsTable.COLUMN_CR07,
                FormsTable.COLUMN_CR08,
                FormsTable.COLUMN_CR09,
                FormsTable.COLUMN_CR10,
                /*FormsTable.COLUMN_ELB7,
                FormsTable.COLUMN_ELB8,
                FormsTable.COLUMN_ELB8a,
                FormsTable.COLUMN_ELB09,
                FormsTable.COLUMN_ELB10,
                FormsTable.COLUMN_ELB11,
                FormsTable.COLUMN_ELB12,
                FormsTable.COLUMN_SC,
                FormsTable.COLUMN_SD,
                FormsTable.COLUMN_SE,
                FormsTable.COLUMN_SF,
                FormsTable.COLUMN_SG,
                FormsTable.COLUMN_SH,
                FormsTable.COLUMN_SI,
                FormsTable.COLUMN_SJ,
                FormsTable.COLUMN_SK,
                FormsTable.COLUMN_SL,
                FormsTable.COLUMN_SN,*/
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

        String whereClause = FormsTable.COLUMN_CR05 + "=? AND " + FormsTable.COLUMN_ELB8a + "=? AND " + FormsTable.COLUMN_ELB11 + "=? AND " + FormsTable.COLUMN_ISTATUS + "=? ";
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