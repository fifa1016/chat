package com.wang.chat.view;

import android.content.Context;
import android.graphics.*;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import com.wang.chat.qrcode.DecodeHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CameraPreview extends SurfaceView
        implements SurfaceHolder.Callback, Camera.AutoFocusCallback, Camera.PreviewCallback {
    static final String TAG = CameraPreview.class.getSimpleName();

    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;
    private int mImageFormat;

    public CameraPreview(Context context) {
        super(context);
        init();
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CameraPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.d(TAG, "init()");
        if (mCamera == null) {
            mCamera = Camera.open();

            mSurfaceHolder = getHolder();
            mSurfaceHolder.addCallback(this);
            // set for prior 3.0
            mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }

    private void release() {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
            mSurfaceHolder.removeCallback(this);
        }
    }

    //    public void pause() {
//        release();
//    }
//
    public void resume() {
        if (mCamera == null) {
            init();

            mCamera.setDisplayOrientation(90);
            setCameraParameters();
            mCamera.autoFocus(this);

            try {
                mCamera.setPreviewDisplay(mSurfaceHolder);
                mCamera.setPreviewCallback(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mCamera.startPreview();
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated()");
//        try {
//            init();
//            mCamera.setDisplayOrientation(90);
//            setCameraParameters();
//            mCamera.autoFocus(this);
//
//            mCamera.setPreviewDisplay(holder);
//            mCamera.setPreviewCallback(this);
//            mCamera.startPreview();
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//            e.printStackTrace();
//        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "surfaceChanged()");

        if (mSurfaceHolder.getSurface() == null) {
            return;
        }

        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mCamera.setDisplayOrientation(90);
            setCameraParameters();
            mCamera.setPreviewDisplay(holder);
            mCamera.setPreviewCallback(this);
            mCamera.startPreview();
            refocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed()");
        release();
    }


    Camera.Size mPreviewSize;

    int mBestPreviewWidth = 1280;

    // set scan focus area
    private void setCameraParameters() {
        Camera.Parameters para = mCamera.getParameters();

        int width = 0, height = 0;
        int diff = mBestPreviewWidth;
        for (Camera.Size size : para.getSupportedPreviewSizes()) {
            int delta = Math.abs(mBestPreviewWidth - size.width);
            if (delta < diff) {
                width = size.width;
                height = size.height;
                diff = delta;
            }
        }
        para.setPreviewSize(width, height);
        Log.d(TAG, "preview size=" + width + "," + height);
        mPreviewSize = mCamera.new Size(width, height);

        int fps = 2;
        for (int[] range : para.getSupportedPreviewFpsRange()) {
            Log.d(TAG, "range:" + range[0] + "~" + range[1]);
            if( range[0]>fps){
                fps = range[0];
                break;
            }
            break;
        }
        para.setPreviewFpsRange(fps, fps);

        for(Integer format :para.getSupportedPreviewFormats()){
            Log.d(TAG, "format:"+ format );
            mImageFormat = format;
            break;
        }

        para.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        if (para.getMaxNumFocusAreas() > 0) {
            Rect focusRect = calculateArea(1f);
            ArrayList<Camera.Area> list = new ArrayList();
            list.add(new Camera.Area(focusRect, 200));
            para.setFocusAreas(list);
        }

        if (para.getMaxNumMeteringAreas() > 0) {
            Rect meterRect = calculateArea(1.5f);
            ArrayList<Camera.Area> list = new ArrayList<Camera.Area>();
            list.add(new Camera.Area(meterRect, 200));
            para.setMeteringAreas(list);
        }

        mCamera.setParameters(para);
    }

    /**
     *
     */
    public void setFocusAreaSize(int size) {
        mFocusAreaSize = size;
    }

    private int mFocusAreaSize = 200;

    /**
     * Convert touch position x:y to {@link Camera.Area} position -1000:-1000 to 1000:1000.
     */
    private Rect calculateArea(float coefficient) {
        int x = getWidth() / 2, y = getHeight() / 2;
        Log.d(TAG,"x="+x+",y="+y);
        int areaSize = (int) (mFocusAreaSize * coefficient);

        x = -1000 + 2000 * x / getWidth();
        y = -1000 + 2000 * y / getHeight();
        Log.d(TAG,"x="+x+",y="+y);


        int left = clamp(x - areaSize / 2, -1000, 1000 - areaSize);
        int top = clamp(y - areaSize / 2, -1000, 1000 - areaSize);
        Log.d(TAG, "calculate left=" + left + ", top=" + top
                + ",right=" + (left + areaSize) + ", bottom=" + (top + areaSize));

        Rect rect = new Rect(left, top, left + areaSize, top + areaSize);
        return rect;
    }

    private int clamp(int x, int min, int max) {
        if (x > max) {
            return max;
        }
        if (x < min) {
            return min;
        }
        return x;
    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {
        Log.d(TAG, "onAutoFocus() success=" + success);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        refocus();

        return super.onTouchEvent(event);
    }

    private void refocus() {
        mCamera.autoFocus(this);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        Log.d(TAG, "onPreviewFrame data.length=" + data.length + ",  decodeHandler null?" + (mDecodeHandler == null));
        if (camera != null && mDecodeHandler != null) {
            Log.d(TAG, "send message");
            mFrameBuffer = new byte[data.length];
            System.arraycopy(data, 0, mFrameBuffer, 0, data.length);
            Message msg = mDecodeHandler.obtainMessage(DecodeHandler.WHAT_DECODE, mPreviewSize.width, mPreviewSize.height, mFrameBuffer);
            //Message msg = mDecodeHandler.obtainMessage(DecodeHandler.WHAT_DECODE, mPreviewSize.width, mPreviewSize.height, data);
            Bundle bundle = new Bundle();
            bundle.putInt("imageFormat", mImageFormat );
            msg.setData(bundle);
            msg.sendToTarget();
            mDecodeHandler = null;
        }
    }

    private volatile byte[] mFrameBuffer;

    private Handler mDecodeHandler;

    public void setDecodeHandler(Handler decodeHandler) {
        mDecodeHandler = decodeHandler;
    }
}
