package com.mysvac.notes;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener{
    // 图片的连接  （第一个是这是百度的搜索图标，第二个是 个人头像）
    private static final String IMAGE_URL = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
    private static final String BASE_URL = "https://himg.bdimg.com/sys/portrait/item/public.1.6fd72a33.xQ_ifH6NU-EEeD7je3uNqA.jpg";
    private ImageView imageView;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        // 获取图片展示框对象
        imageView = findViewById(R.id.imageView);
        // 创建请求队列
        queue = Volley.newRequestQueue(this);
        // 监听修改照片的按钮
        findViewById(R.id.submit).setOnClickListener(this);
        // 获取初始图片
        createRequest(BASE_URL);
    }

    // 更换图片
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submit){
            EditText editText = findViewById(R.id.imgURL);
            String url = editText.getText().toString();
            // 创建网络请求
            createRequest(url);
        }
    }

    // 创建网络请求
    private void createRequest(String url){
        if(url==null || url.isEmpty()) url = IMAGE_URL;
        // 创建网络请求
        ImageRequest imageRequest = new ImageRequest(
                url,
                response -> {
                    // JAVA高版本可以直接用 lambda 表达式
                    // 加载图片到 ImageView
                    imageView.setImageBitmap(response);
                    Toast.makeText(VolleyActivity.this, "图片修改成功",Toast.LENGTH_SHORT).show();
                },
                0,
                0,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.RGB_565,
                error -> {
                    // 错误信息
                    Log.e("RequestError",error.toString());
                    Toast.makeText(VolleyActivity.this, "获取不到图片",Toast.LENGTH_SHORT).show();
                }
        );
        // 添加请求到队列
        queue.add(imageRequest);
    }


    // 创建网络请求
    private void createJsonRequest(String url) {
        if(url == null || url.isEmpty()) url = "example.JSON_URL";

        // 创建一个 JsonObjectRequest 请求
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,  // HTTP 请求方法，GET 或 POST
                url,
                null,  // 如果是 GET 请求，这里可以传递 null；如果是 POST 请求，传递请求体数据
                response -> {
                    // 成功响应，处理 JSON 数据
                    try {
                        // 假设响应数据是 { "name": "John", "age": 30 }
                        String name = response.getString("name");
                        int age = response.getInt("age");

                        // 在 UI 上更新（示例是打印）
                        Log.d("JSONResponse", "Name: " + name + ", Age: " + age);

                        // 显示 Toast 提示
                        Toast.makeText(VolleyActivity.this, "Name: " + name + ", Age: " + age, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        // 解析错误
                        e.printStackTrace();
                        Toast.makeText(VolleyActivity.this, "JSON解析失败", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    // 错误处理
                    Log.e("RequestError", error.toString());
                    Toast.makeText(VolleyActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
        );

        // 将请求添加到请求队列中
        queue.add(jsonObjectRequest);
    }

}
