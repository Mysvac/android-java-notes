package com.mysvac.notes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.time.Year;
import java.util.Calendar;

public class DateActivity extends AppCompatActivity implements View.OnClickListener{

    private DatePicker dpk;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        // 在 onCreate 方法中获取传递的数据
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String value1 = bundle.getString("key1");
            int value2 = bundle.getInt("key2");
            Log.d("Received Data", "String: " + value1 + ", Int: " + value2);
        }

        findViewById(R.id.date_btn).setOnClickListener(this);
        findViewById(R.id.date_dialog_btn).setOnClickListener(this);
        findViewById(R.id.time_dialog_btn).setOnClickListener(this);
        dpk = findViewById(R.id.date_picker);
        tv = findViewById(R.id.date_tv);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.date_btn){
            int year = dpk.getYear();
            int month = dpk.getMonth()+1; // 月份是0-11，所以要加1
            int day = dpk.getDayOfMonth();

            String desc = String.format("您选择的日期是%d年%d月%d日！",year,month,day);
            tv.setText(desc);
        }
        if(id == R.id.date_dialog_btn){
            // 对话框的形式
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dpkd = new DatePickerDialog(this,(datePicker, year, month, dayOfMonth) -> {
                String desc = String.format("您选择的日期是%d年%d月%d日！",year,month,dayOfMonth);
                tv.setText(desc);
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
            dpkd.show();
        }
        if(id == R.id.time_dialog_btn){
            Calendar calendar = Calendar.getInstance();
            TimePickerDialog tpkd = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                String desc = String.format("您选择的时间是%d时%d分！",hourOfDay,minute);
                tv.setText(desc);
            },calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),true);

            tpkd.show();
        }

    }
}
