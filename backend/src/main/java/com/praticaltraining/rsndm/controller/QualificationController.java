package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.*;
import com.praticaltraining.rsndm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qualification")
public class QualificationController {
    @Autowired
    private InvigilatorGroupBiz invigilatorGroupBiz;
    @Autowired
    private InspectionTeamBiz inspectionTeamBiz;
    @Autowired
    private ExamStaffBiz examStaffBiz;
    @Autowired
    private SchoolBiz schoolBiz;
    @Autowired
    private InspectionTeamArrangementBiz inspectionTeamArrangementBiz;
    @Autowired
    private InvigilatorGroupArrangementBiz invigilatorGroupArrangementBiz;


    @RequestMapping(value = "/qualification",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    Qualification getQualification(int esId){
        Qualification qua=new Qualification();
        if(inspectionTeamBiz.getOneByEsId(esId)!=null){
            qua.setType(1);
            InspectionTeam it=inspectionTeamBiz.getOneByEsId(esId);
            qua.setGroupId(it.getInspectionTeamId());
            List<InspectionTeamArrangement> allArrange=inspectionTeamArrangementBiz.getAllInspectionTeamArrangementOfOneInspectionTeam(it.getInspectionTeamId());
            qua.setSchool(schoolBiz.getSchoolName(allArrange.get(0).getSchoolId()));
            qua.setName(examStaffBiz.getName(esId));
        }else if(invigilatorGroupBiz.getOneByEsId(esId)!=null){
            qua.setType(2);
            InvigilatorGroup it=invigilatorGroupBiz.getOneByEsId(esId);
            qua.setGroupId(it.getInvigilatorGroupId());
            List<InvigilatorGroupArrangement> allArrange=invigilatorGroupArrangementBiz.getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(it.getInvigilatorGroupId());
            qua.setSchool(schoolBiz.getSchoolName(allArrange.get(0).getSchoolId()));
            qua.setName(examStaffBiz.getName(esId));
        }
        return qua;
    }
}
