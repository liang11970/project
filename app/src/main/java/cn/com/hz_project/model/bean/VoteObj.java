package cn.com.hz_project.model.bean;

/**
 * Created by ku on 2016/7/28.
 * 投票详情
 * ROWNO: 2,
 * VBD_ID: 12,
 * VBD_TITLE: "123123",
 * VBD_URL: "123123",
 * VBD_STATE: 1,
 * TIME: "2016-07-28 10:00"
 */
public class VoteObj {
    private int ROWNO;
    private int VBD_ID;
    private String VBD_TITLE;
    private String VBD_URL;
    private int VBD_STATE;
    private String TIME;

    public int getROWNO() {
        return ROWNO;
    }

    public void setROWNO(int ROWNO) {
        this.ROWNO = ROWNO;
    }

    public int getVBD_ID() {
        return VBD_ID;
    }

    public void setVBD_ID(int VBD_ID) {
        this.VBD_ID = VBD_ID;
    }

    public String getVBD_TITLE() {
        return VBD_TITLE;
    }

    public void setVBD_TITLE(String VBD_TITLE) {
        this.VBD_TITLE = VBD_TITLE;
    }

    public String getVBD_URL() {
        return VBD_URL;
    }

    public void setVBD_URL(String VBD_URL) {
        this.VBD_URL = VBD_URL;
    }

    public int getVBD_STATE() {
        return VBD_STATE;
    }

    public void setVBD_STATE(int VBD_STATE) {
        this.VBD_STATE = VBD_STATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }
}
