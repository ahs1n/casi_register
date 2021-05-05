package edu.aku.hassannaqvi.casi_register.ui;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.core.AndroidDatabaseManager;
import edu.aku.hassannaqvi.casi_register.core.MainApp;
import edu.aku.hassannaqvi.casi_register.databinding.ActivityMainBinding;
import edu.aku.hassannaqvi.casi_register.models.Form;
import edu.aku.hassannaqvi.casi_register.models.VersionApp;
import edu.aku.hassannaqvi.casi_register.models.Villages;
import edu.aku.hassannaqvi.casi_register.ui.list_activity.FormsReportDate;
import edu.aku.hassannaqvi.casi_register.ui.login_activity.LoginActivity;
import edu.aku.hassannaqvi.casi_register.ui.sections.Section01CS1Activity;
import edu.aku.hassannaqvi.casi_register.ui.sections.Section03WSActivity;
import edu.aku.hassannaqvi.casi_register.ui.sections.followup.SelectedChildrenListActivity;
import edu.aku.hassannaqvi.casi_register.utils.AndroidUtilityKt;
import edu.aku.hassannaqvi.casi_register.utils.AppUtilsKt;
import edu.aku.hassannaqvi.casi_register.utils.CreateTable;
import edu.aku.hassannaqvi.casi_register.utils.WarningActivityInterface;
import edu.aku.hassannaqvi.casi_register.utils.shared.SharedStorage;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static edu.aku.hassannaqvi.casi_register.CONSTANTS.CHILD_FOLLOWUP_TYPE;
import static edu.aku.hassannaqvi.casi_register.CONSTANTS.CHILD_TYPE;
import static edu.aku.hassannaqvi.casi_register.CONSTANTS.FOLLOWUP_FLAG;
import static edu.aku.hassannaqvi.casi_register.CONSTANTS.WRA_FOLLOWUP_TYPE;
import static edu.aku.hassannaqvi.casi_register.CONSTANTS.WRA_TYPE;
import static edu.aku.hassannaqvi.casi_register.core.MainApp.appInfo;

public class MainActivity extends AppCompatActivity implements WarningActivityInterface {

    static File file;
    ActivityMainBinding bi;
    DownloadManager downloadManager;
    String preVer = "", newVer = "";
    VersionApp versionApp;
    Long refID;
    //Setting Spinner
    List<String> regionName, districtName, ucName, villageName;
    Map<String, Villages> ucMap, villageMap;
    List<Villages> areaList;
    Boolean exit = false;
    private final String sysdateToday = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(new Date());
    private final String dtToday = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(new Date());

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {

                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(SharedStorage.INSTANCE.getDownloadFileRefID(MainActivity.this));

                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                assert downloadManager != null;
                Cursor cursor = downloadManager.query(query);
                if (cursor.moveToFirst()) {
                    int colIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(colIndex)) {

                        Toast.makeText(context, getString(R.string.newApp), Toast.LENGTH_SHORT).show();
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + "  " + getString(R.string.newVer)).append(newVer).append("  ").append(getString(R.string.downloaded)));

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);
        bi.toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(bi.toolbar);

        /*
         * Summary
         * */
        Collection<Form> todaysForms = appInfo.getDbHelper().getTodayForms(String.valueOf(SharedStorage.INSTANCE.getCountryCode(this)), sysdateToday);
        Collection<Form> unsyncedForms = appInfo.getDbHelper().getUnsyncedForms();

        StringBuilder rSumText = new StringBuilder()
                .append(getString(R.string.todays_record_summary))
                .append("\r\n")
                .append("=======================\r\n")
                .append("\r\n")
                .append(getString(R.string.total_forms_today))
                .append("(").append(dtToday).append("): ").append(todaysForms.size()).append("\r\n");
        if (todaysForms.size() > 0) {
            String iStatus;
            String formType;
            rSumText.append("------------------------------------------------------------------\r\n")
                    .append("\t")
                    .append(getString(R.string.type))
                    .append("\t\t\t")
                    .append(getString(R.string.village))
                    .append("\t\t\t")
                    .append(getString(R.string.reg_no))
                    .append("\t\t")
                    .append(getString(R.string.name))
                    .append("\t")
                    .append(getString(R.string.form_status))
                    .append("\r\n")
                    .append("------------------------------------------------------------------\r\n");

            for (Form fc : todaysForms) {

                switch (fc.getIstatus()) {
                    case "1":
                        iStatus = getString(R.string.elc701);
                        break;
                    case "2":
                        iStatus = getString(R.string.elc702);
                        break;
                    case "96":
                        iStatus = getString(R.string.elc796);
                        break;
                    default:
                        iStatus = "N/A" + fc.getIstatus();
                }

                switch (fc.getFormType()) {
                    case WRA_TYPE:
                        formType = "WScreening";
                        break;
                    case CHILD_TYPE:
                        formType = "CScreening";
                        break;
                    case WRA_FOLLOWUP_TYPE:
                        formType = "WFollowup";
                        break;
                    case CHILD_FOLLOWUP_TYPE:
                        formType = "CFollowup";
                        break;
                    default:
                        formType = "NA";
                        break;
                }

                rSumText.append(formType)
                        .append("  \t")
                        .append(fc.getVillage())
                        .append("  \t")
                        .append(fc.getReg_no())
                        .append("  \t")
                        .append(fc.getName())
                        .append("  \t")
                        .append(iStatus)
                        .append("\r\n")
                        .append("---------------------------------------------------------\r\n");
            }
        }
        rSumText.append("\r\n").append(getString(R.string.device_info)).append("\r\n")
                .append("========================================================\r\n")
                .append("\t")
                .append(getString(R.string.unsynced_form))
                .append("\t\t\t\t")
                .append(String.format(Locale.ENGLISH, "%02d", unsyncedForms.size()))
                .append("\t\t\t\t\t\t\r\n")
                .append("\t========================================================\r\n");
        bi.recordSummary.setText(rSumText);
        /*
         * Summary end
         * */


        bi.databaseBtn.setVisibility(MainApp.admin ? View.VISIBLE : View.GONE);

        // Auto download app
        versionApp = appInfo.getDbHelper().getVersionApp();

        if (versionApp != null) {
            preVer = appInfo.getVersionName() + "." + appInfo.getVersionCode();
            newVer = versionApp.getVersionname() + "." + versionApp.getVersioncode();
            if (appInfo.getVersionCode() < Integer.parseInt(versionApp.getVersioncode())) {
                bi.lblAppVersion.setVisibility(View.VISIBLE);

                String fileName = CreateTable.DATABASE_NAME.replace(".db", "-New-Apps");
                file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName, versionApp.getPathname());

                if (file.exists()) {
                    bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + getString(R.string.newVer)).append(newVer).append("  ").append(getString(R.string.downloaded)));
                    showDialog(newVer, preVer);
                } else {
                    NetworkInfo networkInfo = ((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + getString(R.string.appVer)).append(newVer).append("  ").append(getString(R.string.downloading)).append(".."));
                        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(MainApp._UPDATE_URL + versionApp.getPathname());
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setDestinationInExternalPublicDir(fileName, versionApp.getPathname())
                                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                .setTitle(getString(R.string.downloading) + getString(R.string.app_name) + getString(R.string.appVer) + newVer);

                        refID = downloadManager.enqueue(request);
                        SharedStorage.INSTANCE.setDownloadFileRefID(this, refID);

                    } else {
                        bi.lblAppVersion.setText(new StringBuilder(getString(R.string.app_name) + getString(R.string.appVer)).append(newVer).append(getString(R.string.available) + "..\n" + getString(R.string.downError)));
                    }
                }

            } else {
                bi.lblAppVersion.setVisibility(View.GONE);
                bi.lblAppVersion.setText(null);
            }
            registerReceiver(broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }

//        Testing visibility
        if (Integer.parseInt(appInfo.getVersionName().split("\\.")[0]) > 0) {
            bi.testing.setVisibility(View.GONE);
        } else {
            bi.testing.setVisibility(View.VISIBLE);
        }

        setUIContent();
        gettingRegionData();
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
    public void callWarningActivity(int id, Object item) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(this, getString(R.string.backBtn),
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


    /*
     * Clickable buttons
     * */
    @SuppressLint("NonConstantResourceId")
    public void openSpecificActivity(View v) {
        Intent oF;
        switch (v.getId()) {
            case R.id.formCS:
                if (!Validator.emptyCheckingContainer(this, bi.fldGrpna10)) return;
                SaveDraft();
                oF = new Intent(this, Section01CS1Activity.class);
                break;
            case R.id.formWS:
                if (!Validator.emptyCheckingContainer(this, bi.fldGrpna10)) return;
                SaveDraft();
                oF = new Intent(this, Section03WSActivity.class);
                break;
            case R.id.formCSFP:
                if (!Validator.emptyCheckingContainer(this, bi.fldGrpna10)) return;
                SaveDraft();
                oF = new Intent(this, SelectedChildrenListActivity.class).putExtra(FOLLOWUP_FLAG, true);
                break;
            case R.id.formWSFP:
                if (!Validator.emptyCheckingContainer(this, bi.fldGrpna10)) return;
                SaveDraft();
                oF = new Intent(this, SelectedChildrenListActivity.class).putExtra(FOLLOWUP_FLAG, false);
                break;
            case R.id.databaseBtn:
                oF = new Intent(this, AndroidDatabaseManager.class);
                break;
            case R.id.uploadData:
                if (!AndroidUtilityKt.isNetworkConnected(this)) {
                    Toast.makeText(this, getString(R.string.noNetwork), Toast.LENGTH_SHORT).show();
                    return;
                }
                oF = new Intent(this, SyncActivity.class);
                break;
            case R.id.btn_download_followup:
                if (!AndroidUtilityKt.isNetworkConnected(this)) {
                    Toast.makeText(this, getString(R.string.noNetwork), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Validator.emptyCheckingContainer(this, bi.fldGrpna10)) return;
                SaveDraft();
                oF = new Intent(this, SyncActivity.class).putExtra(FOLLOWUP_FLAG, true);
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


    /*
     * Other Dependent Functions
     * */
    private void setUIContent() {

        //spRegion
        bi.spRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    if (item.getRegion().equals(bi.spRegion.getSelectedItem().toString()) && !districtName.contains(item.getDistrict())) {
                        districtName.add(item.getDistrict());
                    }
                }
                Collections.sort(districtName);
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
                    if (item.getDistrict().equals(bi.spDistrict.getSelectedItem().toString()) && !ucName.contains(item.getUc())) {
                        ucName.add(item.getUc());
                        ucMap.put(item.getUc(), item);
                    }
                }
                Collections.sort(ucName);
                bi.spUC.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, ucName));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //uc
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
                Collections.sort(villageName);
                bi.spVillage.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, villageName));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //village
        bi.spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    onSettingDropDownContent(false);
                    return;
                }
                onSettingDropDownContent(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void showDialog(String newVer, String preVer) {
        AppUtilsKt.openWarningActivity(
                this,
                1,
                null,
                getString(R.string.app_name) + getString(R.string.appAvailable),
                getString(R.string.app_name) + " App Ver." + newVer + " is now available. Your are currently using older Ver." + preVer + ".\n" + getString(R.string.installNewApp),
                getString(R.string.install),
                getString(R.string.cancel)
        );
    }

    private void onSettingDropDownContent(boolean enable) {
        bi.formCS.setEnabled(enable);
        bi.formCSFP.setEnabled(enable);
        bi.formWS.setEnabled(enable);
        bi.formWSFP.setEnabled(enable);
    }


    /*
     * Initializing variables
     * */
    private void initializingAreaVariables() {
        regionName = new ArrayList<String>() {
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
        bi.spDistrict.setEnabled(true);
    }

    private void initializingUcVariables() {
        ucName = new ArrayList<String>() {
            {
                add("....");
            }
        };
        ucMap = new HashMap<>();
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


    /*
     * Reactive Streams
     * */
    private Observable<List<Villages>> getAreas() {
        return Observable.create(emitter -> {
            emitter.onNext(appInfo.getDbHelper().getCountry(String.valueOf(SharedStorage.INSTANCE.getCountryCode(this)), MainApp.user));
            emitter.onComplete();
        });
    }

    private void gettingRegionData() {
        initializingAreaVariables();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, regionName);
        bi.spRegion.setAdapter(adapter);
        getAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Villages>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NotNull List<Villages> vContract) {
                        for (Villages village : vContract) {
                            if (!regionName.contains(village.getRegion()))
                                regionName.add(village.getRegion());
                            areaList.add(village);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();
                    }
                });
    }


    /*
     * Setting identification data in variables
     * */
    private void SaveDraft() {
        MainApp.mainInfo = villageMap.get(bi.spVillage.getSelectedItem().toString());
    }

}
