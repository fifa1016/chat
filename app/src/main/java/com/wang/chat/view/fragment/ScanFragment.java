package com.wang.chat.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.wang.chat.R;
import com.wang.chat.contract.ScanContract;
import com.wang.chatlib.qrcode.ResultListener;
import com.wang.chatlib.qrcode.ResultHandler;
import com.wang.chat.view.custom.CameraPreview;

/**
 * Created by shawn on 8/27/15.
 */
public class ScanFragment extends BaseFragment implements ScanContract.View, ResultListener {
    private static final String TAG = ScanFragment.class.getSimpleName();

    private CameraPreview cameraPreview;
    private View mMaskView;
    private ImageView mImageScan;

    @Override
    public int getViewLayout() {
        return R.layout.fragment_scan;
    }

    @Override
    public void initViews(View view) {
        cameraPreview = (CameraPreview) view.findViewById(R.id.scan_preview);
        mMaskView = view.findViewById(R.id.scan_mask);
        cameraPreview.setFocusAreaSize(300);

        mImageScan = (ImageView) view.findViewById(R.id.imgScan);

        if (mResultHandler == null) {
            mResultHandler = new ResultHandler(this);
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        cameraPreview.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        cameraPreview.resume();
    }

    private ResultHandler mResultHandler;

    @Override
    public void onResult(Result result, Bitmap bitmap) {
        String text = result.getText().trim();

        if (result != null && !TextUtils.isEmpty(text)) {
            Uri uri = Uri.parse(text);
            Log.d(TAG, "host:" + uri.getHost() + ", port:" + uri.getPort());
        }

    }

    @Override
    public void onDecodeHandlerReady(Handler handler) {
        Log.d(TAG, "onDecodeHandlerReady handler==null?" + (handler == null));
        cameraPreview.setDecodeHandler(handler);
    }

    @Override
    public void requestPreviewFrame(Handler handler) {
        cameraPreview.setDecodeHandler(handler);
    }

    @Override
    public void setPresenter(ScanContract.Presenter presenter) {
    }
}
