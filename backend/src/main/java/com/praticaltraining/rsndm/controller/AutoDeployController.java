package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.*;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.NumberOfCandidates;
import com.praticaltraining.rsndm.entity.School;
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
    @Autowired
    private SchoolBiz schoolBiz;

    boolean flag = false;

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
        int NumberOfScience = 0;
        int NumberOfArt = 0;
        int NumberOfExcellent = 0;
        int countExRoom = 0;
        List<Integer> eduList = enrollmentDepartmentBiz.eduIdAllBelong(eduId);
        for(int i = 0;i < eduList.size();i++){
            NumberOfCandidates NOC = numberOfCandidatesBiz.numberOfCandidatesGet(eduList.get(i));
            numOfScience += NOC.getNumOfScience();
            numOfArt += NOC.getNumOfArt();
            numOfExcellent += NOC.getNumOfExcellent();
            List<School> school = schoolBiz.getByEduId(eduList.get(i));
            for(int j = 0;j<school.size();j++){
                countExRoom += examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId()) - 3;
                schoolBiz.setType(school.get(j).getSchoolId(),"无");
                examRoomBiz.clearArrange(school.get(j).getSchoolId());
            }
        }
        if(numOfScience%30 != 0){
            NumberOfScience = numOfScience/30 + 1;
        }else{
            NumberOfScience = numOfScience/30;
        }
        if(numOfArt%30 != 0){
            NumberOfArt = numOfArt/30 + 1;
        }else{
            NumberOfArt = numOfArt/30;
        }
        if(numOfExcellent%30 != 0){
            NumberOfExcellent = numOfExcellent/30 + 1;
        }else{
            NumberOfExcellent = numOfExcellent/30;
        }
        if(countExRoom < (NumberOfScience + NumberOfArt + NumberOfExcellent)){
            flag = false;
            return -1;
        }
        big:for(int i = 0;i < eduList.size();i++){
            List<School> school = schoolBiz.getByEduId(eduList.get(i));
            for(int j = 0;j < school.size();j++){
                if(NumberOfScience>0){
                    if(examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId()) - 3 <= NumberOfScience){
                        NumberOfScience = NumberOfScience - (examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId()) - 3);
                        List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                        if(NumberOfScience < 3){
                            for(int k = 0;k<exRoom.size()-3+NumberOfScience;k++){
                                examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                            }
                            NumberOfScience = 0;
                            schoolBiz.setType(school.get(j).getSchoolId(),"理科考点");
                        }else{
                            for(int k = 0;k<exRoom.size()-3;k++){
                                examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                            }
                            schoolBiz.setType(school.get(j).getSchoolId(),"理科考点");
                        }
                    }else{
                        int temp = examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId())-NumberOfScience-3;
                        NumberOfScience = 0;
                        System.out.println(temp);
                        if(temp <= NumberOfArt){
                            NumberOfArt = NumberOfArt - temp;
                            List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                            if(NumberOfArt < 3){
                                for(int k = 0;k<exRoom.size()-3+NumberOfArt;k++){
                                    examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                                }
                                NumberOfArt = 0;
                                schoolBiz.setType(school.get(j).getSchoolId(),"文理科考点");
                            }else{
                                for(int k = 0;k<exRoom.size()-3;k++){
                                    examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                                }
                                schoolBiz.setType(school.get(j).getSchoolId(),"文理科考点");
                            }
                        }else{
                            int temp2 = temp - NumberOfArt;
                            NumberOfArt = 0;
                            if(temp2 <= NumberOfExcellent){
                                NumberOfExcellent = NumberOfExcellent - temp2;
                                List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                                if(NumberOfExcellent<3){
                                    for(int k = 0;k<exRoom.size()-3+NumberOfExcellent;k++){
                                        examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                                    }
                                    NumberOfExcellent = 0;
                                    schoolBiz.setType(school.get(j).getSchoolId(),"文理特科考点");
                                }else {
                                    for(int k = 0;k<exRoom.size()-3;k++){
                                        examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                                    }
                                    schoolBiz.setType(school.get(j).getSchoolId(),"文理特科考点");
                                }
                            }else{
                                int temp3 = temp2 - NumberOfExcellent;
                                List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                                for(int k = 0;k<exRoom.size()-3-temp3;k++){
                                    examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                                }
                                NumberOfExcellent = 0;
                                schoolBiz.setType(school.get(j).getSchoolId(),"文理特科考点");
                            }
                        }
                    }
                }else if(NumberOfArt>0){
                    if(examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId()) - 3 <= NumberOfArt){
                        NumberOfArt = NumberOfArt - (examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId()) - 3);
                        List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                        if(NumberOfArt < 3){
                            for(int k = 0;k<exRoom.size()-3+NumberOfArt;k++){
                                examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                            }
                            NumberOfArt = 0;
                            schoolBiz.setType(school.get(j).getSchoolId(),"文科考点");
                        }else{
                            for(int k = 0;k<exRoom.size()-3;k++){
                                examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                            }
                            schoolBiz.setType(school.get(j).getSchoolId(),"文科考点");
                        }
                    }else{
                        int temp = examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId())-NumberOfArt-3;
                        NumberOfArt = 0;
                        if(temp <= NumberOfExcellent) {
                            NumberOfExcellent = NumberOfExcellent - temp;
                            List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                            if (NumberOfExcellent < 3) {
                                for (int k = 0; k < exRoom.size() - 3 + NumberOfExcellent; k++) {
                                    examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                                }
                                NumberOfExcellent = 0;
                                schoolBiz.setType(school.get(j).getSchoolId(), "文特科考点");
                            } else {
                                for (int k = 0; k < exRoom.size() - 3; k++) {
                                    examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                                }
                                schoolBiz.setType(school.get(j).getSchoolId(), "文特科考点");
                            }
                        }else{
                            int temp2 = temp - NumberOfExcellent;
                            List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                            for(int k = 0;k<exRoom.size()-3-temp2;k++){
                                examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                            }
                            NumberOfExcellent = 0;
                            schoolBiz.setType(school.get(j).getSchoolId(),"文特科考点");
                        }
                    }
                }else if(NumberOfExcellent>0){
                    if(examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId()) - 3 <= NumberOfExcellent){
                        NumberOfExcellent = NumberOfExcellent - (examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId()) - 3);
                        List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                        if(NumberOfExcellent < 3){
                            for(int k = 0;k<exRoom.size()-3+NumberOfExcellent;k++){
                                examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                            }
                            NumberOfExcellent = 0;
                            schoolBiz.setType(school.get(j).getSchoolId(),"特科考点");
                        }else{
                            for(int k = 0;k<exRoom.size()-3;k++){
                                examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                            }
                            schoolBiz.setType(school.get(j).getSchoolId(),"特科考点");
                        }
                    }else{
                        int temp = examRoomBiz.getNumberOfExRoom(school.get(j).getSchoolId())-NumberOfExcellent-3;
                        List<ExamRoom> exRoom = examRoomBiz.getAllExamRoom(school.get(j).getSchoolId());
                        for(int k = 0;k<exRoom.size()-3-temp;k++){
                            examRoomBiz.setArrange(exRoom.get(k).getExRoomId());
                        }
                        NumberOfExcellent = 0;
                        schoolBiz.setType(school.get(j).getSchoolId(),"特科考点");
                    }
                }else{
                    break big;
                }
            }
        }
        return 0;
    }

}
