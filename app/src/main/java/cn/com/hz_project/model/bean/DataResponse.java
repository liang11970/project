package cn.com.hz_project.model.bean;

/**
 * ==================================
 * Created by wangl on 2016/7/19.时间10:05
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class DataResponse<T> {


    private boolean success;
    private String msg;

    //用来模仿Data
    private T subjects;

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

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }
}
