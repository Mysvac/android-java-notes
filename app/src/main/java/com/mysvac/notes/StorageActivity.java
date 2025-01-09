package com.mysvac.notes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StorageActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences shared;
    SQLiteManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        shared = getSharedPreferences("test",MODE_PRIVATE);
        dbManager = SQLiteManager.getInstance(this,0);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);

        load_content();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_1){
            func_1();
            load_content();
        }
        if(id == R.id.btn_2){
            func_2();
            load_content();
        }
        if(id == R.id.btn_3){
            func_3();
            load_content();
        }
        if(id == R.id.btn_4){
            func_4();
            load_content();
        }
    }

    public void load_content(){
        String content = shared.getString("content","null");
        TextView tv_1 = findViewById(R.id.tv_1);
        tv_1.setText(content);

        dbManager.openReadLink();
        TestEntity tmpItem = dbManager.queryLast();
        dbManager.closeLink();
        TextView tv_2 = findViewById(R.id.tv_2);
        String content2 = "null";
        if(tmpItem != null) content2 = tmpItem.getContent();
        tv_2.setText(content2);



        TextView tv_4 = findViewById(R.id.tv_4);
        tv_4.setText(MyApplication.appContentText);

    }

    public void func_1(){
        EditText tv = findViewById(R.id.edit_1);
        String content = tv.getText().toString();
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("content",content);
        // editor.commit();
        editor.apply();
    }

    public void func_2(){
        EditText tv = findViewById(R.id.edit_2);
        String content = tv.getText().toString();
        TestEntity item = new TestEntity();
        item.setContent(content);
        dbManager.openWriteLink();
        ArrayList<TestEntity> list = new ArrayList<>();
        list.add(item);
        dbManager.insert(list);
        dbManager.closeLink();
    }

    public void func_3(){

    }
    public void func_4(){
        EditText tv = findViewById(R.id.edit_4);
        MyApplication.appContentText= tv.getText().toString();
    }


}
