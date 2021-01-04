package com.praticaltraining.rsndm.controller;

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
    private ExamStaff examStaff;
    @GetMapping("/exStaff/exStaffId")
    @ResponseBody
    @CrossOrigin
    ExamStaff getOneExamStaff(int esId){
        return examStaffBiz.getOneExStaff(esId);
    }
}
