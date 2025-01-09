package com.mysvac.notes.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mysvac.notes.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DynamicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DynamicFragment extends Fragment {

    public static DynamicFragment newInstance(int position, String desc){
        // 创建fragment并传入参数
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putString("desc",desc);
        args.putInt("position",position);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 布局  父容器  是否直接决定
        // 这里给定了父容器，因此能确定match宽高，但是false，不会添加到容器内
        // 添加操作由viewpager进行
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);

        Bundle bd = getArguments();

        if(bd != null){
            TextView tv = view.findViewById(R.id.tv_dy_frag);
            tv.setText(bd.getString("desc"));
        }

        return view;
    }
}