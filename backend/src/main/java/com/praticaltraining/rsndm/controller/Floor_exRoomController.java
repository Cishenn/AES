package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.EnrollmentDepartmentBiz;
import com.praticaltraining.rsndm.biz.ExamRoomBiz;
import com.praticaltraining.rsndm.biz.FloorBiz;
import com.praticaltraining.rsndm.biz.SchoolBiz;
import com.praticaltraining.rsndm.entity.ExamRoom;
import com.praticaltraining.rsndm.entity.FileUploadUtil;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/floor_exRoom")
public class Floor_exRoomController {

    String name;

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

    @GetMapping("/floor_exRoom/crbe")
    @ResponseBody
    @CrossOrigin
    int createRoomByExcel(int schoolId) throws BiffException, IOException {
        File xlsFile=new File(name);
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
                        xlsFile.delete();
                        return i+1;
                    }
                    if(!Character.isDigit(location.charAt(index))){
                        xlsFile.delete();
                        return i+1;
                    }
                    int step=Integer.parseInt(String.valueOf(location.charAt(index)));
                    int floorId;
                    try{
                        floorId=floorBiz.getFloorId(schoolId,building,step);
                    }catch (FloorException e){
                        xlsFile.delete();
                        return i+1;
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
            xlsFile.delete();
            return -999;//成功！
        }else{
            xlsFile.delete();
            return -1;//表格为空
        }
    }

    @PostMapping(value = "/upload")
    @CrossOrigin
    // 此处的@RequestParam中的file名应与前端upload组件中的name的值保持一致
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        // replaceAll 用来替换windows中的\\ 为 /
        name = "D://"+multipartFile.getOriginalFilename();
        return FileUploadUtil.upload(multipartFile).replaceAll("\\\\", "/");
    }
    @GetMapping("/floor_exRoom/outputAll/higherId")
    @ResponseBody
    @CrossOrigin
    int allExamRommExcelOutput(int eduId) throws IOException, WriteException {
        File xlsFile = new File(enrollmentDepartmentBiz.getOne(eduId).getEduName()+"考场总表.xls");
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        WritableSheet sheet = workbook.createSheet("sheet1", 0);
        int indexBegin=1;

        //添加表头
        sheet.addCell(new Label(0,0,"考场编号"));
        sheet.addCell(new Label(1,0,"学校"));
        sheet.addCell(new Label(2,0,"所在楼"));
        sheet.addCell(new Label(3,0,"所在层"));
        sheet.addCell(new Label(4,0,"房间号"));


        //导出直属考点信息
        List<School> allSchool =schoolBiz.getByEduId(eduId);
        indexBegin=allRoomOut(eduId,sheet,indexBegin);

        //导出下级数据
        if(enrollmentDepartmentBiz.getOne(eduId).getEduLevel()>1){
            List<Integer> allEduBelong=enrollmentDepartmentBiz.eduIdAllBelong(eduId);
            for(int i=0;i<allEduBelong.size();i++){
                indexBegin=allRoomOut(allEduBelong.get(i),sheet,indexBegin);
                if(enrollmentDepartmentBiz.getOne(allEduBelong.get(i)).getEduLevel()>1){
                    List<Integer> allEduBelongLower=enrollmentDepartmentBiz.eduIdAllBelong(allEduBelong.get(i));
                    for(int j=0;j<allEduBelongLower.size();j++){
                        indexBegin=allRoomOut(allEduBelongLower.get(j),sheet,indexBegin);
                    }
                }
            }
        }
        workbook.write();
        workbook.close();
        return 1;
    }

    int allRoomOut(int eduId,WritableSheet sheet,int beginIndex) throws WriteException {
        List<School> allSchool =schoolBiz.getByEduId(eduId);
        for(int i=0;i<allSchool.size();i++){
            if(allSchool.get(i).getExRoomExamine()==2){
                List<ExamRoom> allEr=examRoomBiz.getAllExamRoom(allSchool.get(i).getSchoolId());
                for(int j=0;j<allEr.size();j++){
                    sheet.addCell(new Label(0,beginIndex,String.valueOf(allEr.get(j).getExRoomId())));
                    sheet.addCell(new Label(1,beginIndex,schoolBiz.getSchoolName(allEr.get(j).getSchoolId())));
                    sheet.addCell(new Label(2,beginIndex,floorBiz.getOneFloor(allEr.get(j).getFloorId()).getBuilding()));
                    sheet.addCell(new Label(3,beginIndex,String.valueOf(floorBiz.getOneFloor(allEr.get(j).getFloorId()).getFloorStep())));
                    sheet.addCell(new Label(4,beginIndex,allEr.get(j).getRoomNum()));
                    beginIndex++;
                }
            }
        }
        return beginIndex;
    }


}
