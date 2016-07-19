package com.dss.sframework.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dss.sframework.fragment.DemoFragment_;
import com.dss.sframework.fragment.ListFragment_;

public class PagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;
    Context mContext;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Context context) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();
            switch (position) {
                case 0:

                    fragment = new DemoFragment_();

                    return fragment;

                case 1:

                    fragment = new ListFragment_();

                    return fragment;
            }
        return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
