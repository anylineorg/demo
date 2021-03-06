package org.anyboot;

import org.anyboot.jdbc.ds.DynamicDataSourceRegister;
import org.anyline.service.AnylineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/*@SpringBootConfiguration*/
@SpringBootApplication
@ComponentScan(basePackages = {"org.anyline","org.anyboot"})
@Import(DynamicDataSourceRegister.class)
public class App implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(App.class);

    @Autowired
    private AnylineService service;
    @Override
    public void run(String... args) throws Exception {
       /* DataSet set = service.querys("api_config");
        log.info("[api_config set json]:{}",set.toJson());*/
       /* while (true){
            log.info("[waiting]");
        }*/
    }

    public static void main(String[] args) throws Exception{
        ApplicationContext context = SpringApplication.run(App.class, args);
        /*AnylineService service = context.getBean(AnylineService.class);
        DataSet set = service.querys("api_config");
        for(DataRow row:set){
            System.out.println(row);
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");*/
    }
}
