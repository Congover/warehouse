package com.wh.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/dictionaries")
public class DictController {
	
		
	@RequestMapping({"/", ""})
	public String dictionaries(HttpSession session) {
		return "dictionaries";
	}

}
