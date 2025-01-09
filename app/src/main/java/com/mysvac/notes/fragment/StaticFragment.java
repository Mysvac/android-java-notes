package com.mysvac.notes.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysvac.notes.R;

import org.jetbrains.annotations.NotNull;


public class StaticFragment extends Fragment {
    private static final String TAG = "fragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("fragment","onCreateView");
        return inflater.inflate(R.layout.fragment_static, container, false);
    }
}