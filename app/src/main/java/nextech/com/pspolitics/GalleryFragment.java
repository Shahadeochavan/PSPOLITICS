package nextech.com.pspolitics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import nextech.com.pspolitics.votinglistpojo.GalleryPojo;

public class GalleryFragment extends Fragment {
    public GalleryFragment(){}
    EditText inputSearch;

    private List<GalleryPojo> galleryPojos;
    private String resp;
    private GridView rv;
   // GalleryAdapter adapter;
    public static final String URL =
            "http://192.168.0.105:8080/PSPolitics/img/nitin.jpg";
    ImageView imageView;
    private List<GalleryPojo> galleryList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.Gallery);
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        GetXMLTask task = new GetXMLTask();
        // Execute the task
        task.execute(new String[] { URL });
        return rootView;
    }
   /* public class AsynkGallery extends AsyncTask<String, String, String> {

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

    }*/
   private class GetXMLTask extends AsyncTask<String, String, Bitmap> {
       @Override
       protected Bitmap doInBackground(String... urls) {
           Bitmap map = null;
           for (String url : urls) {
               map = downloadImage(url);
           }
           return map;
       }

       // Sets the Bitmap returned by doInBackground
       @Override
       protected void onPostExecute(Bitmap result) {
           imageView.setImageBitmap(result);
       }

       // Creates Bitmap from InputStream and returns it
       private Bitmap downloadImage(String url) {
           Bitmap bitmap = null;
           InputStream stream = null;
           BitmapFactory.Options bmOptions = new BitmapFactory.Options();
           bmOptions.inSampleSize = 1;

           try {
               stream = getHttpConnection(url);
               bitmap = BitmapFactory.
                       decodeStream(stream, null, bmOptions);
               stream.close();
           } catch (IOException e1) {
               e1.printStackTrace();
           }
           return bitmap;
       }

       // Makes HttpURLConnection and returns InputStream
       private InputStream getHttpConnection(String urlString)
               throws IOException {
           InputStream stream = null;
           URL url = new URL(urlString);
           URLConnection connection = url.openConnection();

           try {
               HttpURLConnection httpConnection = (HttpURLConnection) connection;
               httpConnection.setRequestMethod("GET");
               httpConnection.connect();

               if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                   stream = httpConnection.getInputStream();
               }
           } catch (Exception ex) {
               ex.printStackTrace();
           }
           return stream;
       }
   }
}
