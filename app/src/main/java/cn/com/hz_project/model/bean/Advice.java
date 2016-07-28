package cn.com.hz_project.model.bean;

/**
 * ==================================
 * Created by wangl on 2016/7/27.时间16:56
 * <p>
 * 版本1.0
 * <p>
 * 描述  反馈
 * <p>
 * ================================
 */
public class Advice {


    /**
     * success : true
     * msg : 添加成功!
     * obj : 添加成功!
     */

    private boolean success;
    private String msg;
    private String obj;

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

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }


    
}
