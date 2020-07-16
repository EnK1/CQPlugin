package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@Component
public class CuteChatPlugin extends CQPlugin {


    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        Long groupId = event.getGroupId();
        Long userId = event.getUserId();
//        msg = msg.trim();
//        Long qq = cq.getLoginInfo().getData().getUser_id();//获取酷q登陆的qq好
//        String atId = CQCode.at(qq);//at码
        if (msg.startsWith(" ")){
            msg = msg.substring(1);
            String urlString = CuteApi(msg);
            System.out.println(urlString);
            cq.sendGroupMsg(groupId, CQCode.at(userId)+urlString, false);
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }

    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        Long userId = event.getUserId();
        String msg = event.getMessage();
        String urlString = CuteApi(msg);
        cq.sendPrivateMsg(userId,urlString,false);
        return MESSAGE_BLOCK;
    }

    public static String CuteApi(String msg){
        String urlName = "https://api.sc2h.cn/api/maid.php?data=0&sign=user&msg=" + msg;
        System.out.println(urlName);
        String urlString = null;

        try {
            URL url =new URL(urlName);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if(urlConnection instanceof HttpURLConnection)
            {
                connection = (HttpURLConnection) urlConnection;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String current;
            urlString = "";
            while((current = in.readLine()) != null)
            {
                urlString += current;
                System.out.println(urlString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlString;
    }
}
