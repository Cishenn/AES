package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentBiz;
import com.praticaltraining.rsndm.entity.EnrollmentDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollmentDepartment")
public class EnrollmentDepartmentController {
    @Autowired
    private EnrollmentDepartmentBiz enrollmentDepartmentBiz;

    @RequestMapping(value = "/getOne",method= RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @CrossOrigin
    EnrollmentDepartment getOne(int eduId){
        return enrollmentDepartmentBiz.getOne(eduId);
    }
}
