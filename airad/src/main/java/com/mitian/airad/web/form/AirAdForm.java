/*
 * Copyright 2011 Mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.List;

import com.mitian.airad.model.CoreCampaign;

/**
 * AirAdForm.java
 * 
 * @author Administrator
 */
public class AirAdForm extends AbstractForm {

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.form.AbstractForm#form2domain()
     */
    public List<CoreCampaign> list = new ArrayList<CoreCampaign>();

    public Integer campaignId;

    /**
     * @return the campaignId
     */
    public Integer getCampaignId() {
        return campaignId;
    }

    /**
     * @param campaignId the campaignId to set
     */
    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * @return the list
     */
    public List<CoreCampaign> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<CoreCampaign> list) {
        this.list = list;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.form.AbstractForm#form2domain()
     */
    @Override
    public void form2domain() {

    }

}
