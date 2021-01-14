package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.*;
import com.praticaltraining.rsndm.entity.*;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

@RestController
@RequestMapping("/examStaff_ig_igA")
public class examStaff_ig_igAController {
    @Autowired
    private InvigilatorGroupArrangementBiz invigilatorGroupArrangementBiz;
    @Autowired
    private InvigilatorGroupBiz invigilatorGroupBiz;
    @Autowired
    private ExamStaffBiz examStaffBiz;
    @Autowired
    private FloorBiz floorBiz;
    @Autowired
    private SchoolBiz schoolBiz;
    @Autowired
    private ExamRoomBiz examRoomBiz;

    @GetMapping("/InvigilatorGroupAndPos")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<examStaff_ig_igA>>> getInspectionTeamsAndPos(int schoolId){
        Map<String,List<examStaff_ig_igA>> result = new HashMap<>();
        List<InvigilatorGroup> allInvigilatorGroup=invigilatorGroupBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        List<examStaff_ig_igA> InvigilatorGroupAndPos=new Vector<>();
        for(int i=0;i<allInvigilatorGroup.size();i++){
            List<InvigilatorGroupArrangement> allArrange=invigilatorGroupArrangementBiz.getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(allInvigilatorGroup.get(i).getInvigilatorGroupId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){
                    examStaff_ig_igA examStaff_ig_igARes=new examStaff_ig_igA();
                    examStaff_ig_igARes.setSessions(allArrange.get(j).getSessions());
                    examStaff_ig_igARes.setInvigilatorGroupId(allInvigilatorGroup.get(i).getInvigilatorGroupId());

                    examStaff_ig_igARes.setFirstInvigilatorName(examStaffBiz.getName(allInvigilatorGroup.get(i).getFirstInvigilatorId()));
                    examStaff_ig_igARes.setSecondInvigilatorName(examStaffBiz.getName(allInvigilatorGroup.get(i).getSecondInvigilatorId()));
                    examStaff_ig_igARes.setExaminerName(examStaffBiz.getName(allInvigilatorGroup.get(i).getExaminerId()));

                    ExamRoom examRoom=examRoomBiz.getOneExRoom(allArrange.get(j).getExRoomId());
                    examStaff_ig_igARes.setRoomNum(examRoom.getRoomNum());

                    Floor floor=floorBiz.getOneFloor(examRoom.getFloorId());
                    examStaff_ig_igARes.setFloorStep(floor.getFloorStep());
                    examStaff_ig_igARes.setBuilding(floor.getBuilding());

                    examStaff_ig_igARes.setSchoolName(schoolBiz.getSchoolName(allArrange.get(j).getSchoolId()));

                    InvigilatorGroupAndPos.add(examStaff_ig_igARes);
                }
            }

        }
        result.put("invigilatorGroupAndPos",InvigilatorGroupAndPos);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/InvigilatorGroup")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<examStaff_ig_igA>>> getInvigilatorGroup(int schoolId){
        Map<String,List<examStaff_ig_igA>> result = new HashMap<>();
        List<InvigilatorGroup> allInvigilatorGroup=invigilatorGroupBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        List<examStaff_ig_igA> InvigilatorGroupAndPos=new Vector<>();
        for(int i=0;i<allInvigilatorGroup.size();i++){
            List<InvigilatorGroupArrangement> allArrange=invigilatorGroupArrangementBiz.getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(allInvigilatorGroup.get(i).getInvigilatorGroupId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){
                    examStaff_ig_igA examStaff_ig_igARes=new examStaff_ig_igA();
                    examStaff_ig_igARes.setInvigilatorGroupId(allInvigilatorGroup.get(i).getInvigilatorGroupId());

                    examStaff_ig_igARes.setFirstInvigilatorName(examStaffBiz.getName(allInvigilatorGroup.get(i).getFirstInvigilatorId()));
                    examStaff_ig_igARes.setSecondInvigilatorName(examStaffBiz.getName(allInvigilatorGroup.get(i).getSecondInvigilatorId()));
                    examStaff_ig_igARes.setExaminerName(examStaffBiz.getName(allInvigilatorGroup.get(i).getExaminerId()));

                    examStaff_ig_igARes.setSchoolName(schoolBiz.getSchoolName(allArrange.get(j).getSchoolId()));

                    InvigilatorGroupAndPos.add(examStaff_ig_igARes);
                }
            }

        }
        result.put("invigilatorGroup",InvigilatorGroupAndPos);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/InvigilatorGroupExcel")
    @ResponseBody
    @CrossOrigin
    String getAllInSchoolOfStepOneExcel(int schoolId) throws IOException, WriteException {
        File xlsFile = new File("D://"+schoolBiz.getSchoolName(schoolId)+"监考组(初步排考数据).xls");
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);

        //添加表头
        sheet.addCell(new Label(0,0,"监考组编号"));
        sheet.addCell(new Label(1,0,"考点名称"));
        sheet.addCell(new Label(2,0,"主考人员姓名"));
        sheet.addCell(new Label(3,0,"监考1姓名"));
        sheet.addCell(new Label(4,0,"监考2姓名"));

        //导入数据
        List<InvigilatorGroup> allInvigilatorGroup=invigilatorGroupBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        int beginIndex=1;
        for(int i=0;i<allInvigilatorGroup.size();i++){
            List<InvigilatorGroupArrangement> allArrange=invigilatorGroupArrangementBiz.getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(allInvigilatorGroup.get(i).getInvigilatorGroupId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){
                    sheet.addCell(new Label(0,beginIndex,String.valueOf(allInvigilatorGroup.get(i).getInvigilatorGroupId())));
                    sheet.addCell(new Label(1,beginIndex,schoolBiz.getSchoolName(allArrange.get(j).getSchoolId())));
                    sheet.addCell(new Label(2,beginIndex,examStaffBiz.getName(allInvigilatorGroup.get(i).getExaminerId())));
                    sheet.addCell(new Label(3,beginIndex,examStaffBiz.getName(allInvigilatorGroup.get(i).getFirstInvigilatorId())));
                    sheet.addCell(new Label(4,beginIndex,examStaffBiz.getName(allInvigilatorGroup.get(i).getSecondInvigilatorId())));
                    beginIndex++;
                }
            }
        }
        workbook.write();
        workbook.close();
        return schoolBiz.getSchoolName(schoolId)+"监考组(初步排考数据).xls";
    }

    @GetMapping("/InvigilatorGroupAndPosExcel")
    @ResponseBody
    @CrossOrigin
    String getAllInSchoolOfStepTwoExcel(int schoolId) throws IOException, WriteException {

        File xlsFile = new File("D://"+schoolBiz.getSchoolName(schoolId)+"监考组(完成排考数据).xls");
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);

        //添加表头
        sheet.addCell(new Label(0,0,"监考组编号"));
        sheet.addCell(new Label(1,0,"考点名称"));
        sheet.addCell(new Label(2,0,"楼名称"));
        sheet.addCell(new Label(3,0,"所在楼层"));
        sheet.addCell(new Label(4,0,"房间号"));
        sheet.addCell(new Label(5,0,"主考人员姓名"));
        sheet.addCell(new Label(6,0,"监考1姓名"));
        sheet.addCell(new Label(7,0,"监考2姓名"));
        sheet.addCell(new Label(8,0,"监考科目"));

        //导入数据
        List<InvigilatorGroup> allInvigilatorGroup=invigilatorGroupBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        int beginIndex=1;
        for(int i=0;i<allInvigilatorGroup.size();i++){
            List<InvigilatorGroupArrangement> allArrange=invigilatorGroupArrangementBiz.getAllInvigilatorGroupArrangementOfOneInvigilatorGroup(allInvigilatorGroup.get(i).getInvigilatorGroupId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){

                    sheet.addCell(new Label(0,beginIndex,String.valueOf(allInvigilatorGroup.get(i).getInvigilatorGroupId())));
                    sheet.addCell(new Label(1,beginIndex,schoolBiz.getSchoolName(allArrange.get(j).getSchoolId())));

                    ExamRoom examRoom=examRoomBiz.getOneExRoom(allArrange.get(j).getExRoomId());
                    Floor floor=floorBiz.getOneFloor(examRoom.getFloorId());
                    sheet.addCell(new Label(2,beginIndex,floor.getBuilding()));
                    sheet.addCell(new Label(3,beginIndex,String.valueOf(floor.getFloorStep())));
                    sheet.addCell(new Label(4,beginIndex,examRoom.getRoomNum()));

                    sheet.addCell(new Label(5,beginIndex,examStaffBiz.getName(allInvigilatorGroup.get(i).getExaminerId())));
                    sheet.addCell(new Label(6,beginIndex,examStaffBiz.getName(allInvigilatorGroup.get(i).getFirstInvigilatorId())));
                    sheet.addCell(new Label(7,beginIndex,examStaffBiz.getName(allInvigilatorGroup.get(i).getSecondInvigilatorId())));

                    if(allArrange.get(j).getSessions()==1){
                        sheet.addCell(new Label(8,beginIndex,"语文"));
                    }else if(allArrange.get(j).getSessions()==2){
                        sheet.addCell(new Label(8,beginIndex,"数学"));
                    }else if(allArrange.get(j).getSessions()==3){
                        sheet.addCell(new Label(8,beginIndex,"英语"));
                    }else if(allArrange.get(j).getSessions()==4){
                        sheet.addCell(new Label(8,beginIndex,"综合"));
                    }
                    beginIndex++;
                }
            }
        }
        workbook.write();
        workbook.close();
        return schoolBiz.getSchoolName(schoolId)+"监考组(完成排考数据).xls";
    }


}
