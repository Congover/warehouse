package com.wh.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/incoming")
public class IncomingController {
	
	private static final Logger logger = LoggerFactory.getLogger(IncomingController.class);
	
		
	@RequestMapping({"/", ""})
	public String incoming(HttpSession session) {
		return "incoming";
	}

}
