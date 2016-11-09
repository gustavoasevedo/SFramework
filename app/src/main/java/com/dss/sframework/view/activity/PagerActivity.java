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

import com.dss.sframework.R;
import com.dss.sframework.view.adapter.PagerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.PageScrolled;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_pager)
public class PagerActivity extends ActionBarActivity {

    @ViewById
    TabLayout tab_layout;

    @ViewById
    RadioButton btnCheck1;

    @ViewById
    RadioButton btnCheck2;

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
    }


    public void configureTabs(){
        tab_layout.addTab(tab_layout.newTab().setText(""));
        tab_layout.addTab(tab_layout.newTab().setText(""));

        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_layout.setVisibility(View.INVISIBLE);

        btnCheck1.setBackgroundResource(getIconByIndex(0, true));
        btnCheck2.setBackgroundResource(getIconByIndex(1, false));
    }

    public void configureAdapter(){
        adapter = new PagerAdapter (this.getSupportFragmentManager(), tab_layout.getTabCount(), this);

        pager.setAdapter(adapter);
    }

    @Click({R.id.btnCheck1, R.id.btnCheck2})
    void clickRadio(View view){
        checkButtons(view.getId());
    }

    @PageScrolled(R.id.pager)
    void onPageChage(int position){
        makeScroolAction(position);
    }


    public void checkButtons(int checkedId){
        switch (checkedId) {
            case R.id.btnCheck1:
                pager.setCurrentItem(0);
                btnCheck1.setBackgroundResource(getIconByIndex(0, true));
                btnCheck2.setBackgroundResource(getIconByIndex(1, false));

                break;
            case R.id.btnCheck2:
                pager.setCurrentItem(1);
                btnCheck1.setBackgroundResource(getIconByIndex(0, false));
                btnCheck2.setBackgroundResource(getIconByIndex(1, true));
                break;
        }

    }

    public int getIconByIndex(int index, boolean disabled) {

        int rc = 0;

        switch (index) {

            case 1:
                rc = (disabled ? R.color.lightblue : R.drawable.rounded_quest);

                break;

            case 0:

                rc = (disabled ? R.color.lightblue : R.drawable.rounded_quest);

                break;
        }

        return rc;
    }

    public void makeScroolAction(int position){
        switch (position) {
            case 0:
                btnCheck1.setBackgroundResource(getIconByIndex(0, true));
                btnCheck2.setBackgroundResource(getIconByIndex(1, false));
                break;
            case 1:
                btnCheck1.setBackgroundResource(getIconByIndex(0, false));
                btnCheck2.setBackgroundResource(getIconByIndex(1, true));
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