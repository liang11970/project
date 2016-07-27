package cn.com.hz_project.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tan on 2016/7/21 0021.
 */
public class StaffBeanModle implements Serializable{
    /**
     * msg : 查询成功
     * obj : [{"UBD_DEPT_ID":1,"UBD_DEPT_NAME":"测试","UBD_EXPERT":1,"UBD_IMG_URL":"/page/img/1468999941022.png","UBD_MOBILE_IDNTY":"865291025991166","UBD_PSTN_ID":7,"UBD_PSTN_NAME":"测试人员","UBD_REAL_NAME":"系统管理员","UBD_UPDATE_TIME":1468999934000,"UBD_UPDATE_USER":1,"UBD_USER_ADDRESS":"上海市张江","UBD_USER_ID":1,"UBD_USER_NAME":"admin","UBD_USER_PHONE":13563251254,"UBD_USER_PWD":"e10adc3949ba59abbe56e057f20f883e","UBD_USER_ROLE":9,"UBD_USER_SEX":1,"UBD_USER_STATE":2},{"UBD_CRTE_TIME":1468572345000,"UBD_CRTE_USER":1,"UBD_DEPT_ID":1,"UBD_EXPERT":2,"UBD_IMG_URL":"100","UBD_MOBILE_IDNTY":"nknjkjo","UBD_PSTN_ID":-1,"UBD_REAL_NAME":"zy1","UBD_UPDATE_TIME":1468977539000,"UBD_UPDATE_USER":1,"UBD_USER_ADDRESS":"啊啊大","UBD_USER_ID":11,"UBD_USER_NAME":"zy","UBD_USER_PHONE":13325545,"UBD_USER_PWD":"123","UBD_USER_ROLE":1,"UBD_USER_SEX":1,"UBD_USER_STATE":2}]
     * success : true
     */

    private String msg;
    private boolean success;

    @Override
    public String toString() {
        return "StaffBean{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", obj=" + obj +
                '}';
    }

    /**
     * UBD_DEPT_ID : 1
     * UBD_DEPT_NAME : 测试
     * UBD_EXPERT : 1
     * UBD_IMG_URL : /page/img/1468999941022.png
     * UBD_MOBILE_IDNTY : 865291025991166
     * UBD_PSTN_ID : 7
     * UBD_PSTN_NAME : 测试人员
     * UBD_REAL_NAME : 系统管理员
     * UBD_UPDATE_TIME : 1468999934000
     * UBD_UPDATE_USER : 1
     * UBD_USER_ADDRESS : 上海市张江
     * UBD_USER_ID : 1
     * UBD_USER_NAME : admin
     * UBD_USER_PHONE : 13563251254
     * UBD_USER_PWD : e10adc3949ba59abbe56e057f20f883e
     * UBD_USER_ROLE : 9
     * UBD_USER_SEX : 1
     * UBD_USER_STATE : 2
     */


    private List<ObjBean> obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Serializable{
        private int UBD_DEPT_ID;
        private String UBD_DEPT_NAME;
        private int UBD_EXPERT;
        private String UBD_IMG_URL;
        private String UBD_MOBILE_IDNTY;
        private int UBD_PSTN_ID;
        private String UBD_PSTN_NAME;
        private String UBD_REAL_NAME;
        private long UBD_UPDATE_TIME;
        private int UBD_UPDATE_USER;
        private String UBD_USER_ADDRESS;
        private int UBD_USER_ID;
        private String UBD_USER_NAME;
        private long UBD_USER_PHONE;
        private String UBD_USER_PWD;
        private int UBD_USER_ROLE;
        private int UBD_USER_SEX;
        private int UBD_USER_STATE;
        //用户ID
//        private int UBD_DEPT_ID;
//        //用户名
//        private String UBD_DEPT_NAME;
//        //是否是专家 1是 2否
//        private int UBD_EXPERT;
//        //头像url
//        private String UBD_IMG_URL;
//        //设备(手机)
//        private String UBD_MOBILE_IDNTY;
//        //职位
//        private int UBD_PSTN_ID;
//        //职位名称
//        private String UBD_PSTN_NAME;
//        //真实姓名
//        private String UBD_REAL_NAME;
//        //修改时间
//        private long UBD_UPDATE_TIME;
//        //修改人
//        private int UBD_UPDATE_USER;
//        //地址
//        private String UBD_USER_ADDRESS;
//        //用户ID
//        private int UBD_USER_ID;
//        //用户名
//        private String UBD_USER_NAME;
//        //联系方式
//        private long UBD_USER_PHONE;
//        //用户密码
//        private String UBD_USER_PWD;
//        //用户权限 管理员权限9
//        private int UBD_USER_ROLE;
//        //性别
//        private int UBD_USER_SEX;
//        //用户状态 1.新增2修改3删除
//        private int UBD_USER_STATE;

        public int getUBD_DEPT_ID() {
            return UBD_DEPT_ID;
        }

        public void setUBD_DEPT_ID(int UBD_DEPT_ID) {
            this.UBD_DEPT_ID = UBD_DEPT_ID;
        }

        public String getUBD_DEPT_NAME() {
            return UBD_DEPT_NAME;
        }

        public void setUBD_DEPT_NAME(String UBD_DEPT_NAME) {
            this.UBD_DEPT_NAME = UBD_DEPT_NAME;
        }

        public int getUBD_EXPERT() {
            return UBD_EXPERT;
        }

        public void setUBD_EXPERT(int UBD_EXPERT) {
            this.UBD_EXPERT = UBD_EXPERT;
        }

        public String getUBD_IMG_URL() {
            return UBD_IMG_URL;
        }

        public void setUBD_IMG_URL(String UBD_IMG_URL) {
            this.UBD_IMG_URL = UBD_IMG_URL;
        }

        public String getUBD_MOBILE_IDNTY() {
            return UBD_MOBILE_IDNTY;
        }

        public void setUBD_MOBILE_IDNTY(String UBD_MOBILE_IDNTY) {
            this.UBD_MOBILE_IDNTY = UBD_MOBILE_IDNTY;
        }

        public int getUBD_PSTN_ID() {
            return UBD_PSTN_ID;
        }

        public void setUBD_PSTN_ID(int UBD_PSTN_ID) {
            this.UBD_PSTN_ID = UBD_PSTN_ID;
        }

        public String getUBD_PSTN_NAME() {
            return UBD_PSTN_NAME;
        }

        public void setUBD_PSTN_NAME(String UBD_PSTN_NAME) {
            this.UBD_PSTN_NAME = UBD_PSTN_NAME;
        }

        public String getUBD_REAL_NAME() {
            return UBD_REAL_NAME;
        }

        public void setUBD_REAL_NAME(String UBD_REAL_NAME) {
            this.UBD_REAL_NAME = UBD_REAL_NAME;
        }

        public long getUBD_UPDATE_TIME() {
            return UBD_UPDATE_TIME;
        }

        public void setUBD_UPDATE_TIME(long UBD_UPDATE_TIME) {
            this.UBD_UPDATE_TIME = UBD_UPDATE_TIME;
        }

        public int getUBD_UPDATE_USER() {
            return UBD_UPDATE_USER;
        }

        public void setUBD_UPDATE_USER(int UBD_UPDATE_USER) {
            this.UBD_UPDATE_USER = UBD_UPDATE_USER;
        }

        public String getUBD_USER_ADDRESS() {
            return UBD_USER_ADDRESS;
        }

        public void setUBD_USER_ADDRESS(String UBD_USER_ADDRESS) {
            this.UBD_USER_ADDRESS = UBD_USER_ADDRESS;
        }

        public int getUBD_USER_ID() {
            return UBD_USER_ID;
        }

        public void setUBD_USER_ID(int UBD_USER_ID) {
            this.UBD_USER_ID = UBD_USER_ID;
        }

        public String getUBD_USER_NAME() {
            return UBD_USER_NAME;
        }

        public void setUBD_USER_NAME(String UBD_USER_NAME) {
            this.UBD_USER_NAME = UBD_USER_NAME;
        }

        public long getUBD_USER_PHONE() {
            return UBD_USER_PHONE;
        }

        public void setUBD_USER_PHONE(long UBD_USER_PHONE) {
            this.UBD_USER_PHONE = UBD_USER_PHONE;
        }

        public String getUBD_USER_PWD() {
            return UBD_USER_PWD;
        }

        public void setUBD_USER_PWD(String UBD_USER_PWD) {
            this.UBD_USER_PWD = UBD_USER_PWD;
        }

        public int getUBD_USER_ROLE() {
            return UBD_USER_ROLE;
        }

        public void setUBD_USER_ROLE(int UBD_USER_ROLE) {
            this.UBD_USER_ROLE = UBD_USER_ROLE;
        }

        public int getUBD_USER_SEX() {
            return UBD_USER_SEX;
        }

        public void setUBD_USER_SEX(int UBD_USER_SEX) {
            this.UBD_USER_SEX = UBD_USER_SEX;
        }

        public int getUBD_USER_STATE() {
            return UBD_USER_STATE;
        }

        public void setUBD_USER_STATE(int UBD_USER_STATE) {
            this.UBD_USER_STATE = UBD_USER_STATE;
        }

        @Override
        public String toString() {
            return "ObjBean{" +
                    "UBD_DEPT_ID=" + UBD_DEPT_ID +
                    ", UBD_DEPT_NAME='" + UBD_DEPT_NAME + '\'' +
                    ", UBD_EXPERT=" + UBD_EXPERT +
                    ", UBD_IMG_URL='" + UBD_IMG_URL + '\'' +
                    ", UBD_MOBILE_IDNTY='" + UBD_MOBILE_IDNTY + '\'' +
                    ", UBD_PSTN_ID=" + UBD_PSTN_ID +
                    ", UBD_PSTN_NAME='" + UBD_PSTN_NAME + '\'' +
                    ", UBD_REAL_NAME='" + UBD_REAL_NAME + '\'' +
                    ", UBD_UPDATE_TIME=" + UBD_UPDATE_TIME +
                    ", UBD_UPDATE_USER=" + UBD_UPDATE_USER +
                    ", UBD_USER_ADDRESS='" + UBD_USER_ADDRESS + '\'' +
                    ", UBD_USER_ID=" + UBD_USER_ID +
                    ", UBD_USER_NAME='" + UBD_USER_NAME + '\'' +
                    ", UBD_USER_PHONE=" + UBD_USER_PHONE +
                    ", UBD_USER_PWD='" + UBD_USER_PWD + '\'' +
                    ", UBD_USER_ROLE=" + UBD_USER_ROLE +
                    ", UBD_USER_SEX=" + UBD_USER_SEX +
                    ", UBD_USER_STATE=" + UBD_USER_STATE +
                    '}';
        }
    }

}
