package com.bestv.sdk.bean;

import java.io.Serializable;

/**
 * 对外接口请求信息.
 *
 * @author <a href="http://www.jiangzezhou.com">jiangzezhou</a>
 * @version 1.0.0.0, 6/12/15 17::16
 */
public class ExtRequestInfo implements Serializable {

    /**
     * CP订单�?.
     */
    private String orderId;

    /**
     * 加密手机�?.
     */
    private String mobile;

    /**
     * 真实手机号码.
     */
    private String realMobile;

    /**
     * IMSI
     */
    private String imsi;

    /**
     * imei.
     */
    private String imei;

    /**
     * 运营商类�?.
     */
    private String carrierType;

    /**
     * int型运营商类型.
     */
    private int factCarrierType;

    /**
     * 通知地址.
     */
    private String notifyUrl;

    /**
     * 应用appid
     */
    private String appid;

    /**
     * int类型appid.
     */
    private int factAppid;

    /**
     * �?发�?�id.
     */
    private String devId;


    /**
     * int类型devId.
     */
    private int factDevId;


    /**
     * 产品id.
     */
    private String productId;

    /**
     * 平台名称.
     */
    private String platformName;

    /**
     * 产品名称.
     */
    private String productName;

    /**
     * ip地址.
     */
    private long ip;

    /**
     * 校验是否成功.
     */
    private boolean signPass;


    /**
     * 渠道id
     */
    private String channelId;

    private int factChannelId;

    private String appkey;

    /**
     * 保留字段
     */
    private String ext;

    /**
     * 透传字段.
     */
    private String passData;

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }


    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public int getFactAppid() {
        return factAppid;
    }

    public void setFactAppid(int factAppid) {
        this.factAppid = factAppid;
    }

    public int getFactDevId() {
        return factDevId;
    }

    public void setFactDevId(int factDevId) {
        this.factDevId = factDevId;
    }

    public long getIp() {
        return ip;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getFactChannelId() {
        return factChannelId;
    }

    public void setFactChannelId(int factChannelId) {
        this.factChannelId = factChannelId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getFactCarrierType() {
        return factCarrierType;
    }

    public void setFactCarrierType(int factCarrierType) {
        this.factCarrierType = factCarrierType;
    }

    public String getRealMobile() {
        return realMobile;
    }

    public void setRealMobile(String realMobile) {
        this.realMobile = realMobile;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public boolean isSignPass() {
        return signPass;
    }

    public void setSignPass(boolean signPass) {
        this.signPass = signPass;
    }

    public String getPassData() {
        return passData;
    }

    public void setPassData(String passData) {
        this.passData = passData;
    }

    @Override
    public String toString() {
        return "ExtRequestInfo{" +
                "orderId='" + orderId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", realMobile='" + realMobile + '\'' +
                ", imsi='" + imsi + '\'' +
                ", imei='" + imei + '\'' +
                ", carrierType='" + carrierType + '\'' +
                ", factCarrierType=" + factCarrierType +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", appid='" + appid + '\'' +
                ", factAppid=" + factAppid +
                ", devId='" + devId + '\'' +
                ", factDevId=" + factDevId +
                ", productId='" + productId + '\'' +
                ", platformName='" + platformName + '\'' +
                ", productName='" + productName + '\'' +
                ", ip=" + ip +
                ", signPass=" + signPass +
                ", channelId='" + channelId + '\'' +
                ", factChannelId=" + factChannelId +
                ", appkey='" + appkey + '\'' +
                ", ext='" + ext + '\'' +
                ", passData='" + passData + '\'' +
                '}';
    }
}
