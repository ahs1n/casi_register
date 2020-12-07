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
            + FormsTable.COLUMN_CR01 + " TEXT,"
            + FormsTable.COLUMN_CR02 + " TEXT,"
            + FormsTable.COLUMN_CR03 + " TEXT,"
            + FormsTable.COLUMN_CR04 + " TEXT,"
            + FormsTable.COLUMN_CR05 + " TEXT,"
            + FormsTable.COLUMN_CR06 + " TEXT,"
            + FormsTable.COLUMN_CR07 + " TEXT,"
            + FormsTable.COLUMN_CR08D + " TEXT,"
            + FormsTable.COLUMN_CR08M + " TEXT,"
            + FormsTable.COLUMN_CR08Y + " TEXT,"
            + FormsTable.COLUMN_CR09 + " TEXT,"
            + FormsTable.COLUMN_CR10 + " TEXT,"
            + FormsTable.COLUMN_CR11 + " TEXT,"
            + FormsTable.COLUMN_CR12 + " TEXT,"
            + FormsTable.COLUMN_CR13 + " TEXT,"
            + FormsTable.COLUMN_CR14D + " TEXT,"
            + FormsTable.COLUMN_CR14M + " TEXT,"
            + FormsTable.COLUMN_CR14Y + " TEXT,"
            + FormsTable.COLUMN_CR15M + " TEXT,"
            + FormsTable.COLUMN_CR15Y + " TEXT,"
            + FormsTable.COLUMN_CR16 + " TEXT,"
            + FormsTable.COLUMN_CR17 + " TEXT,"
            + FormsTable.COLUMN_CR18 + " TEXT,"
            + FormsTable.COLUMN_CR19 + " TEXT,"
            + FormsTable.COLUMN_CR20 + " TEXT,"
            + FormsTable.COLUMN_CR21 + " TEXT,"
            + FormsTable.COLUMN_CR22 + " TEXT,"
            + FormsTable.COLUMN_CR23 + " TEXT,"
            + FormsTable.COLUMN_CR24A + " TEXT,"
            + FormsTable.COLUMN_CR24B + " TEXT,"
            + FormsTable.COLUMN_CR24C + " TEXT,"
            + FormsTable.COLUMN_CR24D + " TEXT,"
            + FormsTable.COLUMN_CR24E + " TEXT,"
            + FormsTable.COLUMN_CR24F + " TEXT,"
            + FormsTable.COLUMN_CR25 + " TEXT,"
            + FormsTable.COLUMN_CR26 + " TEXT,"
            + FormsTable.COLUMN_CR27 + " TEXT,"
            + FormsTable.COLUMN_CR28A + " TEXT,"
            + FormsTable.COLUMN_CR28B + " TEXT,"
            + FormsTable.COLUMN_CR28C + " TEXT,"
            + FormsTable.COLUMN_CR28D + " TEXT,"
            + FormsTable.COLUMN_CR28E + " TEXT,"
            + FormsTable.COLUMN_CR28F + " TEXT,"
            + FormsTable.COLUMN_CR28FX + " TEXT,"
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
