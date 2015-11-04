package com.bestv.sdk.bean;

/**
 * 
 * tradeCode string 支付订单号（百视通平台订单id） price string 实际支付金额，金额单位：分 completeTime
 * string 支付完成时间(GMT 1970到支付完成毫秒数) platform string 平台名称: MIGU-DDO（咪咕DDO） orderId
 * string 订单号（CP业务订单流水号） passData string CP透传参数，UTF-8进行UrlEncode platTradeCode
 * string 平台订单号(保留字段，暂不支持) sign string 数据签名，签名算法见：5.支付通知签名算法
 * 
 * @author zj
 *
 */
public class PayResultReq {
    String tradeCode;// string 支付订单号（百视通平台订单id）
    Integer price;// string 实际支付金额，金额单位：分
    Long completeTime;// string 支付完成时间(GMT 1970到支付完成毫秒数)
    String platform;// string 平台名称: MIGU-DDO（咪咕DDO）
    String orderId;// string 订单号（CP业务订单流水号）
    String passData;// string CP透传参数，UTF-8进行UrlEncode
    String platTradeCode;// string 平台订单号(保留字段，暂不支持)
    String sign;// string 数据签名，签名算法见：5.支付通知签名算法

    public String getTradeCode() {
	return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
	this.tradeCode = tradeCode;
    }

    public Integer getPrice() {
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public Long getCompleteTime() {
	return completeTime;
    }

    public void setCompleteTime(Long completeTime) {
	this.completeTime = completeTime;
    }

    public String getPlatform() {
	return platform;
    }

    public void setPlatform(String platform) {
	this.platform = platform;
    }

    public String getOrderId() {
	return orderId;
    }

    public void setOrderId(String orderId) {
	this.orderId = orderId;
    }

    public String getPassData() {
	return passData;
    }

    public void setPassData(String passData) {
	this.passData = passData;
    }

    public String getPlatTradeCode() {
	return platTradeCode;
    }

    public void setPlatTradeCode(String platTradeCode) {
	this.platTradeCode = platTradeCode;
    }

    public String getSign() {
	return sign;
    }

    public void setSign(String sign) {
	this.sign = sign;
    }

}
