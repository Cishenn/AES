package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.*;
import com.praticaltraining.rsndm.entity.ExamStaff;
import com.praticaltraining.rsndm.entity.InspectionTeam;
import com.praticaltraining.rsndm.entity.InvigilatorGroup;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.NumberOfCandidates;
import com.praticaltraining.rsndm.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
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

    @Autowired
    private InvigilatorGroupBiz invigilatorGroupBiz;
    @Autowired
    private InvigilatorGroupArrangementBiz invigilatorGroupArrangementBiz;
    @Autowired
    private InspectionTeamBiz inspectionTeamBiz;
    @Autowired
    private InspectionTeamArrangementBiz inspectionTeamArrangementBiz;


    @RequestMapping(value = "/stepOne",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    List<Integer> autoDeployStepOne(int eduId){
        //examinationSiteDeploy(eduId);
        List<Integer> res=invigilatorGroupOfStepOne(eduId);
        inspectionTeamOfStepOne(eduId);
        return res;
    }

    @RequestMapping(value = "/stepTwo",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int autoDeployStepTwo(){
        return 0;
    }

    List<Integer> invigilatorGroupOfStepOne(int eduId){
        List<Integer> allEduBe = enrollmentDepartmentBiz.eduIdAllBelong(eduId);
        for (int i = 0; i < allEduBe.size(); i++){
            examStaffBiz.clearArrange(allEduBe.get(i));
            invigilatorGroupBiz.clearIG(allEduBe.get(i));
            invigilatorGroupArrangementBiz.clearIGA(allEduBe.get(i));
            inspectionTeamBiz.clearIT(allEduBe.get(i));
            inspectionTeamArrangementBiz.clearITA(allEduBe.get(i));
        }

        if(isNiceSexNum(eduId).get(0)==5){
            return isNiceSexNum(eduId);
        }
        if(isNiceExamStaffNum(eduId).get(0)==6){
            return isNiceExamStaffNum(eduId);
        }
        if(isNiceExaminerNum(eduId).get(0)==7){
            return isNiceExaminerNum(eduId);
        }

        boolean flag=true;
        int cycleNum=0;

        while(flag&&cycleNum<10) {
            flag=false;
            List<Integer> allEduBelong = enrollmentDepartmentBiz.eduIdAllBelong(eduId);
            for (int i = 0; i < allEduBelong.size(); i++) {
                List<School> allSchoolBelongEdu = schoolBiz.getByEduId(allEduBelong.get(i));

                //清除曾经的安排
                examStaffBiz.clearArrange(allEduBelong.get(i));
                invigilatorGroupBiz.clearIG(allEduBelong.get(i));
                invigilatorGroupArrangementBiz.clearIGA(allEduBelong.get(i));
                inspectionTeamBiz.clearIT(allEduBelong.get(i));
                inspectionTeamArrangementBiz.clearITA(allEduBelong.get(i));

                for (int j = 0; j < allSchoolBelongEdu.size(); j++) {
                    List<ExamStaff> allEsInSch = examStaffBiz.getQualifiedExStaffBySchoolId(allSchoolBelongEdu.get(j).getSchoolId());
                    Random r = new Random();
                    for (int k = allEsInSch.size() - 1; k > 0; k--) {
                        Collections.swap(allEsInSch, k, r.nextInt(k + 1));
                    }
                    int count = examRoomBiz.roomsIsArangeOfOneSchool(allSchoolBelongEdu.get(j).getSchoolId());
                    if (allEsInSch.size() < count) {
                        for (int k = 0; k < allEsInSch.size(); k++) {
                            invigilatorGroupBiz.createInvigilatorGroup(allEsInSch.get(k).getEsId(), allSchoolBelongEdu.get(j).getEduId());
                            examStaffBiz.updateToArrange(allEsInSch.get(k).getEsId());
                        }
                    }
                    for (int k = 0; k < count; k++) {
                        invigilatorGroupBiz.createInvigilatorGroup(allEsInSch.get(k).getEsId(), allSchoolBelongEdu.get(j).getEduId());
                        examStaffBiz.updateToArrange(allEsInSch.get(k).getEsId());
                    }
                }

                List<InvigilatorGroup> newInvigilatorGroup = invigilatorGroupBiz.getAllByEduId(allEduBelong.get(i));
                for (int j = 0; j < newInvigilatorGroup.size(); j++) {
                    invigilatorGroupArrangementBiz.createInvigilatorGroupArr(newInvigilatorGroup.get(j).getInvigilatorGroupId(), examStaffBiz.getSchoolId(newInvigilatorGroup.get(j).getExaminerId()));
                }
            }

            //生成一个含有剩下所有未被排考的考务人员的随机序列
            List<ExamStaff> esNotInArrange = examStaffBiz.allNoArrangeGetByEduId(eduId);
            Random r1 = new Random();
            for (int j = esNotInArrange.size() - 1; j > 0; j--) {
                Collections.swap(esNotInArrange, j, r1.nextInt(j + 1));
            }

            List<InvigilatorGroup> newAllInvigilatorGroup = invigilatorGroupBiz.getAllByHighEduId(eduId);
            int indexOfEsNoArrange = 0;
            for (int j = 0; j < newAllInvigilatorGroup.size(); j = j) {
                if (esNotInArrange.get(indexOfEsNoArrange).getSchoolId() != examStaffBiz.getSchoolId(newAllInvigilatorGroup.get(j).getExaminerId())) {
                    invigilatorGroupBiz.setFirst(esNotInArrange.get(indexOfEsNoArrange).getEsId(), newAllInvigilatorGroup.get(j).getInvigilatorGroupId());
                    examStaffBiz.updateToArrange(esNotInArrange.get(indexOfEsNoArrange).getEsId());
                    newAllInvigilatorGroup.get(j).setFirstInvigilatorId(esNotInArrange.get(indexOfEsNoArrange).getEsId());
                    esNotInArrange.remove(indexOfEsNoArrange);
                    j++;
                    indexOfEsNoArrange--;
                }
                indexOfEsNoArrange++;
                if (indexOfEsNoArrange == esNotInArrange.size()) {
                    indexOfEsNoArrange = 0;
                }
            }
            int count = 0;
            for (int j = 0; j < newAllInvigilatorGroup.size(); j = j) {
                if (esNotInArrange.get(indexOfEsNoArrange).getSchoolId() != examStaffBiz.getSchoolId(newAllInvigilatorGroup.get(j).getExaminerId())
                        && esNotInArrange.get(indexOfEsNoArrange).getSchoolId() != examStaffBiz.getSchoolId(newAllInvigilatorGroup.get(j).getFirstInvigilatorId())) {
                    if (!esNotInArrange.get(indexOfEsNoArrange).getSex().equals(examStaffBiz.getOneExamStaff(newAllInvigilatorGroup.get(j).getExaminerId()).getSex())
                            || !esNotInArrange.get(indexOfEsNoArrange).getSex().equals(examStaffBiz.getOneExamStaff(newAllInvigilatorGroup.get(j).getFirstInvigilatorId()).getSex())
                            || !examStaffBiz.getOneExamStaff(newAllInvigilatorGroup.get(j).getExaminerId()).getSex().equals(examStaffBiz.getOneExamStaff(newAllInvigilatorGroup.get(j).getFirstInvigilatorId()).getSex())) {
                        invigilatorGroupBiz.setSecond(esNotInArrange.get(indexOfEsNoArrange).getEsId(), newAllInvigilatorGroup.get(j).getInvigilatorGroupId());
                        examStaffBiz.updateToArrange(esNotInArrange.get(indexOfEsNoArrange).getEsId());
                        esNotInArrange.remove(indexOfEsNoArrange);
                        j++;
                        indexOfEsNoArrange--;
                    }
                }
                indexOfEsNoArrange++;
                if (indexOfEsNoArrange == esNotInArrange.size()) {
                    indexOfEsNoArrange = 0;
                    count++;
                }
                if (count == 5) {
                    flag=true;
                    cycleNum++;
                    break;
                }
            }
        }

        List<Integer> res=new Vector<>();
        if(cycleNum==10){
            res.add(4);
        }
        res.add(-1);
        return res;
    }

    List<Integer> isNiceSexNum(int eduId) {
        int manCount = 0;
        int womanCount = 0;
        List<ExamStaff> allStaff = examStaffBiz.allNoArrangeGetByEduId(eduId);
        for (int i = 0; i < allStaff.size(); i++) {
            if (allStaff.get(i).getSex().equals("男")) {
                manCount++;
            } else {
                womanCount++;
            }
        }
        List<Integer> res=new Vector<>();
        if ((double)manCount / (double)womanCount > 2 || (double)womanCount / (double)manCount > 2) {
            res.add(5);
            res.add(manCount);
            res.add(womanCount);
        }
        res.add(-1);
        return res;
    }

    List<Integer> isNiceExamStaffNum(int eduId){
        List<ExamStaff> allStaff = examStaffBiz.allNoArrangeGetByEduId(eduId);
        List<Integer> allEduBelong =enrollmentDepartmentBiz.eduIdAllBelong(eduId);
        int roomSum=0;
        for(int i=0;i<allEduBelong.size();i++){
            List<School> allSchoolBelongEdu=schoolBiz.getByEduId(allEduBelong.get(i));
            for(int j=0;j<allSchoolBelongEdu.size();j++){
                int count=examRoomBiz.roomsIsArangeOfOneSchool(allSchoolBelongEdu.get(j).getSchoolId());
                roomSum+=count;
            }
        }
        List<Integer> res=new Vector<>();
        if(roomSum*3+examRoomBiz.floorsIsArange(eduId)>allStaff.size()){
            res.add(6);
            res.add(roomSum*3+examRoomBiz.floorsIsArange(eduId)*2);
            res.add(allStaff.size());
        }
        res.add(-1);
        return res;
    }

    List<Integer> isNiceExaminerNum(int eduId){
        List<Integer> allEduBelong =enrollmentDepartmentBiz.eduIdAllBelong(eduId);
        int examinerSum=0;
        int roomSum=0;
        for(int i=0;i<allEduBelong.size();i++){
            List<School> allSchoolBelongEdu=schoolBiz.getByEduId(allEduBelong.get(i));
            for(int j=0;j<allSchoolBelongEdu.size();j++){
                List<ExamStaff> allEsInSch=examStaffBiz.getQualifiedExStaffBySchoolId(allSchoolBelongEdu.get(j).getSchoolId());
                examinerSum+=allEsInSch.size();
                roomSum+=examRoomBiz.roomsIsArangeOfOneSchool(allSchoolBelongEdu.get(j).getSchoolId());
            }
        }
        List<Integer> res=new Vector<>();

        if(examinerSum<roomSum){
            res.add(7);
            res.add(examinerSum);
            res.add(roomSum);
        }
        res.add(-1);
        return res;
    }

    int inspectionTeamOfStepOne(int eduId){
        List<ExamStaff> allStaff = examStaffBiz.allNoArrangeGetByEduId(eduId);
        Random r = new Random();
        for (int i = allStaff.size() - 1; i > 0; i--) {
            Collections.swap(allStaff, i, r.nextInt(i + 1));
        }
        int indexOfAllStaff=0;
        List<Integer> allEduBelong = enrollmentDepartmentBiz.eduIdAllBelong(eduId);
        for (int i = 0; i < allEduBelong.size(); i++) {
            List<School> allSchoolBelongEdu = schoolBiz.getByEduId(allEduBelong.get(i));
            for (int j = 0; j < allSchoolBelongEdu.size(); j++){
                int count=examRoomBiz.floorsIsArangeOfOneSchool(allSchoolBelongEdu.get(j).getSchoolId());
                for(int k=0;k<count;k++){
                    inspectionTeamBiz.createInspectionTeam(allStaff.get(indexOfAllStaff).getEsId(),allStaff.get(indexOfAllStaff+1).getEsId(),allEduBelong.get(i));
                    indexOfAllStaff+=2;
                }
            }
            int indexOfITE=0;
            List<InspectionTeam> allInspectionTeamOfOneEduId=inspectionTeamBiz.getAllByEduId(allEduBelong.get(i));
            for (int j = 0; j < allSchoolBelongEdu.size(); j++){
                int count=examRoomBiz.floorsIsArangeOfOneSchool(allSchoolBelongEdu.get(j).getSchoolId());
                for(int k=0;k<count;k++){
                    inspectionTeamArrangementBiz.createInspectionTeamArrange(allInspectionTeamOfOneEduId.get(indexOfITE).getInspectionTeamId(),allSchoolBelongEdu.get(j).getSchoolId());
                    indexOfITE++;
                }
            }

        }


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
