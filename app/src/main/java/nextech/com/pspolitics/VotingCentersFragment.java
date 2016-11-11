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

import nextech.com.pspolitics.votingAdapter.VotingCenterAdapter;
import nextech.com.pspolitics.votinglistpojo.VotingCenterPojo;


public class VotingCentersFragment extends Fragment {
    private List<VotingCenterPojo> votingCenterPojos;
    private RecyclerView rv;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.VotingCenters);
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
        votingCenterPojos = new ArrayList<>();
        votingCenterPojos.add(new VotingCenterPojo(getContext().getString(R.string.high_school),getContext().getString(R.string.wadegon),getContext().getString(R.string.voting_date),getContext().getString(R.string.voting_start_time),getContext().getString(R.string.voting_end_time)));
        votingCenterPojos.add(new VotingCenterPojo(getContext().getString(R.string.zp_school),getContext().getString(R.string.wadegon),getContext().getString(R.string.voting_date),getContext().getString(R.string.voting_start_time),getContext().getString(R.string.voting_end_time)));
        votingCenterPojos.add(new VotingCenterPojo(getContext().getString(R.string.shivaji_vidyalya),getContext().getString(R.string.wadegon),getContext().getString(R.string.voting_date),getContext().getString(R.string.voting_start_time),getContext().getString(R.string.voting_end_time)));

    }
    private void initializeAdapter(){
        VotingCenterAdapter adapter = new VotingCenterAdapter(votingCenterPojos);
        rv.setAdapter(adapter);
    }

}
