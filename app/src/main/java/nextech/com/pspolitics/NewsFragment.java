package nextech.com.pspolitics;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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

import nextech.com.pspolitics.votingAdapter.NewsAdapter;
import nextech.com.pspolitics.votinglistpojo.NewsListPojo;

public  class NewsFragment extends Fragment {
    private List<NewsListPojo> newsListPojos;
    private String resp;
    private RecyclerView rv;
    NewsAdapter adapter;
    private static String url = "http://192.168.42.174:8080/PSPolitics/json/news/get";
    private List<NewsListPojo> newsList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.News);
     View rootView= inflater.inflate(R.layout.fragment_news, container, false);
        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        AsynkNews asynkNews=new AsynkNews(rv);
        asynkNews.execute();

        return  rootView;
    }
    public class AsynkNews extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(NewsFragment.this.getContext());


        public  AsynkNews(RecyclerView recyclerView){

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
            List<NewsListPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("newss");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    NewsListPojo newsListPojo = new NewsListPojo();
                    newsListPojo.setName(json_data.getString("name"));
                    newsListPojo.setDate(json_data.getString("date"));
                    newsListPojo.setTime(json_data.getString("time"));
                    newsListPojo.setInformationofphots(json_data.getString("informationofphots"));
                    newsListPojo.setPhotoId(json_data.getString("photoId"));
                    newsListPojo.setPhotsNews(json_data.getString("photsNews"));
                    data.add(newsListPojo);
                }
                adapter = new NewsAdapter(NewsFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(NewsFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(NewsFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

}