package com.self.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.killr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test2Activity extends AppCompatActivity implements View.OnClickListener {

    TextView btn1, btn2, btn3, btn4;
    TestProduce mProduce = new TestProduce();

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

    }

    boolean send;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click1:
                if (!send) {
                    RxBus.get().register(mProduce);
                    send = true;
                } else {
                    Toast.makeText(this, "已经发过了，再发就炸了", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_click2:
                RxBus.get().postTag("null_test");
                break;
            case R.id.btn_click3:
                RxBus.get().post();
                break;
            case R.id.btn_click4:
                List<String> d = new ArrayList<>();
                d.add("1");
                d.add("1");
                d.addAll(d);
                d.addAll(d);
                d.addAll(d);


                RxBus.get().post(new AA("xxxx"));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}
