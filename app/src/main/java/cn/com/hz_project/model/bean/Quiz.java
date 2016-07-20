package cn.com.hz_project.model.bean;

import java.util.ArrayList;

/**
 * Created by ku on 2016/7/19.
 */
public class Quiz {
    private String msg;
    private ArrayList<QuizObj> obj;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<QuizObj> getObj() {
        return obj;
    }

    public void setObj(ArrayList<QuizObj> obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
