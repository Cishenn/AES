package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.ExamRoomBiz;
import com.praticaltraining.rsndm.biz.ExamStaffBiz;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.ExamStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exStaff")
public class ExamStaffController {

    @Autowired
    private ExamStaffBiz examStaffBiz;

    @GetMapping("/exStaff/exStaffId")
    @ResponseBody
    @CrossOrigin
    ExamStaff getOneExamStaff(int esId){
        return examStaffBiz.getOneExamStaff(esId);
    }

    @PostMapping("/exStaff")
    @ResponseBody
    @CrossOrigin
    void verifyPass(int esId){
        examStaffBiz.verifyPass(esId);
    }

    @PostMapping("/exStaffNotPass")
    @ResponseBody
    @CrossOrigin
    void verifyNotPass(int esId){
        examStaffBiz.verifyNotPass(esId);
    }

    @PostMapping("/update")
    @ResponseBody
    @CrossOrigin
    void updateExStaff(@RequestBody ExamStaff examStaff){
        examStaffBiz.updateExStaff(examStaff);
    }

    @GetMapping("/getList")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<ExamStaff>>> queryBySchoolId(int schoolId){
        Map<String,List<ExamStaff>> result = new HashMap<>();
        result.put("ExamStaff",examStaffBiz.getExStaffListBySchoolId(schoolId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/eduExamine")
    @ResponseBody
    @CrossOrigin
    void updateEduExamine(int esId, int eduExamine){
        examStaffBiz.updateEduExamine(esId, eduExamine);
    }

    @GetMapping("/getQualifiedList")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<ExamStaff>>> getQualifiedExStaffList(int eduId){
        Map<String,List<ExamStaff>> result = new HashMap<>();
        result.put("ExamStaff",examStaffBiz.getQualifiedExStaffList(eduId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
