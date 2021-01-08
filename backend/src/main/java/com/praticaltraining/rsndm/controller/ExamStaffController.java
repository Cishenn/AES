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

    @GetMapping("/getVerifying")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<ExamStaff>>> getExStaffListVerifying(int schoolId){
        Map<String,List<ExamStaff>> result = new HashMap<>();
        result.put("ExamStaff",examStaffBiz.getExStaffListVerifying(schoolId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getVerified")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<ExamStaff>>> getExStaffListVerified(int schoolId){
        Map<String,List<ExamStaff>> result = new HashMap<>();
        result.put("ExamStaff",examStaffBiz.getExStaffListVerified(schoolId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/schoolExamine")
    @ResponseBody
    @CrossOrigin
    void updateSchoolExamine(int esId, int schoolExamine){
        examStaffBiz.updateSchoolExamine(esId, schoolExamine);
    }

    @PostMapping("/finalRejection")
    @ResponseBody
    @CrossOrigin
    void updateRejection(int esId, String finalRejection){
        examStaffBiz.updateRejection(esId, finalRejection);
    }

    @PostMapping("/feedback")
    @ResponseBody
    @CrossOrigin
    void addStateMessage(int esId, String stateMessage){
        examStaffBiz.addStateMessage(esId, stateMessage);
    }
}
