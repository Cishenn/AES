package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.Floor;

import java.util.List;

public interface FloorBiz {
    List<Floor> getAllFloor(int schoolId);
}
