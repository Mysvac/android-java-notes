package com.mysvac.notes;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.collections.ArrayDeque;


/**
 * 两种基础适配器
 * 测试
 * */
public class AdapterActivity extends AppCompatActivity {
    private final static String[] myContent = {"111","222","333"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        // 数组适配器
        ArrayAdapter<String> myAdapter =
                new ArrayAdapter<String>(this,R.layout.item_text,myContent);

        Spinner tSpinner = findViewById(R.id.test_spinner);
        tSpinner.setAdapter(myAdapter);



        // Simple适配器
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();

        for(int i=0;i<3;++i){
            Map<String,String> item = new HashMap<>();
            item.put("content",myContent[i]);
            list.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,
                R.layout.item_line,new String[]{"content"}, new int[] {R.id.tv});

        Spinner spinner2 = findViewById(R.id.test_spinner2);
        spinner2.setAdapter(simpleAdapter);

    }


}