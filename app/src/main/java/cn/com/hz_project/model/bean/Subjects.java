package cn.com.hz_project.model.bean;

/**
 * ==================================
 * Created by wangl on 2016/7/16.时间10:17
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class Subjects {


    /**
     * NBD_CREATE_TIME : 1468489548000
     * NBD_ID : 1
     * NBD_PICTURE_URL : url1
     * NBD_TITLE : 标题1
     * ROWNO : 2
     * SUBSTR(NBD_CONTEXT,1,200) : 内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
     */

    private long NBD_CREATE_TIME;
    private int NBD_ID;
    private String NBD_PICTURE_URL;
    private String NBD_TITLE;
    private int ROWNO;
    private String SUBSTR;


    public long getNBD_CREATE_TIME() {
        return NBD_CREATE_TIME;
    }

    public void setNBD_CREATE_TIME(long NBD_CREATE_TIME) {
        this.NBD_CREATE_TIME = NBD_CREATE_TIME;
    }

    public int getNBD_ID() {
        return NBD_ID;
    }

    public void setNBD_ID(int NBD_ID) {
        this.NBD_ID = NBD_ID;
    }

    public String getNBD_PICTURE_URL() {
        return NBD_PICTURE_URL;
    }

    public void setNBD_PICTURE_URL(String NBD_PICTURE_URL) {
        this.NBD_PICTURE_URL = NBD_PICTURE_URL;
    }

    public String getNBD_TITLE() {
        return NBD_TITLE;
    }

    public void setNBD_TITLE(String NBD_TITLE) {
        this.NBD_TITLE = NBD_TITLE;
    }

    public int getROWNO() {
        return ROWNO;
    }

    public void setROWNO(int ROWNO) {
        this.ROWNO = ROWNO;
    }

    public String getSUBSTR() {
        return SUBSTR;
    }

    public void setSUBSTR(String SUBSTR) {
        this.SUBSTR = SUBSTR;
    }

    @Override
    public String toString() {
        return "s";
    }
}
