package com.mysvac.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mysvac.notes.Storage.StorageActivity;
import com.mysvac.notes.fragment.FragmentActivity;


/**
 * 主界面，无特殊功能
 * 转发页面用
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.view_test_btn).setOnClickListener(this);
        findViewById(R.id.junior_btn).setOnClickListener(this);
        findViewById(R.id.adapter_btn).setOnClickListener(this);
        findViewById(R.id.storage_btn).setOnClickListener(this);
        findViewById(R.id.fragment_btn).setOnClickListener(this);
        findViewById(R.id.vollety_btn).setOnClickListener(this);
        findViewById(R.id.thread_btn).setOnClickListener(this);
        findViewById(R.id.date_a_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.view_test_btn){
            startActivity(new Intent(this, ViewTestActivity.class ));
        }
        if(id==R.id.junior_btn){
            startActivity(new Intent(this, JuniorActivity.class ));
        }
        if(id==R.id.adapter_btn){
            startActivity(new Intent(this, AdapterActivity.class ));
        }
        if(id==R.id.storage_btn){
            startActivity(new Intent(this, StorageActivity.class ));
        }
        if(id==R.id.fragment_btn){
            startActivity(new Intent(this, FragmentActivity.class));
        }
        if(id==R.id.vollety_btn){
            startActivity(new Intent(this, VolleyActivity.class));
        }
        if(id==R.id.thread_btn){
            startActivity(new Intent(this, ThreadActivity.class));
        }
        if(id==R.id.date_a_btn){
            Intent intent = new Intent(this, DateActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("key1", "Hello, World!");
            bundle.putInt("key2", 123);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }

}