package org.anyboot.start.web.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anyline.entity.DataSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller("web.home.AppController")
@RequestMapping("/web/app")
public class AppController extends BasicController {

	public String dir = "app";

	@RequestMapping("/l")
	public ModelAndView list() {
		ModelAndView mv = template("list.jsp");
		return mv;
	}

	@RequestMapping("navi")
	@ResponseBody
	public String navi(HttpServletRequest request,HttpServletResponse response) {
		DataSet set = service.querys("api_config",parseConfig(true));
		request.setAttribute("set", set);
		return navi(request, response, set, "/WEB-INF/web/home/template/data/app/list.jsp");
	}

}
