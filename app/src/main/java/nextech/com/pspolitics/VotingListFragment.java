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

        View rootView = inflater.inflate(R.layout.fragment_voting_list, container, false);

        ArrayList<VotingListPojo> listContact = getContactList();
        ListView lv = (ListView)rootView.findViewById(R.id.list_item);
        lv.setAdapter(new VotingListAdapter(getActivity(), listContact));
        return rootView;
    }

    private ArrayList<VotingListPojo> getContactList(){
        ArrayList<VotingListPojo> contactlist = new ArrayList<VotingListPojo>();

        VotingListPojo contact = new VotingListPojo();

        contact.setName("Shahadeo");
        contact.setNumber("9403633306");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setName("Ankush");
        contact.setNumber("01213869102");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setName("Abhishek");
        contact.setNumber("01213123985");
        contactlist.add(contact);
        contact = new VotingListPojo();

        contact.setName("Mahesh");
        contact.setNumber("21323444444");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setName("Lahu");
        contact.setNumber("122254565656");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setName("Anil");
        contact.setNumber("33332143434");
        contactlist.add(contact);

        contact = new VotingListPojo();
        contact.setName("Sunli");
        contact.setNumber("5656463664");
        contactlist.add(contact);

        return contactlist;
    }

}
