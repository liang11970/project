package cn.com.projectdemos.model;

import java.util.ArrayList;

/**
 * Created by wee on 16/6/27.
 */
public class MeetingItemMode {


    public int total;
    public ArrayList<MeetingInfoMode> rows;


    public class MeetingInfoMode {
        public String bz;
        public String hy_kssj;
        public String hy_mc;
        public String hy_jssj;
        public String hy_id;
    }
}