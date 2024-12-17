package com.mysvac.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


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
    }

}