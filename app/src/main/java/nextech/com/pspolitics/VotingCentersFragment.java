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
        getActivity().setTitle("Voting Center");
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
        votingCenterPojos.add(new VotingCenterPojo("High Scholl","Wadwgon","01/01/2017","07:AM","08:AM"));
        votingCenterPojos.add(new VotingCenterPojo("Z.P Scholl","Pune","01/01/2017","07:AM","08:AM"));
        votingCenterPojos.add(new VotingCenterPojo("Shivaji Vidyalya","Malkapur","01/01/2017","07:AM","08:AM"));

    }
    private void initializeAdapter(){
        VotingCenterAdapter adapter = new VotingCenterAdapter(votingCenterPojos);
        rv.setAdapter(adapter);
    }

}
