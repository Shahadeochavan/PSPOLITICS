package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import nextech.com.pspolitics.votingAdapter.VotingCenterAdapter;
import nextech.com.pspolitics.votinglistpojo.VotingCenterPojo;


public class VotingCentersFragment extends Fragment {
    public VotingCentersFragment(){}
    EditText inputSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Voting Center");

        View rootView = inflater.inflate(R.layout.fragment_voting_centers, container, false);

        ArrayList<VotingCenterPojo> listContact = getContactList();
        ListView lv = (ListView)rootView.findViewById(R.id.list_item);
        lv.setAdapter(new VotingCenterAdapter(getActivity(), listContact));
        return rootView;
    }

    private ArrayList<VotingCenterPojo> getContactList(){
        ArrayList<VotingCenterPojo> votingCenterList = new ArrayList<VotingCenterPojo>();

        VotingCenterPojo votingCenterPojo = new VotingCenterPojo();

        votingCenterPojo.setPlaceName("Wadegon ,");
        votingCenterPojo.setAddress("High School.");
        votingCenterPojo.setDate("12/12/2016");
        votingCenterPojo.setStartTime("07 Am");
        votingCenterPojo.setEndTime("05 pm");
        votingCenterList.add(votingCenterPojo);

        votingCenterPojo = new VotingCenterPojo();
        votingCenterPojo.setPlaceName("Malkapur ,");
        votingCenterPojo.setAddress("Z. P.School.");
        votingCenterPojo.setDate("12/12/2016");
        votingCenterPojo.setStartTime("07 Am");
        votingCenterPojo.setEndTime("05 pm");
        votingCenterList.add(votingCenterPojo);
        return votingCenterList;


    }

}
