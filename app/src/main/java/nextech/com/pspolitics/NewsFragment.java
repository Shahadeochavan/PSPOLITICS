package nextech.com.pspolitics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
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

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.share:
                        shareImage();
                        break;
                }
            }
        };
        rootView.findViewById(R.id.share).setOnClickListener(handler);


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
        persons.add(new NewsListPojo("Shahadeo Chavan", "12/12/2016","11:AM",R.drawable.prashant1,R.drawable.pelicans_bird_nature_216803, "SAMPLE OF PHOTS",R.drawable.ic_menu_share));
        persons.add(new NewsListPojo("Shubhangi Dhole", "12/12/2016","11:AM", R.drawable.prashant1,R.drawable.winter_beautiful_snow_216404, "SAMPLE OF PHOTS",R.drawable.ic_menu_share));
        persons.add(new NewsListPojo("Priyanka Patil", "12/12/2016","11:AM", R.drawable.prashant1,R.drawable.seagull_bird_nature_275104, "SAMPLE OF PHOTS",R.drawable.ic_menu_share));
    }

    private void initializeAdapter(){
        NewsAdapter adapter = new NewsAdapter(persons);
        rv.setAdapter(adapter);
    }
    private void shareImage() {
        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");

        // Make sure you put example png image named myImage.png in your
        // directory
        String imagePath = Environment.getExternalStorageDirectory()
                + "pelicans_bird_nature_216803.9";

        File imageFileToShare = new File(imagePath);

        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(share, "Share Image!"));
    }

}