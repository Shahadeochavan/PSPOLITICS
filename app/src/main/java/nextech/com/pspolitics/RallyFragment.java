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

import nextech.com.pspolitics.votingAdapter.RallyAdapter;
import nextech.com.pspolitics.votinglistpojo.RallyPojo;

public class RallyFragment extends Fragment {
    private String resp;
    private List<RallyPojo> rallyPojos;
    private RecyclerView rv;
    RallyAdapter adapter;
    View rootView;
    private static String url = "http://192.168.0.100:8080/RESTfulExample/json/rally/get";
    private List<RallyPojo> rallyPojoList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.Rally);
         rootView = inflater.inflate(R.layout.fragment_rally, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        AsynkRally asynkRally=new AsynkRally(rv);
        asynkRally.execute();
        return rootView;
    }

 /*   private void initializeData() {
        rallyPojos = new ArrayList<>();
        rallyPojos.add(new RallyPojo(getContext().getString(R.string.wadegonchowk), getContext().getString(R.string.shirurphata), getContext().getString(R.string.date_of_rally), getContext().getString(R.string.rally_start1), getContext().getString(R.string.rally_end1), getContext().getString(R.string.rally_day1)));
        rallyPojos.add(new RallyPojo(getContext().getString(R.string.baner), getContext().getString(R.string.pune_station), getContext().getString(R.string.date_of_rally1), getContext().getString(R.string.rally_start2), getContext().getString(R.string.rally_end2), getContext().getString(R.string.rally_day2)));
        rallyPojos.add(new RallyPojo(getContext().getString(R.string.karave_nagar), getContext().getString(R.string.hadpasr), getContext().getString(R.string.date_of_rally3), getContext().getString(R.string.rally_start3), getContext().getString(R.string.rally_end3), getContext().getString(R.string.rally_day3)));

    }*/

    private void initializeAdapter() {
        RallyAdapter adapter = new RallyAdapter(rallyPojos);
        rv.setAdapter(adapter);
    }

    public class AsynkRally extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(RallyFragment.this.getContext());


        public  AsynkRally(RecyclerView recyclerView){

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
            List<RallyPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("rallys");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    RallyPojo rallyPojo = new RallyPojo();
                    Log.d("RallyAsync","start time : " + json_data.getString("startTime"));
                    rallyPojo.setStartTime(json_data.getString("startTime"));
                    rallyPojo.setEndTime(json_data.getString("endTime"));
                    rallyPojo.setRallyDate(json_data.getString("date"));
                    rallyPojo.setStartPlaceName(json_data.getString("startPoint"));
                    rallyPojo.setEndPlaceName(json_data.getString("endPoint"));
                    data.add(rallyPojo);
                }



                adapter = new RallyAdapter(RallyFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(RallyFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(RallyFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}