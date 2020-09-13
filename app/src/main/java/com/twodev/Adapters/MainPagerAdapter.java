package com.twodev.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.twodev.ui.firstNavFragmnet.FirstNavFragment;
import com.twodev.ui.secondNavFragment.SecondNavFragment;
import com.twodev.ui.thirdNavFragment.ThirdNavFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position){
            case 0:
                fragment = FirstNavFragment.newInstance();
                break;
            case 1:
                fragment = SecondNavFragment.newInstance();
                break;
            case 2:
                fragment = ThirdNavFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
