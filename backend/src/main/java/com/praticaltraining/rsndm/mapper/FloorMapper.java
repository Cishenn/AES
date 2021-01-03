package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.Floor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FloorMapper {
    List<Floor> getAllFloor(int schoolId);

    int getFloorId(int schoolId,String building,int floorStep);
}
