package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.FloorBiz;
import com.praticaltraining.rsndm.entity.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorBiz floorBiz;

    @GetMapping("/floor/schoolId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<Floor>>> queryBySchoolId(int schoolId){
        Map<String,List<Floor>> result = new HashMap<>();
        result.put("Floor",floorBiz.getAllFloor(schoolId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/floorId/schoolId_building_floorStep")
    @ResponseBody
    @CrossOrigin
    int getFloorId(int schoolId,String building,int floorStep){
        return floorBiz.getFloorId(schoolId,building,floorStep);
    }

    @GetMapping("/floor/floorId")
    @ResponseBody
    @CrossOrigin
    Floor getOneFloor(int floorId){
        return floorBiz.getOneFloor(floorId);
    }
}
