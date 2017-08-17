package com.tower.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tower.entity.StationInfo;

@Mapper
public interface StationMapper {

	public List<StationInfo> getStations(Map map);

	public int getTotalcount(Map map);

	public void deleteStation(@Param(value = "id") String id);

	public void deleteAllStation();
	
	public void insertStation(StationInfo stationInfo);
	
	public void insertStations(List<StationInfo> stationInfo);
}
