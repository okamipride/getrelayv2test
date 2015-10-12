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
        Log.d(TAG,"ReadBytes Enter");
        try {
            if (data != null) {
                read = new  String(data, "UTF-8");
                Log.d(TAG,"ReadBytes"+read);
                if (fd!= -1) {
                    try {
                        Getrelayv2.WriteOk(fd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /*
        error_none                 = 0x00
        error_404                          = 0x01
        error_connect_host         = 0x02
        error_EOF          = 0x03
        error_ErrNoProgress        = 0x04
        error_ErrShortBuffer       = 0x05
        error_ErrShortWrite        = 0x06
        error_ErrUnexpectedEOF     = 0x07
        error_not_io_error         = 0x08
        error_connect_resolve_host = 0x09
        error_reconnect_fail       = 0x0a
    */

    public void RecieveError(long err) {
        Log.d(TAG, "recieve error = "+ err);
    }
}
