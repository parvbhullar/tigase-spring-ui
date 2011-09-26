package com.mitian.airad.web.form;

import org.apache.commons.lang.StringUtils;

/**
 * DemoForm.java
 * 
 * @author baojun
 */
public class DemoForm extends AbstractForm {
    private String name;
    private String email;
    private String birthDay;
    private String age;
    private String sex;

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    private User user = new User();

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the birthDay
     */
    public String getBirthDay() {
        return birthDay;
    }

    /**
     * @param birthDay the birthDay to set
     */
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public void form2domain() {
        if (StringUtils.isNotBlank(age)) {
            user.setAge(Integer.valueOf(age));
        }
    }
}
