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
@RequestMapping("/examStaff_it_itA")
public class examStaff_it_itAController {
    @Autowired
    private InspectionTeamArrangementBiz inspectionTeamArrangementBiz;
    @Autowired
    private InspectionTeamBiz inspectionTeamBiz;
    @Autowired
    private ExamStaffBiz examStaffBiz;
    @Autowired
    private FloorBiz floorBiz;
    @Autowired
    private SchoolBiz schoolBiz;

    @GetMapping("/inspectionTeamsAndPos")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<examStaff_it_itA>>> getInspectionTeamsAndPos(int schoolId){
        Map<String,List<examStaff_it_itA>> result = new HashMap<>();
        List<InspectionTeam> allInspectionTeam=inspectionTeamBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        List<examStaff_it_itA> inspectionTeamsAndPos=new Vector<>();
        for(int i=0;i<allInspectionTeam.size();i++){
            List<InspectionTeamArrangement> allArrange=inspectionTeamArrangementBiz.getAllInspectionTeamArrangementOfOneInspectionTeam(allInspectionTeam.get(i).getInspectionTeamId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){
                    examStaff_it_itA examStaff_it_itARes=new examStaff_it_itA();
                    examStaff_it_itARes.setSessions(allArrange.get(j).getSessions());
                    examStaff_it_itARes.setInspectionTeamId(allInspectionTeam.get(i).getInspectionTeamId());

                    examStaff_it_itARes.setFirstInspectionPersonName(examStaffBiz.getName(allInspectionTeam.get(i).getFirstInspectionPersonId()));
                    examStaff_it_itARes.setSecondInspectionPersonName(examStaffBiz.getName(allInspectionTeam.get(i).getSecondInspectionPersonId()));

                    Floor floor=floorBiz.getOneFloor(allArrange.get(j).getFloorId());
                    examStaff_it_itARes.setFloorStep(floor.getFloorStep());
                    examStaff_it_itARes.setBuilding(floor.getBuilding());

                    examStaff_it_itARes.setSchoolName(schoolBiz.getSchoolName(allArrange.get(j).getSchoolId()));

                    inspectionTeamsAndPos.add(examStaff_it_itARes);
                }
            }

        }
        result.put("inspectionTeamsAndPos",inspectionTeamsAndPos);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/inspectionTeams")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<examStaff_it_itA>>> getInspection(int schoolId){
        Map<String,List<examStaff_it_itA>> result = new HashMap<>();
        List<InspectionTeam> allInspectionTeam=inspectionTeamBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        List<examStaff_it_itA> inspectionTeamsAndPos=new Vector<>();
        for(int i=0;i<allInspectionTeam.size();i++){
            List<InspectionTeamArrangement> allArrange=inspectionTeamArrangementBiz.getAllInspectionTeamArrangementOfOneInspectionTeam(allInspectionTeam.get(i).getInspectionTeamId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId&&allArrange.get(j).getSessions()==1){
                    examStaff_it_itA examStaff_it_itARes=new examStaff_it_itA();
                    examStaff_it_itARes.setInspectionTeamId(allInspectionTeam.get(i).getInspectionTeamId());

                    examStaff_it_itARes.setFirstInspectionPersonName(examStaffBiz.getName(allInspectionTeam.get(i).getFirstInspectionPersonId()));
                    examStaff_it_itARes.setSecondInspectionPersonName(examStaffBiz.getName(allInspectionTeam.get(i).getSecondInspectionPersonId()));

                    examStaff_it_itARes.setSchoolName(schoolBiz.getSchoolName(allArrange.get(j).getSchoolId()));

                    inspectionTeamsAndPos.add(examStaff_it_itARes);
                }
            }
        }
        result.put("inspectionTeams",inspectionTeamsAndPos);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/inspectionTeamsExcel")
    @ResponseBody
    @CrossOrigin
    String getAllInSchoolOfStepOneExcel(int schoolId) throws IOException, WriteException {
        File xlsFile = new File("D://"+schoolBiz.getSchoolName(schoolId)+"巡考组(初步排考数据).xls");
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);

        //添加表头
        sheet.addCell(new Label(0,0,"巡考组编号"));
        sheet.addCell(new Label(1,0,"考点名称"));
        sheet.addCell(new Label(2,0,"巡考1姓名"));
        sheet.addCell(new Label(3,0,"巡考2姓名"));

        //导入数据
        List<InspectionTeam> allInspectionTeam=inspectionTeamBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        int beginIndex=1;
        for(int i=0;i<allInspectionTeam.size();i++){
            List<InspectionTeamArrangement> allArrange=inspectionTeamArrangementBiz.getAllInspectionTeamArrangementOfOneInspectionTeam(allInspectionTeam.get(i).getInspectionTeamId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId&&allArrange.get(j).getSessions()==1){
                    sheet.addCell(new Label(0,beginIndex,String.valueOf(allInspectionTeam.get(i).getInspectionTeamId())));
                    sheet.addCell(new Label(1,beginIndex,schoolBiz.getSchoolName(allArrange.get(j).getSchoolId())));
                    sheet.addCell(new Label(2,beginIndex,examStaffBiz.getName(allInspectionTeam.get(i).getFirstInspectionPersonId())));
                    sheet.addCell(new Label(3,beginIndex,examStaffBiz.getName(allInspectionTeam.get(i).getSecondInspectionPersonId())));
                    beginIndex++;
                }
            }
        }
        workbook.write();
        workbook.close();
        return schoolBiz.getSchoolName(schoolId)+"巡考组(初步排考数据).xls";
    }

    @GetMapping("/inspectionTeamsAndPosExcel")
    @ResponseBody
    @CrossOrigin
    String getAllInSchoolOfStepTwoExcel(int schoolId) throws IOException, WriteException {
        File xlsFile = new File("D://"+schoolBiz.getSchoolName(schoolId)+"巡考组(完成排考数据).xls");
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);

        //添加表头
        sheet.addCell(new Label(0,0,"巡考组编号"));
        sheet.addCell(new Label(1,0,"考点名称"));
        sheet.addCell(new Label(2,0,"楼名称"));
        sheet.addCell(new Label(3,0,"所在楼层"));
        sheet.addCell(new Label(4,0,"巡考1姓名"));
        sheet.addCell(new Label(5,0,"巡考2姓名"));
        sheet.addCell(new Label(6,0,"巡考科目"));

        //导入数据
        List<InspectionTeam> allInspectionTeam=inspectionTeamBiz.getAllByEduId(schoolBiz.getEduId(schoolId));
        int beginIndex=1;
        for(int i=0;i<allInspectionTeam.size();i++){
            List<InspectionTeamArrangement> allArrange=inspectionTeamArrangementBiz.getAllInspectionTeamArrangementOfOneInspectionTeam(allInspectionTeam.get(i).getInspectionTeamId());
            for(int j=0;j<allArrange.size();j++){
                if(allArrange.get(j).getSchoolId()==schoolId){

                    Floor floor=floorBiz.getOneFloor(allArrange.get(j).getFloorId());

                    sheet.addCell(new Label(0,beginIndex,String.valueOf(allInspectionTeam.get(i).getInspectionTeamId())));
                    sheet.addCell(new Label(1,beginIndex,schoolBiz.getSchoolName(allArrange.get(j).getSchoolId())));
                    sheet.addCell(new Label(2,beginIndex,floor.getBuilding()));
                    sheet.addCell(new Label(3,beginIndex,String.valueOf(floor.getFloorStep())));
                    sheet.addCell(new Label(4,beginIndex,examStaffBiz.getName(allInspectionTeam.get(i).getFirstInspectionPersonId())));
                    sheet.addCell(new Label(5,beginIndex,examStaffBiz.getName(allInspectionTeam.get(i).getSecondInspectionPersonId())));
                    if(allArrange.get(j).getSessions()==1){
                        sheet.addCell(new Label(6,beginIndex,"语文"));
                    }else if(allArrange.get(j).getSessions()==2){
                        sheet.addCell(new Label(6,beginIndex,"数学"));
                    }else if(allArrange.get(j).getSessions()==3){
                        sheet.addCell(new Label(6,beginIndex,"英语"));
                    }else if(allArrange.get(j).getSessions()==4){
                        sheet.addCell(new Label(6,beginIndex,"综合"));
                    }
                    beginIndex++;
                }
            }
        }
        workbook.write();
        workbook.close();
        return schoolBiz.getSchoolName(schoolId)+"巡考组(完成排考数据).xls";
    }
}
