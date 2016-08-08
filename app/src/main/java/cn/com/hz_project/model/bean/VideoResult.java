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
     * obj : [{"ROWNO":1,"VF_VIDEO_ID":221,"VF_VIDEO_NAME":"阿达.mp4","VF_VIDEO_NAME2":"阿达.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470380950398.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-05 15:09:07","VF_IMG_URL":"字符","VF_UPDATE_TIME":"2016-08-05 15:09:07"},{"ROWNO":2,"VF_VIDEO_ID":215,"VF_VIDEO_NAME":"视频.mp4","VF_VIDEO_NAME2":"视频.mp4","NO":6,"VF_VIDEO_PATH":"/page/video/1470104519145.mp4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-02 10:21:36","VF_IMG_URL":"http://img3.imgtn.bdimg.com/it/u=2095131399,1090624338&fm=21&gp=0.jpg","VF_UPDATE_TIME":"2016-08-02 10:21:36"},{"ROWNO":3,"VF_VIDEO_ID":209,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","NO":30,"VF_VIDEO_PATH":"/page/video/1470045782572.MP4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 18:02:46","VF_IMG_URL":"https://www.baidu.com/baidu?wd=/20110711/7623130_142714642000_2.jpg&tn=monline_4_dg","VF_UPDATE_TIME":"2016-08-01 18:02:46"},{"ROWNO":4,"VF_VIDEO_ID":208,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","NO":30,"VF_VIDEO_PATH":"/page/video/1470045766749.MP4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 18:02:31","VF_IMG_URL":"https://www.baidu.com/baidu?wd=/20110711/7623130_142714642000_2.jpg&tn=monline_4_dg","VF_UPDATE_TIME":"2016-08-01 18:02:31"},{"ROWNO":5,"VF_VIDEO_ID":207,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","NO":30,"VF_VIDEO_PATH":"/page/video/1470045743063.MP4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 18:02:07","VF_IMG_URL":"https://www.baidu.com/baidu?wd=/20110711/7623130_142714642000_2.jpg&tn=monline_4_dg","VF_UPDATE_TIME":"2016-08-01 18:02:07"},{"ROWNO":6,"VF_VIDEO_ID":206,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","NO":30,"VF_VIDEO_PATH":"/page/video/1470045713548.MP4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 18:01:37","VF_IMG_URL":"https://www.baidu.com/baidu?wd=/20110711/7623130_142714642000_2.jpg&tn=monline_4_dg","VF_UPDATE_TIME":"2016-08-01 18:01:37"},{"ROWNO":7,"VF_VIDEO_ID":205,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","NO":30,"VF_VIDEO_PATH":"/page/video/1470045704446.MP4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 18:01:28","VF_IMG_URL":"https://www.baidu.com/baidu?wd=/20110711/7623130_142714642000_2.jpg&tn=monline_4_dg","VF_UPDATE_TIME":"2016-08-01 18:01:28"},{"ROWNO":8,"VF_VIDEO_ID":204,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","NO":30,"VF_VIDEO_PATH":"/page/video/1470045670483.MP4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 18:00:54","VF_IMG_URL":"https://www.baidu.com/baidu?wd=/20110711/7623130_142714642000_2.jpg&tn=monline_4_dg","VF_UPDATE_TIME":"2016-08-01 18:00:54"},{"ROWNO":9,"VF_VIDEO_ID":203,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","NO":30,"VF_VIDEO_PATH":"/page/video/1470045571596.MP4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 17:59:15","VF_IMG_URL":"https://www.baidu.com/baidu?wd=/20110711/7623130_142714642000_2.jpg&tn=monline_4_dg","VF_UPDATE_TIME":"2016-08-01 17:59:15"},{"ROWNO":10,"VF_VIDEO_ID":202,"VF_VIDEO_NAME":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","VF_VIDEO_NAME2":"中央政法委：多项司法考核指标将取消[新闻早报]_标清.MP4","NO":30,"VF_VIDEO_PATH":"/page/video/1470045340166.MP4","VF_VIDEO_STATE":0,"VF_UP_TIME":"2016-08-01 17:55:24","VF_IMG_URL":"http://pic15.nipic.com/20110711/7623130_142714642000_2.jpg","VF_UPDATE_TIME":"2016-08-01 17:55:24"}]
     */

    private boolean success;
    private String msg;
    /**
     * ROWNO : 1
     * VF_VIDEO_ID : 221
     * VF_VIDEO_NAME : 阿达.mp4
     * VF_VIDEO_NAME2 : 阿达.mp4
     * NO : 6
     * VF_VIDEO_PATH : /page/video/1470380950398.mp4
     * VF_VIDEO_STATE : 0
     * VF_UP_TIME : 2016-08-05 15:09:07
     * VF_IMG_URL : 字符
     * VF_UPDATE_TIME : 2016-08-05 15:09:07
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
