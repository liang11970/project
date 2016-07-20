package cn.com.hz_project.model.bean;

import java.util.List;

/**
 * ==================================
 * Created by wangl on 2016/7/14.时间17:18
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class Login {


    /**
     * success : true
     * msg : 用户登录成功!
     * obj : {"loginIp":"","userId":1,"loginName":"13563251254","realName":"系统管理员","cusNumber":"系统管理员","orgCode":"2","departmentName":"上海市张江","imgUrl":"/page/img/1468896899350.gif","telPhone":null,"roleId":"9","role":"","roleArray":[],"orgClassKey":"","userLevel":0,"dprtmntId":"1"}
     */

    private boolean success;
    private String msg;
    /**
     * loginIp :
     * userId : 1
     * loginName : 13563251254
     * realName : 系统管理员
     * cusNumber : 系统管理员
     * orgCode : 2
     * departmentName : 上海市张江
     * imgUrl : /page/img/1468896899350.gif
     * telPhone : null
     * roleId : 9
     * role :
     * roleArray : []
     * orgClassKey :
     * userLevel : 0
     * dprtmntId : 1
     */

    private ObjBean obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        private String loginIp;
        private int userId;
        private String loginName;
        private String realName;
        private String cusNumber;
        private String orgCode;
        private String departmentName;
        private String imgUrl;
        private String telPhone;
        private String roleId;
        private String role;
        private String orgClassKey;

        public String getUbdExpert() {
            return ubdExpert;
        }

        public void setUbdExpert(String ubdExpert) {
            this.ubdExpert = ubdExpert;
        }

        private int userLevel;
        private String dprtmntId;
        private List<?> roleArray;
        private String ubdExpert;

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCusNumber() {
            return cusNumber;
        }

        public void setCusNumber(String cusNumber) {
            this.cusNumber = cusNumber;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getOrgClassKey() {
            return orgClassKey;
        }

        public void setOrgClassKey(String orgClassKey) {
            this.orgClassKey = orgClassKey;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

        public String getDprtmntId() {
            return dprtmntId;
        }

        public void setDprtmntId(String dprtmntId) {
            this.dprtmntId = dprtmntId;
        }

        public List<?> getRoleArray() {
            return roleArray;
        }

        public void setRoleArray(List<?> roleArray) {
            this.roleArray = roleArray;
        }
    }
}
