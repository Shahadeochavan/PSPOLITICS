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
import nextech.com.pspolitics.votinglistpojo.SocialWorkPojo;

/**
 * Created by welcome on 10/25/2016.
 */
public class SocialWorkAdapter extends RecyclerView.Adapter<SocialWorkAdapter.SocialViewHolder>  {
    public SocialWorkAdapter(Context context) {
    }
    private Context context;
    private LayoutInflater inflater;


    public SocialWorkAdapter(Context context, List<SocialWorkPojo> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.socialWorkPojos=data;
    }
    public static class SocialViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView textsocialPhots;
        TextView  textsocialinformation;
        TextView textshortInfo;
        TextView textsocialDate;

        SocialViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            textsocialPhots = (ImageView)itemView.findViewById(R.id.social_phots);
            textsocialinformation = (TextView)itemView.findViewById(R.id.socila_information);
            textshortInfo = (TextView)itemView.findViewById(R.id.text_shortinfo);
            textsocialDate = (TextView)itemView.findViewById(R.id.social_date);

        }
    }

    List<SocialWorkPojo> socialWorkPojos;

    public SocialWorkAdapter(List<SocialWorkPojo> socialWorkPojos){
        this.socialWorkPojos = socialWorkPojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public SocialViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_social_work, viewGroup, false);
        SocialViewHolder pvh = new SocialViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(SocialViewHolder socialViewHolder, int i) {
      //  socialViewHolder.textsocialPhots.setImageResource(socialWorkPojos.get(i).getSocialPhotos());
        new DownloadImageTask(socialViewHolder.textsocialPhots).execute(socialWorkPojos.get(i).getSocialPhotos());
        socialViewHolder.textsocialinformation.setText(socialWorkPojos.get(i).getSocialInformation());
        socialViewHolder.textshortInfo.setText(socialWorkPojos.get(i).getShortInfo());
        socialViewHolder.textsocialDate.setText(socialWorkPojos.get(i).getSocialDate());
    }

    @Override
    public int getItemCount() {
        return socialWorkPojos.size();
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


