package nextech.com.pspolitics;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import nextech.com.pspolitics.votingAdapter.GalleryAdapter;

public class GalleryFragment extends Fragment {
    public GalleryFragment() {
        // Required empty public constructor
    }
    GridView gv;
    Context context;
    ArrayList prgmName;
    public static String [] nitinNameList={"nitin","nitin","nitin","nitin"," nitin","nitin","nitin","nitin","nitin"};
    public static int [] nitinImages={R.drawable.nitin1,R.drawable.nitin4,R.drawable.nitin3,R.drawable.nitin4,R.drawable.nitin3,R.drawable.nitin7,R.drawable.nitin7,R.drawable.nitin1,R.drawable.nitin9};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.Gallery);
        View v =inflater.inflate(R.layout.fragment_gallery, container, false);
        gv=(GridView) v.findViewById(R.id.gridView1);
        gv.setAdapter(new GalleryAdapter(this,nitinNameList,nitinImages));
        return v;
    }
}
