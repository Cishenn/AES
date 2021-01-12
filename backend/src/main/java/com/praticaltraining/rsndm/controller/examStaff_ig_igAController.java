package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.*;
import com.praticaltraining.rsndm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@RestController
@RequestMapping("/examStaff_ig_igA")
public class examStaff_ig_igAController {
    @Autowired
    private InvigilatorGroupArrangementBiz invigilatorGroupArrangementBiz;
    @Autowired
    private InvigilatorGroupBiz invigilatorGroupBiz;
    @Autowired
    private ExamStaffBiz examStaffBiz;
    @Autowired
    private FloorBiz floorBiz;
    @Autowired
    private SchoolBiz schoolBiz;
    @Autowired
    private ExamRoomBiz examRoomBiz;

    @GetMapping("/InvigilatorGroupAndPos")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<examStaff_ig_igA>>> getInspectionTeamsAndPos(int schoolId){
        Map<String,List<examStaff_ig_igA>> result = new HashMap<>();
        List<InvigilatorGroup> allInvigilatorGroup=invigilatorGroupBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        List<examStaff_ig_igA> InvigilatorGroupAndPos=new Vector<>();
        for(int i=0;i<allInvigilatorGroup.size();i++){
            List<InvigilatorGroupArrangement> allArrange=invigilatorGroupArrangementBiz.getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(allInvigilatorGroup.get(i).getInvigilatorGroupId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){
                    examStaff_ig_igA examStaff_ig_igARes=new examStaff_ig_igA();
                    examStaff_ig_igARes.setSessions(allArrange.get(j).getSessions());
                    examStaff_ig_igARes.setInvigilatorGroupId(allInvigilatorGroup.get(i).getInvigilatorGroupId());

                    examStaff_ig_igARes.setFirstInvigilatorName(examStaffBiz.getName(allInvigilatorGroup.get(i).getFirstInvigilatorId()));
                    examStaff_ig_igARes.setSecondInvigilatorName(examStaffBiz.getName(allInvigilatorGroup.get(i).getSecondInvigilatorId()));
                    examStaff_ig_igARes.setExaminerName(examStaffBiz.getName(allInvigilatorGroup.get(i).getExaminerId()));

                    ExamRoom examRoom=examRoomBiz.getOneExRoom(allArrange.get(j).getExRoomId());
                    examStaff_ig_igARes.setRoomNum(examRoom.getRoomNum());

                    Floor floor=floorBiz.getOneFloor(examRoom.getFloorId());
                    examStaff_ig_igARes.setFloorStep(floor.getFloorStep());
                    examStaff_ig_igARes.setBuilding(floor.getBuilding());

                    examStaff_ig_igARes.setSchoolName(schoolBiz.getSchoolName(allArrange.get(j).getSchoolId()));

                    InvigilatorGroupAndPos.add(examStaff_ig_igARes);
                }
            }

        }
        result.put("invigilatorGroupAndPos",InvigilatorGroupAndPos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/InvigilatorGroup")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<examStaff_ig_igA>>> getInspectionTeams(int schoolId){
        Map<String,List<examStaff_ig_igA>> result = new HashMap<>();
        List<InvigilatorGroup> allInvigilatorGroup=invigilatorGroupBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        List<examStaff_ig_igA> InvigilatorGroupAndPos=new Vector<>();
        for(int i=0;i<allInvigilatorGroup.size();i++){
            List<InvigilatorGroupArrangement> allArrange=invigilatorGroupArrangementBiz.getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(allInvigilatorGroup.get(i).getInvigilatorGroupId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){
                    examStaff_ig_igA examStaff_ig_igARes=new examStaff_ig_igA();
                    examStaff_ig_igARes.setInvigilatorGroupId(allInvigilatorGroup.get(i).getInvigilatorGroupId());

                    examStaff_ig_igARes.setFirstInvigilatorName(examStaffBiz.getName(allInvigilatorGroup.get(i).getFirstInvigilatorId()));
                    examStaff_ig_igARes.setSecondInvigilatorName(examStaffBiz.getName(allInvigilatorGroup.get(i).getSecondInvigilatorId()));
                    examStaff_ig_igARes.setExaminerName(examStaffBiz.getName(allInvigilatorGroup.get(i).getExaminerId()));

                    examStaff_ig_igARes.setSchoolName(schoolBiz.getSchoolName(allArrange.get(j).getSchoolId()));

                    InvigilatorGroupAndPos.add(examStaff_ig_igARes);
                }
            }

        }
        result.put("invigilatorGroup",InvigilatorGroupAndPos);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
