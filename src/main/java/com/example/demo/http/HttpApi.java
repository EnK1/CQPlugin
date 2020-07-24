package com.example.demo.http;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
public class HttpApi {
    /**
     * @param id 股票id 上交所"sh"开头 深交所"sz"开头
     * @return
     * [00]上证指数           股票名称
     * [01]3187.8393		今日开盘价
     * [02]3152.8126		昨日收盘价
     * [03]3332.8807		当前价格
     * [04]3337.2723		今日最高价
     * [05]3187.8393		今日最低价
     * [06]0				竞买价
     * [07]0				竞卖价
     * [08]643878906		成交股数
     * [09]724153675701		成交金额
     * [10]0				买1手
     * [11]0				买1报价
     * [12]0				买2手
     * [13]0				买2报价
     * [14]0				买3手
     * [15]0				买3报价
     * [16]0				买4手
     * [17]0				买4报价
     * [18]0				买5手
     * [19]0				买5报价
     * [20]0				卖1手
     * [21]0				卖1报价
     * [22]0				卖2手
     * [23]0				卖2报价
     * [24]0				卖3手
     * [25]0				卖3报价
     * [26]0				卖4手
     * [27]0				卖4报价
     * [28]0				卖5手
     * [29]0				卖5报价
     * [30]2020-07-06		日期
     * [31]15:06:09			时间
     * [32]00
     */


    /**
     * [00]CHEUNG KONG      股票名称
     * [01]长和              股票名称
     * [02]50.600           今日开盘价
     * [03]50.850           昨日收盘价
     * [04]51.200           今日最高价
     * [05]50.500           今日最低价
     * [06]50.850           当前价格
     * [07]0.000
     * [08]0.000
     * [09]50.750
     * [10]50.800
     * [11]119061907
     * [12]2342015
     * [13]38.348
     * [14]0.000
     * [15]76.900
     * [16]45.050
     * [17]2020/07/22       日期
     * [18]11:09            时间
     * */

    /**
     * [00]哔哩哔哩                   股票名称
     * [01]44.1600                  当前价格
     * [02]4.20                     涨幅百分比
     * [03]2020-07-22 09:30:08      时间
     * [04]1.7800                   涨幅幅度
     * [05]44.0000                  开盘
     * [06]45.3599                  今日最高价
     * [07]43.5000                  今日最低价
     * [08]51.2500                  52周最高
     * [09]13.2300                  52周最低
     * [10]7574738                  成交量
     * [11]8424551                  10日均量
     * [12]15342758348              市值
     * [13]-0.72                    每股收益
     * [14]--
     * [15]0.00
     * [16]0.00
     * [17]0.00
     * [18]0.00
     * [19]3474356510               股本
     * [20]0
     * [21]44.4900                  盘后
     * [22]0.75                     盘后涨幅百分比
     * [23]0.33
     * [24]Jul 21 08:00PM EDT       美国东部时间
     * [25]Jul 21 04:00PM EDT
     * [26]42.3800                  前收盘
     * [27]17694                    成交量
     * [28]1
     * [29]2020
     * */
    public static String[] getHqBySina(String id) {
        String hqResult = getHqResultBySina(id);
        String[] temp = hqResult.split("var hq_str_" + id + "=\"");
        if (temp.length >= 2) {
            temp = temp[1].split("\";");
        }

        String[] hqArr;

        if (temp.length >= 1) {
            String hqStr = temp[0];
            hqArr = hqStr.split(",");
            return hqArr;
        } else return null;
    }

    public static String getHqResultBySina(String id) {
        return httpRequestUtils("http://hq.sinajs.cn/list=" + id);
    }

    public static String getHqResultByCute(String msg){
        log.info(msg);
        return httpRequestUtils("https://api.sc2h.cn/api/maid.php?data=0&sign=user&msg=" + msg);
    }

    public static String httpRequestUtils(String url) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL reqUrl = new URL(url);
            // 建立连接
            URLConnection conn = reqUrl.openConnection();

            //设置请求头
//            if (!url.startsWith("https")){
//                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");
//            }
            //          conn.setRequestProperty("Connection", "Keep-Alive");//保持长连接
            conn.setDoOutput(true); //设置为true才可以使用conn.getOutputStream().write()
            conn.setDoInput(true); //才可以使用conn.getInputStream().read();

            //写入参数
            out = new PrintWriter(conn.getOutputStream());
            //设置参数，可以直接写&参数，也可以直接传入拼接好的
//            out.print(params);
            // flush输出流的缓冲
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            if (!url.startsWith("https")){
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
                log.info("Stock:" + url);
            }else {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                log.info("btc:" + url);
            }
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        log.info(result.toString());

        return result.toString();
    }
}
