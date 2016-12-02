package nextech.com.pspolitics;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
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

import nextech.com.pspolitics.votingAdapter.PartyAdapter;
import nextech.com.pspolitics.votinglistpojo.PartyPojo;
import nextech.com.pspolitics.votinglistpojo.VotingSchedulePojo;

public class PartyFragment extends Fragment {
    private List<PartyPojo> partyPojos;
    private String resp;
    private RecyclerView rv;
    PartyAdapter adapter;
    private static String url = "http://192.168.0.100:8080/RESTfulExample/json/party/get";
    private List<VotingSchedulePojo> votingScheduleList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.Party);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        AsynkParty asynkParty=new AsynkParty(rv);
        asynkParty.execute();
        return  rootView;
    }
    public class AsynkParty extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(PartyFragment.this.getContext());


        public  AsynkParty(RecyclerView recyclerView){

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
            resp=netClientGet.netClientGet(url);
            System.out.println("Respsone is : " + resp);
            return resp;

        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();
            List<PartyPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("partys");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    PartyPojo partyPojo = new PartyPojo();
                    partyPojo.setPersonName(json_data.getString("personName"));
                    partyPojo.setPartyName(json_data.getString("partyName"));
                    partyPojo.setDesgination(json_data.getString("desgination"));
                    data.add(partyPojo);
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