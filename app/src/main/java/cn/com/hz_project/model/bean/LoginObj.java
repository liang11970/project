package cn.com.hz_project.model.bean;

/**
 * Created by ku on 2016/7/14.
 */
public class LoginObj {
    private String cusNumber;
    private String departmentName;
    private String dprtmntId;
//    private String loginIp;
    private String loginName;
//    private String orgClassKey;
//    private String orgCode;
//    private String realName;
//    private String role;
//    private String roleArray;
//    private String roleId;
//    private String userId;
//    private String userLevel;


    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDprtmntId() {
        return dprtmntId;
    }

    public void setDprtmntId(String dprtmntId) {
        this.dprtmntId = dprtmntId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "LoginObj{" +
                "cusNumber='" + cusNumber + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", dprtmntId='" + dprtmntId + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}
