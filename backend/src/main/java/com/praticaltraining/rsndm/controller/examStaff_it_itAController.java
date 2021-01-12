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
@RequestMapping("/examStaff_it_itA")
public class examStaff_it_itAController {
    @Autowired
    private InspectionTeamArrangementBiz inspectionTeamArrangementBiz;
    @Autowired
    private InspectionTeamBiz inspectionTeamBiz;
    @Autowired
    private ExamStaffBiz examStaffBiz;
    @Autowired
    private FloorBiz floorBiz;
    @Autowired
    private SchoolBiz schoolBiz;

    @GetMapping("/inspectionTeamsAndPos")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<examStaff_it_itA>>> getInspectionTeamsAndPos(int schoolId){
        Map<String,List<examStaff_it_itA>> result = new HashMap<>();
        List<InspectionTeam> allInspectionTeam=inspectionTeamBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        List<examStaff_it_itA> inspectionTeamsAndPos=new Vector<>();
        for(int i=0;i<allInspectionTeam.size();i++){
            List<InspectionTeamArrangement> allArrange=inspectionTeamArrangementBiz.getAllInspectionTeamArrangementOfOneInspectionTeam(allInspectionTeam.get(i).getInspectionTeamId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){
                    examStaff_it_itA examStaff_it_itARes=new examStaff_it_itA();
                    examStaff_it_itARes.setSessions(allArrange.get(j).getSessions());
                    examStaff_it_itARes.setInspectionTeamId(allInspectionTeam.get(i).getInspectionTeamId());

                    examStaff_it_itARes.setFirstInspectionPersonName(examStaffBiz.getName(allInspectionTeam.get(i).getFirstInspectionPersonId()));
                    examStaff_it_itARes.setSecondInspectionPersonName(examStaffBiz.getName(allInspectionTeam.get(i).getSecondInspectionPersonId()));

                    Floor floor=floorBiz.getOneFloor(allArrange.get(j).getFloorId());
                    examStaff_it_itARes.setFloorStep(floor.getFloorStep());
                    examStaff_it_itARes.setBuilding(floor.getBuilding());

                    examStaff_it_itARes.setSchoolName(schoolBiz.getSchoolName(allArrange.get(j).getSchoolId()));

                    inspectionTeamsAndPos.add(examStaff_it_itARes);
                }
            }

        }
        result.put("inspectionTeamsAndPos",inspectionTeamsAndPos);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/inspectionTeams")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<examStaff_it_itA>>> getInspection(int schoolId){
        Map<String,List<examStaff_it_itA>> result = new HashMap<>();
        List<InspectionTeam> allInspectionTeam=inspectionTeamBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        List<examStaff_it_itA> inspectionTeamsAndPos=new Vector<>();
        for(int i=0;i<allInspectionTeam.size();i++){
            List<InspectionTeamArrangement> allArrange=inspectionTeamArrangementBiz.getAllInspectionTeamArrangementOfOneInspectionTeam(allInspectionTeam.get(i).getInspectionTeamId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId&&allArrange.get(j).getSessions()==1){
                    examStaff_it_itA examStaff_it_itARes=new examStaff_it_itA();
                    examStaff_it_itARes.setInspectionTeamId(allInspectionTeam.get(i).getInspectionTeamId());

                    examStaff_it_itARes.setFirstInspectionPersonName(examStaffBiz.getName(allInspectionTeam.get(i).getFirstInspectionPersonId()));
                    examStaff_it_itARes.setSecondInspectionPersonName(examStaffBiz.getName(allInspectionTeam.get(i).getSecondInspectionPersonId()));

                    examStaff_it_itARes.setSchoolName(schoolBiz.getSchoolName(allArrange.get(j).getSchoolId()));

                    inspectionTeamsAndPos.add(examStaff_it_itARes);
                }
            }
        }
        result.put("inspectionTeams",inspectionTeamsAndPos);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
