package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.ExamRoomBiz;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.mapper.ExamRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exRoom")
public class ExamRoomController {

    @Autowired
    private ExamRoomBiz examRoomBiz;

    @GetMapping("/exRoom/schoolId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<ExamRoom>>> queryBySchoolId(int schoolId){
        Map<String,List<ExamRoom>> result = new HashMap<>();
        result.put("ExamRoom",examRoomBiz.getAllExamRoom(schoolId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
