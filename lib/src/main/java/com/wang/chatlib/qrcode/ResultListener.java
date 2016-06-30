package com.wang.chatlib.qrcode;

import android.graphics.Bitmap;
import android.os.Handler;

import com.google.zxing.Result;

/**
 * Created by shawn on 9/5/15.
 */
public interface ResultListener {
    void onResult(Result result, Bitmap bitmap);
    void onDecodeHandlerReady(Handler handler);
    void requestPreviewFrame(Handler handler);
}
