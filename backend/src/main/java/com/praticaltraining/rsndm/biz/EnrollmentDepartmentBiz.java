package com.praticaltraining.rsndm.biz;

import com.praticaltraining.rsndm.entity.EnrollmentDepartment;

import java.util.List;

public interface EnrollmentDepartmentBiz {
    List<Integer> eduIdAllBelong(int eduId);

    void setArrangeLevel(int eduId,int level);

    int getArrangeLevel(int eduId);
    
    EnrollmentDepartment getOne(int eduId);

}
