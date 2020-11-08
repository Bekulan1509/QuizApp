package com.twodev.ui.thirdNavFragment;

import com.twodev.core.BaseFragment;
import com.twodev.quizapp.R;

public class ThirdNavFragment extends BaseFragment {

    private ThirdNavViewModel mViewModel;

    public static ThirdNavFragment newInstance() {
        return new ThirdNavFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.third_nav_fragment;
    }

}