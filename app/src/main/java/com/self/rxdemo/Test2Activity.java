package com.self.rxdemo;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.killr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test2Activity extends AppCompatActivity implements View.OnClickListener {

    TextView btn1, btn2, btn3, btn4;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn_click1);
        btn2 = findViewById(R.id.btn_click2);
        btn3 = findViewById(R.id.btn_click3);
        btn4 = findViewById(R.id.btn_click4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        RxBus.get().register(this);

        //跟rxbinding并不冲突
        RxTextView.textChanges(btn4)
                .debounce(100, TimeUnit.MILLISECONDS)
                .map(str -> str.toString().length() >= 6 && str.toString().length() <= 11);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click1:

                break;
            case R.id.btn_click2:
                RxBus.get().postTag("null_test");
                break;
            case R.id.btn_click3:
                RxBus.get().post();
                break;
            case R.id.btn_click4:
                ArrayList<String> d = new ArrayList<>();
                d.add("1");
                d.add("1");
                d.addAll(d);
                d.addAll(d);
                d.addAll(d);
                RxBus.get().post(d);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
