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

import nextech.com.pspolitics.votingAdapter.VotingScheduleAdapter;
import nextech.com.pspolitics.votinglistpojo.VotingSchedulePojo;


/**
 * A simple {@link Fragment} subclass.
 */
public class VotingScheduleFragment extends Fragment {
    private List<VotingSchedulePojo> votingSchedulePojos;
    private RecyclerView rv;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Voting Schedule");
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
        votingSchedulePojos = new ArrayList<>();
        votingSchedulePojos.add(new VotingSchedulePojo("Sunday"," 01/01/2017","07:00 AM","05:00 PM"));

    }

    private void initializeAdapter(){
        VotingScheduleAdapter adapter = new VotingScheduleAdapter(votingSchedulePojos);
        rv.setAdapter(adapter);
    }
}