package com.example.demo.plugin;

import com.example.demo.http.StokHttp;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@Component
public class StockPlugin extends CQPlugin {

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();

        if (msg.equals("大盘")){
            String[] hqArr = StokHttp.getHqBySina("sh000001");
            String reply = "股票名称：" + hqArr[00] +
                    "\n今日开盘价：" + hqArr[01] +
                    "\n昨日收盘价：" + hqArr[02] +
                    "\n当前价格：" + hqArr[03] +
                    "\n今日最高价：" + hqArr[04] +
                    "\n今日最低价：" + hqArr[05] +
                    "\n\n" + hqArr[30] + "\b-\b" + hqArr[31];
            cq.sendGroupMsg(groupId,reply,false);
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }



}
