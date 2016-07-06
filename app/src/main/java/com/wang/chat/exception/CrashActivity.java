package com.wang.chat.exception;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.wang.chat.R;
import com.wang.chatlib.util.DateUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wang on 16-7-1.
 */
public class CrashActivity extends AppCompatActivity {
    private static final String TAG = "CrashActivity";

    private String logFileFullName = null;

    @BindView(R.id.txt_log_info)
    TextView txtInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        ButterKnife.bind(this);

        if( txtInfo == null ){
            Log.e(TAG, "onCreate: txtInfo null!!" );
        }

        logFileFullName = saveLogToFile();
        if (logFileFullName != null) {
            txtInfo.setText("Log saved to :" + logFileFullName);
            sendLogFile(logFileFullName);
        } else {
            txtInfo.setText("Log save failed");
        }
    }

    private void sendLogFile(String fullName) {
        if (fullName == null) return;
    }

    /**
     * @return file name
     */
    private String saveLogToFile() {
        PackageManager pm = getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String model = Build.MODEL;
        if (!model.startsWith(Build.MANUFACTURER)) {
            model = Build.MANUFACTURER + " " + model;
        }

        String dirPath = Environment.getExternalStorageDirectory() + "/com.wang.chat/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fullName = dirPath + "log_" + DateUtil.getSimpleTimeString() + ".txt";
        File file = new File(fullName);
        InputStreamReader reader = null;
        FileWriter writer = null;
        try {
            String cmd = Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 ?
                    "logcat -d -v time MyApp:v dalvikvm:v System.err:v *:s" :
                    "logcat -d -v time";
            Process process = Runtime.getRuntime().exec(cmd);
            reader = new InputStreamReader(process.getInputStream());

            writer = new FileWriter(file);
            writer.write("Android version: " + Build.VERSION.SDK_INT + "\n");
            writer.write("Device: " + model + "\n");
            writer.write("App version: " + (packageInfo == null ? "(null)" : packageInfo.versionCode + "\n"));

            char[] buffer = new char[10000];
            int n;
            while ((n = reader.read(buffer, 0, buffer.length)) != -1) {
                writer.write(buffer, 0, n);
            }
            reader.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            return null;
        }

        return fullName;
    }
}
