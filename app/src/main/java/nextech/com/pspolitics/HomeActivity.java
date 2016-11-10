package nextech.com.pspolitics;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.util.Locale;
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 /*       ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            if (!info.isConnected()) {
                Toast.makeText(this, "Please check your wireless connection and try again.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Please check your wireless connection and try again.", Toast.LENGTH_SHORT).show();
        }*/
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString("LANG", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragment()).commit();
        }

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
        switch (item.getItemId()) {
            case R.id.get_marathi:
                String languageToLoad = "mr"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                this.setContentView(R.layout.activity_home);
                break;
            case R.id.get_hindi:
                languageToLoad = "hi"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                this.setContentView(R.layout.activity_home);

                break;
            case R.id.get_english:
                languageToLoad = "en"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                this.setContentView(R.layout.activity_home);
                break;
            default:
                break;
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.

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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Locale locale = null;
        if (locale != null)
        {
            newConfig.locale = locale;
            Locale.setDefault(locale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }

}

