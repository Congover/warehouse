package com.wh.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/report")
public class ReportController {
	
		
	@RequestMapping({"/", ""})
	public String reports(HttpSession session) {
		return "report";
	}

}
