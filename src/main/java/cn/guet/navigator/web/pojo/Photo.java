package cn.guet.navigator.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 照片
 *
 * @author Administrator
 */
public class Photo implements Serializable{

    private static final long serialVersionUID = 701767941819793189L;

    private String photoId;
    private String suffix;
    private String prefix;
    private Timestamp createTime;
    private Timestamp updateTime;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Photo() {
    }
}
