package com.emedinaa.meetupapp;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.emedinaa.meetupapp.data.mapper.MemberMapper;
import com.emedinaa.meetupapp.data.rest.MembersRestInteractor;
import com.emedinaa.meetupapp.data.rest.EventsRestInteractor;
import com.emedinaa.meetupapp.domain.callback.StorageCallback;
import com.emedinaa.meetupapp.domain.interactors.EventsInteractor;
import com.emedinaa.meetupapp.domain.interactors.MembersInteractor;
import com.emedinaa.meetupapp.presentation.fragments.EventsFragment;
import com.emedinaa.meetupapp.presentation.fragments.MembersFragment;
import com.emedinaa.meetupapp.presentation.fragments.OnFragmentListener;

public class MainActivity extends AppCompatActivity implements OnFragmentListener {

    private static final String TAG = "MainActivity";
    private final String GROUP_NAME="Android-Dev-Peru";

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

        //testService();
        //testMembers(GROUP_NAME);
        //setMemberFragment(savedInstanceState);
        setupDrawerContent(nvDrawer);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = EventsFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = EventsFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = MembersFragment.class;
                break;
            default:
                fragmentClass = EventsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flayContainer, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setMemberFragment(Bundle savedInstanceState) {

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.flayContainer) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            MembersFragment membersFragment = new MembersFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            membersFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flayContainer, membersFragment).commit();
        }

    }

    private void testMembers(String group_name) {
        MemberMapper memberMapper= new MemberMapper();
        MembersInteractor membersInteractor= new MembersRestInteractor(memberMapper);
        membersInteractor.membersByGroup(group_name,restCallback);
    }

    private void testService() {
        EventsInteractor eventsInteractor= new EventsRestInteractor();
        eventsInteractor.pastEvents("Android-Dev-Peru",restCallback);
    }

    private StorageCallback restCallback= new StorageCallback() {
        @Override
        public void onSuccess(Object object) {
            Log.v(TAG, "onSuccess "+object);
        }

        @Override
        public void onFailure(Exception e) {
            Log.v(TAG, "onFailure "+e.getMessage());
        }
    };

    /**
     * References
     * https://developer.android.com/training/basics/fragments/fragment-ui.html
     */
}
