package nextech.com.pspolitics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

  /*  ViewFlipper viewFlipper;
    Animation Fade_in, Fade_out;*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(R.string.PersonalInfo);
        View v = inflater.inflate(R.layout.fragment_about,
                container, false);

        WebView view = new WebView(this.getContext());
        view.setVerticalScrollBarEnabled(false);

        view.loadData(getString(R.string.about_nitin), "text/html; charset=utf-8", "utf-8");
        return v;
    }
}
