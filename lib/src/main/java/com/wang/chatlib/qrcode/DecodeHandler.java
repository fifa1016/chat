package com.wang.chatlib.qrcode;

import android.graphics.*;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;

import java.io.ByteArrayOutputStream;

/**
 * Created by shawn on 9/5/15.
 */
public class DecodeHandler extends Handler {
    private static final String TAG = "DecodeHandler";

    public static final int WHAT_DECODE = 0x001;
    public static final int WHAT_QUIT = 0x002;

    private Handler mResultHandler;
    private boolean mRunning = true;
    private MultiFormatReader mReader;

    public DecodeHandler(Handler resultHandler) {
        mResultHandler = resultHandler;
        mReader = new MultiFormatReader();

        Message msg = Message.obtain(resultHandler, ResultHandler.WHAT_DECODE_HANDLER_CREATED);
        msg.obj = this;
        msg.sendToTarget();
    }

    @Override
    public void handleMessage(Message msg) {
        Log.d(TAG, "handleMessage()");
        if (!mRunning) {
            return;
        }

        switch (msg.what) {
            case WHAT_DECODE:
                byte[] data = (byte[]) msg.obj;
                int width = msg.arg1;
                int height = msg.arg2;
                int format = msg.getData().getInt("imageFormat", ImageFormat.NV21);
                decode(data, width, height, format);
                break;
            case WHAT_QUIT:
                mRunning = false;
                Looper.myLooper().quit();
                break;
        }
    }

    private void decode(byte[] data, int width, int height, int format) {
        if (data == null)
            Log.d(TAG, "decode  data null");
        else
            Log.d(TAG, "decode  data not null, length=" + data.length );

        Result result = null;
        try {
            if( isYuvFormat(format)) {
                LuminanceSource source = new PlanarYUVLuminanceSource(data, width, height, 0, 0, width, height, false);
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
                result = mReader.decodeWithState(binaryBitmap);
                mReader.reset();
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (result != null) {
            Log.d(TAG, "decode success");
            Log.d(TAG, "result text:" + result.getText()==null?"null":result.getText() );
            Message msg = mResultHandler.obtainMessage(ResultHandler.WHAT_DECODE_SUCCESS, result);
            msg.sendToTarget();
        }else{
            Log.d(TAG, "decode failed");
            Message msg = mResultHandler.obtainMessage(ResultHandler.WHAT_DECODE_FAILED);
            msg.sendToTarget();
        }
    }

    private Bitmap getBitmap(byte[] data, int width, int height, int format ){

        if( isYuvFormat(format) ) {
            YuvImage yuvImage = new YuvImage(data, format, width, height, null);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, width, height), 50, outputStream);
            byte[] bytes = outputStream.toByteArray();

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            Log.d(TAG, " yuv w:" + yuvImage.getWidth() + ",h:" + yuvImage.getHeight()
                    + "  bytes[] length:" + bytes.length);
            return bitmap;
        }

        return null;
    }

    private boolean isYuvFormat( int format){
        if( format == ImageFormat.NV21 || format == ImageFormat.YUY2 ) {
            return true;
        }else{
            return false;
        }
    }

}
