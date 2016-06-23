package com.wang.chat.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * user's account profile: id, avatar ....
 * Created by wang on 16-6-22.
 */
public class ProfileManager {
    private static final String TAG = "ProfileManager";

    private Context context;

    private ProfileManager(Context context) {
        this.context = context;
    }

    private volatile static ProfileManager instance;

    public static void init(Context context) {
        if (instance == null) {
            synchronized (ProfileManager.class) {
                if (instance == null) {
                    instance = new ProfileManager(context);
                }
            }
        }
    }

    public static ProfileManager getInstance() {
        return instance;
    }


    public static String AVATAR_FILE_NAME = "avatar_image";

    public void saveAvatar(Uri uri) {

        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);

            File outputFile = new File(context.getFilesDir(), AVATAR_FILE_NAME);
            outputFile.createNewFile();
            outputStream = new FileOutputStream(outputFile);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }// save avatar

    public String getAvatarPath() {
        return context.getFilesDir() + "/" + AVATAR_FILE_NAME;
    }


    private static final String PREF_NAME = "pref_account_profile";
    private static final String PREF_KEY_ID = "nickname";

    public void saveLoginId(String id) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PREF_KEY_ID, id);
        editor.commit();
    }

    public String getLoginId() {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(PREF_KEY_ID, "");
    }
}
