package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nextech.com.pspolitics.votingAdapter.NewsAdapter;
import nextech.com.pspolitics.votinglistpojo.NewsListPojo;

public  class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    private NewsAdapter adapter;
    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }



    List<NewsListPojo> data = fill_with_data();
    recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    adapter = new Recycler_View_Adapter(data, getApplication());
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        Modify the DefaultItemAnimator
//        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//        itemAnimator.setAddDuration(1000);
//        itemAnimator.setRemoveDuration(1000);
//        recyclerView.setItemAnimator(itemAnimator);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Create a list of Data objects
    public List<NewsListPojo> fill_with_data() {

        List<NewsListPojo> data = new ArrayList<>();

        data.add(new NewsListPojo("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_action_home));
        data.add(new NewsListPojo("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_action_home));
        data.add(new NewsListPojo("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_action_home));
        data.add(new NewsListPojo("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_action_home));
        data.add(new NewsListPojo("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_action_home));
        data.add(new NewsListPojo("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", R.drawable.ic_action_home));

        data.add(new NewsListPojo("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_action_home));
        data.add(new NewsListPojo("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_action_home));
        data.add(new NewsListPojo("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_action_home));
        data.add(new NewsListPojo("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_action_home));
        data.add(new NewsListPojo("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_action_home));
        data.add(new NewsListPojo("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", R.drawable.ic_action_home));

        return data;
    }


}

