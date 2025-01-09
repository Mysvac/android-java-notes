package com.mysvac.notes.fragment;

import android.icu.util.BuddhistCalendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class PageFragAdapater extends FragmentPagerAdapter {

    private List<String> mStrList;

    public PageFragAdapater(@NonNull FragmentManager fm, List<String> mStrList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mStrList = mStrList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // 获取新界面(返回一个fragment)
        return DynamicFragment.newInstance(position,mStrList.get(position));
    }

    @Override
    public int getCount() {
        // 获取页面总长度
        return mStrList.size();
    }
}
