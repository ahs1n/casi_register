package edu.aku.hassannaqvi.casi_register.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class VillagesContract {
    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.casi_register";

    public static abstract class Table implements BaseColumns {

        public static final String TABLE_NAME = "villages";

        public static final String _ID = "_id";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_DISTRICT = "district";
        public static final String COLUMN_UC = "uc";
        public static final String COLUMN_VILLAGE = "village";
        public static final String COLUMN_COUNTRY_CODE = "country_code";
        public static final String COLUMN_DISTRICT_CODE = "district_code";
        public static final String COLUMN_UC_CODE = "uc_code";
        public static final String COLUMN_VILLLAGE_CODE = "villlage_code";
        public static final String COLUMN_CLUSTER_NO = "cluster_no";
        public static final String SERVER_URI = "villages.php";
        public static String PATH = "villages";
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        //        public static final String REGION_DSS = "region";
        public static Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY)
                .buildUpon().appendPath(PATH).build();

        public static String getMovieKeyFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri buildUriWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}