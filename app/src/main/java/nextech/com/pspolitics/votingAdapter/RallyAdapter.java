package nextech.com.pspolitics.votingAdapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.RallyPojo;

/**
 * Created by welcome on 10/21/2016.
 */
public class RallyAdapter extends RecyclerView.Adapter<RallyAdapter.RallyViewHolder> {
    public static class RallyViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView rallyStartPlace;
        TextView rallyEndPlace;
        TextView rallytextDate;
        TextView rallyStartTime;
        TextView rallyEndTime;

        RallyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            rallyStartPlace = (TextView)itemView.findViewById(R.id.start_place_name);
            rallyEndPlace = (TextView)itemView.findViewById(R.id.end_place_name);
            rallytextDate = (TextView)itemView.findViewById(R.id.rally_date);
            rallyStartTime = (TextView)itemView.findViewById(R.id.rally_start_time);
            rallyEndTime = (TextView)itemView.findViewById(R.id.rally_end_time);

        }
    }

    List<RallyPojo> rallyPojos;

    public RallyAdapter(List<RallyPojo> rallyPojos){
        this.rallyPojos = rallyPojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RallyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_rally_card, viewGroup, false);
        RallyViewHolder pvh = new RallyViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RallyViewHolder rallyViewHolder, int i) {
        rallyViewHolder.rallyStartPlace.setText(rallyPojos.get(i).startPlaceName);
        rallyViewHolder.rallyEndPlace.setText(rallyPojos.get(i).endPlaceName);
        rallyViewHolder.rallytextDate.setText(rallyPojos.get(i).rallyDate);
        rallyViewHolder.rallyStartTime.setText(rallyPojos.get(i).startTime);
        rallyViewHolder.rallyEndTime.setText(rallyPojos.get(i).endTime);
    }

    @Override
    public int getItemCount() {
        return rallyPojos.size();
    }
}

