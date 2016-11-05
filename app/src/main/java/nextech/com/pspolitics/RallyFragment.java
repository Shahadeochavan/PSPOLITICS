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
        getActivity().setTitle("Rally");
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
        rallyPojos.add(new RallyPojo("Wadegon Chowk", "Shirur Phata","12/12/2016", "11:00 AM","03:00 PM","Sunday"));
        rallyPojos.add(new RallyPojo("Baner", "Pune Statiobn","24/12/2016", "10:00 AM","02:00 PM","Monday"));
        rallyPojos.add(new RallyPojo("Karve Nagar", "Hadpasr","01/01/2017", "12:00 PM","04:00 PM","Saturday"));

    }

    private void initializeAdapter(){
        RallyAdapter adapter = new RallyAdapter(rallyPojos);
        rv.setAdapter(adapter);
    }
}