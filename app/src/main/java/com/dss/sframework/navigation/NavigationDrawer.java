package com.dss.sframework.navigation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.dss.sframework.R;
import com.dss.sframework.adapter.NavigationDrawerAdapter;
import com.dss.sframework.fragment.DemoFragment;
import com.dss.sframework.fragment.ListFragment;

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

        final Context c = context;

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());



                if(child!=null && mGestureDetector.onTouchEvent(motionEvent)){
                    Drawer.closeDrawers();

                    switch(String.valueOf(recyclerView.getChildPosition(child))) {

                        case "1":
                            FragmentManager fragmentManager = ((Activity) c).getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            DemoFragment demoFragment = new DemoFragment();
                            fragmentTransaction.replace(R.id.fragment_container, demoFragment, "demoFragment");
                            fragmentTransaction.commit();
                            break;
                        case "2":
                            FragmentManager fragmentManager2 = ((Activity) c).getFragmentManager();
                            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                            ListFragment listFragment = new ListFragment();
                            fragmentTransaction2.replace(R.id.fragment_container, listFragment, "listFragment");
                            fragmentTransaction2.commit();
                            break;
                        case "3":
                            Toast.makeText(context,"3",Toast.LENGTH_SHORT).show();
                            break;
                        case "4":
                            Toast.makeText(context,"4",Toast.LENGTH_SHORT).show();
                            break;
                        case "5":
                            Toast.makeText(context,"5",Toast.LENGTH_SHORT).show();
                            break;

                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

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
        Drawer.openDrawer(Gravity.LEFT);
    }
    final GestureDetector mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

        @Override public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

    });



}
