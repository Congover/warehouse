package com.wh.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.model.BaseDataTableModel;
import com.wh.model.IncomingDataTableModel;
import com.wh.service.ContragentService;
import com.wh.service.DictionaryService;
import com.wh.service.IncomingService;

@Controller
@RequestMapping(value = "/incoming")
public class IncomingController {

    private static final Logger log = LoggerFactory.getLogger(IncomingController.class);

    private static final String REDIRECT = "redirect:/incoming";

    @Autowired
    private IncomingService incomingService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ContragentService contragentService;

    @RequestMapping({ "/", "" })
    public String incoming(HttpSession session, Map<String, Object> map) {
	session.setAttribute("incContrId", null);
	map.put("contragentList", contragentService.findAll());
	return "incoming";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public BaseDataTableModel<?> getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length,
	    @RequestParam("start") Integer start, @RequestParam("search[value]") Long contragentId) {
	return new IncomingDataTableModel(contragentId == null ? incomingService.findAll()
		: incomingService.findByContragentId(contragentId), draw, length, start);

    }

    @RequestMapping({ "/add" })
    public String add(Map<String, Object> map) {
	map.put("productList", dictionaryService.getProducts());
	map.put("storeList", dictionaryService.getStories());
	map.put("contragentList", contragentService.findAll());
	return "addIncoming";
    }

    @RequestMapping({ "/save" })
    public String addIncoming(HttpSession session, @RequestParam("date") String date,
	    @RequestParam("contragent") Long contragentId, @RequestParam("product") Long productId,
	    @RequestParam("productCount") Double productCount, @RequestParam("store") Long storeId,
	    @RequestParam("comment") String comment) {
	incomingService.save(date, contragentId, productId, productCount, storeId, comment);
	return REDIRECT;
    }

    @RequestMapping({ "/edit/{id}" })
    public String edit(@PathVariable("id") Long id, Map<String, Object> map) {
	map.put("incoming", incomingService.find(id));
	map.put("storeList", dictionaryService.getStories());
	map.put("contragentList", contragentService.findAll());
	return "editIncoming";
    }

    @RequestMapping({ "update" })
    public String update(@RequestParam("id") Long id, @RequestParam("date") String date,
	    @RequestParam("contragent") Long contragentId, @RequestParam("store") Long storeId,
	    @RequestParam("comment") String comment) {
	incomingService.update(id, date, contragentId, storeId, comment);
	return REDIRECT;
    }

    @RequestMapping({ "delete" })
    public @ResponseBody Boolean delete(@RequestParam("id") Long id) {
	return incomingService.delete(id);
    }

    @RequestMapping({ "changeContragent" })
    public @ResponseBody Boolean changeContragent(@RequestParam("id") Long id, HttpSession session) {
	session.setAttribute("incContrId", id);
	return true;
    }

}
