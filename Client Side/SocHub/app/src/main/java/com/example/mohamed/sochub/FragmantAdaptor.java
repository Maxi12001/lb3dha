package com.example.mohamed.sochub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmantAdaptor extends FragmentStatePagerAdapter {
    private List<Fragment>ActivtiyFragmant=new ArrayList<Fragment>();
    public FragmantAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ActivtiyFragmant.get(position);
    }

    @Override
    public int getCount() {
        return ActivtiyFragmant.size();
    }
    public void addFragmant(Fragment fragment){
        ActivtiyFragmant.add(fragment);
    }
}
