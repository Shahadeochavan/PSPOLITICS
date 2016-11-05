package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nextech.com.pspolitics.votingAdapter.NewsAdapter;
import nextech.com.pspolitics.votinglistpojo.NewsListPojo;

public  class NewsFragment extends Fragment {
    private List<NewsListPojo> persons;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("News");
     View rootView= inflater.inflate(R.layout.fragment_news, container, false);
        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeData();
        initializeAdapter();

        return  rootView;
    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new NewsListPojo("Shahadeo Chavan", "12/12/2016","11:AM",R.drawable.raje1,R.drawable.nitin, "NITIN SHELKE  PHOTS AT STUDIO",R.drawable.ic_menu_share));
        persons.add(new NewsListPojo("Shubhangi Dhole", "12/12/2016","11:AM", R.drawable.prashant1,R.drawable.cat, "SAMPLE OF PHOTS",R.drawable.ic_menu_share));
        persons.add(new NewsListPojo("Priyanka Patil", "12/12/2016","11:AM", R.drawable.prashant1,R.drawable.nature, "SAMPLE OF PHOTS",R.drawable.ic_menu_share));
    }

    private void initializeAdapter(){
        NewsAdapter adapter = new NewsAdapter(persons,getContext());
        rv.setAdapter(adapter);
    }

}