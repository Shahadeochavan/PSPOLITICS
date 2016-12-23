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

import nextech.com.pspolitics.votingAdapter.SocialWorkAdapter;
import nextech.com.pspolitics.votinglistpojo.SocialWorkPojo;


/**
 * A simple {@link Fragment} subclass.
 */
public class SocialWorkFragment extends Fragment {
    private List<SocialWorkPojo> socialWorkPojos;
    private String resp;
    private RecyclerView rv;
    SocialWorkAdapter adapter;
    private static String url = "http://192.168.2.102:8080/PSPolitics/json/socialwork/get";
    private List<SocialWorkPojo> socialWrokList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.SocialWork);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        AsynkSocialWork asynkSocialWork=new AsynkSocialWork(rv);
        asynkSocialWork.execute();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        return  rootView;
    }

    public class AsynkSocialWork extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(SocialWorkFragment.this.getContext());


        public  AsynkSocialWork(RecyclerView recyclerView){

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
            List<SocialWorkPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("soicalWorks");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    SocialWorkPojo socialWorkPojo = new SocialWorkPojo();
                    socialWorkPojo.setSocialDate(json_data.getString("socialDate"));
                    socialWorkPojo.setSocialInformation(json_data.getString("socialInformation"));
                    socialWorkPojo.setSocialPhotos(json_data.getString("socialPhotos"));
                    data.add(socialWorkPojo);
                }
                adapter = new SocialWorkAdapter(SocialWorkFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(SocialWorkFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(SocialWorkFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}