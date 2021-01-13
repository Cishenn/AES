package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentBiz;
import com.praticaltraining.rsndm.biz.ExamRoomBiz;
import com.praticaltraining.rsndm.biz.FloorBiz;
import com.praticaltraining.rsndm.biz.SchoolBiz;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.Floor;
import com.praticaltraining.rsndm.entity.School;
import com.praticaltraining.rsndm.entity.floor_exRoom;
import com.praticaltraining.rsndm.exception.FloorException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
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
import java.util.*;

@RestController
@RequestMapping("/floor_exRoom")
public class Floor_exRoomController {

    @Autowired
    private FloorBiz floorBiz;

    @Autowired
    private ExamRoomBiz examRoomBiz;

    @Autowired
    private EnrollmentDepartmentBiz enrollmentDepartmentBiz;
    @Autowired
    private SchoolBiz schoolBiz;

    @GetMapping("/floor_exRoom/schoolId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<floor_exRoom>>> queryBySchoolId(int schoolId){
        Map<String,List<floor_exRoom>> result = new HashMap<>();
        List<ExamRoom> room = examRoomBiz.getAllExamRoom(schoolId);
        List<Floor> floor = floorBiz.getAllFloor(schoolId);
        List<floor_exRoom> floor_room = new ArrayList();
        List<floor_exRoom> fr = new ArrayList();
        int fId = 0;
        int flag = 0;
        for(int i = 0;i<room.size();i++){
            fId = room.get(i).getFloorId();
            for(int j = 0;j<floor.size();j++){
                if(floor.get(j).getFloorId() == fId){
                    flag = j;
                    break;
                }
            }
            floor_exRoom floor_exroom = new floor_exRoom(floor.get(flag),room.get(i));
            floor_room.add(floor_exroom);
        }
        List<String> building = floorBiz.getBuilding(schoolId);
        for(int i = 0;i < building.size();i++){
            List<floor_exRoom> temp = new ArrayList();
            for(int j = 0;j < floor_room.size();j++){
                if(floor_room.get(j).floor.getBuilding().equals(building.get(i))){
                    temp.add(floor_room.get(j));
                }
            }
            Collections.sort(temp);
            fr.addAll(temp);
        }
        result.put("floor_exRoom",fr);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/floor_exRoom/exRoomId")
    @ResponseBody
    @CrossOrigin
    floor_exRoom getOneExRoom(int exRoomId){
        ExamRoom exroom = examRoomBiz.getOneExRoom(exRoomId);
        Floor floor = floorBiz.getOneFloor(exroom.getFloorId());
        floor_exRoom floor_exroom = new floor_exRoom(floor,exroom);
        return floor_exroom;
    }

    @GetMapping("/ArrangeFloor_exRoom/schoolId")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<floor_exRoom>>> queryArrangedBySchoolId(int schoolId){
        Map<String,List<floor_exRoom>> result = new HashMap<>();
        List<ExamRoom> room = examRoomBiz.getAllArrangedExamRoom(schoolId);
        List<Floor> floor = floorBiz.getAllFloor(schoolId);
        List<floor_exRoom> floor_room = new ArrayList();
        List<floor_exRoom> fr = new ArrayList();
        int fId = 0;
        int flag = 0;
        for(int i = 0;i<room.size();i++){
            fId = room.get(i).getFloorId();
            for(int j = 0;j<floor.size();j++){
                if(floor.get(j).getFloorId() == fId){
                    flag = j;
                    break;
                }
            }
            floor_exRoom floor_exroom = new floor_exRoom(floor.get(flag),room.get(i));
            floor_room.add(floor_exroom);
        }
        List<String> building = floorBiz.getBuilding(schoolId);
        for(int i = 0;i < building.size();i++){
            List<floor_exRoom> temp = new ArrayList();
            for(int j = 0;j < floor_room.size();j++){
                if(floor_room.get(j).floor.getBuilding().equals(building.get(i))){
                    temp.add(floor_room.get(j));
                }
            }
            Collections.sort(temp);
            fr.addAll(temp);
        }
        result.put("floor_exRoom",fr);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/floor_exRoom/crbe")
    @ResponseBody
    @CrossOrigin
    int createRoomByExcel(String path,int schoolId) throws BiffException, IOException {
        File xlsFile=new File(path);
        Workbook workbook = Workbook.getWorkbook(xlsFile);
        Sheet[] sheets=workbook.getSheets();
        if(sheets!=null){
            for(Sheet sheet : sheets){
                //获得行数
                int rows = sheet.getRows();
                for(int i=0;i<rows;i++){
                    StringBuffer location=new StringBuffer(sheet.getCell(0,i).getContents());
                    int index=0;
                    String building="";
                    while(index<location.length()){
                        if(location.charAt(index)=='第'){
                            index++;//直接指向表示层数的位置然后结束
                            break;
                        }else{
                            building+=location.charAt(index);
                            index++;
                        }
                    }
                    if(index==location.length()){
                        //格式错误返回发生错误的行位置
                        return i;
                    }
                    if(!Character.isDigit(location.charAt(index))){
                        return i;
                    }
                    int step=Integer.parseInt(String.valueOf(location.charAt(index)));
                    int floorId;
                    try{
                        floorId=floorBiz.getFloorId(schoolId,building,step);
                    }catch (FloorException e){
                        return i;
                    }
                    if(examRoomBiz.getIdByAll(floorId,schoolId,sheet.getCell(1,i).getContents())==null){
                        ExamRoom eroom=new ExamRoom();
                        eroom.setFloorId(floorId);
                        eroom.setRoomNum(sheet.getCell(1,i).getContents());
                        eroom.setSchoolId(schoolId);
                        eroom.setIsArrange(0);
                        examRoomBiz.createExRoom(eroom);
                    }
                }
            }
            return -999;//成功！
        }else{
            return -1;//表格为空
        }
    }

    @GetMapping("/floor_exRoom/outputAll/higherId")
    @ResponseBody
    @CrossOrigin
    int allExamRommExcelOutput(int eduId) throws IOException, WriteException {
        File xlsFile = new File("lowLevelRoom.xls");
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);

        //添加表头
        sheet.addCell(new Label(0,0,"考场编号"));
        sheet.addCell(new Label(1,0,"学校"));
        sheet.addCell(new Label(2,0,"所在楼"));
        sheet.addCell(new Label(3,0,"所在层"));
        sheet.addCell(new Label(4,0,"房间号"));

        //导入数据
        List<Integer> allEduBelong=enrollmentDepartmentBiz.eduIdAllBelong(eduId);
        for(int i=0;i<allEduBelong.size();i++){
            List<School> allSchool =schoolBiz.getByEduId(allEduBelong.get(i));
            for(int j=0;j<allSchool.size();j++){
                if(allSchool.get(j).getExRoomExamine()==2){
                    List<ExamRoom> allEr=examRoomBiz.getAllExamRoom(allSchool.get(j).getSchoolId());
                    for(int k=0;k<allEr.size();k++){
                        sheet.addCell(new Label(0,i*j+k+1,String.valueOf(allEr.get(k).getExRoomId())));
                        sheet.addCell(new Label(1,i*j+k+1,schoolBiz.getSchoolName(allEr.get(k).getSchoolId())));
                        sheet.addCell(new Label(2,i*j+k+1,floorBiz.getOneFloor(allEr.get(k).getFloorId()).getBuilding()));
                        sheet.addCell(new Label(3,i*j+k+1,String.valueOf(floorBiz.getOneFloor(allEr.get(k).getFloorId()).getFloorStep())));
                        sheet.addCell(new Label(4,i*j+k+1,allEr.get(k).getRoomNum()));
                    }
                }
            }
        }
        workbook.write();
        workbook.close();
        return 1;
    }
}
