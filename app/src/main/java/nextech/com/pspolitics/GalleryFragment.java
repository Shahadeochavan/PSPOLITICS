package nextech.com.pspolitics;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    List<GalleryPojo> galleryData = new ArrayList<>();
    private String resp;
    View rootView;
    private GridView lv;
    GalleryAdapter galleryAdapter;
    private ArrayList<GalleryPojo> mGridData;
    private static String url = "http://192.168.2.107:8080/PSPolitics/json/gallery/get";
    private List<GalleryPojo> galleryPojosList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.Gallery);
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        lv = (GridView) rootView.findViewById(R.id.gridView);
        AsynkGallery asynkGallery = new AsynkGallery(lv);
        asynkGallery.execute();
        return rootView;
    }

    public class AsynkGallery extends AsyncTask<String, String, String> {

        private GridView gridView;
        private ProgressDialog pdLoading = new ProgressDialog(GalleryFragment.this.getContext());


        public AsynkGallery(GridView gridView) {

            this.gridView = gridView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading = new ProgressDialog(GalleryFragment.this.getContext());
            pdLoading.setMessage("Loading, please wait");
            pdLoading.setTitle("Connecting server");
            pdLoading.show();
            pdLoading.setCancelable(false);

        }

        @Override
        protected String doInBackground(String... params) {
            NetClientGet netClientGet = new NetClientGet();
            resp = netClientGet.netClientGet(url);
            System.out.println("Respsone is : " + resp);
            return resp;

        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();

            pdLoading.dismiss();
            try {
                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("galleries");
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    GalleryPojo partyListPojo = new GalleryPojo();
                    partyListPojo.setImages(json_data.getString("images"));
                    galleryData.add(partyListPojo);
                }

                galleryAdapter = new GalleryAdapter(GalleryFragment.this.getContext(), galleryData);
                lv.setAdapter(galleryAdapter);
            } catch (JSONException e) {
                Toast.makeText(GalleryFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
