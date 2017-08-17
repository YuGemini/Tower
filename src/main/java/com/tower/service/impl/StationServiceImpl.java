package com.tower.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tower.entity.Result;
import com.tower.entity.StationInfo;
import com.tower.mapper.StationMapper;
import com.tower.service.StationService;

@Service
public class StationServiceImpl implements StationService{

	@Resource
	private StationMapper map;
	
	public void insertStation(StationInfo stationInfo) {
		// TODO Auto-generated method stub
		this.map.insertStation(stationInfo);
	}

	public void deleteStation(String id) {
		// TODO Auto-generated method stub
		this.map.deleteStation(id);
	}

	public Result findall(Map map) {
		// TODO Auto-generated method stub
		Result result = new Result();
		result.setRows(this.map.getStations(map));
		result.setTotal(this.map.getTotalcount(map));
		return result;
	}

	public void insertStations(List<StationInfo> stationInfos) {
		// TODO Auto-generated method stub
		this.map.insertStations(stationInfos);
	}

	public void deleteAllStation() {
		// TODO Auto-generated method stub
		this.map.deleteAllStation();
	}


}
