package com.wh.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/packing")
public class PackingController {
	
		
	@RequestMapping({"/", ""})
	public String packing(HttpSession session) {
		return "packing";
	}

}
