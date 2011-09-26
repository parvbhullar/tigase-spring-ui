package com.mitian.airad.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitian.airad.dao.RelAdSDKMappingDAO;
import com.mitian.airad.model.RelAdSDKMapping;

/**
 * RegisterService.java
 */
@Service
public class RelAdSDKMappingService {
    //private static final Log LOGGER = LogFactory.getLog(RelAdSDKMappingService.class);
    @Autowired
    private RelAdSDKMappingDAO relAdSDKMappingDAO;
    
    
    public void txAddAdSdkMapping(RelAdSDKMapping record){
        //LOGGER.debug("add sdk mapping adid:"+record.getAdId()+" sdkid:"+record.getSdkId());
        relAdSDKMappingDAO.insert(record);
    }
    
    public void txDeleteAdSdkMapping(Integer id){
    //    LOGGER.debug("delete sdk mapping adid:"+id);
        relAdSDKMappingDAO.deleteByPrimaryKey(id);
    }
   
    
}
