package nextech.com.pspolitics;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import nextech.com.pspolitics.votingAdapter.PartyAdapter;
import nextech.com.pspolitics.votinglistpojo.PartyPojo;

public class PartyFragment extends Fragment {
    private List<PartyPojo> partyListPojos;
    private String resp;
    private RecyclerView rv;
    PartyAdapter adapter;
    private static String url = "http://192.168.2.103:8080/PSPolitics/json/party/get";
    private static String urlmr= "http://192.168.2.103:8080/PSPolitics/json/party/mr/get";
    private static String urlhn= "http://192.168.2.103:8080/PSPolitics/json/party/hn/get";
    SharedPreferences mPrefs1;
    private String lanuagemr="mr";
    private  String languagehn="hi";
    private String languageen="en";
    private List<PartyPojo> partyList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.Party);
        View rootView = inflater.inflate(R.layout.fragment_party_recylerview, container, false);
        mPrefs1 = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        AsynkParty asynkparty = new AsynkParty(rv);
        asynkparty.execute();
        return rootView;
    }

    public class AsynkParty extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(PartyFragment.this.getContext());


        public AsynkParty(RecyclerView recyclerView) {

            this.recyclerView = recyclerView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading = new ProgressDialog(PartyFragment.this.getContext());
            pdLoading.setMessage("Loading, please wait");
            pdLoading.setTitle("Connecting server");
            pdLoading.show();
            pdLoading.setCancelable(false);

        }

        @Override
        protected String doInBackground(String... params) {
            NetClientGet netClientGet = new NetClientGet();
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
            List<PartyPojo> data = new ArrayList<>();
            pdLoading.dismiss();
            try {
                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("partys");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    PartyPojo partyListPojo = new PartyPojo();
                    partyListPojo.setPersonName(json_data.getString("personName"));
                    partyListPojo.setPartyName(json_data.getString("partyName"));
                    partyListPojo.setDesgination(json_data.getString("desgination"));
                    partyListPojo.setPersonImage(json_data.getString("personImage"));
                    partyListPojo.setPartyImage(json_data.getString("partyImage"));
                    partyListPojo.setAboutParty(json_data.getString("aboutParty"));
                    data.add(partyListPojo);
                }
                adapter = new PartyAdapter(PartyFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(PartyFragment.this.getContext()));
                rv.setAdapter(adapter);
            } catch (JSONException e) {
                Toast.makeText(PartyFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
