package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.Floor;

import java.util.List;

public interface FloorBiz {
    List<Floor> getAllFloor(int schoolId);

    List<Floor> getFloorByBuilding(String building,int schoolId);

    List<String> getBuilding(int schoolId);

    int getFloorId(int schoolId,String building,int floorStep);

    Floor getOneFloor(int floorId);
}
