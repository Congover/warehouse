package com.wh.web;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wh.service.ContragentService;
import com.wh.service.DictionaryService;
import com.wh.service.IncomingService;
import com.wh.utils.Utils;

@Controller
@RequestMapping(value = "/report")
public class ReportController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ContragentService contragentService;

    @Autowired
    private IncomingService incomingService;

    @RequestMapping({ "/", "" })
    public String reports(HttpSession session, Map<String, Object> map) {
	map.put("productList", dictionaryService.getProducts());
	map.put("storeList", dictionaryService.getStories());
	map.put("contragentList", contragentService.findAll());
	return "report";
    }

    @RequestMapping({ "/formIncoming" })
    public String addIncoming(HttpServletRequest request, @RequestParam("dateStart") String dateStart,
	    @RequestParam("dateEnd") String dateEnd, @RequestParam("contragent") Long contragentId,
	    @RequestParam("product") Long productId, @RequestParam("store") Long storeId, HttpServletResponse resp)
	    throws IOException {
	boolean useContragent = !StringUtils.isEmpty(request.getParameter("useContragent"));
	boolean useProduct = !StringUtils.isEmpty(request.getParameter("useProduct"));
	boolean useStore = !StringUtils.isEmpty(request.getParameter("useStore"));
	HSSFWorkbook wb = incomingService.generateReport(dateStart, dateEnd, useContragent ? contragentId : null,
		useProduct ? productId : null, useStore ? storeId : null);
	resp.setContentType("application/octet-stream"); //$NON-NLS-1$
	String fileName = "incoming" + Utils.convertDateTimeToStr(new Date()) + ".xls";
	resp.setHeader("Content-Disposition", "attachment; filename=\"" //$NON-NLS-1$ //$NON-NLS-2$
		+ fileName + "\""); //$NON-NLS-1$
	ServletOutputStream os = resp.getOutputStream();
	wb.write(os);
	os.flush();
	os.close();
	return "/";
    }
}
