package nextech.com.pspolitics;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nextech.com.pspolitics.votingAdapter.MeetingAdapter;
import nextech.com.pspolitics.votinglistpojo.MeetingPojo;

public class MeetingFragment extends Fragment {
    private List<MeetingPojo> meetingPojos;
    private RecyclerView rv;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.Meeting);
        View rootView= inflater.inflate(R.layout.fragment_rally, container, false);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
        return  rootView;
    }

    private void initializeData(){
        meetingPojos = new ArrayList<>();
        meetingPojos.add(new MeetingPojo(getContext().getString(R.string.NitinShelke),getContext().getString(R.string.meeting_date),getContext().getString(R.string.meeting_day),getContext().getString(R.string.meeting_start_time),getContext().getString(R.string.meeting_end_time),getContext().getString(R.string.meeting_location)));
        meetingPojos.add(new MeetingPojo(getContext().getString(R.string.NitinShelke),getContext().getString(R.string.meeting_date),getContext().getString(R.string.meeting_day),getContext().getString(R.string.meeting_start_time),getContext().getString(R.string.meeting_end_time),getContext().getString(R.string.meeting_location)));
        meetingPojos.add(new MeetingPojo(getContext().getString(R.string.NitinShelke),getContext().getString(R.string.meeting_date),getContext().getString(R.string.meeting_day),getContext().getString(R.string.meeting_start_time),getContext().getString(R.string.meeting_end_time),getContext().getString(R.string.meeting_location)));
    }

    private void initializeAdapter(){
        MeetingAdapter adapter = new MeetingAdapter(meetingPojos);
        rv.setAdapter(adapter);
    }
}