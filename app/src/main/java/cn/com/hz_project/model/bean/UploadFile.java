package cn.com.hz_project.model.bean;

/**
 * ==================================
 * Created by wangl on 2016/7/19.时间10:01
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class UploadFile  {
    private String fid;
    private String sourceFileName;
    private String uploadFileName;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
