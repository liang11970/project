package cn.com.hz_project.model.bean;

import java.util.List;

/**
 * Created by liukun on 16/3/5.
 */
public class HttpResult{


    /**
     * success : true
     * msg : 查询成功
     * obj : [{"ROWNO":1,"NBD_ID":7,"NBD_TITLE":"重中之重","SUBSTR":"得到的","NBD_PICTURE_URL":"http://127.0.0.1:8080/WsbxMobile/newsctrl/news","TIME":"2016-07-16 12:51"},{"ROWNO":2,"NBD_ID":4,"NBD_TITLE":"标题4","SUBSTR":"内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容","NBD_PICTURE_URL":"url4","TIME":"2016-07-16 10:51"},{"ROWNO":3,"NBD_ID":1,"NBD_TITLE":"标题1","SUBSTR":"内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容","NBD_PICTURE_URL":"url1","TIME":"2016-07-14 17:45"},{"ROWNO":4,"NBD_ID":3,"NBD_TITLE":"标题3","SUBSTR":"内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容","NBD_PICTURE_URL":"url3","TIME":"2016-07-13 16:54"},{"ROWNO":5,"NBD_ID":2,"NBD_TITLE":"标题2","SUBSTR":"内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容","NBD_PICTURE_URL":"url2","TIME":"2016-07-13 16:54"},{"ROWNO":6,"NBD_ID":5,"NBD_TITLE":"标题5","SUBSTR":"内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容","NBD_PICTURE_URL":"url5","TIME":"2012-07-13 16:54"},{"ROWNO":7,"NBD_ID":6,"NBD_TITLE":"标题6","SUBSTR":"内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容","NBD_PICTURE_URL":"url6","TIME":"2011-07-13 16:54"}]
     */

    private boolean success;
    private String msg;
    /**
     * ROWNO : 1
     * NBD_ID : 7
     * NBD_TITLE : 重中之重
     * SUBSTR : 得到的
     * NBD_PICTURE_URL : http://127.0.0.1:8080/WsbxMobile/newsctrl/news
     * TIME : 2016-07-16 12:51
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

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        private int ROWNO;
        private int NBD_ID;
        private String NBD_TITLE;
        private String NBD_PICTURE_URL;
        private String TIME;

        public int getROWNO() {
            return ROWNO;
        }

        public void setROWNO(int ROWNO) {
            this.ROWNO = ROWNO;
        }

        public int getNBD_ID() {
            return NBD_ID;
        }

        public void setNBD_ID(int NBD_ID) {
            this.NBD_ID = NBD_ID;
        }

        public String getNBD_TITLE() {
            return NBD_TITLE;
        }

        public void setNBD_TITLE(String NBD_TITLE) {
            this.NBD_TITLE = NBD_TITLE;
        }


        public String getNBD_PICTURE_URL() {
            return NBD_PICTURE_URL;
        }

        public void setNBD_PICTURE_URL(String NBD_PICTURE_URL) {
            this.NBD_PICTURE_URL = NBD_PICTURE_URL;
        }

        public String getTIME() {
            return TIME;
        }

        public void setTIME(String TIME) {
            this.TIME = TIME;
        }
    }
}
