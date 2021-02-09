package edu.aku.hassannaqvi.casi_register.utils;

import edu.aku.hassannaqvi.casi_register.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.casi_register.contracts.ZStandardContract;
import edu.aku.hassannaqvi.casi_register.models.Users.UsersTable;
import edu.aku.hassannaqvi.casi_register.models.VersionApp.VersionAppTable;
import edu.aku.hassannaqvi.casi_register.models.Villages.VillagesTable;

public final class CreateTable {

    public static final String DATABASE_NAME = "casi_register.db";
    public static final String DATABASE_COPY = "casi_register_copy.db";
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
            + UsersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULLNAME + " TEXT"
            + " );";


    public static final String SQL_CREATE_VILLAGES = "CREATE TABLE " + VillagesTable.TABLE_NAME + "("
            + VillagesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VillagesTable.COLUMN_COUNTRY + " TEXT,"
            + VillagesTable.COLUMN_DISTRICT + " TEXT,"
            + VillagesTable.COLUMN_UC + " TEXT,"
            + VillagesTable.COLUMN_VILLAGE + " TEXT,"
            + VillagesTable.COLUMN_COUNTRY_CODE + " TEXT,"
            + VillagesTable.COLUMN_DISTRICT_CODE + " TEXT,"
            + VillagesTable.COLUMN_UC_CODE + " TEXT,"
            + VillagesTable.COLUMN_VILLLAGE_CODE + " TEXT,"
            + VillagesTable.COLUMN_CLUSTER_NO + " TEXT );";


    public static final String SQL_CREATE_VERSIONAPP = "CREATE TABLE " + VersionAppTable.TABLE_NAME + " (" +
            VersionAppTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VersionAppTable.COLUMN_VERSION_CODE + " TEXT, " +
            VersionAppTable.COLUMN_VERSION_NAME + " TEXT, " +
            VersionAppTable.COLUMN_PATH_NAME + " TEXT " +
            ");";


    public static final String SQL_CREATE_ZSTANDARD = "CREATE TABLE " + ZStandardContract.ZScoreTable.TABLE_NAME + " (" +
            ZStandardContract.ZScoreTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ZStandardContract.ZScoreTable.COLUMN_SEX + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_AGE + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_MEASURE + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_L + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_M + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_S + " TEXT, " +
            ZStandardContract.ZScoreTable.COLUMN_CAT + " TEXT " +
            ");";

}