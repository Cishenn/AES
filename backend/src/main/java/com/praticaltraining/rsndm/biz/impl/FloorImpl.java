package com.praticaltraining.rsndm.biz.impl;

import com.praticaltraining.rsndm.biz.FloorBiz;
import com.praticaltraining.rsndm.entity.Floor;
import com.praticaltraining.rsndm.exception.FloorException;
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
            throw new FloorException("getAll Floor error");
        }
        return res;
    }

    @Override
    public List<Floor> getFloorByBuilding(String building,int schoolId){
        List<Floor> res = floorMapper.getFloorByBuilding(building,schoolId);
        if(res == null){
            throw new FloorException("get Floor error");
        }
        return res;
    }

    @Override
    public List<String> getBuilding(int schoolId){
        List<String> res = floorMapper.getBuilding(schoolId);
        if(res == null){
            throw new FloorException("get building error");
        }
        return res;
    }

    @Override
    public int getFloorId(int schoolId,String building,int floorStep){
        int res = floorMapper.getFloorId(schoolId,building,floorStep);
        if(res==0){
            throw new FloorException("get floorId error");
        }
        return res;
    }

    @Override
    public Floor getOneFloor(int floorId){
        Floor res = floorMapper.getOneFloor(floorId);
        if(res == null){
            throw new FloorException("get one floor error");
        }
        return res;
    }
}
