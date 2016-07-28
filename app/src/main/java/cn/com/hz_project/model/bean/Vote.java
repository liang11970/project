package cn.com.hz_project.model.bean;

import java.util.ArrayList;

/**
 * Created by ku on 2016/7/28.
 */
public class Vote {
    private Boolean success;
    private String msg;
    private ArrayList<VoteObj> obj;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<VoteObj> getObj() {
        return obj;
    }

    public void setObj(ArrayList<VoteObj> obj) {
        this.obj = obj;
    }
}
