package com.wang.chat.qrcode;

import android.graphics.Bitmap;
import android.os.Handler;

/**
 * Created by shawn on 9/5/15.
 */
public interface ResultListener {
    public void onResult(String result, Bitmap bitmap);
    public void onDecodeHandlerReady(Handler handler);
    public void requestPreviewFrame(Handler handler);
}
