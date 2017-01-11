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

import com.google.gson.Gson;
import com.nextech.util.HTTPURLConnection;
import com.nextech.util.NetClientPost;

import org.json.JSONObject;

import nextech.com.pspolitics.votinglistpojo.AskQuestionPojo;

public class AskQuestinsFragment extends Fragment {
    public AskQuestinsFragment() {
    }

    private ProgressDialog pDialog;
    private JSONObject json;
    private int success = 0;
    String firstname, lastname, email, mobile, questions;
    private HTTPURLConnection service;
    String MobilePattern = "[0-9]{10}+";
    Button btnReg;
    EditText edtfirstname, edtLastname, edtmobile, edtemail, edtquestions;
    String response;
    String jsonbody;
    private String url = "http://192.168.42.174:8080/PSPolitics/json/askquestins/post";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(R.string.ask_questions);
        View v = inflater.inflate(R.layout.fragment_ask_questins,
                container, false);

        edtfirstname = (EditText) v.findViewById(R.id.text_first);
        edtLastname = (EditText) v.findViewById(R.id.text_last);
        edtmobile = (EditText) v.findViewById(R.id.text_phone);
        edtemail = (EditText) v.findViewById(R.id.email);
        edtquestions = (EditText) v.findViewById(R.id.edt_ask);
        btnReg = (Button) v.findViewById(R.id.btn_submit);
        service = new HTTPURLConnection();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AskQuestionPojo askQuestionPojo = new AskQuestionPojo();

                if (edtfirstname.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "First Name cannot be Blank", Toast.LENGTH_LONG).show();
                    edtfirstname.setError("First Name cannot be Blank");
                    return;

                } else if (edtLastname.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Last Name cannot be Blank", Toast.LENGTH_LONG).show();
                    edtLastname.setError("Last Name cannot be Blank");

                    return;
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(edtemail.getText().toString()).matches()) {
                    //Validation for Invalid Email Address
                    Toast.makeText(getApplicationContext(), "Please enter valid Email", Toast.LENGTH_LONG).show();
                    edtemail.setError("Please enter valid Email");

                    return;
                } else if (!edtmobile.getText().toString().matches(MobilePattern)) {
                    Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                    edtmobile.setError("Please enter valid 10 digit phone number");

                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "Validated Succesfully", Toast.LENGTH_LONG).show();
                    firstname = edtfirstname.getText().toString();
                    askQuestionPojo.setFirst_name(firstname);
                    lastname = edtLastname.getText().toString();
                    askQuestionPojo.setLast_name(lastname);
                    email = edtemail.getText().toString();
                    askQuestionPojo.setEmail(email);
                    askQuestionPojo.setPhone_number(edtmobile.getText().toString());
                    askQuestionPojo.setQuestion(edtquestions.getText().toString());
                    Gson gson = new Gson();
                    jsonbody = gson.toJson(askQuestionPojo);
                    System.out.println("response is" + response);
                    AsynkAskQuestions asynkAskQuestions = new AsynkAskQuestions();
                    asynkAskQuestions.execute();
                }

            }

            private Context getApplicationContext() {
                return getContext();
            }


        });
        return v;

    }

    public class AsynkAskQuestions extends AsyncTask<String, String, String> {
        private ProgressDialog pdLoading = new ProgressDialog(AskQuestinsFragment.this.getContext());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            NetClientPost netClientPost = new NetClientPost();
            response = netClientPost.netClientPost(url, jsonbody);
            System.out.println("Respsone is : " + response);
            return response;

        }

        @Override
        protected void onPostExecute(String result) {
            JSONObject jsonObject = new JSONObject();

        }

    }
}