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

import nextech.com.pspolitics.votingAdapter.VotingScheduleAdapter;
import nextech.com.pspolitics.votinglistpojo.VotingSchedulePojo;


/**
 * A simple {@link Fragment} subclass.
 */
public class VotingScheduleFragment extends Fragment {
    private List<VotingSchedulePojo> votingSchedulePojos;
    private String resp;
    private RecyclerView rv;
    VotingScheduleAdapter adapter;
    private static String url = "http://192.168.2.102:8080/PSPolitics/json/votingschedule/get";
    private List<VotingSchedulePojo> votingScheduleList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.VotingSchedule);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);
        AsynkVotingSchedule asynkVotingSchedule=new AsynkVotingSchedule(rv);
        asynkVotingSchedule.execute();

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        return  rootView;
    }
    public class AsynkVotingSchedule extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(VotingScheduleFragment.this.getContext());


        public  AsynkVotingSchedule(RecyclerView recyclerView){

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
            List<VotingSchedulePojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("votingSchedule");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    VotingSchedulePojo votingSchedulePojo = new VotingSchedulePojo();
                    votingSchedulePojo.setVotingDay(json_data.getString("votingDay"));
                    votingSchedulePojo.setVotingDate(json_data.getString("votingDate"));
                    votingSchedulePojo.setVotingStartTime(json_data.getString("votingStartTime"));
                    votingSchedulePojo.setVotingEndTime(json_data.getString("votingEndTime"));
                    data.add(votingSchedulePojo);
                }
                adapter = new VotingScheduleAdapter(VotingScheduleFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(VotingScheduleFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(VotingScheduleFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}