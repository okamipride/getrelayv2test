package com.miiicasa.getrelayv2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import go.getrelayv2.Getrelayv2;


public class GetRelayActivity extends AppCompatActivity {
    long myfd = -1;
    Getrelayv2.ReadCallback cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_relay);
        cb = new ReadGoData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_relay, menu);
        return true;
    }


    public void OnGetRelay(View v) {
        new GetRelayAsyncTask().execute();
    }

    public void OnCloseFD(View v) {
        new CloseFDAsyncTask().execute(myfd);
    }

    public void OnWriteOK(View v) {
        new WriteOKAsyncTask().execute(myfd);

    }

    private class GetRelayAsyncTask extends AsyncTask<Void, Void , Long> {
        String relay_url = "r9402.dch.dlink.com";
        String default_did = "12345678901234567890000000000001";
        String default_hash ="12345678901234567890000000000001";

        @Override
        protected Long doInBackground(Void... params) {

            try {
                myfd = Getrelayv2.Getrelay(default_did, default_hash, relay_url, cb);
                ((ReadGoData)cb).SetFD(myfd);
            }catch (Exception e) {
                e.printStackTrace();
            }
            Long ret = Long.valueOf(myfd);
            return   ret;
        }

        @Override
        protected void onPostExecute(Long result) {
            TextView resview =  (TextView)findViewById(R.id.txtv_result);
            resview.setText(result.toString());
        }
    }

    private class CloseFDAsyncTask extends AsyncTask<Long, Void , Long> {

        @Override
        protected Long doInBackground(Long... fd) {
            Getrelayv2.CloseConn(myfd);
            return myfd;
        }

        @Override
        protected void onPostExecute(Long ret) {
            TextView resview =  (TextView)findViewById(R.id.txtv_result);
            resview.setText("fd = " + ret.toString()+ " has closed");
        }
    }

    private class WriteOKAsyncTask extends AsyncTask<Long, Void , Long> {

        @Override
        protected Long doInBackground(Long... fd) {
            try {
                Getrelayv2.WriteOk(myfd);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return myfd;
        }

        @Override
        protected void onPostExecute(Long ret) {
            //TextView resview =  (TextView)findViewById(R.id.txtv_result);
            //resview.setText("fd = " + ret.toString()+ " has closed");
        }
    }
}
