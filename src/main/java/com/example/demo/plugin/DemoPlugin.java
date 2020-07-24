package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;


/**
 * 示例插件
 * 插件必须继承CQPlugin，上面要 @Component
 * <p>
 * 添加事件：光标移动到类中，按 Ctrl+O 添加事件(讨论组消息、加群请求、加好友请求等)
 * 查看API参数类型：光标移动到方法括号中按Ctrl+P
 * 查看API说明：光标移动到方法括号中按Ctrl+Q
 */
@Component
public class DemoPlugin extends CQPlugin {
    /**
     * 收到私聊消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送私聊消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        // 获取 发送者QQ 和 消息内容
        long userId = event.getUserId();
        String msg = event.getMessage();

        if (msg.equals("功能")||msg.equals("菜单")||msg.equals("列表")||msg.equals("帮助")||msg.equals("help")||msg.equals("menu")) {
            // 调用API发送hello
            cq.sendPrivateMsg(userId, "功能列表:\n" +
                    "sh000001 查询股票支持sh，sz,hk,us\n" +
                    "百科 梁非凡\n" +
                    "病理 头痛\n" +
                    "无损音乐 龙的传人\n" +
                    "翻译 dog\n" +
                    "养鲲游戏请发送【*菜单】\n" +
                    "赛马游戏请发送【@机器人+赛马】\n" +
                    "方舟抽卡请发送【非酋测试】\n" +
                    "百度云密码查询请直接发送【百度云链接】", false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


    /**
     * 收到群消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送群消息 sendGroupMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        // 获取 消息内容 群号 发送者QQ
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();

        if (msg.equals("功能")||msg.equals("菜单")||msg.equals("列表")||msg.equals("帮助")||msg.equals("help")||msg.equals("menu")) {
            // 回复内容为 at发送者 + hi
            String result = "功能列表\n" +
                    "sh000001 查询股票支持sh，sz,hk,us\n" +
                    "百科 梁非凡\n" +
                    "病理 头痛\n" +
                    "无损音乐 龙的传人\n" +
                    "翻译 dog\n" +
                    "养鲲游戏请发送【*菜单】\n" +
                    "赛马游戏请发送【@机器人+赛马】\n" +
                    "方舟抽卡请发送【非酋测试】\n" +
                    "百度云密码查询请直接发送【百度云链接】";

            // 调用API发送消息
            cq.sendGroupMsg(groupId, result, false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }

        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


}
