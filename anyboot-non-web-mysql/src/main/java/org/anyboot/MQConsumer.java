package org.anyboot;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RocketMQMessageListener(topic = "${rocketmq.config.queue.topic.test.key}",consumerGroup = "hello-rocketmq-group-rocket")
public class MQConsumer implements RocketMQListener<String>{

	@Override
	public void onMessage(String msg) {
		System.out.println("consumer:"+msg);
	}


	public static void main(String[] args) throws InterruptedException, MQClientException {

		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("sso_consumer");
		consumer.setNamesrvAddr("192.168.9.10:9876");
		consumer.subscribe("topic_user", "*");
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for(MessageExt msg:msgs){
					System.out.printf("%s Receive New Messages: %s %n", 
							Thread.currentThread().getName(), 
							new String(msg.getBody())
							);
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		consumer.start();

	}
}
