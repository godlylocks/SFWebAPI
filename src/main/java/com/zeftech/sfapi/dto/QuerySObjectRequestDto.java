package com.zeftech.sfapi.dto;

import java.util.Map;

/**
 * Created by Cody_OPI on 10/31/16.
 */
public class QuerySObjectRequestDto {

    private String objectType;
    private Map<String, String> fieldMap;
    private boolean fuzzyMatch;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public boolean isFuzzyMatch() {
        return fuzzyMatch;
    }

    public void setFuzzyMatch(boolean fuzzyMatch) {
        this.fuzzyMatch = fuzzyMatch;
    }
}
