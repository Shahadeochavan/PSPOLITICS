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
import nextech.com.pspolitics.votinglistpojo.MeetingPojo;

/**
 * Created by welcome on 11/12/2016.
 */
public class MeetingAdapter  extends  RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>{
    public MeetingAdapter(Context context) {
    }
    private Context context;
    private LayoutInflater inflater;


    public MeetingAdapter(Context context, List<MeetingPojo> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.meetingPojos=data;
    }
    public static class MeetingViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView textMeetingPersonName;
        TextView textMeetingDate;
        TextView textMeetingDay;
        TextView textMeetingStart;
        TextView textMeetinEnd;
        TextView textMeetingLocation;


        MeetingViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            textMeetingPersonName = (TextView)itemView.findViewById(R.id.meeting_person_name);
            textMeetingDate = (TextView)itemView.findViewById(R.id.meeting_date);
            textMeetingDay = (TextView)itemView.findViewById(R.id.meeting_day);
            textMeetingStart = (TextView)itemView.findViewById(R.id.meeting_start);
            textMeetinEnd = (TextView)itemView.findViewById(R.id.meeting_end);
            textMeetingLocation = (TextView)itemView.findViewById(R.id.meeting_location);
        }
    }

    List<MeetingPojo> meetingPojos;

    public MeetingAdapter(List<MeetingPojo> meetingPojos){
        this.meetingPojos = meetingPojos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_meeting, viewGroup, false);
        MeetingViewHolder pvh = new MeetingViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder meetingViewHolder, int i) {
        meetingViewHolder.textMeetingPersonName.setText(meetingPojos.get(i).getMeetingPersonName());
        meetingViewHolder.textMeetingDate.setText(meetingPojos.get(i).getMeetingDate());
        meetingViewHolder.textMeetingDay.setText(meetingPojos.get(i).getMeetingDay());
        meetingViewHolder.textMeetingStart.setText(meetingPojos.get(i).getMeetingStartTime());
        meetingViewHolder.textMeetinEnd.setText(meetingPojos.get(i).getMeetingEndTime());
        meetingViewHolder.textMeetingLocation.setText(meetingPojos.get(i).getMeetingLocation());
    }

    @Override
    public int getItemCount() {
        return meetingPojos.size();
    }
}


