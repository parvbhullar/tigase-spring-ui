package com.mitian.airad.web.form;

import java.util.ArrayList;
import java.util.List;

import com.mitian.airad.model.AccIncome;

public class IncomForm extends AbstractForm {
    /** 应用名称 */
    private String appName;
    /** 收益 */
    private AccIncome accIncome = new AccIncome();
    private List<AccIncome> listBean = new ArrayList<AccIncome>();

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * @return the accIncome
     */
    public AccIncome getAccIncome() {
        return accIncome;
    }

    /**
     * @param accIncome the accIncome to set
     */
    public void setAccIncome(AccIncome accIncome) {
        this.accIncome = accIncome;
    }

    /**
     * @return the listBean
     */
    public List<AccIncome> getListBean() {
        return listBean;
    }

    /**
     * @param listBean the listBean to set
     */
    public void setListBean(List<AccIncome> listBean) {
        this.listBean = listBean;
    }

    @Override
    public void form2domain() {

    }
}
