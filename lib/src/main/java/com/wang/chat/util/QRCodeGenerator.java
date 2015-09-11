package com.wang.chat.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.ByteMatrix;

/**
 * Created by shawn on 8/27/15.
 */
public class QRCodeGenerator {
    static final String TAG = "QRCodeGenerator";

    public static void encodeToBitmap(String content, ImageView imageView ){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            int width=256, height=256;
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height );
            Bitmap bitmap = Bitmap.createBitmap( width, height , Bitmap.Config.RGB_565 );
            for( int w=0; w < width; w ++){
                for( int h=0; h<height; h++){
                    if( bitMatrix.get( w, h) ){
                        bitmap.setPixel(w, h, Color.BLACK );
                    }else{
                        bitmap.setPixel(w, h, Color.WHITE );
                    }
                }
            }
            imageView.setImageBitmap( bitmap );
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
