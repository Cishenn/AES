package com.praticaltraining.rsndm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AutoDeploy")
public class AutoDeployController {

    @RequestMapping(value = "/stepOne",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int autoDeployStepOne(){
        return 0;
    }

    @RequestMapping(value = "/stepTwo",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @CrossOrigin
    int autoDeployStepTwo(){
        return 0;
    }

}
