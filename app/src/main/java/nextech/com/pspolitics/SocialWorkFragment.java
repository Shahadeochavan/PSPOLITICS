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

import nextech.com.pspolitics.votingAdapter.SocialWorkAdapter;
import nextech.com.pspolitics.votinglistpojo.SocialWorkPojo;


/**
 * A simple {@link Fragment} subclass.
 */
public class SocialWorkFragment extends Fragment {
    private List<SocialWorkPojo> socialWorkPojos;
    private RecyclerView rv;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Social Work");
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
        socialWorkPojos = new ArrayList<>();
        socialWorkPojos.add(new SocialWorkPojo(R.drawable.social_work,"Tree Plantation at Wadegon","01/04/2015"));
        socialWorkPojos.add(new SocialWorkPojo(R.drawable.social_work_tree,"Tree Plantation at Wadegon","01/04/2015"));

    }

    private void initializeAdapter(){
        SocialWorkAdapter adapter = new SocialWorkAdapter(socialWorkPojos);
        rv.setAdapter(adapter);
    }
}