package com.tower.util;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ViewExcel {  
	  
    public void buildExcelDocument(Map<String, Object> map,  
            XSSFWorkbook book, HttpServletRequest request, HttpServletResponse response)  
            throws Exception {  
        	String filename = "站点信息表.xls";//设置下载时客户端Excel的名称         
        	filename = URLEncoder.encode(filename, "UTF-8");      
            response.setContentType("application/vnd.ms-excel");         
            response.setHeader("Content-disposition", "attachment;filename=" + filename);         
            OutputStream ouputStream = response.getOutputStream();         
            book.write(ouputStream);         
            ouputStream.flush();         
            ouputStream.close();     
  
    }

	 
}  