package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentBiz;
import com.praticaltraining.rsndm.biz.SchoolBiz;
import com.praticaltraining.rsndm.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolBiz schoolBiz;
    @Autowired
    private EnrollmentDepartmentBiz enrollmentDepartmentBiz;

    @GetMapping("/school")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<School>>> queryAll(){
        Map<String,List<School>> result = new HashMap<>();
        result.put("School",schoolBiz.getAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/school/eduId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<School>>> queryByEduId(int eduId){
        Map<String,List<School>> result = new HashMap<>();
        result.put("School",schoolBiz.getByEduId(eduId));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/exRoomExamine/schoolId_exRoomExamine")
    @ResponseBody
    @CrossOrigin
    void examineExRoom(int schoolId,int exRoomExamine){
        schoolBiz.examineExRoom(schoolId,exRoomExamine);
    }

    @PostMapping("/exRoomExamine/schoolId")
    @ResponseBody
    @CrossOrigin
    void submitExRoom(int schoolId){
        schoolBiz.submitExRoom(schoolId);
    }

    @GetMapping("/schoolName/schoolId")
    @ResponseBody
    @CrossOrigin
    String getSchoolName(int schoolId){
        return schoolBiz.getSchoolName(schoolId);
    }

    @GetMapping("/exRoomExamine/schoolId")
    @ResponseBody
    @CrossOrigin
    int getState(int schoolId){
        return schoolBiz.getState(schoolId);
    }

    @GetMapping("/schools/eduId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<School>>> getAllBelongSchool(int eduId){
        Map<String,List<School>> result = new HashMap<>();
        List<School> res=schoolBiz.getByEduId(eduId);
        int level=enrollmentDepartmentBiz.getOne(eduId).getEduLevel();
        if(level>1&&enrollmentDepartmentBiz.eduIdAllBelong(eduId)!=null){
            List<Integer> eduIdList=enrollmentDepartmentBiz.eduIdAllBelong(eduId);
            for(int i=0;i<eduIdList.size();i++){
                res.addAll(schoolBiz.getByEduId(eduIdList.get(i)));
                level=enrollmentDepartmentBiz.getOne(eduIdList.get(i)).getEduLevel();
                if(level>1&&enrollmentDepartmentBiz.eduIdAllBelong(eduIdList.get(i))!=null){
                    List<Integer> eduIdListLow=enrollmentDepartmentBiz.eduIdAllBelong(eduIdList.get(i));
                    for(int j=0;j<eduIdListLow.size();j++){
                        res.addAll(schoolBiz.getByEduId(eduIdListLow.get(j)));
                    }
                }
            }
        }
        result.put("School",res);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/arrangedSchools/eduId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<School>>> getAllArrangedSchool(int eduId){
        Map<String,List<School>> result = new HashMap<>();
        List<School> res=schoolBiz.getArrangedSchool(eduId);
        int level=enrollmentDepartmentBiz.getOne(eduId).getEduLevel();
        if(level>1&&enrollmentDepartmentBiz.eduIdAllBelong(eduId)!=null){
            List<Integer> eduIdList=enrollmentDepartmentBiz.eduIdAllBelong(eduId);
            for(int i=0;i<eduIdList.size();i++){
                res.addAll(schoolBiz.getArrangedSchool(eduIdList.get(i)));
                level=enrollmentDepartmentBiz.getOne(eduIdList.get(i)).getEduLevel();
                if(level>1&&enrollmentDepartmentBiz.eduIdAllBelong(eduIdList.get(i))!=null){
                    List<Integer> eduIdListLow=enrollmentDepartmentBiz.eduIdAllBelong(eduIdList.get(i));
                    for(int j=0;j<eduIdListLow.size();j++){
                        res.addAll(schoolBiz.getArrangedSchool(eduIdListLow.get(j)));
                    }
                }
            }
        }
        result.put("School",res);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
