package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.*;
import com.praticaltraining.rsndm.entity.ExamStaff;
import com.praticaltraining.rsndm.entity.InspectionTeam;
import com.praticaltraining.rsndm.entity.InvigilatorGroup;
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

@RestController
@RequestMapping("/AutoDeploy")
public class AutoDeployController {

    @Autowired
    private EnrollmentDepartmentBiz enrollmentDepartmentBiz;
    @Autowired
    private SchoolBiz schoolBiz;
    @Autowired
    private ExamStaffBiz examStaffBiz;
    @Autowired
    private InvigilatorGroupBiz invigilatorGroupBiz;
    @Autowired
    private InvigilatorGroupArrangementBiz invigilatorGroupArrangementBiz;
    @Autowired
    private InspectionTeamBiz inspectionTeamBiz;
    @Autowired
    private InspectionTeamArrangementBiz inspectionTeamArrangementBiz;
    @Autowired
    private ExamRoomBiz examRoomBiz;

    @RequestMapping(value = "/stepOne",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    List<Integer> autoDeployStepOne(int eduId){
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
}
