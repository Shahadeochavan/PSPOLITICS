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

import nextech.com.pspolitics.votingAdapter.VotingCenterAdapter;
import nextech.com.pspolitics.votinglistpojo.MeetingPojo;
import nextech.com.pspolitics.votinglistpojo.VotingCenterPojo;


public class VotingCentersFragment extends Fragment {
    private List<VotingCenterPojo> votingCenterPojos;
    private String resp;
    private RecyclerView rv;
    VotingCenterAdapter adapter;
    private static String url = "http://192.168.0.100:8080/RESTfulExample/json/votingcenter/get";
    private List<MeetingPojo> meetingPojoList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.VotingCenters);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        AsynkVotingCenter asynkVotingCenter=new AsynkVotingCenter(rv);
        asynkVotingCenter.execute();
        return  rootView;
    }

    public class AsynkVotingCenter extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(VotingCentersFragment.this.getContext());


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
            List<VotingCenterPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("votingCenters");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    VotingCenterPojo votingCenterPojo = new VotingCenterPojo();
                    votingCenterPojo.setAddress(json_data.getString("address"));
                    votingCenterPojo.setPlaceName(json_data.getString("placeName"));
                    votingCenterPojo.setDate(json_data.getString("date"));
                    votingCenterPojo.setStartTime(json_data.getString("startTime"));
                    votingCenterPojo.setEndTime(json_data.getString("endTime"));
                    data.add(votingCenterPojo);
                }
                adapter = new VotingCenterAdapter(VotingCentersFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(VotingCentersFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(VotingCentersFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

}
