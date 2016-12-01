package nextech.com.pspolitics.votingAdapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.VotingCenterPojo;

/**
 * Created by welcome on 10/19/2016.
 */
public class VotingCenterAdapter extends RecyclerView.Adapter<VotingCenterAdapter.VotingCenterViewHolder>  {

    public static class VotingCenterViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView textPlacename;
        TextView textAddress;
        TextView textDate;
        TextView textStartTime;
        TextView textEndTime;

        VotingCenterViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            textPlacename = (TextView)itemView.findViewById(R.id.text_location);
            textAddress = (TextView)itemView.findViewById(R.id.text_address);
            textDate = (TextView)itemView.findViewById(R.id.date);
            textStartTime = (TextView)itemView.findViewById(R.id.start_time);
            textEndTime = (TextView)itemView.findViewById(R.id.end_time);

        }
    }

    List<VotingCenterPojo> votingCenterPojos;

    public VotingCenterAdapter(List<VotingCenterPojo> votingCenterPojos){
        this.votingCenterPojos = votingCenterPojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public VotingCenterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_voting_centers, viewGroup, false);
        VotingCenterViewHolder pvh = new VotingCenterViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(VotingCenterViewHolder votingCenterViewHolder, int i) {
        votingCenterViewHolder.textPlacename.setText(votingCenterPojos.get(i).getPlaceName());
        votingCenterViewHolder.textAddress.setText(votingCenterPojos.get(i).getAddress());
        votingCenterViewHolder.textDate.setText(votingCenterPojos.get(i).getDate());
        votingCenterViewHolder.textStartTime.setText(votingCenterPojos.get(i).getStartTime());
        votingCenterViewHolder.textEndTime.setText(votingCenterPojos.get(i).getEndTime());
    }

    @Override
    public int getItemCount() {
        return votingCenterPojos.size();
    }
}