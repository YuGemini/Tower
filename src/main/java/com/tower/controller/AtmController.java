package com.tower.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.tower.bean.SearchParam;
import com.tower.entity.Result;
import com.tower.entity.StationInfo;
import com.tower.service.StationService;
import com.tower.util.ExcelExportUtil;
import com.tower.util.ExcelImportUtil;
import com.tower.util.ViewExcel;

@Controller
public class AtmController {

	@Resource
	private StationService stationService;

	@ResponseBody
	@RequestMapping(value = "/station/list.action", method = RequestMethod.POST)
	public Result userList(@RequestBody SearchParam param) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("curRow", param.getPageSize() * (param.getCurPage() - 1));
		map.put("pageSize", param.getPageSize());
		map.put("id", param.getId());
		map.put("ct", param.getTtmc());
		Result list = this.stationService.findall(map);

		return list;
	}

	@RequestMapping(value = "/station/delete")
	public void delete(
			@RequestParam(value = "id", required = false) String row,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PrintWriter printWriter = response.getWriter();
			map.put("status", "1");
			String data = JSON.toJSONString(map);
			printWriter.print(data);
			this.stationService.deleteStation(row);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/station/deleteAll")
	public void deleteAll(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PrintWriter printWriter = response.getWriter();
			map.put("status", "1");
			String data = JSON.toJSONString(map);
			printWriter.print(data);
			this.stationService.deleteAllStation();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String toStation(Integer id, Model model) {
		return "index";
	}

	@RequestMapping("/station/add.action")
	public void addStation(StationInfo stationInfo, HttpSession session,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String data = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "0");
		data = JSON.toJSONString(map);
		printWriter.print(data);

		this.stationService.insertStation(stationInfo);

	}

	@RequestMapping(value = "/station/import.action")
	@ResponseBody
	public String importExcel(
			@RequestParam(value = "fileUpload") MultipartFile excelFile,
			HttpServletRequest request) throws IOException {
		String fileName = excelFile.getOriginalFilename();// report.xls
		String fileName2 = excelFile.getName();// excelFile

		InputStream fis = excelFile.getInputStream();

		List<Map<String, String>> data = ExcelImportUtil.parseExcel(fis);
		List<StationInfo> stationInfo = new ArrayList<StationInfo>();
		for (Map item : data) {
			StationInfo info = new StationInfo();
			info.setId(item.get("站址编号").toString());
			info.setCntower(item.get("铁塔名称").toString());
			info.setCnmobile(item.get("移动名称").toString());
			info.setCntelecom(item.get("电信名称").toString());
			info.setCnunicom(item.get("联通名称").toString());
			stationInfo.add(info);
		}
		this.stationService.insertStations(stationInfo);

		return "true";
	}

	@RequestMapping(value = "/station/export.action")
	@ResponseBody
	public void report(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "ttmc", required = false) String ttmc,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("curRow", 0);
		map.put("pageSize", 3000);
		map.put("id", id);
		map.put("ct", ttmc);
		ViewExcel viewExcel = new ViewExcel();

		Map obj = null;
		System.out.println("response:" + response);
		// 获取数据库表生成的workbook
		Map condition = new HashMap();
		// 这里是从数据库里查数据并组装成我们想要的数据结构的过程，略。。
		Result list = this.stationService.findall(map);
		List<StationInfo> infos = list.getRows();
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (StationInfo item : infos) {
			Map<String, String> station = new LinkedHashMap<String, String>();
			station.put("站址编号", item.getId());
			station.put("铁塔名称", item.getCntower());
			station.put("移动名称", item.getCnmobile());
			station.put("电信名称", item.getCntelecom());
			station.put("联通名称", item.getCnunicom());
			data.add(station);

		}
		XSSFWorkbook workbook = ExcelExportUtil.generateExcel(data, "站点信息表");
		try {
			viewExcel.buildExcelDocument(obj, workbook, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/station/template.action")
	public void template(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ViewExcel viewExcel = new ViewExcel();
		Map obj = null;
		System.out.println("response:" + response);
		// 获取数据库表生成的workbook
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		Map<String, String> station = new LinkedHashMap<String, String>();
		station.put("站址编号", "xxx");
		station.put("铁塔名称", "xxx");
		station.put("移动名称", "xxx");
		station.put("电信名称", "xxx");
		station.put("联通名称", "xxx");
		data.add(station);

		XSSFWorkbook workbook = ExcelExportUtil.generateExcel(data, "站点信息模板表");
		try {
			viewExcel.buildExcelDocument(obj, workbook, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/towerlist", method = RequestMethod.GET)
	public String toTowerList() {
		return "towerlist";
	}

	@RequestMapping(value = "/addtower", method = RequestMethod.GET)
	public String toAddTower() {
		return "addtower";
	}

	@RequestMapping(value = "/uploadtower", method = RequestMethod.GET)
	public String toUploadTower() {
		return "uploadtower";
	}

}
