package com.zeftech.sfapi.dto;

import java.util.Map;

/**
 * Created by Cody_OPI on 10/31/16.
 */
public class InsertSObjectRequestDto {


    private String objectType;
    private Map<String, Object> fieldMap;
    private String recordId;


    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
