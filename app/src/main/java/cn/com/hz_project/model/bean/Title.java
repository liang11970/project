package cn.com.hz_project.model.bean;

/**
 * Created by peng on 2016/7/18.
 */
public class Title {
    private int iamge;
    private String name;
    private String date;

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public int getIamge() {
        return iamge;
    }

    public void setIamge(int iamge) {
        this.iamge = iamge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String jianjie;

    public Title(int iamge, String name, String jianjie, String date) {
        this.iamge = iamge;
        this.name = name;
        this.jianjie = jianjie;
        this.date = date;
    }




}
