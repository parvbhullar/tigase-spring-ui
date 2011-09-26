/*
 * Copyright 2011 mitian Technology, Co., Ltd. All rights reserved.
 */
package com.mitian.airad.web.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mitian.airad.model.CoreAdvertiser;

/**
 * AdvertiserForm.java
 * 
 * @author leifenghai
 */
public class AdvertiserForm extends AbstractForm {

    private CommonsMultipartFile photo;

    /**
     * @return the photo
     */
    public CommonsMultipartFile getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(CommonsMultipartFile photo) {
        this.photo = photo;
    }

    private CoreAdvertiser coreAdvertiser = new CoreAdvertiser();

    /**
     * @return the coreAdvertiser
     */
    public CoreAdvertiser getCoreAdvertiser() {
        return coreAdvertiser;
    }

    /**
     * @param coreAdvertiser the coreAdvertiser to set
     */
    public void setCoreAdvertiser(CoreAdvertiser coreAdvertiser) {
        this.coreAdvertiser = coreAdvertiser;
    }

    /*
     * (non-Javadoc)
     * @see com.mitian.airad.web.form.AbstractForm#form2domain()
     */
    @Override
    public void form2domain() {
        // TODO Auto-generated method stub

    }

}
