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
import nextech.com.pspolitics.votinglistpojo.PartyPojo;

/**
 * Created by welcome on 11/3/2016.
 */
public class PartyAdapter extends  RecyclerView.Adapter<PartyAdapter.PartyViewHolder>{
    public PartyAdapter(Context context) {
    }
    private Context context;
    private LayoutInflater inflater;


    public PartyAdapter(Context context, List<PartyPojo> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.partyPojos=data;
    }
    public static class PartyViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView textPersonName;
        TextView textdesgination;
        ImageView imagePerson;
        TextView textPartyName;
        ImageView imagePartyImages;
        PartyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            textPersonName = (TextView)itemView.findViewById(R.id.party_person_name);
            textdesgination = (TextView)itemView.findViewById(R.id.party_desgination);
            imagePerson = (ImageView)itemView.findViewById(R.id.person_photo_party);
            textPartyName = (TextView)itemView.findViewById(R.id.party_name);
            imagePartyImages = (ImageView)itemView.findViewById(R.id.party_image);
        }
    }

    List<PartyPojo> partyPojos;

    public PartyAdapter(List<PartyPojo> partyPojos){
        this.partyPojos = partyPojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PartyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_party, viewGroup, false);
        PartyViewHolder pvh = new PartyViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PartyViewHolder partyViewHolder, int i) {
        new DownloadImageTask(partyViewHolder.imagePerson).execute(partyPojos.get(i).getPersonImage());
        new DownloadImageTask(partyViewHolder.imagePartyImages).execute(partyPojos.get(i).getPartyImage());
        partyViewHolder.textPersonName.setText(partyPojos.get(i).getPersonName());
        partyViewHolder.textdesgination.setText(partyPojos.get(i).getDesgination());
        partyViewHolder.textPartyName.setText(partyPojos.get(i).getPartyName());

    }

    @Override
    public int getItemCount() {
        return partyPojos.size();
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


