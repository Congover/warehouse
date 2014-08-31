package com.wh.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wh.service.ContragentService;
import com.wh.service.DictionaryService;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ContragentService contragentService;

    @RequestMapping({ "/", "" })
    public String reports(HttpSession session, Map<String, Object> map) {
	map.put("productList", dictionaryService.getProducts());
	map.put("storeList", dictionaryService.getStories());
	map.put("contragentList", contragentService.findAll());
	return "report";
    }

    @RequestMapping({ "/formIncoming" })
    public String addIncoming(HttpServletRequest request, @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateStart") String dateEnd, @RequestParam("contragent") Long contragentId,
	    @RequestParam("product") Long productId, @RequestParam("store") Long storeId) {
	boolean useContagent = !StringUtils.isEmpty(request.getParameter("useContagent"));
	boolean useProduct = !StringUtils.isEmpty(request.getParameter("useProduct"));
	boolean useStore = !StringUtils.isEmpty(request.getParameter("useStore"));
	System.out.println(String.valueOf(useStore) + useProduct + "");
	return "/";
    }
}
