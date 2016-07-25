package cn.com.hz_project.model.bean;

import java.util.ArrayList;

/**
 * Created by ku on 2016/7/25.
 */
public class ServerFile {
        private boolean success;
        private String msg;
        private ArrayList<ServerFileObj> obj;

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

        public ArrayList<ServerFileObj> getObj() {
            return obj;
        }

        public void setObj(ArrayList<ServerFileObj> obj) {
            this.obj = obj;
        }

}
