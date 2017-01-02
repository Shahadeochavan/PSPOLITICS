package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AskQuestinsFragment extends Fragment {
    public AskQuestinsFragment() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle(R.string.ask_questions);
        View v = inflater.inflate(R.layout.fragment_ask_questins,
                container, false);
        return v;
    }
}
