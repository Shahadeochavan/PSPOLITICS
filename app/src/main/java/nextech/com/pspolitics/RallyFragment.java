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

import nextech.com.pspolitics.votingAdapter.RallyAdapter;
import nextech.com.pspolitics.votinglistpojo.RallyPojo;

public class RallyFragment extends Fragment {
    private List<RallyPojo> rallyPojos;
    private RecyclerView rv;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.Rally);
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
        rallyPojos = new ArrayList<>();
        rallyPojos.add(new RallyPojo(getContext().getString(R.string.wadegonchowk), getContext().getString(R.string.shirurphata),getContext().getString(R.string.date_of_rally), getContext().getString(R.string.rally_start1),getContext().getString(R.string.rally_end1),getContext().getString(R.string.rally_day1)));
        rallyPojos.add(new RallyPojo(getContext().getString(R.string.baner), getContext().getString(R.string.pune_station),getContext().getString(R.string.date_of_rally1), getContext().getString(R.string.rally_start2),getContext().getString(R.string.rally_end2),getContext().getString(R.string.rally_day2)));
        rallyPojos.add(new RallyPojo(getContext().getString(R.string.karave_nagar), getContext().getString(R.string.hadpasr),getContext().getString(R.string.date_of_rally3), getContext().getString(R.string.rally_start3),getContext().getString(R.string.rally_end3),getContext().getString(R.string.rally_day3)));

    }

    private void initializeAdapter(){
        RallyAdapter adapter = new RallyAdapter(rallyPojos);
        rv.setAdapter(adapter);
    }
}