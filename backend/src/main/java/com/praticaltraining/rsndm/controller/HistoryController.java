package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.HistoryBiz;
import com.praticaltraining.rsndm.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryBiz historyBiz;

    @GetMapping("/oneHistory")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<History>>> getEsHistory(int esId){
        Map<String,List<History>> result = new HashMap<>();
        result.put("History",historyBiz.getEsHistory(esId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
