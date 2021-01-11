package com.praticaltraining.rsndm.biz;

import java.util.List;

public interface EnrollmentDepartmentBiz {
    List<Integer> eduIdAllBelong(int eduId);

    void setArrangeLevel(int eduId,int level);

    int getArrangeLevel(int eduId);
}
