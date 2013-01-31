/**
 * 
 */
package com.ce.service.json;

/**
 * @author sanyaeng The class which contain all the information of the communication option type and will be used through the json
 */
public class JCommunicationOption {

    private String comType;
    private String displayVal;
    private String useVal;

    /**
     * @return the comType
     */
    public String getComType() {
        return comType;
    }

    /**
     * @param comType
     *            the comType to set
     */
    public void setComType(String comType) {
        this.comType = comType;
    }

    /**
     * @return the displayVal
     */
    public String getDisplayVal() {
        return displayVal;
    }

    /**
     * @param displayVal
     *            the displayVal to set
     */
    public void setDisplayVal(String displayVal) {
        this.displayVal = displayVal;
    }

    /**
     * @return the useVal
     */
    public String getUseVal() {
        return useVal;
    }

    /**
     * @param useVal
     *            the useVal to set
     */
    public void setUseVal(String useVal) {
        this.useVal = useVal;
    }

}
