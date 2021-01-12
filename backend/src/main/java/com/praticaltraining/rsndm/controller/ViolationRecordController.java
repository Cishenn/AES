package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.ViolationRecordBiz;
import com.praticaltraining.rsndm.entity.ViolationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/violationRecord")
public class ViolationRecordController {
    @Autowired
    private ViolationRecordBiz violationRecordBiz;

    @GetMapping("/violationRecords")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<ViolationRecord>>> queryAllByEsId(int esId){
        Map<String,List<ViolationRecord>> result = new HashMap<>();
        result.put("violationRecords",violationRecordBiz.getAllByEsId(esId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
