package nextech.com.pspolitics.votingAdapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nextech.com.pspolitics.R;
import nextech.com.pspolitics.votinglistpojo.VotingSchedulePojo;

/**
 * Created by welcome on 10/27/2016.
 */
public class VotingScheduleAdapter extends  RecyclerView.Adapter<VotingScheduleAdapter.VotingScheduleViewHolder>{
    public static class VotingScheduleViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView textvotingDay;
        TextView textvotingDate;
        TextView textvotingStartTime;
        TextView textvotingEndtime;

        VotingScheduleViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            textvotingDay = (TextView)itemView.findViewById(R.id.voting_day);
            textvotingDate = (TextView)itemView.findViewById(R.id.voting_date);
            textvotingStartTime = (TextView)itemView.findViewById(R.id.voting_start);
            textvotingEndtime = (TextView)itemView.findViewById(R.id.voting_end);

        }
    }

    List<VotingSchedulePojo> votingSchedulePojos;

    public VotingScheduleAdapter(List<VotingSchedulePojo> votingSchedulePojos){
        this.votingSchedulePojos = votingSchedulePojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public VotingScheduleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_voting_schedule, viewGroup, false);
        VotingScheduleViewHolder pvh = new VotingScheduleViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(VotingScheduleViewHolder votingScheduleViewHolder, int i) {
        votingScheduleViewHolder.textvotingDay.setText(votingSchedulePojos.get(i).votingDay);
        votingScheduleViewHolder.textvotingDate.setText(votingSchedulePojos.get(i).votingDate);
        votingScheduleViewHolder.textvotingStartTime.setText(votingSchedulePojos.get(i).votingStartTime);
        votingScheduleViewHolder.textvotingEndtime.setText(votingSchedulePojos.get(i).votingEndTime);
    }

    @Override
    public int getItemCount() {
        return votingSchedulePojos.size();
    }
}
