package nextech.com.pspolitics;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.nextech.util.NetClientGet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import nextech.com.pspolitics.votingAdapter.PartyAdapter;
import nextech.com.pspolitics.votinglistpojo.PartyPojo;
import nextech.com.pspolitics.votinglistpojo.VotingSchedulePojo;

public class PartyFragment extends Fragment {
    private List<PartyPojo> partyPojos;
    private String resp;
    private RecyclerView rv;
    private ImageView imageView;
    PartyAdapter adapter;
    private static String url = "http://192.168.0.100:8080/PSPolitics/json/party/get";
    public static final String URL =
            "http://192.168.0.100:8080/PSPolitics/img/nitin.jpg";
    private List<VotingSchedulePojo> votingScheduleList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.Party);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        imageView=(ImageView)rootView.findViewById(R.id.person_photo_party);
        AsynkParty asynkParty=new AsynkParty(rv,imageView);
        asynkParty.execute(new String[] { URL });
        return  rootView;
    }
    public class AsynkParty extends AsyncTask<String, Void, Bitmap> {

        private RecyclerView recyclerView;
        private  ImageView imageView;
        private ProgressDialog pdLoading = new ProgressDialog(PartyFragment.this.getContext());


        public  AsynkParty(RecyclerView recyclerView,ImageView imageView){
            this.recyclerView = recyclerView;
            this.imageView=imageView;
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
        protected Bitmap doInBackground(String... URL) {
            NetClientGet netClientGet=new NetClientGet();
            resp=netClientGet.netClientGet(url);
            System.out.println("Respsone is : " + resp);
            Bitmap map = null;
            for (String url : URL) {
                map = downloadImage(url);
            }
            return map;

        }

        @Override
        protected void onPostExecute(Bitmap  result) {
            pdLoading.dismiss();
            List<PartyPojo> data=new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONObject partyyResponse = new JSONObject(String.valueOf(result));
                JSONArray jArray = partyyResponse.getJSONArray("partys");
                for(int i=0;i<jArray.length();i++){
                    JSONObject json_data = jArray.getJSONObject(i);
                    PartyPojo partyPojo = new PartyPojo();
                    Log.d("AsynkParty","person name : " + json_data.getString("personName"));
                    partyPojo.setPersonName(json_data.getString("personName"));
                    partyPojo.setPartyName(json_data.getString("partyName"));
                    partyPojo.setDesgination(json_data.getString("desgination"));
                    partyPojo.setPersonImage(json_data.getInt("personPhoto"));
                    imageView.setImageBitmap(result);
                    data.add(partyPojo);
                }
                adapter = new PartyAdapter(PartyFragment.this.getContext(), data);
                rv.setLayoutManager(new LinearLayoutManager(PartyFragment.this.getContext()));
                rv.setAdapter(adapter);

            } catch (JSONException e) {
                Toast.makeText(PartyFragment.this.getContext(), e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
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
        java.net.URL url = new URL(urlString);
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