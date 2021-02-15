package edu.aku.hassannaqvi.casi_register.workers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import edu.aku.hassannaqvi.casi_register.R;
import edu.aku.hassannaqvi.casi_register.contracts.ZStandardContract;
import edu.aku.hassannaqvi.casi_register.core.MainApp;

import static edu.aku.hassannaqvi.casi_register.utils.CreateTable.PROJECT_NAME;


public class DataDownWorkerALL extends Worker {

    private final String TAG = "DataWorkerEN()";
    private final int position;
    private final String uploadTable;
    HttpURLConnection urlConnection;
    private final String uploadWhere;
    private int length;

    public DataDownWorkerALL(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        // to be initialised by workParams
        uploadTable = workerParams.getInputData().getString("table");
        position = workerParams.getInputData().getInt("position", -2);
        Log.d(TAG, "DataDownWorkerALL: position " + position);
        //uploadColumns = workerParams.getInputData().getString("columns");
        uploadWhere = workerParams.getInputData().getString("where");
    }

    /*
     * This method is responsible for doing the work
     * so whatever work that is needed to be performed
     * we will put it here
     *
     * For example, here I am calling the method displayNotification()
     * It will display a notification
     * So that we will understand the work is executed
     * */

    @NonNull
    @Override
    public Result doWork() {

        Log.d(TAG, "doWork: Starting");
        //displayNotification(nTitle, "Starting upload");

        StringBuilder result = new StringBuilder();

        URL url;
        Data data;


        if (!uploadTable.equals(ZStandardContract.ZScoreTable.TABLE_NAME)) {

            try {
                url = new URL(MainApp._HOST_URL + MainApp._SERVER_GET_URL);
                Log.d(TAG, "doWork: Connecting...");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(100000 /* milliseconds */);
                urlConnection.setConnectTimeout(150000 /* milliseconds */);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("charset", "utf-8");
                urlConnection.setUseCaches(false);
                urlConnection.connect();
                Log.d(TAG, "downloadURL: " + url);

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());

                JSONObject jsonTable = new JSONObject();
                JSONArray jsonParam = new JSONArray();

                jsonTable.put("table", uploadTable);
                //jsonTable.put("select", uploadColumns);
                jsonTable.put("filter", uploadWhere);
                //jsonTable.put("limit", "3");
                //jsonTable.put("orderby", "rand()");
                //jsonSync.put(uploadData);
                jsonParam.put(jsonTable);

                Log.d(TAG, "Upload Begins: " + jsonTable.toString());


                wr.writeBytes(String.valueOf(jsonTable));
                wr.flush();
                wr.close();

                Log.d(TAG, "doInBackground: " + urlConnection.getResponseCode());

                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "Connection Response: " + urlConnection.getResponseCode());
                    //displayNotification(nTitle, "Connection Established");

                    length = urlConnection.getContentLength();
                    Log.d(TAG, "Content Length: " + length);

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    if (result.equals("[]")) {
                        Log.d(TAG, "No data received from server: " + result);
                        data = new Data.Builder()
                                .putString("error", "No data received from server: " + result)
                                .putInt("position", this.position)
                                .build();
                        return Result.failure(data);
                    }
                    //displayNotification(nTitle, "Received Data");
                    Log.d(TAG, "doWork(EN): " + result.toString());
                } else {
                    Log.d(TAG, "Connection Response (Server Failure): " + urlConnection.getResponseCode());
                    data = new Data.Builder()
                            .putString("error", String.valueOf(urlConnection.getResponseCode()))
                            .putInt("position", this.position)
                            .build();
                    return Result.failure(data);
                }
            } catch (java.net.SocketTimeoutException e) {
                Log.d(TAG, "doWork (Timeout): " + e.getMessage());
                //displayNotification(nTitle, "Timeout Error: " + e.getMessage());
                data = new Data.Builder()
                        .putString("error", String.valueOf(e.getMessage()))
                        .putInt("position", this.position)
                        .build();
                return Result.failure(data);

            } catch (IOException | JSONException e) {
                Log.d(TAG, "doWork (IO Error): " + e.getMessage());
                //displayNotification(nTitle, "IO Error: " + e.getMessage());
                data = new Data.Builder()
                        .putString("error", String.valueOf(e.getMessage()))
                        .putInt("position", this.position)
                        .build();

                return Result.failure(data);

            }

        } else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open("zStandardJson.json")));
                String eachline = bufferedReader.readLine();
                while (eachline != null) {
                    result.append(eachline);
                    eachline = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        //Do something with the JSON string
        if (result != null) {
            //displayNotification(nTitle, "Starting Data Processing");

            //String json = result.toString();
            /*if (json.length() > 0) {*/
            //displayNotification(nTitle, "Data Size: " + result.length());


            // JSONArray jsonArray = new JSONArray(json);


            //JSONObject jsonObjectCC = jsonArray.getJSONObject(0);
            ///BE CAREFULL DATA.BUILDER CAN HAVE ONLY 1024O BYTES. EACH CHAR HAS 8 bits

            MainApp.downloadData[this.position] = String.valueOf(result);

            data = new Data.Builder()
                    //     .putString("data", String.valueOf(result))
                    .putInt("position", this.position)
                    .build();


            //displayNotification(nTitle, "Uploaded successfully");
            Log.d(TAG, "doWork: " + result);
            Log.d(TAG, "doWork (success) : position " + data.getInt("position", -1));
            return Result.success(data);

        } else {
            data = new Data.Builder()
                    .putString("error", String.valueOf(result))
                    .putInt("position", this.position)
                    .build();
            //displayNotification(nTitle, "Error Received");
            return Result.failure(data);
        }


    }

    /*
     * The method is doing nothing but only generating
     * a simple notification
     * If you are confused about it
     * you should check the Android Notification Tutorial
     * */
    private void ddisplayNotification(String title, String task) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("scrlog", "BLF", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "scrlog")
                .setContentTitle(title)
                .setContentText(task)
                .setSmallIcon(R.mipmap.ic_launcher);

        final int maxProgress = 100;
        int curProgress = 0;
        notification.setProgress(length, curProgress, false);

        notificationManager.notify(1, notification.build());
    }
}