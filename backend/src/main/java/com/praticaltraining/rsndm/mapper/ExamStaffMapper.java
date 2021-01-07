package com.praticaltraining.rsndm.mapper;

import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.ExamStaff;
import com.praticaltraining.rsndm.entity.Floor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamStaffMapper {
    int register(ExamStaff examStaff);

    String getName(int esId);

    ExamStaff getOneExStaff(int esId);

    int verifyPass(int esId);

    int getEsIdByTele(String telephoneNumber);

    int verifyNotPass(int esId);

    int updateExStaff(@Param("examStaff") ExamStaff examStaff);

    List<ExamStaff> getExStaffListBySchoolId(int schoolId);

    int updateEduExamine(int esId, int eduExamine);

    List<ExamStaff> getQualifiedExStaffList(int eduId);

    int clearArrange(int eduId);

    int updateToArrange(int esId);

    List<ExamStaff> allNoArrangeGetByEduId(int eduId);

    int getSchoolId(int esId);

    List<ExamStaff> getQualifiedExStaffBySchoolId(int schoolId);

    List<ExamStaff> getExStaffListVerifying(int schoolId);

    List<ExamStaff> getExStaffListVerified(int schoolId);

    int updateSchoolExamine(int esId, int schoolExamine);

    int updateRejection(int esId, String finalRejection);
}
