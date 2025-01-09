package com.mysvac.notes.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.mysvac.notes.R;

import java.util.ArrayList;

public class FragmentActivity extends AppCompatActivity {

    ArrayList<String> mStrList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

         ViewPager viewPager = findViewById(R.id.vp_content);
         for(int i=0;i<10;++i){
             mStrList.add(""+i);
         }

         // 创建适配器
         PageFragAdapater mAdapater = new PageFragAdapater(getSupportFragmentManager(),mStrList);

         // 添加适配器
         viewPager.setAdapter(mAdapater);
    }
}
