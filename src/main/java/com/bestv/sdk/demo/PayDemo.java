package com.bestv.sdk.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.bestv.sdk.bean.ExtRequestInfo;
import com.bestv.sdk.bean.ExtResponseInfo;
import com.bestv.sdk.contants.BestvParams;
import com.bestv.sdk.utils.HttpUtils;
import com.bestv.sdk.utils.JsonUtils;
import com.bestv.sdk.utils.SecurityUtils;
import com.bestv.sdk.utils.StringUtils;

/**
 * 支付接口示例.
 * @author jia.zhichao jia.zhichao@bestv.com.cn
 * @date 2015年8月18日 上午11:25:54
 * @version V1.0.0.0
 */
public class PayDemo {
    static final String HOST = "http://payproxy.bestv.com.cn/";
    //static final String HOST = "http://127.0.0.1:9990/";
    static final String PAY_HOST = HOST + "extpay";

    /**
     * 支付校验heander中key值.
     */
    static final String BESTV_SIGN_HEADER = "BESTV-SIGN";

    private static final Logger LOGGER =
            Logger.getLogger(PayDemo.class.getName());
    
	public static void main(String[] args){
		String notifyUrl = null;
		String productId = null;
		String realMobile = null;
		String orderId = null;
		String productName = null;
		ExtRequestInfo extRequestInfo = initExtRequestInfo(orderId, realMobile, productId, productName, notifyUrl);
		final ExtResponseInfo extResponseInfo = miguDdoPay(extRequestInfo);
		LOGGER.info(extResponseInfo + "");
	}

	private static ExtResponseInfo miguDdoPay(ExtRequestInfo extRequestInfo) {
		final String body = JsonUtils.bean2Json(extRequestInfo);
        //签名计算
        final String needSignStr = body + BestvParams.APPKEY;
        
        LOGGER.info("needSignStr[" + needSignStr + "]");
        final String bestvSign = SecurityUtils.md5(body + BestvParams.APPKEY);
        LOGGER.info("bestvSign[" + bestvSign + "]");
        final Map<String, String> headers = new HashMap<String, String>();
        headers.put(BESTV_SIGN_HEADER, bestvSign);
        ExtResponseInfo extResponseInfo = null;
        try {
            final String respBody = HttpUtils.postRstr(PAY_HOST, StringUtils.getBytes(body), headers);
            LOGGER.info("respBody:" + respBody);
            if (!StringUtils.isEmptyOrNull(respBody)) {
            	extResponseInfo = JsonUtils.json2Bean(respBody, ExtResponseInfo.class);
            	Map<String, String> map = JsonUtils.jsonMapString(extResponseInfo.getData());
            	LOGGER.info("smsContent:" + map.get("smsContent"));
                return extResponseInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	private static ExtRequestInfo initExtRequestInfo(String orderId,String realMobile,String productId,String productName,String notifyUrl) {
		ExtRequestInfo extRequestInfo = new ExtRequestInfo();
		extRequestInfo.setAppid(BestvParams.APPID);
		extRequestInfo.setAppkey(BestvParams.APPKEY);
		extRequestInfo.setChannelId(BestvParams.CHANNEL_ID);
		extRequestInfo.setDevId(BestvParams.DEV_ID);
		//每个计费点id对应一个价格,需要申请多个
		extRequestInfo.setProductId(productId);
		//extRequestInfo.setCarrierType("1");//运营商-移动
		//支付服务器端回调通知地址
		extRequestInfo.setNotifyUrl(notifyUrl);
		extRequestInfo.setPlatformName("MIGU-DDO");
		extRequestInfo.setProductName(productName);
		final String mobile = SecurityUtils.aesEncrypt(realMobile, BestvParams.APPKEY);
		extRequestInfo.setMobile(mobile);
		//需要透传的参数,不需要则不传入
		//extRequestInfo.setPassData("0980000099");
		//CP订单号
		extRequestInfo.setOrderId(orderId);
		return extRequestInfo;
	}


}
