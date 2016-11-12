package nextech.com.pspolitics;

import android.content.Intent;
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
import android.widget.Toast;

import com.nextech.util.Util;

import java.util.Locale;
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private WebView webView;

    SharedPreferences mPrefs1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        String languageToLoad = mPrefs1.getString("languagePref", Locale.getDefault().getLanguage());
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(Util.isInternetAvailable(HomeActivity.this)) //returns true if internet available
        {

            //do something. loadwebview.
        }
        else
        {
            Toast.makeText(HomeActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }
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
        mPrefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPrefs1.edit();
        Intent intent=new Intent(this,HomeActivity.class);
        switch (item.getItemId()) {
            case R.id.get_marathi:
                /*String languageToLoad = "mr"; // your language
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                this.setContentView(R.layout.activity_home);*/

                editor.putString("languagePref", "mr");
                editor.commit(); // Very important to save the preference
                finish();
                startActivity(intent);
                return true;
            case R.id.get_hindi:
                /*languageToLoad = "hi"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                this.setContentView(R.layout.activity_home);*/


                editor.putString("languagePref", "hi");
                editor.commit(); // Very important to save the preference
                finish();
                startActivity(intent);
                return true;

            case R.id.get_english:
                /*languageToLoad = "en"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                this.setContentView(R.layout.activity_home);*/



                editor.putString("languagePref", "en");
                editor.commit(); // Very important to save the preference
                finish();

                startActivity(intent);

                return true;
            default:
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
            PersonalInfoFragment personalInfoFragment= new PersonalInfoFragment();
            fragmentTransaction.replace(R.id.fragment_container,personalInfoFragment).commit();
        } else if (id == R.id.nav_about) {
            AboutFragment aboutFragment= new AboutFragment();
            fragmentTransaction.replace(R.id.fragment_container,aboutFragment).commit();
        } else if (id == R.id.nav_news) {
            NewsFragment newsFragment = new NewsFragment();
            fragmentTransaction.replace(R.id.fragment_container,newsFragment).commit();
        } else if (id == R.id.nav_social_work) {
            SocialWorkFragment socialWorkFragment = new SocialWorkFragment();
            fragmentTransaction.replace(R.id.fragment_container,socialWorkFragment).commit();
        }
        else  if(id==R.id.nav_voting_schedule){
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
        else if(id==R.id.nav_meeting){
            MeetingFragment meetingFragment =new MeetingFragment();
            fragmentTransaction.replace(R.id.fragment_container,meetingFragment).commit();
        }
        else if(id==R.id.nav_gallery){
            GalleryFragment galleryFragment =new GalleryFragment();
            fragmentTransaction.replace(R.id.fragment_container,galleryFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPrefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        String languageToLoad = mPrefs1.getString("languagePref", Locale.getDefault().getLanguage());
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_home);
    }

}

