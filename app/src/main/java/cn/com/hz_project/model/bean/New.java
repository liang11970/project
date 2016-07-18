package cn.com.hz_project.model.bean;

import java.util.List;

/**
 * ==================================
 * Created by wangl on 2016/7/16.时间9:48
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class New<T>{


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

    private String falg;

    //用来模仿Data
    private T subjects;






    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(falg);
        if (null != subjects) {
            sb.append(" subjects:" + subjects.toString());
        }
        return sb.toString();
    }
}
