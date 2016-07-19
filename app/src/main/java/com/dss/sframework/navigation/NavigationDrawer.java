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
import com.dss.sframework.activity.PagerActivity_;
import com.dss.sframework.adapter.NavigationDrawerAdapter;
import com.dss.sframework.fragment.FragmentStarter;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

/**
 * Created by gustavo.vieira on 22/05/2015.
 */
public class NavigationDrawer {
    String TITLES[];
    int ICONS[];
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

    public void mountDrawer(Toolbar toolbar){
        mRecyclerView = (RecyclerView) ((Activity)context).findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new NavigationDrawerAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE,context);       // Creating the Adapter of NavigationDrawerAdapter class(which we are going to see in a bit)
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(context);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) ((Activity)context).findViewById(R.id.DrawerLayout);
    }


    public void initDrawer(Context context, Toolbar toolbar){

        this.context = context;

        mountDrawer(toolbar);

        mRecyclerView.addOnItemTouchListener(onItemTouchListener);

        initDrawerToggle(toolbar);

        showInScreen();

    }


    final GestureDetector mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

        @Override public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

    });


    public RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(),e.getY());

            if(child!=null && mGestureDetector.onTouchEvent(e)){
                Drawer.closeDrawers();

                getClick(child,rv);

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
    };


    public void getClick(View child,RecyclerView rv) {

        switch (String.valueOf(rv.getChildAdapterPosition(child))) {

            case "1":
                FragmentStarter.startDemoFragment(context);

                break;

            case "2":
                FragmentStarter.startListFragment(context);

                break;

            case "3":
                Intent intent = new Intent(context, PagerActivity_.class);
                ((Activity) context).startActivity(intent);
                break;

            case "4":

                FragmentStarter.startMapsFragment(context);

                break;
            case "5":

//               caminhoThumb = "http://img.youtube.com/vi/" + video + "/0.jpg";
                context.startActivity(YouTubeStandalonePlayer.createVideoIntent(((Activity) context),
                        "AIzaSyBdp77tuS3_7MZSoZLUX-YmCw250udgb68", "p5DVeDWtA5U"));
                break;

            case "6":

                FragmentStarter.startVideoFragment(context);

                break;

        }
    }

    public void initDrawerToggle(Toolbar toolbar) {

        mDrawerToggle = new ActionBarDrawerToggle(((Activity) context), Drawer, toolbar, R.string.hello_world, R.string.hello_world) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    public void showInScreen(){
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();
        //        Drawer.openDrawer(Gravity.LEFT);
    }
}
