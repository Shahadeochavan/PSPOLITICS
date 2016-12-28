package nextech.com.pspolitics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    private static final String TAG = GalleryFragment.class.getSimpleName();

    private GridView mGridView;
    private ProgressBar mProgressBar;

    private GalleryAdapter galleryAdapter;
    private ArrayList<GalleryPojo> galleryPojos;
    private String url = "http://192.168.2.103:8080/PSPolitics/json/gallery/get";
    ImageView imageView;
    private List<GalleryPojo> galleryList = new ArrayList<>();
    String resp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.Gallery);
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        mGridView = (GridView) rootView.findViewById(R.id.gridView);
        //Initialize with empty data
        galleryPojos = new ArrayList<>();
        galleryAdapter = new GalleryAdapter(this.getContext(), R.layout.gallery_list, galleryPojos);
        mGridView.setAdapter(galleryAdapter);

        //Grid view click event
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Get item at position
                GalleryPojo item = (GalleryPojo) parent.getItemAtPosition(position);

                Intent intent = new Intent(GalleryFragment.this.getContext(), GalleryFragment.class);
                ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                int[] screenLocation = new int[2];
                imageView.getLocationOnScreen(screenLocation);

                //Pass the image title and url to DetailsActivity
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", imageView.getWidth()).
                        putExtra("height", imageView.getHeight()).
                        putExtra("image", item.getImages());

                //Start details activity
                startActivity(intent);
            }
        });
        AsynkGallery asynkGallery=new AsynkGallery(mGridView);
        asynkGallery.execute();

        //Start download
        return  rootView;
    }

    public class AsynkGallery extends AsyncTask<String, String, String> {

        private GridView gridView;
        private ProgressDialog pdLoading = new ProgressDialog(GalleryFragment.this.getContext());


        public  AsynkGallery(GridView gridView){

            this.gridView = gridView;
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
            System.out.println("i am in postexecute");
            pdLoading.dismiss();
            try {

                JSONObject rallyResponse = new JSONObject(result);
                JSONArray jArray = rallyResponse.getJSONArray("galleries");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    GalleryPojo galleryPojo = new GalleryPojo();
                    Log.d("galleryPojo","Images : " + json_data.getString("images"));
                    galleryPojo.setImages(json_data.getString("images"));
                    data.add(galleryPojo);
                }
                galleryAdapter = new GalleryAdapter(GalleryFragment.this.getContext(), R.layout.gallery_list,galleryPojos);
                mGridView.setAdapter(galleryAdapter);

            } catch (JSONException e) {
                Toast.makeText(GalleryFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

}