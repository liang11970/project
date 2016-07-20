package cn.com.hz_project.model.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ku on 2016/7/19.
 */
public class QuizObj implements Serializable{
    /**
     * 互动提问问题对象
     * ROWNO
     * TIME 时间
     * WQD_ANSWER_ROLE 回答对象
     * WQD_CONTEXT 问题内容
     * WQD_ID=7
     * WQD_QUESTION 问题标题
     */
    private String ROWNO;
    private String TIME;
    private String WQD_ANSWER_ROLE;
    private String WQD_CONTEXT;
    private String WQD_ID;
    private String WQD_QUESTION;

    public String getROWNO() {
        return ROWNO;
    }

    public void setROWNO(String ROWNO) {
        this.ROWNO = ROWNO;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getWQD_ANSWER_ROLE() {
        return WQD_ANSWER_ROLE;
    }

    public void setWQD_ANSWER_ROLE(String WQD_ANSWER_ROLE) {
        this.WQD_ANSWER_ROLE = WQD_ANSWER_ROLE;
    }

    public String getWQD_CONTEXT() {
        return WQD_CONTEXT;
    }

    public void setWQD_CONTEXT(String WQD_CONTEXT) {
        this.WQD_CONTEXT = WQD_CONTEXT;
    }

    public String getWQD_ID() {
        return WQD_ID;
    }

    public void setWQD_ID(String WQD_ID) {
        this.WQD_ID = WQD_ID;
    }

    public String getWQD_QUESTION() {
        return WQD_QUESTION;
    }

    public void setWQD_QUESTION(String WQD_QUESTION) {
        this.WQD_QUESTION = WQD_QUESTION;
    }
}
