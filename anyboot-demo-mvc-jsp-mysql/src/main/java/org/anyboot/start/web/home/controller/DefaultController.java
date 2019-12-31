package org.anyboot.start.web.home.controller;

import org.anyline.entity.DataSet;
import org.anyline.jdbc.ds.DataSourceHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DefaultController extends BasicController {

	public String dir = "";

	@RequestMapping("/")
	public ModelAndView index() {
		
		DataSet set = service.querys("api_config");//默认数据源(default)
		System.out.println("=========================        0             =====================================");
		DataSourceHolder.setDataSource("cms");//修改数据源(cms)
		set = service.querys("cms_config");
		System.out.println("=========================        1             =====================================");
		set = service.querys("<sso>sso_config");//一次性设置数据源(sso),只针对当前查询有效，执行完后，数据源恢复到设置之前状态(cms)
		System.out.println("=========================        2             =====================================");
		set = service.querys("<api>api_config");//一次性设置数据源(api),只针对当前查询有效，执行完后，数据源恢复到设置之前状态(cms)
		System.out.println("=========================        3             =====================================");
		set = service.querys("<sso>sso_config");//一次性设置数据源(sso),只针对当前查询有效，执行完后，数据源恢复到设置之前状态(cms)
		System.out.println("=========================        4             =====================================");
		DataSourceHolder.setDataSource("cms");//设置数据源(cms)
		set = service.querys("<sso>sso_config");//一次性设置数据源(sso),只针对当前查询有效，执行完后，数据源恢复到设置之前状态(cms)
		System.out.println("=========================        5             =====================================");
		set = service.querys("cms_config");//数据源(cms)
		System.out.println("=========================        6             =====================================");
		DataSourceHolder.setDataSource("default");//设置成默认数据源与setDefaultDataSource一 
		set = service.querys("api_config");
		System.out.println("=========================        7             =====================================");
		DataSourceHolder.setDefaultDataSource();//设置成默认数据源
		set = service.querys("api_config");
		//Map<String, String> context = RpcContext.getContext().getAttachments();
		
		ModelAndView mv = template("index.jsp");
		mv.addObject("set", set);
		//mv.setViewName("/WEB-INF/web/admin/template/layout/default.jsp");
		
		//mv.addObject("anyline_template_content_path", "/WEB-INF/web/admin/page/app/index.jsp");
		/*
注意
#spring.mvc.view.suffix=.jsp
#spring.mvc.view.prefix=/WEB-INF*/
		return mv;
	}
}
