package cn.com.hz_project.model.bean;

/**
 * Created by Tan on 2016/7/20 0020.
 */
public class MeetingAddBean {

    /**
     * success : true
     * msg : 会议添加成功
     * obj : null
     */

    private boolean success;
    private String msg;
    private Object obj;

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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
