package cn.com.hz_project.model.bean;

/**
 * Created by Tan on 2016/7/22 0022.
 */
public class DeleteMeeting {

    /**
     * success : true
     * msg : 删除成功
     * obj : null
     */

    private boolean success;
    private String msg;
    private Object obj;

    @Override
    public String toString() {
        return "DeleteMeeting{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }

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
