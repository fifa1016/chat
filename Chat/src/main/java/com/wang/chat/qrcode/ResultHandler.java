package com.wang.chat.qrcode;

import android.os.Handler;
import android.os.Message;

/**
 * Created by shawn on 9/5/15.
 */
public class ResultHandler extends Handler {
    public static final int WHAT_DECODE_SUCCESS = 0x001;
    public static final int WHAT_DECODE_FAILED = 0x002;
    public static final int WHAT_DECODE_HANDLER_CREATED = 0x003;

    private ResultListener mListener;
    private DecodeThread mThread;

    public ResultHandler(ResultListener listener) {
        mListener = listener;
        mThread = new DecodeThread(this);
        mThread.start();
    }

    public Handler getDecodeHandler() {
        if (mThread != null) {
            return mThread.getDecodeHandler();
        }
        return null;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case WHAT_DECODE_HANDLER_CREATED:
                Handler decodeHandler = (Handler)msg.obj;
                mListener.onDecodeHandlerReady( decodeHandler );
                break;
            case WHAT_DECODE_SUCCESS:
                break;
            case WHAT_DECODE_FAILED:
            default:
                mListener.requestPreviewFrame( mThread.getDecodeHandler() );
                break;
        }
    }
}
