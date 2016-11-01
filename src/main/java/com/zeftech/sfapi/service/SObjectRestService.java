package com.zeftech.sfapi.service;

import com.sforce.soap.partner.sobject.SObject;
import com.zeftech.sfapi.dto.QuerySObjectRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Cody_OPI on 10/31/16.
 */
@Component
public class SObjectRestService {
    private static final Logger LOG = LoggerFactory.getLogger(SObjectSoapService.class);

//    @Autowired
//    private RestTemplate restTemplate;

//    public SObject[] querySobject(QuerySObjectRequestDto querySObjectRequestDto) {
//        this.
//    }

}
