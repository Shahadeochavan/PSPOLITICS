package nextech.com.pspolitics.votingAdapter;

import android.content.Context;
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
    public RallyAdapter(Context context) {
    }
    private Context context;
    private LayoutInflater inflater;


    public RallyAdapter(Context context, List<RallyPojo> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.rallyPojos=data;
    }
    public static class RallyViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView rallyStartPlace;
        TextView rallyEndPlace;
        TextView rallytextDate;
        TextView rallyStartTime;
        TextView rallyEndTime;
        TextView textDay;
        RallyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            rallyStartPlace = (TextView)itemView.findViewById(R.id.start_place_name);
            textDay = (TextView)itemView.findViewById(R.id.rally_day);
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
        rallyViewHolder.rallyStartPlace.setText(rallyPojos.get(i).getStartPlaceName());
        rallyViewHolder.textDay.setText(rallyPojos.get(i).getDayRally());
        rallyViewHolder.rallyEndPlace.setText(rallyPojos.get(i).getEndPlaceName());
        rallyViewHolder.rallytextDate.setText(rallyPojos.get(i).getRallyDate());
        rallyViewHolder.rallyStartTime.setText(rallyPojos.get(i).getStartTime());
        rallyViewHolder.rallyEndTime.setText(rallyPojos.get(i).getEndTime());

    }

    @Override
    public int getItemCount() {
        return rallyPojos.size();
    }


}


