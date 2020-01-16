package org.anyboot;

import org.anyboot.jdbc.ds.DynamicDataSourceRegister;
import org.anyline.entity.DataSet;
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
public class Main implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(Main.class);

    @Autowired
    private AnylineService service;
    @Override
    public void run(String... args) throws Exception {
        DataSet set = service.querys("api_config");
        log.info("[api_config set json]:{}",set.toJson());
       /* while (true){
            log.info("[waiting]");
        }*/
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

    }
}
