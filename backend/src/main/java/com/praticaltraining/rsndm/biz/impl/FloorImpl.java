package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.FloorBiz;
import com.praticaltraining.rsndm.entity.Floor;
import com.praticaltraining.rsndm.exception.SchoolException;
import com.praticaltraining.rsndm.mapper.FloorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorImpl implements FloorBiz {
    @Autowired
    private FloorMapper floorMapper;

    @Override
    public List<Floor> getAllFloor(int schoolId){
        List<Floor> res = floorMapper.getAllFloor(schoolId);
        if(res == null){
            throw new SchoolException("getAll Floor error");
        }
        return res;
    }
}
