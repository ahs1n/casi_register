package edu.aku.hassannaqvi.casi_register.utils;

import edu.aku.hassannaqvi.casi_register.contracts.DistrictContract;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.casi_register.contracts.UCContract;
import edu.aku.hassannaqvi.casi_register.contracts.UCContract.UCTable;
import edu.aku.hassannaqvi.casi_register.contracts.UsersContract.UsersTable;
import edu.aku.hassannaqvi.casi_register.contracts.VersionAppContract.VersionAppTable;
import edu.aku.hassannaqvi.casi_register.contracts.VillageContract.VillageTable;
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
            + FormsTable.COLUMN_CR05 + " TEXT,"
            + FormsTable.COLUMN_CR06 + " TEXT,"
            + FormsTable.COLUMN_CR07 + " TEXT,"
            + FormsTable.COLUMN_CR01 + " TEXT,"
            + FormsTable.COLUMN_CR09 + " TEXT,"
            + FormsTable.COLUMN_CR10 + " TEXT,"
            /*+ FormsTable.COLUMN_ELB7 + " TEXT,"
            + FormsTable.COLUMN_ELB8 + " TEXT,"
            + FormsTable.COLUMN_ELB8a + " TEXT,"
            + FormsTable.COLUMN_ELB09 + " TEXT,"
            + FormsTable.COLUMN_ELB10 + " TEXT,"
            + FormsTable.COLUMN_ELB11 + " TEXT,"
            + FormsTable.COLUMN_ELB12 + " TEXT,"
            + FormsTable.COLUMN_SC + " TEXT,"
            + FormsTable.COLUMN_SD + " TEXT,"
            + FormsTable.COLUMN_SE + " TEXT,"
            + FormsTable.COLUMN_SF + " TEXT,"
            + FormsTable.COLUMN_SG + " TEXT,"
            + FormsTable.COLUMN_SH + " TEXT,"
            + FormsTable.COLUMN_SI + " TEXT,"
            + FormsTable.COLUMN_SJ + " TEXT,"
            + FormsTable.COLUMN_SK + " TEXT,"
            + FormsTable.COLUMN_SL + " TEXT,"
            + FormsTable.COLUMN_SN + " TEXT,"*/
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


    public static final String SQL_CREATE_DISTRICTS = "CREATE TABLE " + DistrictContract.DISTRICTSTable.TABLE_NAME + "("
            + DistrictContract.DISTRICTSTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DistrictContract.DISTRICTSTable.COLUMN_DISTRICT_CODE + " TEXT,"
            + DistrictContract.DISTRICTSTable.COLUMN_DISTRICT_NAME + " TEXT,"
            + DistrictContract.DISTRICTSTable.COLUMN_DISTRICT_TYPE + " TEXT );";


    public static final String SQL_CREATE_UCs = "CREATE TABLE " + UCContract.UCTable.TABLE_NAME + "("
            + UCContract.UCTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UCTable.COLUMN_TEHSIL_CODE + " TEXT,"
            + UCTable.COLUMN_UC_CODE + " TEXT,"
            + UCTable.COLUMN_UC_NAME + " TEXT );";


    public static final String SQL_CREATE_VILLAGE_TABLE = "CREATE TABLE " + VillageTable.TABLE_NAME + " (" +
            VillageTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            VillageTable.COLUMN_VILLAGE_CODE + " TEXT, " +
            VillageTable.COLUMN_VILLAGE_NAME + " TEXT, " +
            VillageTable.COLUMN_AREA_CODE + " TEXT, " +
            VillageTable.COLUMN_CLUSTER_CODE + " TEXT );";


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
