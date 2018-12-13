package com.self.rxdemo;

import com.killr.rxbus.annotation.Produce;
import com.killr.rxbus.annotation.Tag;

public class TestProduce {

    @Produce(
            tags = {
                    @Tag(value = "Produce")
            }
    )
    public String sendMsg() {
        //我觉得这个有毛病。。。注册即发送，又不能重复发送？？？
        //而且不能放在Activity里。
        //这个方法的本质：
        //RxBus.get().register(mProduce); ==> RxBus.get().post("Produce","注册立即发送该消息");
        //把它删了岂不是美滋滋。。。
        return "注册立即发送该消息";
    }

}
