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
     * obj : [{"ROWNO":1,"VF_VIDEO_ID":141,"VF_VIDEO_NAME":"阿达.mp4","VF_VIDEO_NAME2":"阿达.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470014169801.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 09:15:45","VF_UPDATE_TIME":"2016-08-01 09:15:45"},{"ROWNO":2,"VF_VIDEO_ID":135,"VF_VIDEO_NAME":"VID_20160331_143419.mp4","VF_VIDEO_NAME2":"VID_20160331_143419.mp4","NO":23,"VF_VIDEO_PATH":"/page/video/1469783954230.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 17:19:02","VF_UPDATE_TIME":"2016-07-29 17:19:02"},{"ROWNO":3,"VF_VIDEO_ID":134,"VF_VIDEO_NAME":"小猪佩奇＆nbsp;070＆nbsp;去游泳.mp4","VF_VIDEO_NAME2":"小猪佩奇＆nbsp;070＆nbsp;去游泳.mp4","NO":26,"VF_VIDEO_PATH":"/page/video/1469783798121.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 17:16:22","VF_UPDATE_TIME":"2016-07-29 17:16:22"},{"ROWNO":4,"VF_VIDEO_ID":133,"VF_VIDEO_NAME":"小猪佩奇＆nbsp;070＆nbsp;去游泳.mp4","VF_VIDEO_NAME2":"小猪佩奇＆nbsp;070＆nbsp;去游泳.mp4","NO":26,"VF_VIDEO_PATH":"/page/video/1469779341260.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 16:02:05","VF_UPDATE_TIME":"2016-07-29 16:02:05"},{"ROWNO":5,"VF_VIDEO_ID":132,"VF_VIDEO_NAME":"小猪佩奇＆nbsp;070＆nbsp;去游泳.mp4","VF_VIDEO_NAME2":"小猪佩奇＆nbsp;070＆nbsp;去游泳.mp4","NO":26,"VF_VIDEO_PATH":"/page/video/1469779201451.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 15:59:46","VF_UPDATE_TIME":"2016-07-29 15:59:46"},{"ROWNO":6,"VF_VIDEO_ID":130,"VF_VIDEO_NAME":"VID_20160331_143419.mp4","VF_VIDEO_NAME2":"VID_20160331_143419.mp4","NO":23,"VF_VIDEO_PATH":"/page/video/1469762112948.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 11:15:05","VF_UPDATE_TIME":"2016-07-29 11:15:05"},{"ROWNO":7,"VF_VIDEO_ID":124,"VF_VIDEO_NAME":"阿达.mp4","VF_VIDEO_NAME2":"阿达.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1469760006929.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 10:39:55","VF_UPDATE_TIME":"2016-07-29 10:39:55"},{"ROWNO":8,"VF_VIDEO_ID":122,"VF_VIDEO_NAME":"阿达.mp4","VF_VIDEO_NAME2":"阿达.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1469759144104.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 10:25:32","VF_UPDATE_TIME":"2016-07-29 10:25:32"},{"ROWNO":9,"VF_VIDEO_ID":121,"VF_VIDEO_NAME":"阿达.mp4","VF_VIDEO_NAME2":"阿达.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1469758961196.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 10:22:30","VF_UPDATE_TIME":"2016-07-29 10:22:30"},{"ROWNO":10,"VF_VIDEO_ID":120,"VF_VIDEO_NAME":"阿达.mp4","VF_VIDEO_NAME2":"阿达.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1469758622726.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-29 10:16:51","VF_UPDATE_TIME":"2016-07-29 10:16:51"},{"ROWNO":11,"VF_VIDEO_ID":118,"VF_VIDEO_NAME":"误删测试.mp4","VF_VIDEO_NAME2":"误删测试.mp4","NO":8,"VF_VIDEO_PATH":"/page/video/1469694189879.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-28 16:23:02","VF_UPDATE_TIME":"2016-07-28 16:23:02"},{"ROWNO":12,"VF_VIDEO_ID":117,"VF_VIDEO_NAME":"误删测试.mp4","VF_VIDEO_NAME2":"误删测试.mp4","NO":8,"VF_VIDEO_PATH":"/page/video/1469686420686.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-28 14:13:33","VF_UPDATE_TIME":"2016-07-28 14:13:33"},{"ROWNO":13,"VF_VIDEO_ID":114,"VF_VIDEO_NAME":"年长春市法制建设十件实事新闻发布会_标清.WMV","VF_VIDEO_NAME2":"年长春市法制建设十件实事新闻发布会_标清.WMV","NO":24,"VF_VIDEO_PATH":"/page/video/1469609191682.WMV","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-27 16:46:28","VF_UPDATE_TIME":"2016-07-27 16:46:28"},{"ROWNO":14,"VF_VIDEO_ID":113,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.flv","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.flv","NO":30,"VF_VIDEO_PATH":"/page/video/1469609128577.flv","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-27 16:45:25","VF_UPDATE_TIME":"2016-07-27 16:45:25"},{"ROWNO":15,"VF_VIDEO_ID":112,"VF_VIDEO_NAME":"年长春市法制建设十件实事新闻发布会_标清.WMV","VF_VIDEO_NAME2":"年长春市法制建设十件实事新闻发布会_标清.WMV","NO":24,"VF_VIDEO_PATH":"/page/video/1469604238455.WMV","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-27 15:23:55","VF_UPDATE_TIME":"2016-07-27 15:23:55"},{"ROWNO":16,"VF_VIDEO_ID":111,"VF_VIDEO_NAME":"南方都市报：别让\u201c监狱发明\u201d损害司法公信力[看东方]_标清.flv","VF_VIDEO_NAME2":"南方都市报：别让\u201c监狱发明\u201d损害司法公信力[看东方]_标清.flv","NO":33,"VF_VIDEO_PATH":"/page/video/1469603229474.flv","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-27 15:07:06","VF_UPDATE_TIME":"2016-07-27 15:07:06"},{"ROWNO":17,"VF_VIDEO_ID":110,"VF_VIDEO_NAME":"QQ.mp4","VF_VIDEO_NAME2":"QQ.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1469602378266.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-27 14:52:54","VF_UPDATE_TIME":"2016-07-27 14:52:54"},{"ROWNO":18,"VF_VIDEO_ID":109,"VF_VIDEO_NAME":"陈安之：企业要以行业的世界顶尖为目标.云商怎么做mp4_标清.flv","VF_VIDEO_NAME2":"陈安之：企业要以行业的世界顶尖为目标.云商怎么做mp4_标清.flv","NO":34,"VF_VIDEO_PATH":"/page/video/1469601651323.flv","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-27 14:40:48","VF_UPDATE_TIME":"2016-07-27 14:40:48"},{"ROWNO":19,"VF_VIDEO_ID":108,"VF_VIDEO_NAME":"QQ.mp4","VF_VIDEO_NAME2":"QQ.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1469601575923.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-27 14:39:29","VF_UPDATE_TIME":"2016-07-27 14:39:29"},{"ROWNO":20,"VF_VIDEO_ID":107,"VF_VIDEO_NAME":"南方都市报：别让\u201c监狱发明\u201d损害司法公信力[看东方]_标清.flv","VF_VIDEO_NAME2":"南方都市报：别让\u201c监狱发明\u201d损害司法公信力[看东方]_标清.flv","NO":33,"VF_VIDEO_PATH":"/page/video/1469601309295.flv","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-07-27 14:35:06","VF_UPDATE_TIME":"2016-07-27 14:35:06"}]
     */

    private boolean success;
    private String msg;
    /**
     * ROWNO : 1
     * VF_VIDEO_ID : 141
     * VF_VIDEO_NAME : 阿达.mp4
     * VF_VIDEO_NAME2 : 阿达.mp4
     * NO : 6
     * VF_VIDEO_PATH : /page/video/1470014169801.mp4
     * VF_VIDEO_STATE : 0
     * VF_UP_TIME : 2016-08-01 09:15:45
     * VF_UPDATE_TIME : 2016-08-01 09:15:45
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

        public String getVF_UPDATE_TIME() {
            return VF_UPDATE_TIME;
        }

        public void setVF_UPDATE_TIME(String VF_UPDATE_TIME) {
            this.VF_UPDATE_TIME = VF_UPDATE_TIME;
        }
    }
}
