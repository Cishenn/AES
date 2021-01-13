package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.Floor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FloorMapper {
    List<Floor> getAllFloor(int schoolId);

    List<Floor> getFloorByBuilding(String building,int schoolId);

    List<String> getBuilding(int schoolId);

    Integer getFloorId(int schoolId,String building,int floorStep);

    Floor getOneFloor(int floorId);
}
