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
import nextech.com.pspolitics.votinglistpojo.PersonalInfoPojo;

/**
 * Created by welcome on 12/3/2016.
 */
public class PersonalInfoAdapter extends  RecyclerView.Adapter<PersonalInfoAdapter.PersonalViewHolder> {
    public PersonalInfoAdapter(Context context) {
    }

    private Context context;
    private LayoutInflater inflater;


    public PersonalInfoAdapter(Context context, List<PersonalInfoPojo> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.personalInfoPojos = data;
    }

    public static class PersonalViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView textPersonName;
        TextView texteducation;
        TextView textWorking;
        TextView textAddress;
        ImageView imgNitin;

        PersonalViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            textPersonName = (TextView) itemView.findViewById(R.id.text_nitin_name);
            texteducation = (TextView) itemView.findViewById(R.id.text_education_nitin);
            textWorking = (TextView) itemView.findViewById(R.id.text_working_nitin);
            textAddress = (TextView) itemView.findViewById(R.id.text_address_nitin);
            imgNitin=(ImageView)itemView.findViewById(R.id.imageViewnitin);
        }
    }

    List<PersonalInfoPojo> personalInfoPojos;

    public PersonalInfoAdapter(List<PersonalInfoPojo> personalInfoPojos) {
        this.personalInfoPojos = personalInfoPojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_personal_info, viewGroup, false);
        PersonalViewHolder pvh = new PersonalViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonalViewHolder partyViewHolder, int i) {
        new DownloadImageTask(partyViewHolder.imgNitin).execute(personalInfoPojos.get(i).getImageNitin());
        partyViewHolder.textPersonName.setText(personalInfoPojos.get(i).getName());
        partyViewHolder.texteducation.setText(personalInfoPojos.get(i).getEducation());
        partyViewHolder.textWorking.setText(personalInfoPojos.get(i).getWorking());
        partyViewHolder.textAddress.setText(personalInfoPojos.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return personalInfoPojos.size();
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
