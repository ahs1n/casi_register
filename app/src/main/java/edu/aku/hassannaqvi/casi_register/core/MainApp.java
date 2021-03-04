package edu.aku.hassannaqvi.casi_register.core;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.json.JSONArray;

import java.util.List;

import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.Identification;
import edu.aku.hassannaqvi.casi_register.models.Users;
import edu.aku.hassannaqvi.casi_register.models.Villages;


/**
 * @author hassan.naqvi.
 */

public class MainApp extends Application {

    public static final String TAG = "AppMain";
    //    public static final String _IP = "https://vcoe1.aku.edu";// .LIVE server
    public static final String _IP = "http://f38158";// .TEST server
    public static final String _HOST_URL = MainApp._IP + "/casi_gm/api/";// .TEST server;
    public static final String _SERVER_URL = "sync.php";
    public static final String _SERVER_GET_URL = "getData.php";
    public static final String _PHOTO_UPLOAD_URL = MainApp._IP + "/casi_gm/api/uploads.php";
    public static final String _UPDATE_URL = MainApp._IP + "/casi_gm/app/";
    public static AppInfo appInfo;
    public static Boolean admin = false;
    public static Users user;
    public static Form form;
    public static String DeviceURL = "devices.php";
    public static String IMEI;
    public static String UC_ID;
    public static List<JSONArray> uploadData;
    public static String[] downloadData;
    public static Villages mainInfo;


    @Override
    public void onCreate() {
        super.onCreate();

        //Initiate DateTime
        AndroidThreeTen.init(this);
        //Initializ App info
        appInfo = new AppInfo(this);

    }

}