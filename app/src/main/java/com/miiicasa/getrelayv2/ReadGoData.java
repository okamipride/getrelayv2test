package com.miiicasa.getrelayv2;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import go.getrelayv2.Getrelayv2;
import java.io.UnsupportedEncodingException;

import go.getrelayv2.Getrelayv2;

/**
 * Created by rachael on 2015/9/29.
 */
public  class ReadGoData extends Getrelayv2.ReadCallback.Stub {

    final static String TAG = "ReadGoData";
    TextView show;
    long fd = -1;


    public void  SetFD(long connfd) {
        fd = connfd;
    }

    public void ReadBytes(byte[] data){
        String read = null;
        try {
            read = new String(data, "UTF-8");

            if (fd!= -1) {
                try {
                    Getrelayv2.WriteOk(fd);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d(TAG, read);
    }
}
