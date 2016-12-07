package nextech.com.pspolitics;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
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
    private ListView noteList;

    private List<VotingListPojo> votingListPojos;
    private String resp;
    private ListView rv;
    VotingListAdapter adapter;
    private static String url = "http://192.168.0.105:8080/PSPolitics/json/votinglist/get";
    private List<VotingListPojo> votingList = new ArrayList<>();
    public VotingListFragment(){}
    EditText inputSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.VotingList);
        View rootView = inflater.inflate(R.layout.fragment_voting_list, container, false);

      rv = (ListView)rootView.findViewById(R.id.list_item);
        AsynkVotingList asynkVotingList=new AsynkVotingList(rv);
        asynkVotingList.execute();

        return rootView;
    }

    public class AsynkVotingList extends AsyncTask<String, String, String> {

        private ListView listView;
        private ProgressDialog pdLoading = new ProgressDialog(VotingListFragment.this.getContext());


        public  AsynkVotingList(ListView listView){

            this.listView = listView;
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
                    Log.d("RallyAsync","lastName  : " + json_data.getString("lastName"));
                    votingListPojo.setLastName(json_data.getString("lastName"));
                    votingListPojo.setFirstName(json_data.getString("firstName"));
                    votingListPojo.setMiddleName(json_data.getString("middleName"));
                    votingListPojo.setPhoneNumber(json_data.getString("phoneNumber"));
                    votingListPojo.setWardDetails(json_data.getString("wardDetails"));
                    data.add(votingListPojo);
                }
                adapter = new VotingListAdapter(VotingListFragment.this.getContext(), data);
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(VotingListFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
