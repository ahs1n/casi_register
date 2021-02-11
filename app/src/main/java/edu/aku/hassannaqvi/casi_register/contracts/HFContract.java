package edu.aku.hassannaqvi.casi_register.contracts;

import android.provider.BaseColumns;

/**
 * @author abdul.sajid
 */

public class HFContract {
    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.casi_register";

    public static abstract class HFTable implements BaseColumns {

        public static final String TABLE_NAME = "healthfacility";

        public static final String _ID = "_id";
        public static final String COLUMN_COUNTRY_CODE = "country_code";
        public static final String COLUMN_COUNTRY_NAME = "country_name";
        public static final String COLUMN_REGION_CODE = "region_code";
        public static final String COLUMN_REGION = "region";
        public static final String COLUMN_DISTRICT_CODE = "district_code";
        public static final String COLUMN_DISTRICT = "district";
        public static final String COLUMN_UC_CODE = "uc_code";
        public static final String COLUMN_UC = "uc";
        public static final String COLUMN_VILLAGE_CODE = "village_code";
        public static final String COLUMN_VILLAGE = "village";
    }
}