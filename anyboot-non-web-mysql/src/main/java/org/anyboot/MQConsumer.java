package org.anyboot;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

public class MQConsumer {
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
