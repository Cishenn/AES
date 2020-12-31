package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentLoginBiz;
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
    int login(String account,String password){
        String res=enrollmentDepartmentLoginBiz.getPwd(account);
        if(res==null||!res.equals(password)){
            return 0;
        }
        return 1;
    }


}
