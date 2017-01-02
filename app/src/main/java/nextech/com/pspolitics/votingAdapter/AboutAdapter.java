package nextech.com.pspolitics.votingAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.AboutPojo;

/**
 * Created by welcome on 12/3/2016.
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.AboutViewHolder> {
    public AboutAdapter(Context context) {
    }
    private Context context;
    private LayoutInflater inflater;


    public AboutAdapter(Context context, List<AboutPojo> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.aboutPojos=data;
    }
    public static class AboutViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView textAbout;
        ImageView imgNitinAbout;
        AboutViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            textAbout = (TextView)itemView.findViewById(R.id.about_nitin);
            imgNitinAbout=(ImageView)itemView.findViewById(R.id.image_nitin);
        }
    }

    List<AboutPojo> aboutPojos;

    public AboutAdapter(List<AboutPojo> aboutPojos){
        this.aboutPojos = aboutPojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public AboutViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_about, viewGroup, false);
        AboutViewHolder pvh = new AboutViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AboutViewHolder aboutViewHolder, int i) {
        aboutViewHolder.textAbout.setText(aboutPojos.get(i).getAboutNitin());
        new DownloadImageTask(aboutViewHolder.imgNitinAbout).execute(aboutPojos.get(i).getNitinImage());

    }

    @Override
    public int getItemCount() {
        return aboutPojos.size();
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);

        }
    }
}


