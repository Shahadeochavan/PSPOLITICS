package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import nextech.com.pspolitics.votingAdapter.RallyAdapter;
import nextech.com.pspolitics.votinglistpojo.RallyPojo;

public class Rally extends Fragment {
    public Rally(){}
    EditText inputSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Rally");
        View rootView = inflater.inflate(R.layout.fragment_rally, container, false);

        ArrayList<RallyPojo> listContact = getContactList();
        ListView lv = (ListView)rootView.findViewById(R.id.list_item);
        lv.setAdapter(new RallyAdapter(getActivity(), listContact));
        return rootView;
    }

    private ArrayList<RallyPojo> getContactList(){
        ArrayList<RallyPojo> contactlist = new ArrayList<RallyPojo>();

        RallyPojo rallyPojo = new RallyPojo();
        rallyPojo.setStartPlaceName("Wadegon Chowk");
        rallyPojo.setEndPlaceName("Shirur Phata");
        rallyPojo.setDate("12/12/2016");
        rallyPojo.setStartTime("11:00 AM");
        rallyPojo.setEndTime("02:00 PM");
        contactlist.add(rallyPojo);
        return contactlist;
    }

}