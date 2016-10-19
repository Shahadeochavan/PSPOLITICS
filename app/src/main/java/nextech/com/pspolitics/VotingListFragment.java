package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import nextech.com.pspolitics.votingAdapter.VotingListAdapter;
import nextech.com.pspolitics.votinglistpojo.VotingListPojo;


public class VotingListFragment extends Fragment {

    public VotingListFragment(){}
    EditText inputSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Voting List");
        View rootView = inflater.inflate(R.layout.fragment_voting_list, container, false);

        ArrayList<VotingListPojo> listContact = getContactList();
        ListView lv = (ListView)rootView.findViewById(R.id.list_item);
        lv.setAdapter(new VotingListAdapter(getActivity(), listContact));
        return rootView;
    }

    private ArrayList<VotingListPojo> getContactList(){
        ArrayList<VotingListPojo> contactlist = new ArrayList<VotingListPojo>();

        VotingListPojo contact = new VotingListPojo();

        contact.setLastName("Chavan");
        contact.setFirstName("Shahadeo");
        contact.setMiddleName("Gowardhan");
        contact.setPhoneNumber("9403633306");
        contact.setWardDetails("ward number 1");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setLastName("Chavan");
        contact.setFirstName("Anil");
        contact.setMiddleName("Ramkisan");
        contact.setPhoneNumber("8275266891");
        contact.setWardDetails("ward number 2");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setLastName("Chavan");
        contact.setFirstName("Ankush");
        contact.setMiddleName("Janardhan");
        contact.setPhoneNumber("9960454648");
        contact.setWardDetails("ward number 3");
        contactlist.add(contact);


        contact = new VotingListPojo();
        contact.setLastName("Chavan");
        contact.setFirstName("Sunil");
        contact.setMiddleName("Devidas");
        contact.setPhoneNumber("9421342030");
        contact.setWardDetails("ward number 4");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setLastName("Chavan");
        contact.setFirstName("Mahadeo");
        contact.setMiddleName("Gowardhan");
        contact.setPhoneNumber("9403633296");
        contact.setWardDetails("ward number 5");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setLastName("Chavan");
        contact.setFirstName("Vilas");
        contact.setMiddleName("Prabhakar");
        contact.setPhoneNumber("9630384656");
        contact.setWardDetails("ward number 6");
        contactlist.add(contact);

        return contactlist;
    }

}
