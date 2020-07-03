package com.application.boxmadikv1.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DynamicPagerAdapter extends FragmentPagerAdapter {

    public static final String TAG = DynamicPagerAdapter.class.getSimpleName();

    private ArrayList<Fragment> registeredFragments;
    private List<Integer> hashList;

    public DynamicPagerAdapter(FragmentManager fm) {
        super(fm);
        this.registeredFragments = new ArrayList<>();
        hashList = new ArrayList<>();
        //Log.d(TAG, "DynamicPagerAdapter : ");
    }

    public int getItemPosition(Object object) {
        // Log.d(TAG, "getItemPosition : ");
        int index = getIndexByTag(object.hashCode());
        Log.d(TAG, "index : " + index);
        if (index >= 0) {
            Log.d(TAG, "POSITION_NONE : ");
            hashList.remove(index);
            return POSITION_NONE;
        } else {
            Log.d(TAG, "POSITION_UNCHANGED : " + index);
            return POSITION_UNCHANGED;
        }
    }

    public long getItemId(int position) {
        Log.d(TAG, "getItemId : ");
        return registeredFragments.get(position).hashCode();
    }

    @Override
    public Fragment getItem(int position) {
        // Log.d(TAG, "OverridegetItemId : ");
        return registeredFragments.get(position);
    }

    @Override
    public int getCount() {
        // Log.d(TAG, "registeredFragments : ");
        return registeredFragments.size();
    }

    public void add(Fragment fragment) {
        registeredFragments.add(fragment);
        hashList.add(fragment.hashCode());
        notifyDataSetChanged();
    }

    public void remove(int position) {
        registeredFragments.remove(position);
        notifyDataSetChanged();
    }

    private int getIndexByTag(int hashCode) {
        Log.d(TAG, "getIndexByTag : ");
        for (int i = 0; i < hashList.size(); i++) {
            Log.d(TAG, "hashList : " + hashList.size());
            if (hashList.get(i) == hashCode) {
                Log.d(TAG, "if : " + hashList.get(i));
                return i;
            }
        }
        return -1;
    }
}
