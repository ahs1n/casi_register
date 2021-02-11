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
        public static final String COLUMN_REGION_CODE = "region_code";
        public static final String COLUMN_HF_CODE = "hf_code";
        public static final String COLUMN_HEALTH_FACILITY = "health_facility";
        public static final String COLUMN_FACILITY_TYPE = "facility_type";
    }
}