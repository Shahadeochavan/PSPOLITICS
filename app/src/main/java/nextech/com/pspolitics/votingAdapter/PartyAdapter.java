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
import nextech.com.pspolitics.votinglistpojo.PartyPojo;

/**
 * Created by welcome on 11/3/2016.
 */
public class PartyAdapter extends  RecyclerView.Adapter<PartyAdapter.PartyViewHolder>{
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
        partyViewHolder.textPersonName.setText(partyPojos.get(i).personName);
        partyViewHolder.textdesgination.setText(partyPojos.get(i).desgination);
        partyViewHolder.imagePerson.setImageResource(partyPojos.get(i).personImage);
        partyViewHolder.textPartyName.setText(partyPojos.get(i).partyName);
        partyViewHolder.imagePartyImages.setImageResource(partyPojos.get(i).partyImage);
    }

    @Override
    public int getItemCount() {
        return partyPojos.size();
    }
}


