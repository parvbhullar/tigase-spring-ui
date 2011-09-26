package com.mitian.airad.web.form;

import com.mitian.airad.model.AiradDevmember;

/**
 * AiradDevmemberForm.java
 * 
 * @author baojun
 */
public class AiradDevmemberForm extends AbstractForm {

    private AiradDevmember airadDevmember;

    /**
     *id
     */
    private Long id;

    /**
     *姓名
     */
    private String name;
    /**
     * 公司
     */
    private String company;

    /**
     * 职位
     */
    private String position;

    /**
     * 操作平台
     */
    private String os;

    /**
     * 应用个数
     */
    private String appnumber;

    /**
     * 电话
     */
    private String cellphone;

    /**
     * email
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    private String province;
    private String city;
    private String country;

    private String provinceName;
    private String cityName;

    private String provinceName1;
    private String cityName1;

    private String inviteCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAppnumber() {
        return appnumber;
    }

    public void setAppnumber(String appnumber) {
        this.appnumber = appnumber;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public AiradDevmember getAiradDevmember() {
        return airadDevmember;
    }

    public void setAiradDevmember(AiradDevmember airadDevmember) {
        this.airadDevmember = airadDevmember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void form2domain() {
        // TODO Auto-generated method stub

    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getProvinceName1() {
        return provinceName1;
    }

    public void setProvinceName1(String provinceName1) {
        this.provinceName1 = provinceName1;
    }

    public String getCityName1() {
        return cityName1;
    }

    public void setCityName1(String cityName1) {
        this.cityName1 = cityName1;
    }

}
