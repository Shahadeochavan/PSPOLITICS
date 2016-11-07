package nextech.com.pspolitics;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragment()).commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout   mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       FragmentManager fragmentManager= getSupportFragmentManager();
     FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        if (id == R.id.nav_home) {
            HomeFragment homeFragment= new HomeFragment();
            fragmentTransaction.replace(R.id.fragment_container,homeFragment).commit();
        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment= new AboutFragment();
            fragmentTransaction.replace(R.id.fragment_container,aboutFragment).commit();
        } else if (id == R.id.nav_news) {
            NewsFragment newsFragment = new NewsFragment();
            fragmentTransaction.replace(R.id.fragment_container,newsFragment).commit();
        } else if (id == R.id.nav_social_work) {
            SocialWorkFragment socialWorkFragment = new SocialWorkFragment();
            fragmentTransaction.replace(R.id.fragment_container,socialWorkFragment).commit();
        } else if (id == R.id.nav_share) {
            HomeFragment homeFragment= new HomeFragment();
            fragmentTransaction.replace(R.id.fragment_container,homeFragment).commit();
        } else if (id == R.id.nav_send) {
            HomeFragment homeFragment= new HomeFragment();
            fragmentTransaction.replace(R.id.fragment_container,homeFragment).commit();
        }else  if(id==R.id.nav_voting_schedule){
            VotingScheduleFragment votingScheduleFragment= new VotingScheduleFragment();
            fragmentTransaction.replace(R.id.fragment_container,votingScheduleFragment).commit();
        }else if(id== R.id.nav_voting_centers){
            VotingCentersFragment votingCentersFragment= new VotingCentersFragment();
            fragmentTransaction.replace(R.id.fragment_container,votingCentersFragment).commit();
        }else if(id==R.id.nav_voting_list){
            VotingListFragment votingListFragment= new VotingListFragment();
            fragmentTransaction.replace(R.id.fragment_container,votingListFragment).commit();
        }else if(id==R.id.nav_rally){
            RallyFragment rally =new RallyFragment();
            fragmentTransaction.replace(R.id.fragment_container,rally).commit();
        }
        else if(id==R.id.nav_party){
            PartyFragment partyFragment =new PartyFragment();
            fragmentTransaction.replace(R.id.fragment_container,partyFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

