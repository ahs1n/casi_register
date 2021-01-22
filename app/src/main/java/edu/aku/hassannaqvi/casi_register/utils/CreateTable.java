package edu.aku.hassannaqvi.casi_register.utils;

import edu.aku.hassannaqvi.casi_register.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.casi_register.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.casi_register.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.casi_register.contracts.VillagesContract;
import edu.aku.hassannaqvi.casi_register.contracts.ZStandardContract;

public final class CreateTable {

    public static final String DATABASE_NAME = "casi_register.db";
    public static final String DB_NAME = "casi_register_copy.db";
    public static final String PROJECT_NAME = "casi_register";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsTable.COLUMN_SYSDATE + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT,"
            + FormsTable.COLUMN_USERNAME + " TEXT,"
            + FormsTable.COLUMN_COUNTRY_CODE + " TEXT,"
            + FormsTable.COLUMN_COUNTRY + " TEXT,"
            + FormsTable.COLUMN_DISTRICT_CODE + " TEXT,"
            + FormsTable.COLUMN_DISTRICT + " TEXT,"
            + FormsTable.COLUMN_UC_CODE + " TEXT,"
            + FormsTable.COLUMN_UC + " TEXT,"
            + FormsTable.COLUMN_VILLAGE_CODE + " TEXT,"
            + FormsTable.COLUMN_VILLAGE + " TEXT,"
            + FormsTable.COLUMN_CS + " TEXT,"
            + FormsTable.COLUMN_CSFP + " TEXT,"
            + FormsTable.COLUMN_WS + " TEXT,"
            + FormsTable.COLUMN_WSFP + " TEXT,"
            + FormsTable.COLUMN_GPSLAT + " TEXT,"
            + FormsTable.COLUMN_GPSLNG + " TEXT,"
            + FormsTable.COLUMN_GPSDATE + " TEXT,"
            + FormsTable.COLUMN_GPSACC + " TEXT,"
            + FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsTable.COLUMN_ENDINGDATETIME + " TEXT,"
            + FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsTable.COLUMN_ISTATUS96x + " TEXT,"
            + FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULL_NAME + " TEXT"
            + " );";


    public static final String SQL_CREATE_VILLAGES = "CREATE TABLE " + VillagesContract.Table.TABLE_NAME + "("
            + VillagesContract.Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VillagesContract.Table.COLUMN_COUNTRY + " TEXT,"
            + VillagesContract.Table.COLUMN_DISTRICT + " TEXT,"
            + VillagesContract.Table.COLUMN_UC + " TEXT,"
            + VillagesContract.Table.COLUMN_VILLAGE + " TEXT,"
            + VillagesContract.Table.COLUMN_COUNTRY_CODE + " TEXT,"
            + VillagesContract.Table.COLUMN_DISTRICT_CODE + " TEXT,"
            + VillagesContract.Table.COLUMN_UC_CODE + " TEXT,"
            + VillagesContract.Table.COLUMN_VILLLAGE_CODE + " TEXT,"
            + VillagesContract.Table.COLUMN_CLUSTER_NO + " TEXT );";


    public static final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";


    public static final String SQL_CREATE_ZSTANDARD = "CREATE TABLE " + ZStandardContract.Table.TABLE_NAME + " (" +
            ZStandardContract.Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ZStandardContract.Table.COLUMN_SEX + " TEXT, " +
            ZStandardContract.Table.COLUMN_AGE + " TEXT, " +
            ZStandardContract.Table.COLUMN_MEASURE + " TEXT, " +
            ZStandardContract.Table.COLUMN_L + " TEXT, " +
            ZStandardContract.Table.COLUMN_M + " TEXT, " +
            ZStandardContract.Table.COLUMN_S + " TEXT, " +
            ZStandardContract.Table.COLUMN_CAT + " TEXT " +
            ");";

}
