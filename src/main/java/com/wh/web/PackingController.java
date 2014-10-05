package com.wh.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wh.model.BaseDataTableModel;
import com.wh.model.PackingDataTableModel;
import com.wh.service.DictionaryService;
import com.wh.service.PackingService;

@Controller
@RequestMapping(value = "/packing")
public class PackingController {

    private static final String REDIRECT = "redirect:/packing";

    @Autowired
    PackingService packingService;

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping({ "/", "" })
    public String packing(HttpSession session) {
	return "packing";
    }

    @RequestMapping("/getList")
    @ResponseBody
    public BaseDataTableModel<?> getList(@RequestParam("draw") Integer draw, @RequestParam("length") Integer length,
	    @RequestParam("start") Integer start) {
	return new PackingDataTableModel(packingService.findAll(), draw, length, start);
    }

    @RequestMapping({ "/add" })
    public String add(Map<String, Object> map) {
	map.put("productList", dictionaryService.getAvailibleProductForPacking());
	return "addPacking";
    }

    @RequestMapping({ "/save" })
    public String addIncoming(HttpSession session, @RequestParam("date") String date,
	    @RequestParam("product") Long productId, @RequestParam("productCount") Double productCount,
	    @RequestParam("packedProduct") Long packedProductId) {
	packingService.save(date, productId, packedProductId, productCount);
	return REDIRECT;
    }

    @RequestMapping({ "delete" })
    public @ResponseBody Boolean delete(@RequestParam("id") Long id) {
	return packingService.delete(id);
    }

}
