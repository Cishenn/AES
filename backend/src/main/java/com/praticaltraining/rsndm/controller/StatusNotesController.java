package com.praticaltraining.rsndm.controller;

import com.praticaltraining.rsndm.biz.ExamStaffBiz;
import com.praticaltraining.rsndm.biz.StatusNotesBiz;
import com.praticaltraining.rsndm.entity.StatusNotes;
import com.praticaltraining.rsndm.entity.ViolationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/StatusNotes")
public class StatusNotesController {
    @Autowired
    private StatusNotesBiz statusNotesBiz;
    @Autowired
    private ExamStaffBiz examStaffBiz;

    @GetMapping("/StatusNotes")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<StatusNotes>>> queryAllByEsId(int esId){
        Map<String,List<StatusNotes>> result = new HashMap<>();
        result.put("StatusNotes",statusNotesBiz.getAllByEsId(esId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/StatusNotesToSchool")
    @ResponseBody
    @CrossOrigin
    ResponseEntity<Map<String, List<StatusNotes>>> queryAllBySchoolId(int schoolId){
        Map<String,List<StatusNotes>> result = new HashMap<>();
        result.put("StatusNotes",statusNotesBiz.getAllBySchool(schoolId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/StatusNotesSloved")
    @ResponseBody
    @CrossOrigin
    void StatusNotesSloved(int esId,int stateId,int auditState){
        statusNotesBiz.updateAuditState(stateId,auditState);
        if(auditState==2) {
            examStaffBiz.updateToFeedBackSolved(esId);
        }
    }
}
