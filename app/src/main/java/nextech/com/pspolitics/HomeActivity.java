package nextech.com.pspolitics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
        Util.isConnectingToInternet(getApplicationContext());
        if(!Util.isConnectingToInternet(getApplicationContext())){
           showAlertDialog(HomeActivity.this, "No Internet Connection",
                    "You don't have internet connection.", false);
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
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.ic_action_done : R.drawable.ic_av_not_interested);


        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                    //code for exit here it send to home screen
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() > 1) {
            // If there are back-stack entries, leave the FragmentActivity
            // implementation take care of them.
            manager.popBackStack();

        } else {
            // Otherwise, ask user if he wants to leave :)
            new AlertDialog.Builder(this)
                    .setTitle(R.string.really_exit)
                    .setMessage(R.string.sure_exit)
                    .setNegativeButton(R.string.no_no, null)
                    .setPositiveButton(R.string.yes_yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            // MainActivity.super.onBackPressed();
                            finish();
                            moveTaskToBack(true);
                        }
                    }).create().show();
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
        item.setChecked(true);
        mPrefs1 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPrefs1.edit();
        Intent intent=new Intent(this,HomeActivity.class);
        switch (item.getItemId()) {
            case R.id.get_marathi:
                editor.putString("languagePref", "mr");
                editor.commit(); // Very important to save the preference
                finish();
                startActivity(intent);
                return true;
            case R.id.get_hindi:
                editor.putString("languagePref", "hi");
                editor.commit(); // Very important to save the preference
                finish();
                startActivity(intent);
                return true;

            case R.id.get_english:
                editor.putString("languagePref", "en");
                editor.commit(); // Very important to save the preference
                finish();
                startActivity(intent);

                return true;
            default:

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

