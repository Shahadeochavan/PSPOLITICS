package nextech.com.pspolitics;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import nextech.com.pspolitics.votingAdapter.MeetingAdapter;
import nextech.com.pspolitics.votinglistpojo.MeetingPojo;

public class MeetingFragment extends Fragment {
    private List<MeetingPojo> meetingPojos;
    private String resp;
    private RecyclerView rv;
    MeetingAdapter adapter;
    private static String url = "http://192.168.2.107:8080/PSPolitics/json/meeting/get";
    private List<MeetingPojo> meetingPojoList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.Meeting);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        AsynkMeeting asynkMeeting=new AsynkMeeting(rv);
        asynkMeeting.execute();
        return  rootView;
    }
/*
    private void initializeData(){
        meetingPojos = new ArrayList<>();
        meetingPojos.add(new MeetingPojo(getContext().getString(R.string.NitinShelke),getContext().getString(R.string.meeting_date),getContext().getString(R.string.meeting_day),getContext().getString(R.string.meeting_start_time),getContext().getString(R.string.meeting_end_time),getContext().getString(R.string.meeting_location)));
        meetingPojos.add(new MeetingPojo(getContext().getString(R.string.NitinShelke),getContext().getString(R.string.meeting_date),getContext().getString(R.string.meeting_day),getContext().getString(R.string.meeting_start_time),getContext().getString(R.string.meeting_end_time),getContext().getString(R.string.meeting_location)));
        meetingPojos.add(new MeetingPojo(getContext().getString(R.string.NitinShelke),getContext().getString(R.string.meeting_date),getContext().getString(R.string.meeting_day),getContext().getString(R.string.meeting_start_time),getContext().getString(R.string.meeting_end_time),getContext().getString(R.string.meeting_location)));
    }*/

    private void initializeAdapter(){
        MeetingAdapter adapter = new MeetingAdapter(meetingPojos);
        rv.setAdapter(adapter);
    }
    public class AsynkMeeting extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(MeetingFragment.this.getContext());


        public  AsynkMeeting(RecyclerView recyclerView){

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
            List<MeetingPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("meetings");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    MeetingPojo meetingPojo = new MeetingPojo();
                    Log.d("RallyAsync","start time : " + json_data.getString("meetingStartTime"));
                    meetingPojo.setMeetingPersonName(json_data.getString("meetingPersonName"));
                    meetingPojo.setMeetingDate(json_data.getString("meetingDate"));
                    meetingPojo.setMeetingDay(json_data.getString("meetingDay"));
                    meetingPojo.setMeetingStartTime(json_data.getString("meetingStartTime"));
                    meetingPojo.setMeetingEndTime(json_data.getString("meetingEndTime"));
                    meetingPojo.setMeetingLocation(json_data.getString("meetingLocation"));
                    data.add(meetingPojo);
                }
                adapter = new MeetingAdapter(MeetingFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(MeetingFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(MeetingFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}