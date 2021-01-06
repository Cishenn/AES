package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentBiz;
import com.praticaltraining.rsndm.biz.ExamRoomBiz;
import com.praticaltraining.rsndm.biz.ExamStaffBiz;
import com.praticaltraining.rsndm.biz.NumberOfCandidatesBiz;
import com.praticaltraining.rsndm.entity.NumberOfCandidates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/AutoDeploy")
public class AutoDeployController {
    @Autowired
    private ExamStaffBiz examStaffBiz;
    @Autowired
    private ExamRoomBiz examRoomBiz;
    @Autowired
    private NumberOfCandidatesBiz numberOfCandidatesBiz;
    @Autowired
    private EnrollmentDepartmentBiz enrollmentDepartmentBiz;

    @RequestMapping(value = "/stepOne",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int autoDeployStepOne(int eduId){
        examinationSiteDeploy(eduId);
        return 0;
    }

    @RequestMapping(value = "/stepTwo",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int autoDeployStepTwo(){
        return 0;
    }

    int examinationSiteDeploy(int eduId){
        int numOfScience = 0;
        int numOfArt = 0;
        int numOfExcellent = 0;
        List<Integer> eduList = enrollmentDepartmentBiz.eduIdAllBelong(eduId);
        for(int i = 0;i < eduList.size();i++){
            NumberOfCandidates NOC = numberOfCandidatesBiz.numberOfCandidatesGet(eduList.get(i));
            numOfScience += NOC.getNumOfScience();
            numOfArt += NOC.getNumOfArt();
            numOfExcellent += NOC.getNumOfExcellent();
        }

        return 0;
    }

}
