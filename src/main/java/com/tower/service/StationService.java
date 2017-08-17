package com.tower.service;

import java.util.List;
import java.util.Map;

import com.tower.entity.Result;
import com.tower.entity.StationInfo;

public interface StationService {
	public void insertStation(StationInfo stationInfo);

	public void insertStations(List<StationInfo> stationInfo);

	public void deleteStation(String id);

	public void deleteAllStation();

	public Result findall(Map map);
}