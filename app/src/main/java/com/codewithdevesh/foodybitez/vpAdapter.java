package com.codewithdevesh.foodybitez;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class vpAdapter extends FragmentPagerAdapter {


    public vpAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HomeFragment h = new HomeFragment();
                return h;
            case 1:
                SearchFragment h2 = new SearchFragment();
                return h2;
            case 2:
                CartFragment h3 = new CartFragment();
                return h3;
            case 3:
                AccountFragment h4 = new AccountFragment();
                return h4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
