package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.NumberOfCandidatesBiz;
import com.praticaltraining.rsndm.entity.NumberOfCandidates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/numberOfCandidates")
public class NumberOfCandidatesController {
    @Autowired
    private NumberOfCandidatesBiz numberOfCandidatesBiz;

    @RequestMapping(value = "/Candidates",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @CrossOrigin
    NumberOfCandidates numberOfCandidatesGet(int eduId){
        return numberOfCandidatesBiz.numberOfCandidatesGet(eduId);
    }

    @RequestMapping(value = "/Candidates",method= RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    void NumberOfCandidatesUpdate(@RequestBody NumberOfCandidates numberOfCandidates){
        numberOfCandidatesBiz.NumberOfCandidatesUpdate(numberOfCandidates);
    }

    @GetMapping("/num2/eduId")
    @ResponseBody
    @CrossOrigin
    NumberOfCandidates edu2GetNum(int eduId){
        return numberOfCandidatesBiz.edu2GetNum(eduId);
    }

    @GetMapping("/num3/eduId")
    @ResponseBody
    @CrossOrigin
    NumberOfCandidates edu3GetNum(int eduId){
        return numberOfCandidatesBiz.edu3GetNum(eduId);
    }

}
