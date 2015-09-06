package com.wang.chat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.wang.chat.R;
import com.wang.chat.util.NetworkUtil;
import com.wang.chat.util.Util;
import com.wang.chat.util.QRCodeGenerator;

/**
 * Created by shawn on 8/26/15.
 */
public class ServerFragment extends BaseFragment {

    private TextView mTxtIp;
    private EditText mEditPort;
    private ImageView mQrCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_server, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String ip = NetworkUtil.getIPV4Address();
        mTxtIp = (TextView) view.findViewById(R.id.server_ip);
        mTxtIp.setText( ip );

        mEditPort = (EditText) view.findViewById(R.id.server_port);
        mEditPort.setText("59999");
        String port = mEditPort.getText().toString();

        mQrCode = (ImageView)view.findViewById(R.id.server_qrcode);
        String serverInfo = "http://" + ip +":" + port;

        QRCodeGenerator.encodeToBitmap(serverInfo, mQrCode);
    }
}
