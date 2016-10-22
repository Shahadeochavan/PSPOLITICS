package nextech.com.pspolitics.votinglistpojo;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.URL;

import nextech.com.pspolitics.NewsFragment;

/**
 * Created by welcome on 10/20/2016.
 */
public class GetBitmap extends AsyncTask<Void,Void,Void> {

    private Context context;
    private String[] urls;
    private ProgressDialog loading;
    private NewsFragment newsFragment;

    public GetBitmap(Context context, NewsFragment newsFragment, String[] urls){
        this.context = context;
        this.urls = urls;
        this.newsFragment = newsFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(context,"Downloading Image","Please wait...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        loading.dismiss();
        newsFragment.showData();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(int i=0; i<urls.length; i++){
            Config.bitmaps[i] = getImage(urls[i]);
        }
        return null;
    }

    private Bitmap getImage(String bitmapUrl){
        URL url;
        Bitmap image = null;
        try {
            url = new URL(bitmapUrl);
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch(Exception e){}
        return image;
    }
}