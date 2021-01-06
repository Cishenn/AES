package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.ExamStaff;
import com.praticaltraining.rsndm.entity.Floor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamStaffMapper {
    int register(ExamStaff examStaff);

    String getName(int esId);

    ExamStaff getOneExStaff(int esId);

    int verifyPass(int esId);

    int getEsIdByTele(String telephoneNumber);

    int verifyNotPass(int esId);

    int updateExStaff(ExamStaff examStaff);

    List<ExamStaff> getExStaffListBySchoolId(int schoolId);

    int updateEduExamine(int esId, int eduExamine);

    List<ExamStaff> getQualifiedExStaffList(int eduId);

    int clearArrange();

    List<ExamStaff> getExStaffListVerifying(int schoolId);

    List<ExamStaff> getExStaffListVerified(int schoolId);

    int updateSchoolExamine(int esId, int schoolExamine);
}
