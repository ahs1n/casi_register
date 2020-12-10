package edu.aku.hassannaqvi.casi_register.ui.other;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.FormsContract;
import edu.aku.hassannaqvi.casi_register.core.AndroidDatabaseManager;
import edu.aku.hassannaqvi.casi_register.core.DatabaseHelper;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.databinding.ActivityMainBinding;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.VersionApp;
import edu.aku.hassannaqvi.casi_register.models.Villages;
import edu.aku.hassannaqvi.casi_register.ui.list_activity.FormsReportDate;
import edu.aku.hassannaqvi.casi_register.ui.sections.SectionN02Activity;
import edu.aku.hassannaqvi.casi_register.utils.AndroidUtilityKt;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.CreateTable;
import edu.aku.hassannaqvi.casi_register.utils.WarningActivityInterface;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static edu.aku.hassannaqvi.casi_register.core.MainApp.appInfo;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.form;

public class MainActivity extends AppCompatActivity implements WarningActivityInterface {

    static File file;
    ActivityMainBinding bi;
    String dtToday = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date().getTime());
    String sysdateToday = new SimpleDateFormat("dd-MM-yy", Locale.getDefault()).format(new Date());
    SharedPreferences sharedPrefDownload;
    SharedPreferences.Editor editorDownload;
    DownloadManager downloadManager;
    String preVer = "", newVer = "";
    VersionApp versionApp;
    Long refID;
    //Setting Spinner
    List<String> countryName, districtName, ucName, villageName;
    Map<String, Villages> villageMap;
    List<Villages> areaList;
    Villages village;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {

                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(sharedPrefDownload.getLong("refID", 0));

                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                assert downloadManager != null;
                Cursor cursor = downloadManager.query(query);
                if (cursor.moveToFirst()) {
                    int colIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(colIndex)) {

                        editorDownload.putBoolean("flag", true);
                        editorDownload.commit();

                        Toast.makeText(context, "New App downloaded!!", Toast.LENGTH_SHORT).show();
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + " App New Version ").append(newVer).append("  Downloaded"));

                        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);

                        if (Objects.requireNonNull(taskInfo.get(0).topActivity).getClassName().equals(MainActivity.class.getName())) {
                            showDialog(newVer, preVer);
                        }
                    }
                }
            }
        }
    };
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);

     /*   //bi.txtinstalldate.setText(appInfo.getAppInfo());
        Collection<Form> todaysForms = appInfo.getDbHelper().getTodayForms(sysdateToday);
        Collection<Form> unsyncedForms = appInfo.getDbHelper().getUnsyncedForms();

        //TODO: Commented for testing
        *//* Collection<Form> unclosedForms = appInfo.getDbHelper().getUnclosedForms(); *//*

        StringBuilder rSumText = new StringBuilder()
                .append("TODAY'S RECORDS SUMMARY\r\n")
                .append("=======================\r\n")
                .append("\r\n")
                .append("Total Forms Today" + "(").append(dtToday).append("): ").append(todaysForms.size()).append("\r\n");
        String TAG = "MainActivity";
        if (todaysForms.size() > 0) {
            String iStatus;
            rSumText.append("---------------------------------------------------------\r\n")
                    .append("[  Name  ][Ref. No][Form Status][Sync Status]\r\n")
                    .append("---------------------------------------------------------\r\n");

            for (Form form : todaysForms) {
                Log.d(TAG, "onCreate: '" + form.getIstatus() + "'");
                switch (form.getIstatus()) {
                    case "1":
                        iStatus = "Complete   ";
                        break;
                    case "2":
                        iStatus = "Incomplete ";
                        break;
                    case "3":
                        iStatus = "Refused    ";
                        break;
                    case "96":
                        iStatus = "Other    ";
                        break;
                    case "":
                        iStatus = "Open     ";
                        break;
                    default:
                        iStatus = "  -N/A-  " + form.getIstatus();
                }

                rSumText
                        *//*.append((form.getMp101() + "          ").substring(0, 10))
                        .append((form.getMp102() + "      ").substring(0, 6))*//*
                        .append("  \t\t")
                        .append(iStatus)
                        .append("\t\t\t\t")
                        .append(form.getSynced() == null ? "Not Synced" : "Synced    ")
                        .append("\r\n")
                        .append("---------------------------------------------------------\r\n");
            }
        }
        SharedPreferences syncPref = getSharedPreferences("src", Context.MODE_PRIVATE);
        rSumText.append("\r\nDEVICE INFORMATION\r\n")
                .append("  ========================================================\r\n")

                //TODO: Commented for Testing
                *//* .append("\t|| Open Forms: \t\t\t\t\t\t").append(String.format("%02d", unclosedForms.size())) *//*
                .append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t||\r\n")
                .append("\t|| Unsynced Forms: \t\t\t\t").append(String.format("%02d", unsyncedForms.size()))
                .append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t||\r\n")
                .append("\t|| Last Data Download: \t\t").append(syncPref.getString("LastDataDownload", "Never Downloaded   "))
                .append("\t\t\t\t\t\t||\r\n")
                .append("\t|| Last Data Upload: \t\t\t").append(syncPref.getString("LastDataUpload", "Never Uploaded     "))
                .append("\t\t\t\t\t\t||\r\n")
                .append("\t|| Last Photo Upload: \t\t").append(syncPref.getString("LastPhotoUpload", "Never Uploaded     "))
                .append("\t\t\t\t\t\t||\r\n")
                .append("\t========================================================\r\n");
        bi.recordSummary.setText(rSumText);

        Log.d(TAG, "onCreate: " + rSumText);*/
        if (MainApp.admin) {
            bi.databaseBtn.setVisibility(View.VISIBLE);
        } else {
            bi.databaseBtn.setVisibility(View.GONE);
        }

        // Auto download app
        sharedPrefDownload = getSharedPreferences("appDownload", MODE_PRIVATE);
        editorDownload = sharedPrefDownload.edit();
        versionApp = appInfo.getDbHelper().getVersionApp();
        if (versionApp.getVersioncode() != null) {

            preVer = appInfo.getVersionName() + "." + appInfo.getVersionCode();
            newVer = versionApp.getVersionname() + "." + versionApp.getVersioncode();

            if (appInfo.getVersionCode() < Integer.parseInt(versionApp.getVersioncode())) {
                bi.lblAppVersion.setVisibility(View.VISIBLE);

                String fileName = CreateTable.DATABASE_NAME.replace(".db", "-New-Apps");
                file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName, versionApp.getPathname());

                if (file.exists()) {
                    bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + " New Version ").append(newVer).append("  Downloaded"));
                    showDialog(newVer, preVer);
                } else {
                    NetworkInfo networkInfo = ((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + " App New Version ").append(newVer).append("  Downloading.."));
                        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(MainApp._UPDATE_URL + versionApp.getPathname());
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setDestinationInExternalPublicDir(fileName, versionApp.getPathname())
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setTitle("Downloading " + getString(R.string.app_name) + " App new App ver." + newVer);
                        refID = downloadManager.enqueue(request);

                        editorDownload.putLong("refID", refID);
                        editorDownload.putBoolean("flag", false);
                        editorDownload.apply();

                    } else {
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + " App New Version ").append(newVer).append("  Available..\n(Can't download.. Internet connectivity issue!!)"));
                    }
                }

            } else {
                bi.lblAppVersion.setVisibility(View.GONE);
                bi.lblAppVersion.setText(null);
            }
        }
        registerReceiver(broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

//        Testing visibility
        if (Integer.parseInt(appInfo.getVersionName().split("\\.")[0]) > 0) {
            bi.testing.setVisibility(View.GONE);
        } else {
            bi.testing.setVisibility(View.VISIBLE);
        }

        setUIContent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.onSync:
                intent = new Intent(MainActivity.this, SyncActivity.class);
                break;
         /*   case R.id.checkOpenForms:
                intent = new Intent(MainActivity.this, PendingFormsActivity.class);
                break;*/
            case R.id.formsReportDate:
                intent = new Intent(MainActivity.this, FormsReportDate.class);
                break;
        /*    case R.id.formsReportCluster:
                intent = new Intent(MainActivity.this, FormsReportCluster.class);
                break;*/
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void callWarningActivity() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    private void showDialog(String newVer, String preVer) {
        AppUtilsKt.openWarningActivity(
                this,
                getString(R.string.app_name) + " APP is available!",
                getString(R.string.app_name) + " App Ver." + newVer + " is now available. Your are currently using older Ver." + preVer + ".\nInstall new version to use this app.",
                "Install",
                "Cancel"
        );
    }

    @SuppressLint("NonConstantResourceId")
    public void openSpecificActivity(View v) {
        Intent oF = null;
        switch (v.getId()) {
            case R.id.formA:
                if (!Validator.emptyCheckingContainer(this, bi.fldGrpna10)) return;
                SaveDraft();
                UpdateDB();
                oF = new Intent(this, SectionN02Activity.class);
                break;
            case R.id.databaseBtn:
                oF = new Intent(this, AndroidDatabaseManager.class);
                break;
            case R.id.uploadData:
                if (!AndroidUtilityKt.isNetworkConnected(this)) {
                    Toast.makeText(this, "No network connection available!", Toast.LENGTH_SHORT).show();
                    return;
                }
                oF = new Intent(this, SyncActivity.class);
                break;

            default:
                oF = new Intent();
        }
        startActivity(oF);
    }

    public void toggleSummary(View view) {

        if (bi.recordSummary.getVisibility() == View.VISIBLE) {
            bi.recordSummary.setVisibility(View.GONE);
        } else {
            bi.recordSummary.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        gettingAreaData();
    }

    //Other Dependent Functions
    private void setUIContent() {

        //spProvince
        bi.spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onSettingDropDownContent(false);
                if (i == 0) {
                    bi.spDistrict.setEnabled(false);
                    bi.spDistrict.setSelection(0);
                    return;
                }
                initializingDistrictVariables();
                for (Villages item : areaList) {
                    if (item.getCountry().equals(bi.spCountry.getSelectedItem().toString())) {
                        districtName.add(item.getDistrict());
                        villageMap.put(item.getDistrict(), item);
                    }
                }
                bi.spDistrict.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, districtName));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //spDistrict
        bi.spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onSettingDropDownContent(false);
                if (i == 0) {
                    bi.spUC.setEnabled(false);
                    bi.spUC.setSelection(0);
                    return;
                }
                initializingUcVariables();
                for (Villages item : areaList) {
                    if (item.getDistrict().equals(bi.spDistrict.getSelectedItem().toString())) {
                        ucName.add(item.getUc());
                        //villageMap.put(item.getUc(), item);
                    }
                }
                bi.spUC.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, ucName));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bi.spUC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                onSettingDropDownContent(false);
                if (i == 0) {
                    bi.spVillage.setEnabled(false);
                    bi.spVillage.setSelection(0);
                    return;
                }
                initializingVillageVariables();
                for (Villages item : areaList) {
                    if (item.getUc().equals(bi.spUC.getSelectedItem().toString())) {
                        villageName.add(item.getVillage());
                        villageMap.put(item.getVillage(), item);
                    }
                }
                bi.spVillage.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, villageName));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bi.spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    onSettingDropDownContent(false);
                    return;
                }
                village = villageMap.get(bi.spVillage.getSelectedItem().toString());
                onSettingDropDownContent(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void onSettingDropDownContent(boolean enable) {
        bi.formA.setEnabled(enable);
    }

    private void initializingAreaVariables() {
        countryName = new ArrayList<String>() {
            {
                add("....");
            }
        };
        areaList = new ArrayList<>();
    }

    private void initializingDistrictVariables() {
        districtName = new ArrayList<String>() {
            {
                add("....");
            }
        };
        villageMap = new HashMap<>();
        bi.spDistrict.setEnabled(true);
    }

    private void initializingUcVariables() {
        ucName = new ArrayList<String>() {
            {
                add("....");
            }
        };
        villageMap = new HashMap<>();
        bi.spUC.setEnabled(true);
    }

    private void initializingVillageVariables() {
        villageName = new ArrayList<String>() {
            {
                add("....");
            }
        };
        villageMap = new HashMap<>();
        bi.spVillage.setEnabled(true);
    }

    //Reactive Streams
    private Observable<List<Villages>> getAreas() {
        return Observable.create(emitter -> {
            //emitter.onNext(appInfo.getDbHelper().getEnumBlock(MainApp.UC_ID));
            emitter.onNext(appInfo.getDbHelper().getCountry());
            emitter.onComplete();
        });
    }

    //Getting data from db
    public void gettingAreaData() {
        initializingAreaVariables();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countryName);
        bi.spCountry.setAdapter(adapter);
        getAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Villages>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<Villages> vContract) {
                        for (Villages village : vContract) {
                            if (!countryName.contains(village.getCountry()))
                                countryName.add(village.getCountry());
                            areaList.add(village);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }


    private void SaveDraft() {
        form = new Form();
        form.setSysdate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date().getTime()));
        form.setUsername(MainApp.userName);
        form.setDeviceID(MainApp.appInfo.getDeviceID());
        form.setDevicetagID(MainApp.appInfo.getTagName());
        form.setAppversion(MainApp.appInfo.getAppVersion());

        form.setCountry(bi.spCountry.getSelectedItem().toString());
        form.setDistrict(bi.spDistrict.getSelectedItem().toString());
        form.setUc(bi.spUC.getSelectedItem().toString());
        form.setVillage(bi.spVillage.getSelectedItem().toString());
        MainApp.setGPS(this);
    }

    private boolean UpdateDB() {
        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addForm(form);
        form.set_ID(String.valueOf(updcount));
        if (updcount > 0) {
            form.set_UID(form.getDeviceID() + form.get_ID());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UID, form.get_UID());
            /*db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_SYSDATE, form.getSysdate());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_USERNAME, form.getUsername());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_DEVICEID, form.getDeviceID());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_DEVICETAGID, form.getDevicetagID());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_APPVERSION, form.getAppversion());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_COUNTRY, form.getCountry());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_DISTRICT, form.getDistrict());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_UC, form.getUc());
            db.updatesFormsColumn(FormsContract.FormsTable.COLUMN_VILLAGE, form.getVillage());*/
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


}
