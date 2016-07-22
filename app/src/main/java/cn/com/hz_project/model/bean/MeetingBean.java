package cn.com.hz_project.model.bean;

import java.util.List;

/**
 * Created by Tan on 2016/7/16 0016.
 */
public class MeetingBean {


    /**
     * success : true
     * msg : 查询会议列表成功
     * obj : [{"ROWNO":1,"MBD_ID":6,"MBD_NAME":"阿萨德","STIME":"28-11月-99 12.11.00.000000 上午","ETIME":"15-7月 -16 11.07.59.000000 上午","MBD_REMARKS":null},{"ROWNO":2,"MBD_ID":1,"MBD_NAME":"人民代表大会1","STIME":"13-7月 -14 04.54.03.000000 上午","ETIME":"13-7月 -14 04.54.03.000000 上午","MBD_REMARKS":null},{"ROWNO":3,"MBD_ID":2,"MBD_NAME":"会议2","STIME":"15-7月 -16 03.07.32.000000 上午","ETIME":"16-7月 -16 03.07.33.000000 上午","MBD_REMARKS":"0"},{"ROWNO":4,"MBD_ID":3,"MBD_NAME":"会议2","STIME":"15-7月 -16 04.07.15.000000 下午","ETIME":"16-7月 -16 04.07.15.000000 下午","MBD_REMARKS":null},{"ROWNO":5,"MBD_ID":5,"MBD_NAME":"在上大三的","STIME":"16-7月 -16 10.07.42.000000 上午","ETIME":"17-7月 -16 10.07.43.000000 上午","MBD_REMARKS":null},{"ROWNO":6,"MBD_ID":4,"MBD_NAME":"阿萨德","STIME":"16-7月 -16 10.07.44.000000 上午","ETIME":"17-7月 -16 10.07.44.000000 上午","MBD_REMARKS":null},{"ROWNO":7,"MBD_ID":7,"MBD_NAME":"去问","STIME":"17-7月 -16 01.07.09.000000 下午","ETIME":"18-7月 -16 01.07.09.000000 下午","MBD_REMARKS":"11"}]
     */

    private boolean success;
    private String msg;
    /**
     * ROWNO : 1
     * MBD_ID : 6
     * MBD_NAME : 阿萨德
     * STIME : 28-11月-99 12.11.00.000000 上午
     * ETIME : 15-7月 -16 11.07.59.000000 上午
     * MBD_REMARKS : null
     */

    private List<ObjBean> obj;

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

    @Override
    public String toString() {
        return "MeetingBean{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", obj=" + obj +
                '}';
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        private int ROWNO;
        private int MBD_ID;
        private String MBD_NAME;
        private String STIME;
        private String ETIME;
        private String MBD_REMARKS;

        public int getROWNO() {
            return ROWNO;
        }

        public void setROWNO(int ROWNO) {
            this.ROWNO = ROWNO;
        }

        public int getMBD_ID() {
            return MBD_ID;
        }

        public void setMBD_ID(int MBD_ID) {
            this.MBD_ID = MBD_ID;
        }

        public String getMBD_NAME() {
            return MBD_NAME;
        }

        public void setMBD_NAME(String MBD_NAME) {
            this.MBD_NAME = MBD_NAME;
        }

        public String getSTIME() {
            return STIME;
        }

        @Override
        public String toString() {
            return "ObjBean{" +
                    "ROWNO=" + ROWNO +
                    ", MBD_ID=" + MBD_ID +
                    ", MBD_NAME='" + MBD_NAME + '\'' +
                    ", STIME='" + STIME + '\'' +
                    ", ETIME='" + ETIME + '\'' +
                    ", MBD_REMARKS='" + MBD_REMARKS + '\'' +
                    '}';
        }

        public void setSTIME(String STIME) {
            this.STIME = STIME;
        }

        public String getETIME() {
            return ETIME;
        }

        public void setETIME(String ETIME) {
            this.ETIME = ETIME;
        }

        public String getMBD_REMARKS() {
            return MBD_REMARKS;
        }

        public void setMBD_REMARKS(String MBD_REMARKS) {
            this.MBD_REMARKS = MBD_REMARKS;
        }
    }
}
