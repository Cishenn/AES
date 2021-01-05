package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.ExamStaffBiz;
import com.praticaltraining.rsndm.biz.TestPersonnelLoginBiz;
import com.praticaltraining.rsndm.entity.ExamStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testPersonnelLogin")
public class TestPersonnelLoginController {
    @Autowired
    private TestPersonnelLoginBiz testPersonnelLoginBiz;
    @Autowired
    private ExamStaffBiz examStaffBiz;

    @RequestMapping(value = "/login",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int login(String telephoneNumber,String password){
        String res=testPersonnelLoginBiz.getPwd(telephoneNumber);
        if(res==null||!res.equals(password)){
            return -1;
        }
        return examStaffBiz.getEsIdByTele(telephoneNumber);
    }

    @RequestMapping(value = "/register",method= RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    void register(String telephoneNumber,String password,int schoolId){
        testPersonnelLoginBiz.register(telephoneNumber,password);
        ExamStaff examStaff=new ExamStaff();
        examStaff.setSchoolId(schoolId);
        examStaff.setTelephoneNumber(telephoneNumber);
        examStaffBiz.register(examStaff);
    }
}
