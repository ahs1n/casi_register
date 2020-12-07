package edu.aku.hassannaqvi.casi_register.contracts;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {

    public static String CONTENT_AUTHORITY = "edu.aku.hassannaqvi.uen_tmk_el";

    public static abstract class FormsTable implements BaseColumns {
        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_CR01 = "cr01";
        public static final String COLUMN_CR02 = "cr02";
        public static final String COLUMN_CR03 = "cr03";
        public static final String COLUMN_CR04 = "cr04";
        public static final String COLUMN_CR05 = "cr05";
        public static final String COLUMN_CR06 = "cr06";
        public static final String COLUMN_CR07 = "cr07";
        public static final String COLUMN_CR08D = "cr08d";
        public static final String COLUMN_CR08M = "cr08m";
        public static final String COLUMN_CR08Y = "cr08y";
        public static final String COLUMN_CR09 = "cr09";
        public static final String COLUMN_CR10 = "cr10";
        public static final String COLUMN_CR11 = "cr11";
        public static final String COLUMN_CR12 = "cr12";
        public static final String COLUMN_CR13 = "cr13";
        public static final String COLUMN_CR14D = "cr14d";
        public static final String COLUMN_CR14M = "cr14m";
        public static final String COLUMN_CR14Y = "cr14y";
        public static final String COLUMN_CR15M = "cr15m";
        public static final String COLUMN_CR15Y = "cr15y";
        public static final String COLUMN_CR16 = "cr16";
        public static final String COLUMN_CR17 = "cr17";
        public static final String COLUMN_CR18 = "cr18";
        public static final String COLUMN_CR19 = "cr19";
        public static final String COLUMN_CR20 = "cr20";
        public static final String COLUMN_CR21 = "cr21";
        public static final String COLUMN_CR22 = "cr22";
        public static final String COLUMN_CR23 = "cr23";
        public static final String COLUMN_CR24A = "cr24a";
        public static final String COLUMN_CR24B = "cr24b";
        public static final String COLUMN_CR24C = "cr24c";
        public static final String COLUMN_CR24D = "cr24d";
        public static final String COLUMN_CR24E = "cr24e";
        public static final String COLUMN_CR24F = "cr24f";
        public static final String COLUMN_CR25 = "cr25";
        public static final String COLUMN_CR26 = "cr26";
        public static final String COLUMN_CR27A = "cr27a";
        public static final String COLUMN_CR27B = "cr27b";
        public static final String COLUMN_CR27C = "cr27c";
        public static final String COLUMN_CR28A = "cr28a";
        public static final String COLUMN_CR28B = "cr28b";
        public static final String COLUMN_CR28C = "cr28c";
        public static final String COLUMN_CR28D = "cr28d";
        public static final String COLUMN_CR28E = "cr28e";
        public static final String COLUMN_CR28F = "cr28f";
        public static final String COLUMN_CR28FX = "cr28fx";
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
