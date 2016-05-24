package com.dss.sframework.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dss.sframework.R;
import com.dss.sframework.adapter.PagerAdapter;

/**
 * Created by digipronto on 24/05/16.
 */
public class PagerActivityHelper {
    private TabLayout tabLayout;

    private RadioGroup group;
    private RadioButton fotos;
    private RadioButton web;
    private Toolbar toolbar;



    public void InitBar(Context context){
        toolbar = (Toolbar) ((Activity)context).findViewById(R.id.tool_bar); // Attaching the layout to the menu_toolbar object
        ((ActionBarActivity)context).setSupportActionBar(toolbar); // Setting menu_toolbar as the ActionBar with setSupportActionBar() call

    }

    public void PagerActivity(Context context){
        tabLayout = (TabLayout) ((Activity)context).findViewById(R.id.tab_layout);

        group = (RadioGroup) ((Activity)context).findViewById(R.id.groupTabs);
        fotos = (RadioButton)((Activity)context).findViewById(R.id.btnFotos);
        web = (RadioButton) ((Activity)context).findViewById(R.id.btnWeb);
    }

    public void startPager(Context context){
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setVisibility(View.INVISIBLE);

        final ViewPager viewPager = (ViewPager) ((Activity)context).findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(((FragmentActivity)context).getSupportFragmentManager(), tabLayout.getTabCount(), context);

        viewPager.setAdapter(adapter);

        fotos.setBackgroundResource(getIconByIndex(0, true));
        web.setBackgroundResource(getIconByIndex(1, false));



        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btnFotos:
                        viewPager.setCurrentItem(0);
                        fotos.setBackgroundResource(getIconByIndex(0, true));
                        web.setBackgroundResource(getIconByIndex(1, false));
                        break;
                    case R.id.btnWeb:
                        viewPager.setCurrentItem(1);
                        fotos.setBackgroundResource(getIconByIndex(0, false));
                        web.setBackgroundResource(getIconByIndex(1, true));
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        group.check(R.id.btnFotos);
                        break;
                    case 1:
                        group.check(R.id.btnWeb);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }



    public int getIconByIndex(int index, boolean disabled) {

        int rc = 0;

        switch (index) {

            case 1:
                rc = (disabled ? R.drawable.ic_search : R.drawable.ic_search);

                break;

            case 0:

                rc = (disabled ? R.drawable.ic_file : R.drawable.ic_file);

                break;
        }

        return rc;
    }
}


