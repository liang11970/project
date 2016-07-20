package cn.com.hz_project.model.bean;

import java.util.List;

/**
 * ==================================
 * Created by wangl on 2016/7/20.时间16:00
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class NewsContext {


    /**
     * success : true
     * msg : null
     * obj : [{"NBD_TITLE":"ad","NBD_CONTEXT":"ada","NBD_PICTURE_URL":"ada","NBD_READNUMBER":2,"NBD_CREATE_USER":1,"TIME":"2016-07-20 10:49"}]
     */

    private boolean success;
    private Object msg;
    /**
     * NBD_TITLE : ad
     * NBD_CONTEXT : ada
     * NBD_PICTURE_URL : ada
     * NBD_READNUMBER : 2
     * NBD_CREATE_USER : 1
     * TIME : 2016-07-20 10:49
     */

    private List<ObjBean> obj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        private String NBD_TITLE;
        private String NBD_CONTEXT;
        private String NBD_PICTURE_URL;
        private int NBD_READNUMBER;
        private int NBD_CREATE_USER;
        private String TIME;

        public String getNBD_TITLE() {
            return NBD_TITLE;
        }

        public void setNBD_TITLE(String NBD_TITLE) {
            this.NBD_TITLE = NBD_TITLE;
        }

        public String getNBD_CONTEXT() {
            return NBD_CONTEXT;
        }

        public void setNBD_CONTEXT(String NBD_CONTEXT) {
            this.NBD_CONTEXT = NBD_CONTEXT;
        }

        public String getNBD_PICTURE_URL() {
            return NBD_PICTURE_URL;
        }

        public void setNBD_PICTURE_URL(String NBD_PICTURE_URL) {
            this.NBD_PICTURE_URL = NBD_PICTURE_URL;
        }

        public int getNBD_READNUMBER() {
            return NBD_READNUMBER;
        }

        public void setNBD_READNUMBER(int NBD_READNUMBER) {
            this.NBD_READNUMBER = NBD_READNUMBER;
        }

        public int getNBD_CREATE_USER() {
            return NBD_CREATE_USER;
        }

        public void setNBD_CREATE_USER(int NBD_CREATE_USER) {
            this.NBD_CREATE_USER = NBD_CREATE_USER;
        }

        public String getTIME() {
            return TIME;
        }

        public void setTIME(String TIME) {
            this.TIME = TIME;
        }
    }
}
