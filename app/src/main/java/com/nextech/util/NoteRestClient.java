package com.nextech.util;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by welcome on 11/21/2016.
 */
public class NoteRestClient extends AsyncTask<String, String, String> {
    private Button button;
    private EditText time;
    private TextView finalResult;
    ProgressDialog progressDialog;

    private String resp;

    @Override
    protected String doInBackground(String... params) {
       // NetClientGet netClientGet=new NetClientGet();
//       // resp=netClientGetRally.netClientGetRaly();
      //  resp = netClientGet.netClientGet();
        System.out.println("Respsone is : " + resp);
        return resp;
    }

    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
//        finalResult.setText(result);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.AsyncTask#onPreExecute()
     */
    @Override
    protected void onPreExecute() {
        // Things to be done before execution of long running operation. For
        // example showing ProgessDialog
    }

    @Override
    protected void onProgressUpdate(String... text) {
        //finalResult.setText(text[0]);
        // Things to be done while execution of long running operation is in
        // progress. For example updating ProgessDialog
    }


}