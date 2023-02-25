package com.xxzou.javaexample.rocketmq.client.producer;

import com.xxzou.javaexample.utils.BlockUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RocketMQProducer {

    public static void main(String[] args) throws Exception{

        // 初始化一个producer并设置Producer group name
        DefaultMQProducer producer = new DefaultMQProducer("test_group"); //（1）
        // 设置NameServer地址
        producer.setNamesrvAddr("192.168.152.138:9876");  //（2）
        // 启动producer
        producer.start();



        while(true){
            String text = BlockUtils.blockUntilInput();
            if("exit".equals(text)){
                break;
            }

            // 创建一条消息，并指定topic、tag、body等信息，tag可以理解成标签，对消息进行再归类，RocketMQ可以在消费端对tag进行过滤
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + text).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );   //（3）
            // 利用producer进行发送，并同步等待发送结果
            SendResult sendResult = producer.send(msg);   //（4）
            System.out.printf("%s%n", sendResult);
        }


        // 一旦producer不再使用，关闭producer
        producer.shutdown();
    }
}
