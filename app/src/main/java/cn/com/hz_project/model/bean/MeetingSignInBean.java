package cn.com.hz_project.model.bean;

/**
 * Created by Tan on 2016/7/20 0020.
 */
public class MeetingSignInBean {

    /**
     * success : true
     * msg : 查询成功
     */

    private boolean success;
    private String msg;

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
}
