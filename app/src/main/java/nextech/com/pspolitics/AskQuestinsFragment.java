package nextech.com.pspolitics;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AskQuestinsFragment extends Fragment {
    public AskQuestinsFragment() {
    }

    String MobilePattern = "[0-9]{10}+";
    Button btnReg;
    EditText edtfirstname, edtLastname, edtmobile, edtemail;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(R.string.ask_questions);
        View v = inflater.inflate(R.layout.fragment_ask_questins,
                container, false);

        edtfirstname = (EditText) v.findViewById(R.id.text_first);
        edtLastname = (EditText) v.findViewById(R.id.text_last);
        edtmobile = (EditText) v.findViewById(R.id.text_phone);
        edtemail = (EditText) v.findViewById(R.id.email);
        btnReg = (Button) v.findViewById(R.id.btn_submit);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                }
            }

            private Context getApplicationContext() {
                return getContext();
            }


        });
        return v;

    }

}

