package cn.com.hz_project.model;


import rx.Subscriber;

/**
 * 接口
 */
public interface Teach4Model {
    /**
     * 查询IP信息
     *
     * @param ip ip地址
     */
    public void queryIpInfo(String ip);
}
