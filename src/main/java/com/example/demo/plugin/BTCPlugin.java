package com.example.demo.plugin;

import com.example.demo.pojo.Data;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class BTCPlugin extends CQPlugin {

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        Long groupID = event.getGroupId();
        String msg = event.getMessage();
        if (msg.equals("btc") || msg.equals("BTC")) {
            String url = "https://api.coincap.io/v2/assets/bitcoin";
            cq.sendGroupMsg(groupID, BTCJson(url), false);
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }

    public static String BTCJson(String url) {

        String btc = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            btc = response.body().string();
            log.info("json:" + btc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        com.example.demo.pojo.Response btcInfo = gson.fromJson(btc, com.example.demo.pojo.Response.class);
        String s = "货币名：" + btcInfo.getData().getId()+
                "\n价格：" + btcInfo.getData().getPriceUsd() +
                "\n24小时变换率："+ btcInfo.getData().getChangePercent24Hr() +
                "%\n24h成交加权平均：" + btcInfo.getData().getVwap24Hr();
        return s;
    }
}
