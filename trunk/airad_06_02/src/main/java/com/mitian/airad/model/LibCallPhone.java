package com.mitian.airad.model;

public class LibCallPhone {

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((richId == null) ? 0 : richId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((callPhoneNumber == null) ? 0 : callPhoneNumber.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LibCallPhone other = (LibCallPhone) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        }
        else if (!id.equals(other.id)) {
            return false;
        }
        if (richId == null) {
            if (other.richId != null) {
                return false;
            }
        }
        else if (!richId.equals(other.richId)) {
            return false;
        }
        if (status == null) {
            if (other.status != null) {
                return false;
            }
        }
        else if (!status.equals(other.status)) {
            return false;
        }
        if (callPhoneNumber == null) {
            if (other.callPhoneNumber != null) {
                return false;
            }
        }
        else if (!callPhoneNumber.equals(other.callPhoneNumber)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LibcallPhone [id=" + id + ", richId=" + richId + ", status=" + status + ", callPhoneNumber=" + callPhoneNumber + "]";
    }

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column LIB_TAOBAO.ID
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    private Integer id;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * LIB_TAOBAO.RICH_ID
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    private Integer richId;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column
     * LIB_TAOBAO.TAOBAO_URL
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    private String callPhoneNumber;
    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds to the database column LIB_TAOBAO.STATUS
     * 
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    private String status;

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * LIB_TAOBAO.ID
     * 
     * @return the value of LIB_TAOBAO.ID
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * LIB_TAOBAO.ID
     * 
     * @param id the value for LIB_TAOBAO.ID
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * LIB_TAOBAO.RICH_ID
     * 
     * @return the value of LIB_TAOBAO.RICH_ID
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    public Integer getRichId() {
        return richId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * LIB_TAOBAO.RICH_ID
     * 
     * @param richId the value for LIB_TAOBAO.RICH_ID
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    public void setRichId(Integer richId) {
        this.richId = richId;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * LIB_TAOBAO.TAOBAO_URL
     * 
     * @return the value of LIB_TAOBAO.TAOBAO_URL
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    public String getCallPhoneNumber() {
        return callPhoneNumber;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * LIB_TAOBAO.TAOBAO_URL
     * 
     * @param taobaoUrl the value for LIB_TAOBAO.TAOBAO_URL
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    public void setCallPhoneNumber(String callPhoneNumber) {
        this.callPhoneNumber = callPhoneNumber;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns the value of the database column
     * LIB_TAOBAO.STATUS
     * 
     * @return the value of LIB_TAOBAO.STATUS
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the value of the database column
     * LIB_TAOBAO.STATUS
     * 
     * @param status the value for LIB_TAOBAO.STATUS
     * @ibatorgenerated Wed Jun 01 15:51:58 CST 2011
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
