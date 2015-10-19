package com.bestv.sdk.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 对外接口扩展请求封装.
 *
 * @author <a href="http://www.jiangzezhou.com">jiangzezhou</a>
 * @version 1.0.0.0, 6/18/15 09::05
 */
public class ExtExtraRequestInfo implements Serializable {

    /**
     * 平台类型.
     */
    private String platformName;

    /**
     * 请求事务id.
     */
    private String tid;

    /**
     * 应用appid.
     */
    private String appid;

    private int factAppid;

    /**
     * 验证签名是否通过.
     */
    private boolean signPass;

    /**
     * 应用密钥.
     */
    private String appkey;

    /**
     * 服务类型.
     */
    private String serviceType;

    /**
     * 数据内容,�?般为json结构.
     */
    private String data;

    /**
     * data转换后的json结构.
     */
    private Map<String, String> dataParams;

    private String imsi;

    private String imei;


    private long ip;

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public int getFactAppid() {
        return factAppid;
    }

    public void setFactAppid(int factAppid) {
        this.factAppid = factAppid;
    }

    public boolean isSignPass() {
        return signPass;
    }

    public void setSignPass(boolean signPass) {
        this.signPass = signPass;
    }

    public long getIp() {
        return ip;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Map<String, String> getDataParams() {
        return dataParams;
    }

    public void setDataParams(Map<String, String> dataParams) {
        this.dataParams = dataParams;
    }

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

    @Override
    public String toString() {
        return "ExtExtraRequestInfo{" +
                "platformName='" + platformName + '\'' +
                ", tid='" + tid + '\'' +
                ", appid='" + appid + '\'' +
                ", factAppid=" + factAppid +
                ", signPass=" + signPass +
                ", appkey='" + appkey + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", data='" + data + '\'' +
                ", dataParams=" + dataParams +
                ", ip=" + ip +
                '}';
    }
}
