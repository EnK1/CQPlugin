package com.example.demo.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import com.example.demo.http.HttpApi;

@Slf4j
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
            msg = msg.trim();
            if (msg.length() != 0){
                String result = HttpApi.getHqResultByCute(msg);
                result = result.trim();
                log.info("cute返回值：" + result);
                if (result.length() != 0){
                    cq.sendGroupMsg(groupId, CQCode.at(userId) + result, false);
                }
            }

            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }

    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        Long userId = event.getUserId();
        String msg = event.getMessage();
        String result = HttpApi.getHqResultByCute(msg);
        result = result.trim();
        if (result.length() != 0){
            cq.sendPrivateMsg(userId,result,false);
        }

        return MESSAGE_BLOCK;
    }

//    public static String CuteApi(String msg){
//        String urlName = "https://api.sc2h.cn/api/maid.php?data=0&sign=user&msg=" + msg;
//        PrintWriter out = null;
//        BufferedReader in = null;
//        StringBuilder result = new StringBuilder();
//        try {
//            URL reqUrl = new URL(urlName);
//            // 建立连接
//            URLConnection conn = reqUrl.openConnection();
//
//            //设置请求头
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//            //          conn.setRequestProperty("Connection", "Keep-Alive");//保持长连接
//            conn.setDoOutput(true); //设置为true才可以使用conn.getOutputStream().write()
//            conn.setDoInput(true); //才可以使用conn.getInputStream().read();
//
//            //写入参数
//            out = new PrintWriter(conn.getOutputStream());
//            //设置参数，可以直接写&参数，也可以直接传入拼接好的
////            out.print(params);
//            // flush输出流的缓冲
//            out.flush();
//
//            // 定义BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result.append(line);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {// 使用finally块来关闭输出流、输入流
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return result.toString();
//        System.out.println(urlName);
//        String urlString = null;
//
//        try {
//            URL url = new URL(urlName);
//            URLConnection urlConnection = url.openConnection();
//            HttpURLConnection connection = null;
//            if(urlConnection instanceof HttpURLConnection)
//            {
//                connection = (HttpURLConnection) urlConnection;
//            }
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream()));
//
//            String current;
//            urlString = "";
//            while((current = in.readLine()) != null)
//            {
//                urlString += current;
//                System.out.println(urlString);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return urlString;
//    }
}
