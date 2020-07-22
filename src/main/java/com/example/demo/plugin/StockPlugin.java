package com.example.demo.plugin;

import com.example.demo.http.HttpApi;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockPlugin extends CQPlugin {

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        String msg1 = msg;
        long groupId = event.getGroupId();

        if (msg.equals("大盘")) {
            String[] hqArr = HttpApi.getHqBySina("sh000001");
            String str = Str(hqArr);
            String reply = "股票名称：" + hqArr[00] + " " + str + "%"+
                    "\n今日开盘价：" + hqArr[01] +
                    "\n昨日收盘价：" + hqArr[02] +
                    "\n当前价格：" + hqArr[03] +
                    "\n今日最高价：" + hqArr[04] +
                    "\n今日最低价：" + hqArr[05] +
                    "\n\n" + hqArr[30] + "\b-\b" + hqArr[31];
            cq.sendGroupMsg(groupId, reply, false);
            return MESSAGE_BLOCK;
        }
        if (msg.startsWith("sh") || msg.startsWith("sz")) {
            msg1 = msg1.substring(2);
            if (msg.length() == 8) {
                try {
                    Integer id = Integer.parseInt(msg1);
                    if (id instanceof Integer == true) {
                        try {
                            String[] hqArr = HttpApi.getHqBySina(msg);
                            String reply = null;
                            reply = Reply(hqArr, msg);
                            cq.sendGroupMsg(groupId, reply, false);
                        } catch (Exception e) {
                            cq.sendGroupMsg(groupId, "查询的股票代码不存在哦", false);
                        }

                    }
                } catch (NumberFormatException e) {
                    cq.sendGroupMsg(groupId, "请不要输入奇怪的东西", false);
                }
            }

            return MESSAGE_BLOCK;
        }
        if (msg.startsWith("hk")) {
            msg1 = msg1.substring(2);
            if (msg.length() == 7) {
                try {
                    Integer id = Integer.parseInt(msg1);
                    if (id instanceof Integer == true) {
                        try {
                            String[] hqArr = HttpApi.getHqBySina(msg);
                            String reply = null;
                            reply = HkReply(hqArr);
                            cq.sendGroupMsg(groupId, reply, false);
                        } catch (Exception e) {
                            log.info(e.toString());
                            cq.sendGroupMsg(groupId, "查询的股票代码不存在哦", false);
                        }

                    }
                } catch (NumberFormatException e) {
                    cq.sendGroupMsg(groupId, "请不要输入奇怪的东西", false);
                }
            }

            return MESSAGE_BLOCK;
        }


        return MESSAGE_IGNORE;
    }

    public static String Reply(String[] s, String msg) {
        String picLink = "\n日K线： http://image.sinajs.cn/newchart/daily/n/" + msg + ".gif\n" +
                "分时线： http://image.sinajs.cn/newchart/min/n/" + msg + ".gif\n" +
                "周K线： http://image.sinajs.cn/newchart/weekly/n/" + msg + ".gif\n" +
                "月K线： http://image.sinajs.cn/newchart/monthly/n/" + msg + ".gif\n";
        String str = Str(s);
        String reply = "股票名称：" + s[00] + " " + str + "%"+
                "\n今日开盘价：" + s[01] +
                "\n昨日收盘价：" + s[02] +
                "\n当前价格：" + s[03] +
                "\n今日最高价：" + s[04] +
                "\n今日最低价：" + s[05] +
                picLink +
                s[30] + "-" + s[31];
        return reply;
    }

    public static String HkReply(String[] s){
        float start = Float.parseFloat(s[03]);
        float current = Float.parseFloat(s[06]);
        float amplitude = (current - start)/start*100;
        String str = String.valueOf(amplitude);
        int index = str.indexOf(".");
        str = str.substring(0,index + 3);
        String reply = "股票名称：" + s[01] + " " + str + "%"+
                "\n今日开盘价：" + s[02] +
                "\n昨日收盘价：" + s[03] +
                "\n当前价格：" + s[06] +
                "\n今日最高价：" + s[04] +
                "\n今日最低价：" + s[05] +
                "\n" + s[17] + "-" + s[18];
        return reply;
    }

    public static String Str (String[] hqArr){
        float start = Float.parseFloat(hqArr[02]);
        float current = Float.parseFloat(hqArr[03]);
        float amplitude = (current - start)/start*100;
        String str = String.valueOf(amplitude);
        int index = str.indexOf(".");
        str = str.substring(0,index + 3);
        return str;
    }


}
