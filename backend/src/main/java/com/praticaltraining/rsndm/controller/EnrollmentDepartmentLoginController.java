package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentLoginBiz;
import com.praticaltraining.rsndm.entity.EnrollmentDepartmentLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EnrollmentDepartmentLogin")
public class EnrollmentDepartmentLoginController {
    @Autowired
    private EnrollmentDepartmentLoginBiz enrollmentDepartmentLoginBiz;

    @RequestMapping(value = "/login",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int login(String account,String password,int eduId){
        EnrollmentDepartmentLogin res=enrollmentDepartmentLoginBiz.getPwdEduId(account);
        if(res==null||!res.getPassword().equals(password)|| eduId!=res.getEduId()){
            return -1;
        }
        return res.getEduId();
    }


}
