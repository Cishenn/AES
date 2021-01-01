package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.SchoolAdminLoginBiz;
import com.praticaltraining.rsndm.entity.SchoolAdminLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SchoolAdminLogin")
public class SchoolAdminLoginController {
    @Autowired
    private SchoolAdminLoginBiz schoolAdminLoginBiz;

    @RequestMapping(value = "/login",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int login(String account,String password,int schoolId){
        SchoolAdminLogin res=schoolAdminLoginBiz.getPwdSchoolId(account);
        if(res==null||!res.getPassword().equals(password)||res.getSchoolId()!=schoolId){
            return -1;
        }
        return res.getSchoolId();
    }
}
