package com.wang.chat.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by shawn on 8/29/15.
 */
public class FileUtil {

    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public static void copyFile(File source, File dest) {
        FileChannel inputChnl = null, outputChnl = null;
        try {
            inputChnl = new FileInputStream(source).getChannel();
            outputChnl = new FileOutputStream(dest).getChannel();
            outputChnl.transferFrom(inputChnl, 0, inputChnl.size() );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputChnl.close();
                outputChnl.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
