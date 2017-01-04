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

import nextech.com.pspolitics.votingAdapter.VotingListAdapter;
import nextech.com.pspolitics.votinglistpojo.VotingListPojo;

public class VotingListFragment extends Fragment {
    private List<VotingListPojo> votingListPojos;
    private String resp;
    private RecyclerView rv;
    VotingListAdapter adapter;
    private static String url = "http://192.168.2.104:8080/PSPolitics/json/votinglist/get";
    private List<VotingListPojo> meetingPojoList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.VotingList);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        AsynkVotingCenter asynkVotingCenter=new AsynkVotingCenter(rv);
        asynkVotingCenter.execute();
        return  rootView;
    }

    public class AsynkVotingCenter extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(VotingListFragment.this.getContext());


        public  AsynkVotingCenter(RecyclerView recyclerView){

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
            List<VotingListPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("votingLists");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    VotingListPojo votingListPojo = new VotingListPojo();
                    votingListPojo.setFirstName(json_data.getString("firstName"));
                    votingListPojo.setLastName(json_data.getString("lastName"));
                    votingListPojo.setMiddleName(json_data.getString("middleName"));
                    votingListPojo.setWardDetails(json_data.getString("wardDetails"));
                    votingListPojo.setPhoneNumber(json_data.getString("phoneNumber"));
                    data.add(votingListPojo);
                }
                adapter = new VotingListAdapter(VotingListFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(VotingListFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(VotingListFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

}

