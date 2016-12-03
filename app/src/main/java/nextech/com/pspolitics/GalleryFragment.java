package nextech.com.pspolitics;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.nextech.util.NetClientGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nextech.com.pspolitics.votingAdapter.GalleryAdapter;
import nextech.com.pspolitics.votinglistpojo.GalleryPojo;

public class GalleryFragment extends Fragment {
    public GalleryFragment(){}
    EditText inputSearch;

    private List<GalleryPojo> galleryPojos;
    private String resp;
    private GridView rv;
    GalleryAdapter adapter;
    private static String url = "http://192.168.0.100:8080/PSPolitics/json/gallery/get";
    private List<GalleryPojo> galleryList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.Gallery);
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        GridView lv = (GridView)rootView.findViewById(R.id.gridview);
        return rootView;
    }
    public class AsynkGallery extends AsyncTask<String, String, String> {

        private RecyclerView recyclerView;
        private ProgressDialog pdLoading = new ProgressDialog(GalleryFragment.this.getContext());


        public  AsynkGallery(RecyclerView recyclerView){

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
            List<GalleryPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("newss");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    GalleryPojo galleryPojo = new GalleryPojo();
                    data.add(galleryPojo);
                }
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(GalleryFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
