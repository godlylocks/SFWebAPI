package com.zeftech.sfapi.service;

import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.zeftech.sfapi.dto.InsertSObjectRequestDto;
import com.zeftech.sfapi.dto.QuerySObjectRequestDto;
import com.zeftech.sfapi.dto.UpdateSObjectRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Cody_OPI on 10/31/16.
 */
@Component
public class SObjectSoapService {

    private static final Logger LOG = LoggerFactory.getLogger(SObjectSoapService.class);

    @Autowired
    private PartnerConnection partnerConnection;


    public SObject[] querySobject(QuerySObjectRequestDto querySObjectRequestDto) throws ConnectionException {
        LOG.info("Querying for SObjects");
        String queryString = buildQueryString(querySObjectRequestDto.getObjectType(), querySObjectRequestDto.getFieldMap(), querySObjectRequestDto.isFuzzyMatch());
        return this.partnerConnection.query(queryString).getRecords();

    }

    public SaveResult[] updateSobject(UpdateSObjectRequestDto updateSObjectRequestDto) throws ConnectionException {
        LOG.info("Updating SObjects");
        SObject[] sObjects = buildSObjectArray(updateSObjectRequestDto.getObjectType(), updateSObjectRequestDto.getFieldMap(), updateSObjectRequestDto.getRecordId());
        return this.partnerConnection.update(sObjects);
    }

    public SaveResult[] insertSobject(InsertSObjectRequestDto insertSObjectRequestDto) throws ConnectionException {
        LOG.info("Inserting SObjects");
        SObject[] sObjects = buildSObjectArray(insertSObjectRequestDto.getObjectType(), insertSObjectRequestDto.getFieldMap(), insertSObjectRequestDto.getRecordId());
        return this.partnerConnection.create(sObjects);
    }

    public DeleteResult[] deleteSobject(List<String> idsForDeletion) throws ConnectionException {
        LOG.info("Deleting SObjects");
        String[] idsToDelete = buildIdArray(idsForDeletion);
        return this.partnerConnection.delete(idsToDelete);
    }

    private SObject[] buildSObjectArray(String objectType, Map<String, Object> fieldMap, String recordId) {
        SObject[] sObjects = new SObject[1];
        SObject updatedObject = new SObject();
        updatedObject.setId(recordId);
        updatedObject.setType(objectType);
        for (Map.Entry<String, Object> fieldEntry : fieldMap.entrySet()) {
            updatedObject.setSObjectField(fieldEntry.getKey(), fieldEntry.getValue());
        }
        sObjects[0] = updatedObject;
        return sObjects;
    }

    private String buildQueryString(String objectName, Map<String, String> fieldMap, boolean fuzzyMatch) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT Id, Name FROM ");
        sb.append(objectName);
        sb.append(" WHERE ");
        for (Map.Entry<String, String> fieldEntry : fieldMap.entrySet()) {
            sb.append(fieldEntry.getKey());
            sb.append(fuzzyMatch ? " LIKE " : "=");
            sb.append(wrapInQuotes(fieldEntry.getValue(), fuzzyMatch));
            sb.append(" AND ");
        }

        LOG.info(sb.substring(0, sb.length() - 5));

        return sb.substring(0, sb.length()-5);
    }

    private String wrapInQuotes(String value, boolean fuzzyMatch) {
        StringBuilder sb = new StringBuilder();
        sb.append(fuzzyMatch ? "'%" : "'");
        sb.append(value);
        sb.append(fuzzyMatch ? "%'" : "'");
        return sb.toString();
    }

    private String[] buildIdArray(List<String> idsForDeletion) {
        String[] idsToDelete = new String[idsForDeletion.size()];
        for (int i = 0; i < idsForDeletion.size(); i++) {
            idsToDelete[i] = idsForDeletion.get(i);
        }
        return idsToDelete;
    }



}
