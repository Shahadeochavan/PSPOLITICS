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

        getActivity().setTitle("Personal Phots");
        View v = inflater.inflate(R.layout.fragment_about,
                container, false);

        WebView view = new WebView(this.getContext());
        view.setVerticalScrollBarEnabled(false);

        view.loadData(getString(R.string.about_nitin), "text/html; charset=utf-8", "utf-8");
  /*      viewFlipper = (ViewFlipper) v.findViewById(R.id.bckgrndViewFlipper1);
        viewFlipper.setInAnimation(Fade_in);
        viewFlipper.setOutAnimation(Fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.startFlipping();
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                return false;
            }
        });
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();

            }
        });
        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
            }
        });*/
        return v;
    }
}
