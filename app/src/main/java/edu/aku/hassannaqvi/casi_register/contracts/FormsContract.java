package edu.aku.hassannaqvi.casi_register.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author hassan.naqvi.
 */

public class FormsContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.casi_register";

    public static abstract class FormsTable implements BaseColumns {
        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "casi_register";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_COUNTRY_CODE = "countryCode";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_DISTRICT_CODE = "districtCode";
        public static final String COLUMN_DISTRICT = "district";
        public static final String COLUMN_UC_CODE = "ucCode";
        public static final String COLUMN_UC = "uc";
        public static final String COLUMN_VILLAGE = "village";
        public static final String COLUMN_VILLAGE_CODE = "villageCode";
        public static final String COLUMN_CS = "cS";
        public static final String COLUMN_CSFP = "cSFP";
        public static final String COLUMN_WS = "wS";
        public static final String COLUMN_WSFP = "wSFP";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS96x = "istatus96x";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDATE = "gpsdate";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_FORM_TYPE = "formType";
        public static String PATH = "forms";
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();
        public static String SERVER_URL = "sync.php";


        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
