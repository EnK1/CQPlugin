package com.example.demo.schedule;

import com.example.demo.http.StokHttp;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.cq.CQGlobal;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduleTask {
    @Scheduled(cron = "0 30 9,10,11 * * 1,2,3,4,5",zone = "Asia/Shanghai")
    public void Morning(){
        log.info("123");
        String reply = Reply();
        CQGlobal.robots.get(3487542722L).sendGroupMsg(912790863,reply,false);
    }
    @Scheduled(cron = "0 0 13,14,15 * * 1,2,3,4,5",zone = "Asia/Shanghai")
    public void Afternoon(){
        String reply = Reply();
        CQGlobal.robots.get(3487542722L).sendGroupMsg(912790863,reply,false);
    }

    public static String Reply(){
        String[] hqArr = StokHttp.getHqBySina("sh000001");
        float start = Float.parseFloat(hqArr[02]);
        float current = Float.parseFloat(hqArr[03]);
        float amplitude = (current - start)/start*100;

//        String reply =  hqArr[00] + "：" + hqArr[03] +"%f.2%"+
//                "\n\n" + hqArr[30] + "\b-\b" + hqArr[31];
        String reply =String.format("%s：%s %f.2%\n %s - %s ",hqArr[00],hqArr[03],amplitude,hqArr[30],hqArr[31]);
        return reply;
    }
}

