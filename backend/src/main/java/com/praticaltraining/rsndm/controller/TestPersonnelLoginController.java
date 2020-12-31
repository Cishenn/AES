package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.TestPersonnelLoginBiz;
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

    @RequestMapping(value = "/login",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int login(String telephoneNumber,String password){
        String res=testPersonnelLoginBiz.getPwd(telephoneNumber);
        if(res==null||!res.equals(password)){
            return 0;
        }
        return 1;
    }
}
