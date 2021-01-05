package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.ExamRoomBiz;
import com.praticaltraining.rsndm.biz.FloorBiz;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.Floor;
import com.praticaltraining.rsndm.entity.floor_exRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/floor_exRoom")
public class Floor_exRoomController {

    @Autowired
    private FloorBiz floorBiz;

    @Autowired
    private ExamRoomBiz examRoomBiz;

    @GetMapping("/floor_exRoom/schoolId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<floor_exRoom>>> queryBySchoolId(int schoolId){
        Map<String,List<floor_exRoom>> result = new HashMap<>();
        List<ExamRoom> room = examRoomBiz.getAllExamRoom(schoolId);
        List<Floor> floor = floorBiz.getAllFloor(schoolId);
        List<floor_exRoom> floor_room = new ArrayList();
        List<floor_exRoom> fr = new ArrayList();
        int fId = 0;
        int flag = 0;
        for(int i = 0;i<room.size();i++){
            fId = room.get(i).getFloorId();
            for(int j = 0;j<floor.size();j++){
                if(floor.get(j).getFloorId() == fId){
                    flag = j;
                    break;
                }
            }
            floor_exRoom floor_exroom = new floor_exRoom(floor.get(flag),room.get(i));
            floor_room.add(floor_exroom);
        }
        List<String> building = floorBiz.getBuilding(schoolId);
        for(int i = 0;i < building.size();i++){
            List<floor_exRoom> temp = new ArrayList();
            for(int j = 0;j < floor_room.size();j++){
                if(floor_room.get(j).floor.getBuilding().equals(building.get(i))){
                    temp.add(floor_room.get(j));
                }
            }
            Collections.sort(temp);
            fr.addAll(temp);
        }
        result.put("floor_exRoom",fr);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/floor_exRoom/exRoomId")
    @ResponseBody
    @CrossOrigin
    floor_exRoom getOneExRoom(int exRoomId){
        ExamRoom exroom = examRoomBiz.getOneExRoom(exRoomId);
        Floor floor = floorBiz.getOneFloor(exroom.getFloorId());
        floor_exRoom floor_exroom = new floor_exRoom(floor,exroom);
        return floor_exroom;
    }
}
