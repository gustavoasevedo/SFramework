package com.dss.sframework.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.dss.sframework.R;
import com.dss.sframework.activity.PagerActivity;
import com.dss.sframework.adapter.NavigationDrawerAdapter;
import com.dss.sframework.fragment.FragmentStarter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

/**
 * Created by gustavo.vieira on 22/05/2015.
 */
public class NavigationDrawer {
    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[];
    int ICONS[];
    //Similarly we Create a String Resource for the name and email in the menu_header view
    //And we also create a int resource for profile picture in the menu_header view
    String NAME;
    String EMAIL;
    int PROFILE;
    Context context;

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    public NavigationDrawer(String[] TITLES, int[] ICONS, String NAME, String EMAIL, int PROFILE,Context context) {
        this.TITLES = TITLES;
        this.ICONS = ICONS;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.PROFILE = PROFILE;
        this.context = context;
    }

    public void initDrawer(final Context context, Toolbar toolbar){
        mRecyclerView = (RecyclerView) ((Activity)context).findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new NavigationDrawerAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE,context);       // Creating the Adapter of NavigationDrawerAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,menu_header view name, menu_header view email,
        // and menu_header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(context);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager

        final Context fContext = context;

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());



                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();

                    switch(String.valueOf(recyclerView.getChildAdapterPosition(child))) {

                        case "1":
                            FragmentStarter.startDemoFragment(fContext);

                            break;

                        case "2":
                            FragmentStarter.startListFragment(fContext);

                            break;

                        case "3":
                            Intent intent = new Intent(fContext, PagerActivity.class);
                            ((Activity)fContext).startActivity(intent);
                            break;

                        case "4":

                            FragmentStarter.startMapsFragment(fContext);

                            break;
                        case "5":

//                            caminhoThumb = "http://img.youtube.com/vi/" + video + "/0.jpg";
                            fContext.startActivity(YouTubeStandalonePlayer.createVideoIntent(((Activity)fContext),
                                    "AIzaSyBdp77tuS3_7MZSoZLUX-YmCw250udgb68", "p5DVeDWtA5U"));
                            break;

                        case "6":

                            FragmentStarter.startVideoFragment(fContext);

                            break;

                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

                Drawer = (DrawerLayout) ((Activity)context).findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(((Activity)context),Drawer,toolbar,R.string.hello_world,R.string.hello_world){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


            }

            ; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
//        Drawer.openDrawer(Gravity.LEFT);
    }
    final GestureDetector mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

        @Override public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

    });



}
