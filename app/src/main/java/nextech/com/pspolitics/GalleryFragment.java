package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import nextech.com.pspolitics.votingAdapter.GalleryAdapter;
import nextech.com.pspolitics.votinglistpojo.GalleryPojo;

public class GalleryFragment extends Fragment {
  public GalleryFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.Gallery);
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        ArrayList<GalleryPojo> listGallery = getGalleryList();
        GridView lv = (GridView) rootView.findViewById(R.id.gridView1);
        lv.setAdapter(new GalleryAdapter(getActivity(), listGallery));
       // lv.setAdapter(new GalleryAdapter(this.getContext()));

        return rootView;
    }

    private ArrayList<GalleryPojo> getGalleryList(){
        ArrayList<GalleryPojo> getGalleryList = new ArrayList<GalleryPojo>();

        GalleryPojo galleryPojo = new GalleryPojo();

        galleryPojo.setImages(R.drawable.nitin);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin7);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin9);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin7);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin9);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin7);
        getGalleryList.add(galleryPojo);

        galleryPojo=new GalleryPojo();
        galleryPojo.setImages(R.drawable.nitin9);
        getGalleryList.add(galleryPojo);

        return getGalleryList;
    }

}

