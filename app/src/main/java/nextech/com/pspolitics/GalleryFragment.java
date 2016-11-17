package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import nextech.com.pspolitics.votingAdapter.GalleryAdapter;

public class GalleryFragment extends Fragment {
    public GalleryFragment(){}
    EditText inputSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.Gallery);
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        GridView lv = (GridView)rootView.findViewById(R.id.gridview);
        lv.setAdapter(new GalleryAdapter(this.getContext()));
        return rootView;
    }
}
