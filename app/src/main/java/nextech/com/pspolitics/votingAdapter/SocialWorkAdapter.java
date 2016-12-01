package nextech.com.pspolitics.votingAdapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.SocialWorkPojo;

/**
 * Created by welcome on 10/25/2016.
 */
public class SocialWorkAdapter extends RecyclerView.Adapter<SocialWorkAdapter.SocialViewHolder>  {
    public static class SocialViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView textsocialPhots;
        TextView  textsocialinformation;
        TextView textsocialDate;

        SocialViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            textsocialPhots = (ImageView)itemView.findViewById(R.id.social_phots);
            textsocialinformation = (TextView)itemView.findViewById(R.id.socila_information);
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
        socialViewHolder.textsocialPhots.setImageResource(socialWorkPojos.get(i).getSocialPhotos());
        socialViewHolder.textsocialinformation.setText(socialWorkPojos.get(i).getSocialInformation());
        socialViewHolder.textsocialDate.setText(socialWorkPojos.get(i).getSocialDate());
    }

    @Override
    public int getItemCount() {
        return socialWorkPojos.size();
    }
}


