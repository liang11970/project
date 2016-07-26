package cn.com.hz_project.model.bean;

import java.util.List;

/**
 * Created by Tan on 2016/7/25 0025.
 */
public class SignInStaffBean {

    /**
     * msg : 查询上传文件集合成功
     * obj : {"list":[{"COUNT":2,"DBD_DEPT_NAME":"监狱管理局"},{"COUNT":1,"DBD_DEPT_NAME":"办公厅"}],"total":13}
     * success : true
     */

    private String msg;
    /**
     * list : [{"COUNT":2,"DBD_DEPT_NAME":"监狱管理局"},{"COUNT":1,"DBD_DEPT_NAME":"办公厅"}]
     * total : 13
     */

    private ObjBean obj;
    private boolean success;


    @Override
    public String toString() {
        return "SignInStaffBean{" +
                "msg='" + msg + '\'' +
                ", obj=" + obj +
                ", success=" + success +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ObjBean {
        private int total;

        @Override
        public String toString() {
            return "ObjBean{" +
                    "total=" + total +
                    ", list=" + list +
                    '}';
        }

        /**
         * COUNT : 2
         * DBD_DEPT_NAME : 监狱管理局
         */



        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int COUNT;
            private String DBD_DEPT_NAME;

            @Override
            public String toString() {
                return "ListBean{" +
                        "COUNT=" + COUNT +
                        ", DBD_DEPT_NAME='" + DBD_DEPT_NAME + '\'' +
                        '}';
            }

            public int getCOUNT() {
                return COUNT;
            }

            public void setCOUNT(int COUNT) {
                this.COUNT = COUNT;
            }

            public String getDBD_DEPT_NAME() {
                return DBD_DEPT_NAME;
            }

            public void setDBD_DEPT_NAME(String DBD_DEPT_NAME) {
                this.DBD_DEPT_NAME = DBD_DEPT_NAME;
            }
        }
    }
}
