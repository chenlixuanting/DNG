package cn.guet.navigator.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户信息
 *
 * @author Administrator
 */
public class User implements Serializable {

    private static final long serialVersionUID = 7629060401141713559L;

    /**
     * 主键
     */
    private String userId;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 身份证号码
     */
    private String idCardNumber;

    /**
     * 车牌号码
     */
    private String plateNumber;

    /**
     * 真实姓名
     */
    private String username;

    /**
     * 产品激活码
     */
    private String cdKey;

    /**
     * 出生年月日
     */
    private Date birthday;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 身份证正面
     */
    private String idCardFrontPic;

    /**
     * 身份证反面
     */
    private String idCardReversePic;

    /**
     * 驾驶证
     */
    private String driverLicensePic;

    /**
     * 行车证
     */
    private String driverPermitPic;

    /**
     * 生成时间
     */
    private Timestamp createTime;

    /**
     * 时间戳
     */
    private Timestamp updateTime;

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getCdKey() {
        return cdKey;
    }

    public void setCdKey(String cdKey) {
        this.cdKey = cdKey;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getIdCardFrontPic() {
        return idCardFrontPic;
    }

    public void setIdCardFrontPic(String idCardFrontPic) {
        this.idCardFrontPic = idCardFrontPic;
    }

    public String getIdCardReversePic() {
        return idCardReversePic;
    }

    public void setIdCardReversePic(String idCardReversePic) {
        this.idCardReversePic = idCardReversePic;
    }

    public String getDriverLicensePic() {
        return driverLicensePic;
    }

    public void setDriverLicensePic(String driverLicensePic) {
        this.driverLicensePic = driverLicensePic;
    }

    public String getDriverPermitPic() {
        return driverPermitPic;
    }

    public void setDriverPermitPic(String driverPermitPic) {
        this.driverPermitPic = driverPermitPic;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User() {
    }
}
