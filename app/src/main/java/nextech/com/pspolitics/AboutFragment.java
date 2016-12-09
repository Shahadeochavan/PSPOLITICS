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

import nextech.com.pspolitics.votingAdapter.AboutAdapter;
import nextech.com.pspolitics.votinglistpojo.AboutPojo;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private List<AboutPojo> partyPojos;
    private String resp;
    private RecyclerView rv;
    AboutAdapter adapter;
    private static String url = "http://192.168.0.100:8080/PSPolitics/json/aboutnitin/get";
    private List<AboutPojo> votingScheduleList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.AboutNitinShelke);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        AsynkAbout asynkParty=new AsynkAbout(rv);
        asynkParty.execute();
        return  rootView;
    }
    public class AsynkAbout extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(AboutFragment.this.getContext());


        public  AsynkAbout(RecyclerView recyclerView){

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
            List<AboutPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("aboutNitins");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    AboutPojo aboutPojo = new AboutPojo();
                    aboutPojo.setAboutNitin(json_data.getString("aboutNitin"));
                    aboutPojo.setNitinImage(json_data.getString("nitinImage"));
                    data.add(aboutPojo);
                }
                adapter = new AboutAdapter(AboutFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(AboutFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(AboutFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}