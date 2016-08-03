package cn.com.hz_project.model.bean;

import java.util.List;

/**
 * ==================================
 * Created by wangl on 2016/8/1.时间9:42
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class VideoResult {


    /**
     * success : true
     * msg : 查询视频列表成功
     * obj : [{"ROWNO":1,"VF_VIDEO_ID":167,"VF_VIDEO_NAME":"RRRRRR.mp4","VF_VIDEO_NAME2":"RRRRRR.mp4","NO":10,"VF_VIDEO_PATH":"/page/video/1470033009668.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 14:29:50","VF_IMG_URL":"http://img0.imgtn.bdimg.com/it/u=1372552593,2383984082","VF_UPDATE_TIME":"2016-08-01 14:29:50"},{"ROWNO":2,"VF_VIDEO_ID":166,"VF_VIDEO_NAME":"肉肉肉肉肉肉.mp4","VF_VIDEO_NAME2":"肉肉肉肉肉肉.mp4","NO":10,"VF_VIDEO_PATH":"/page/video/1470032962863.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 14:29:03","VF_IMG_URL":"http://img5.imgtn.bdimg.com/it/u=3501925067,448385442","VF_UPDATE_TIME":"2016-08-01 14:29:03"},{"ROWNO":3,"VF_VIDEO_ID":165,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030627470.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:50:08","VF_IMG_URL":"http://picm.photophoto.cn/005/008/007/0080073258.jpg","VF_UPDATE_TIME":"2016-08-01 13:50:08"},{"ROWNO":4,"VF_VIDEO_ID":164,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030610790.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:49:51","VF_IMG_URL":"http://pic1.nipic.com/2008-09-19/200891915522560_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:49:51"},{"ROWNO":5,"VF_VIDEO_ID":162,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030582704.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:49:23","VF_IMG_URL":"http://picm.photophoto.cn/005/008/009/0080090057.jpg","VF_UPDATE_TIME":"2016-08-01 13:49:23"},{"ROWNO":6,"VF_VIDEO_ID":161,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030568904.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:49:09","VF_IMG_URL":"http://imgsrc.baidu.com/forum/pic/item/a275666b3b092e466a60fbfe.jpg","VF_UPDATE_TIME":"2016-08-01 13:49:09"},{"ROWNO":7,"VF_VIDEO_ID":160,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030555026.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:48:55","VF_IMG_URL":"http://pic27.nipic.com/20130321/9252150_173837690333_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:48:55"},{"ROWNO":8,"VF_VIDEO_ID":159,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030542700.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:48:43","VF_IMG_URL":"http://img.sucai.redocn.com/attachments/images/201003/20100315/Redocn_2010031515224376.jpg","VF_UPDATE_TIME":"2016-08-01 13:48:43"},{"ROWNO":9,"VF_VIDEO_ID":158,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030530286.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:48:30","VF_IMG_URL":"http://pic23.nipic.com/20120906/2600394_114057258135_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:48:30"},{"ROWNO":10,"VF_VIDEO_ID":157,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030516442.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:48:17","VF_IMG_URL":"http://pic28.nipic.com/20130427/9252150_175017424337_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:48:17"},{"ROWNO":11,"VF_VIDEO_ID":156,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030503239.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:48:03","VF_IMG_URL":"http://pic15.nipic.com/20110711/7623130_142714642000_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:48:03"},{"ROWNO":12,"VF_VIDEO_ID":155,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030489665.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:47:50","VF_IMG_URL":"http://picm.photophoto.cn/005/008/017/0080170433.jpg","VF_UPDATE_TIME":"2016-08-01 13:47:50"},{"ROWNO":13,"VF_VIDEO_ID":154,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030475915.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:47:36","VF_IMG_URL":"http://pic14.nipic.com/20110527/7011463_171952275366_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:47:36"},{"ROWNO":14,"VF_VIDEO_ID":153,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030464224.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:47:24","VF_IMG_URL":"http://pic3.nipic.com/20090709/2893198_075124038_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:47:24"},{"ROWNO":15,"VF_VIDEO_ID":152,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030450589.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:47:11","VF_IMG_URL":"http://www.51ksw.net/uploads/allimg/101205/13550W114-0.jpg","VF_UPDATE_TIME":"2016-08-01 13:47:11"},{"ROWNO":16,"VF_VIDEO_ID":151,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030434503.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:46:55","VF_IMG_URL":"http://pic20.nipic.com/20120406/7892613_103240820000_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:46:55"},{"ROWNO":17,"VF_VIDEO_ID":150,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030421638.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:46:42","VF_IMG_URL":"http://pic23.nipic.com/20120826/4114542_172731008363_2.jpg","VF_UPDATE_TIME":"2016-08-01 13:46:42"},{"ROWNO":18,"VF_VIDEO_ID":149,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030407137.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:46:27","VF_IMG_URL":"http://picm.photophoto.cn/005/008/007/0080071507.jpg","VF_UPDATE_TIME":"2016-08-01 13:46:27"},{"ROWNO":19,"VF_VIDEO_ID":148,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030394177.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:46:14","VF_IMG_URL":"http://picm.photophoto.cn/005/008/008/0080080139.jpg","VF_UPDATE_TIME":"2016-08-01 13:46:14"},{"ROWNO":20,"VF_VIDEO_ID":147,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470030380197.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 13:46:00","VF_IMG_URL":"http://img10.3lian.com/c1/newpic/10/08/04.jpg","VF_UPDATE_TIME":"2016-08-01 13:46:00"}]
     */

    private boolean success;
    private String msg;
    /**
     * ROWNO : 1
     * VF_VIDEO_ID : 167
     * VF_VIDEO_NAME : RRRRRR.mp4
     * VF_VIDEO_NAME2 : RRRRRR.mp4
     * NO : 10
     * VF_VIDEO_PATH : /page/video/1470033009668.mp4
     * VF_VIDEO_STATE : 0
     * VF_UP_TIME : 2016-08-01 14:29:50
     * VF_IMG_URL : http://img0.imgtn.bdimg.com/it/u=1372552593,2383984082
     * VF_UPDATE_TIME : 2016-08-01 14:29:50
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
        private int VF_VIDEO_ID;
        private String VF_VIDEO_NAME;
        private String VF_VIDEO_NAME2;
        private int NO;
        private String VF_VIDEO_PATH;
        private int VF_VIDEO_STATE;
        private String VF_UP_TIME;
        private String VF_IMG_URL;
        private String VF_UPDATE_TIME;

        public int getROWNO() {
            return ROWNO;
        }

        public void setROWNO(int ROWNO) {
            this.ROWNO = ROWNO;
        }

        public int getVF_VIDEO_ID() {
            return VF_VIDEO_ID;
        }

        public void setVF_VIDEO_ID(int VF_VIDEO_ID) {
            this.VF_VIDEO_ID = VF_VIDEO_ID;
        }

        public String getVF_VIDEO_NAME() {
            return VF_VIDEO_NAME;
        }

        public void setVF_VIDEO_NAME(String VF_VIDEO_NAME) {
            this.VF_VIDEO_NAME = VF_VIDEO_NAME;
        }

        public String getVF_VIDEO_NAME2() {
            return VF_VIDEO_NAME2;
        }

        public void setVF_VIDEO_NAME2(String VF_VIDEO_NAME2) {
            this.VF_VIDEO_NAME2 = VF_VIDEO_NAME2;
        }

        public int getNO() {
            return NO;
        }

        public void setNO(int NO) {
            this.NO = NO;
        }

        public String getVF_VIDEO_PATH() {
            return VF_VIDEO_PATH;
        }

        public void setVF_VIDEO_PATH(String VF_VIDEO_PATH) {
            this.VF_VIDEO_PATH = VF_VIDEO_PATH;
        }

        public int getVF_VIDEO_STATE() {
            return VF_VIDEO_STATE;
        }

        public void setVF_VIDEO_STATE(int VF_VIDEO_STATE) {
            this.VF_VIDEO_STATE = VF_VIDEO_STATE;
        }

        public String getVF_UP_TIME() {
            return VF_UP_TIME;
        }

        public void setVF_UP_TIME(String VF_UP_TIME) {
            this.VF_UP_TIME = VF_UP_TIME;
        }

        public String getVF_IMG_URL() {
            return VF_IMG_URL;
        }

        public void setVF_IMG_URL(String VF_IMG_URL) {
            this.VF_IMG_URL = VF_IMG_URL;
        }

        public String getVF_UPDATE_TIME() {
            return VF_UPDATE_TIME;
        }

        public void setVF_UPDATE_TIME(String VF_UPDATE_TIME) {
            this.VF_UPDATE_TIME = VF_UPDATE_TIME;
        }
    }
}
