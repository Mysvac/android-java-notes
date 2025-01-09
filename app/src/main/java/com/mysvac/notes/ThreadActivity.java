package com.mysvac.notes;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener{
    public TextView tv;

    // 创建handler
    private static class MyHandler extends Handler {
        private final ThreadActivity mActivity;
        MyHandler(ThreadActivity activity) {
            super(Looper.getMainLooper());
            this.mActivity = activity;
        }
        @Override
        public void handleMessage(Message msg) {
            if(mActivity == null){
                return;
            }
            switch (msg.what){
                case 1:
                    Bundle bundle = msg.getData();
                    int num = bundle.getInt("num");
                    mActivity.tv.setText(""+num);
                    break;
                case 2:
                    break;
                default:
                    break;
            }

        }
    }

    private final MyHandler handler = new MyHandler(this);;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        findViewById(R.id.thread_layout_btn).setOnClickListener(this);
        tv = findViewById(R.id.thread_layout_tv);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.thread_layout_btn){
            Log.i("btn","be clicked");
            int base = Integer.parseInt(tv.getText().toString());
            // 创建线程
            new Thread(() -> {
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putInt("num",base+1);
                message.setData(bundle);
                handler.sendMessage(message);
            }).start();
        }
    }

}
