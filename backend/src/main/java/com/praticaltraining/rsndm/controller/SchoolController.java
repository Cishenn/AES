package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.SchoolBiz;
import com.praticaltraining.rsndm.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolBiz schoolBiz;

    @GetMapping("/school")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<School>>> queryAll(){
        Map<String,List<School>> result = new HashMap<>();
        result.put("School",schoolBiz.getAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/school/eduId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<School>>> queryByEduId(int eduId){
        Map<String,List<School>> result = new HashMap<>();
        result.put("School",schoolBiz.getByEduId(eduId));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/exRoomExamine/schoolId_exRoomExamine")
    @ResponseBody
    @CrossOrigin
    void examineExRoom(int schoolId,int exRoomExamine){
        schoolBiz.examineExRoom(schoolId,exRoomExamine);
    }

    @PostMapping("/exRoomExamine/schoolId")
    @ResponseBody
    @CrossOrigin
    void submitExRoom(int schoolId){
        schoolBiz.submitExRoom(schoolId);
    }

    @GetMapping("/schoolName/schoolId")
    @ResponseBody
    @CrossOrigin
    String getSchoolName(int schoolId){
        return schoolBiz.getSchoolName(schoolId);
    }
}
