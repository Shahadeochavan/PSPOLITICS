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

import nextech.com.pspolitics.votingAdapter.PartyAdapter;
import nextech.com.pspolitics.votinglistpojo.PartyPojo;
public class PartyFragment extends Fragment {
    private List<PartyPojo> partyPojos;
    private RecyclerView rv;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Party");
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
        partyPojos = new ArrayList<>();
        partyPojos.add(new PartyPojo("Nitin Shelke",R.drawable.prashant1,"Party Name:BJP",R.drawable.is));
    }

    private void initializeAdapter(){
        PartyAdapter adapter = new PartyAdapter(partyPojos);
        rv.setAdapter(adapter);
    }
}