package com.zeftech.sfapi.controller;

import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.zeftech.sfapi.dto.InsertSObjectRequestDto;
import com.zeftech.sfapi.dto.QuerySObjectRequestDto;
import com.zeftech.sfapi.dto.UpdateSObjectRequestDto;
import com.zeftech.sfapi.service.SObjectRestService;
import com.zeftech.sfapi.service.SObjectSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cody_OPI on 10/31/16.
 */
@RestController
@RequestMapping(value = "/rest/v1/Controller")
public class SFWebAPIController {

    @Autowired
    private SObjectSoapService sObjectSoapService;

    @Autowired
    private SObjectRestService sObjectRestService;

    @RequestMapping(value = "/testReturnCode", method = RequestMethod.GET)
    public String testReturnCode() {
        return "Hello World";
    }

    @RequestMapping(value = "/querySobjectRaw", method = RequestMethod.POST, consumes = "application/json")
    public SObject[] querySobjectRaw(@RequestBody QuerySObjectRequestDto querySObjectRequestDto) throws ConnectionException {
        return this.sObjectSoapService.querySobject(querySObjectRequestDto);
    }

    @RequestMapping(value = "/querySobject", method = RequestMethod.POST, consumes = "application/json")
    public List<Map<String, Object>> querySobject(@RequestBody QuerySObjectRequestDto querySObjectRequestDto) throws ConnectionException {
        SObject[] sObjects = this.sObjectSoapService.querySobject(querySObjectRequestDto);
        return parseSobjectArray(sObjects);
    }

    @RequestMapping(value = "/updateSobject", method = RequestMethod.POST, consumes = "application/json")
    public SaveResult[] updateSobject(@RequestBody UpdateSObjectRequestDto updateSObjectRequestDto) throws ConnectionException {
        return this.sObjectSoapService.updateSobject(updateSObjectRequestDto);
    }

    @RequestMapping( value = "/insertSobject", method = RequestMethod.POST, consumes = "application/json")
    public SaveResult[] insertSobject(@RequestBody InsertSObjectRequestDto insertSObjectRequestDto) throws ConnectionException {
        return this.sObjectSoapService.insertSobject(insertSObjectRequestDto);
    }

    @RequestMapping(value = "/deleteSobjects", method = RequestMethod.POST, consumes = "application/json")
    public DeleteResult[] deleteSobjects(@RequestBody List<String> idsForDeletion) throws ConnectionException {
        return this.sObjectSoapService.deleteSobject(idsForDeletion);
    }

    private List<Map<String, Object>> parseSobjectArray(SObject[] sObjects) {
        List<Map<String, Object>> sObjectList = new ArrayList<>();
        for (SObject object : sObjects) {
            Map<String, Object> fieldMap = new HashMap<>();
            fieldMap.put("Id", object.getField("Id"));
            fieldMap.put("Name", object.getField("Name"));
            sObjectList.add(fieldMap);
        }
        return sObjectList;
    }
}
