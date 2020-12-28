package com.priticaltraining.rsndm.controller;

import com.priticaltraining.rsndm.biz.testBiz;
import com.priticaltraining.rsndm.entity.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class testController {
    @Autowired
    private testBiz testBiz;

    @GetMapping("/t")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<test>>> getTest() {
        Map<String, List<test>> result = new HashMap<>();
        result.put("test", testBiz.getTest());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
