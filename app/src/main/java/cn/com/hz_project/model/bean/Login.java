package cn.com.hz_project.model.bean;

/**
 * Created by ku on 2016/7/14.
 */
public class Login {
    private String msg;
    private LoginObj obj;
    private Boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginObj getObj() {
        return obj;
    }

    public void setObj(LoginObj obj) {
        this.obj = obj;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
