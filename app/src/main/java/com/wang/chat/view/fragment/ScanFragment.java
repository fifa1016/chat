package com.wang.chat.view.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wang.chat.R;
import com.wang.chatlib.qrcode.ResultListener;
import com.wang.chatlib.qrcode.ResultHandler;
import com.wang.chat.view.custom.CameraPreview;

/**
 * Created by shawn on 8/27/15.
 */
public class ScanFragment extends BaseFragment implements ResultListener {
    private static final String TAG = ScanFragment.class.getSimpleName();

    private CameraPreview mPreview;
    private View mMaskView;
    private ImageView mImageScan;

    @Override
    public int getViewLayout() {
        return R.layout.fragment_scan;
    }

    @Override
    public void initViews(View view) {
        mPreview = (CameraPreview)view.findViewById(R.id.scan_preview);
        mMaskView = view.findViewById(R.id.scan_mask);
        mPreview.setFocusAreaSize(300);

        mImageScan = (ImageView)view.findViewById(R.id.imgScan);

        if( mResultHandler == null ){
            mResultHandler = new ResultHandler( this );
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPreview.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPreview.resume();
        //mPreview.setDecodeHandler( mResultHandler.getDecodeHandler() );
    }

    private ResultHandler mResultHandler;

    @Override
    public void onResult(String result, Bitmap bitmap){

        if( result!=null && !result.trim().equals("")){
            Uri uri = Uri.parse(result);
            Log.d(TAG,"host:"+uri.getHost() +", port:"+uri.getPort() );
        }

    }

    @Override
    public void onDecodeHandlerReady(Handler handler) {
        Log.d(TAG,"onDecodeHandlerReady handler==null?"+(handler==null) );
        mPreview.setDecodeHandler( handler );
    }

    @Override
    public void requestPreviewFrame( Handler handler) {
        mPreview.setDecodeHandler( handler );
    }

}
