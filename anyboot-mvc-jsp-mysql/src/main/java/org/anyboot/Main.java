package org.anyboot;

import org.anyboot.jdbc.ds.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"org.anyline","org.anyboot"})
@Import(DynamicDataSourceRegister.class)
public class Main extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Main.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
/*
 * 
 * 多数据源切换
 * 需要@Import(DynamicDataSourceRegister.class)
 * 同时需要扫描这两个包@ComponentScan(basePackages = {"org.anyline","org.anyboot"}) 
 * 配置文件中需要指定一个默认数据源(spring.datasource.driver)
 * 其他数据源先定义列表(spring.datasource.list=ds1,ds2)
 * 再定义详细参数(spring.datasource.ds1.driver)
 * 
 * 可以通过DataSourceHolder.setDataSource("ds1");切换数据源
 * 也可以AnylineService.query("<ds1>表名");查询时切换，查询后数据源会切换回切换前的数据源
 * 
 * 注意controller.template()与dir属性值 不要与以下两个属性冲突
 * #spring.mvc.view.suffix=.jsp
 * #spring.mvc.view.prefix=/WEB-INF
 * 
 */