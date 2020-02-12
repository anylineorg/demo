package org.anyboot.mq;

import org.anyline.util.DateUtil;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MQProducer implements CommandLineRunner {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.config.queue.topic.test.key}")
    private String topicKey;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("[start produce]");
        int cnt = 0;
        while (cnt<=9){
            rocketMQTemplate.convertAndSend(topicKey,"hello rocketmq"+ DateUtil.now());
            cnt++;
        }
        System.out.println("[end produce]");
    }
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("hr_producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("topic_user",
                    "tag_update",
                    ("{user:uid"+i+",tel:15800000000"+i+"}").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
