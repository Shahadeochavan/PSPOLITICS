package nextech.com.pspolitics;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nextech.util.NetClientGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nextech.com.pspolitics.votingAdapter.PersonalInfoAdapter;
import nextech.com.pspolitics.votinglistpojo.PersonalInfoPojo;


public class PersonalInfoFragment extends Fragment {
    private List<PersonalInfoPojo> personalInfoPojos;
    private String resp;
    private RecyclerView rv;
    PersonalInfoAdapter adapter;
    private static String url = "http://192.168.2.103:8080/PSPolitics/json/personalinfo/get";
    private static String urlmr= "http://192.168.2.103:8080/PSPolitics/json/personalinfo/mr/get";
    private static String urlhn= "http://192.168.2.103:8080/PSPolitics/json/personalinfo/hn/get";
    SharedPreferences mPrefs1;
    private String lanuagemr="mr";
    private  String languagehn="hi";
    private List<PersonalInfoPojo> personalInfoList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.PersonalInfo);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);
        mPrefs1 = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        AsynkPersonalInfo asynkParty=new AsynkPersonalInfo(rv);
        asynkParty.execute();
        return  rootView;
    }
    public class AsynkPersonalInfo extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(PersonalInfoFragment.this.getContext());


        public  AsynkPersonalInfo(RecyclerView recyclerView){

            this.recyclerView = recyclerView;
        }

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
            NetClientGet netClientGet=new NetClientGet();
            String languageToLoad = mPrefs1.getString("languagePref", Locale.getDefault().getLanguage());
            if(languageToLoad.equals(lanuagemr)){
                resp=netClientGet.netClientGet(urlmr);
            }else if(languageToLoad.equals(languagehn)) {
                resp = netClientGet.netClientGet(urlhn);
            }else {
                resp = netClientGet.netClientGet(url);
            }
            System.out.println("Respsone is : " + resp);
            return resp;

        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();
            List<PersonalInfoPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("personalInfos");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    PersonalInfoPojo personalInfoPojo = new PersonalInfoPojo();
                    personalInfoPojo.setImageNitin(json_data.getString("image"));
                    personalInfoPojo.setName(json_data.getString("name"));
                    personalInfoPojo.setEducation(json_data.getString("education"));
                    personalInfoPojo.setWorking(json_data.getString("working"));
                    personalInfoPojo.setAddress(json_data.getString("address"));
                    data.add(personalInfoPojo);
                }
                adapter = new PersonalInfoAdapter(PersonalInfoFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(PersonalInfoFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(PersonalInfoFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
