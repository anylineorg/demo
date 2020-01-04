package org.anyboot.start.json.home.controller;

import org.anyline.entity.DataSet;
import org.anyboot.start.common.BasicJSONController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("json.home.DefaultController")
@RequestMapping("/js")
public class DefaultController extends BasicJSONController {

	@RequestMapping("/index")
	public String index() {
		DataSet set = service.querys("api_config");
		return success(set);
	}
}
