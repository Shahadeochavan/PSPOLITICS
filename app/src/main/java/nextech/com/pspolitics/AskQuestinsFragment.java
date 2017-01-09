package nextech.com.pspolitics;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nextech.util.HTTPURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AskQuestinsFragment extends Fragment {
    public AskQuestinsFragment() {
    }

    private ProgressDialog pDialog;
    private JSONObject json;
    private int success = 0;
    String firstname, lastname, email, mobile;
    private HTTPURLConnection service;
    String MobilePattern = "[0-9]{10}+";
    Button btnReg;
    EditText edtfirstname, edtLastname, edtmobile, edtemail;
    private String path = "http://192.168.2.105:8080/PSPolitics/json/askquestins/post";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(R.string.ask_questions);
        View v = inflater.inflate(R.layout.fragment_ask_questins,
                container, false);

        edtfirstname = (EditText) v.findViewById(R.id.text_first);
        edtLastname = (EditText) v.findViewById(R.id.text_last);
        edtmobile = (EditText) v.findViewById(R.id.text_phone);
        edtemail = (EditText) v.findViewById(R.id.email);
        btnReg = (Button) v.findViewById(R.id.btn_submit);
        service = new HTTPURLConnection();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtfirstname.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "First Name cannot be Blank", Toast.LENGTH_LONG).show();
                    edtfirstname.setError("First Name cannot be Blank");
                    firstname = edtfirstname.getText().toString();
                    return;

                } else if (edtLastname.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Last Name cannot be Blank", Toast.LENGTH_LONG).show();
                    edtLastname.setError("Last Name cannot be Blank");
                    edtLastname.getText().toString();
                    return;
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString()).matches()) {
                    //Validation for Invalid Email Address
                    Toast.makeText(getApplicationContext(), "Please enter valid Email", Toast.LENGTH_LONG).show();
                    edtemail.setError("Please enter valid Email");
                    edtemail.getText().toString();
                    return;
                } else if (!edtmobile.getText().toString().matches(MobilePattern)) {
                    Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                    edtmobile.setError("Please enter valid 10 digit phone number");
                    edtmobile.getText().toString();
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "Validated Succesfully", Toast.LENGTH_LONG).show();
                }

            }

            private Context getApplicationContext() {
                return getContext();
            }


        });
        new PostDataTOServer().execute();
        return v;

    }

    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {

        String response = "";
        //Create hashmap Object to send parameters to web service
        HashMap<String, String> postDataParams;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(AskQuestinsFragment.this.getContext());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            postDataParams = new HashMap<>();
            postDataParams.put("firstname", firstname);
            postDataParams.put("lastname", lastname);
            postDataParams.put("email", email);
            postDataParams.put("mobile", mobile);
            //Call ServerData() method to call webservice and store result in response
            response = service.ServerData(path, postDataParams);
            try {
                json = new JSONObject(response);
                //Get Values from JSONobject
                System.out.println("success=" + json.get("success"));
                success = json.getInt("success");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if (success == 1) {
                Toast.makeText(getApplicationContext(), "Your information  Added successfully..!", Toast.LENGTH_LONG).show();
            }
        }

        private Context getApplicationContext() {
            return getContext();
        }
    }

}

