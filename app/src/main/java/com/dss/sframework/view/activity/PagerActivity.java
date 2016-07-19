package com.dss.sframework.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dss.sframework.R;
import com.dss.sframework.view.adapter.PagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_pager)
public class PagerActivity extends ActionBarActivity {

    @ViewById
    TabLayout tab_layout;

    @ViewById
    RadioGroup groupTabs;

    @ViewById
    RadioButton btnFotos;

    @ViewById
    RadioButton btnWeb;

    @ViewById
    Toolbar tool_bar;

    @ViewById
    ViewPager pager;

    PagerAdapter adapter;

    @AfterViews
    void afterViews() {

        setSupportActionBar(tool_bar);

        startPager();
    }

    public void startPager(){

        configureTabs();

        configureAdapter();

        groupTabs.setOnCheckedChangeListener(onCheckedChangeListener);

        pager.addOnPageChangeListener(onPageChangeListener);
    }


    public void configureTabs(){
        tab_layout.addTab(tab_layout.newTab().setText(""));
        tab_layout.addTab(tab_layout.newTab().setText(""));

        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.setVisibility(View.INVISIBLE);

        btnFotos.setBackgroundResource(getIconByIndex(0, true));
        btnWeb.setBackgroundResource(getIconByIndex(1, false));
    }

    public void configureAdapter(){
        adapter = new PagerAdapter (this.getSupportFragmentManager(), tab_layout.getTabCount(), this);

        pager.setAdapter(adapter);
    }

    public RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            checkButtons(checkedId);
        }
    };

    public void checkButtons(int checkedId){
        switch (checkedId) {
            case R.id.btnFotos:
                pager.setCurrentItem(0);
                btnFotos.setBackgroundResource(getIconByIndex(0, true));
                btnWeb.setBackgroundResource(getIconByIndex(1, false));
                break;
            case R.id.btnWeb:
                pager.setCurrentItem(1);
                btnFotos.setBackgroundResource(getIconByIndex(0, false));
                btnWeb.setBackgroundResource(getIconByIndex(1, true));
                break;
        }
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

    public ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            makeScrooAction(position);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void makeScrooAction(int position){
        switch (position) {
            case 0:
                groupTabs.check(R.id.btnFotos);
                break;
            case 1:
                groupTabs.check(R.id.btnWeb);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_reload:;
                break;

            case R.id.action_close:;
                Intent i = new Intent(this,MainActivity_.class);

                startActivity(i);
                PagerActivity.this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}