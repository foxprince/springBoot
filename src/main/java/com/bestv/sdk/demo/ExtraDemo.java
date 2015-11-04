package com.bestv.sdk.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.bestv.sdk.bean.ExtExtraRequestInfo;
import com.bestv.sdk.bean.ExtExtraResponseInfo;
import com.bestv.sdk.contants.BestvParams;
import com.bestv.sdk.contants.DataContants;
import com.bestv.sdk.utils.HttpUtils;
import com.bestv.sdk.utils.JsonUtils;
import com.bestv.sdk.utils.SecurityUtils;
import com.bestv.sdk.utils.StringUtils;

/**
 * 扩展服务示例.
 * 
 * @author jia.zhichao jia.zhichao@bestv.com.cn
 * @date 2015年8月18日 下午3:12:44
 * @version V1.0.0.0
 */
public class ExtraDemo {
    private static final Logger LOGGER = Logger.getLogger(ExtraDemo.class.getName());

    static final String HOST = "http://payproxy.bestv.com.cn/";
    // static final String HOST = "http://127.0.0.1:9990/";
    static final String EXTRA_HOST = HOST + "extra";

    static final String BESTV_SIGN_HEADER = "BESTV-SIGN";

    public static void main(String[] args) {
	String telephone = null;
	String verifyCode = null;
	String orderId = null;
	String realMobile = null;
	String tradeCode = null;
	String transactionId = null;
	ExtExtraRequestInfo requestInfo = initRequestInfo(realMobile, tradeCode, orderId, verifyCode, telephone, transactionId);
	final ExtExtraResponseInfo responseInfo = miguDdoVerify(requestInfo);
	if (responseInfo != null && responseInfo.getCode() == 200) {
	}
    }

    private static ExtExtraRequestInfo initRequestInfo(String realMobile, String tradeCode, String orderId, String verifyCode, String telephone,
	    String transactionId) {
	ExtExtraRequestInfo extraRequestInfo = new ExtExtraRequestInfo();
	extraRequestInfo.setPlatformName("MIGU-DDO");
	extraRequestInfo.setAppid(BestvParams.APPID);
	// 由开发者自己构造,方便发生异常时诊断分析
	extraRequestInfo.setTid(BestvParams.APPID + new Date().getTime());
	// 构建DATA信息
	final Map<String, String> dataParams = new HashMap<String, String>();
	// 用户上次输入的手机号，需和上次保持一致
	extraRequestInfo.setServiceType("verify");
	dataParams.put(DataContants.TRADE_CODE, tradeCode);
	dataParams.put(DataContants.ORDERID, orderId);
	dataParams.put(DataContants.MOBILE, SecurityUtils.aesEncrypt(realMobile, BestvParams.APPKEY));
	// 短信中获取到的验证码
	dataParams.put(DataContants.VERIFY_CODE, verifyCode);
	dataParams.put(DataContants.TELEPHONE, telephone);
	dataParams.put(DataContants.TRANSATION_ID, transactionId);
	final String data = JsonUtils.bean2Json(dataParams);
	extraRequestInfo.setData(data);
	return extraRequestInfo;
    }

    /**
     * 发起支付请求 @param @param requestInfo @param @return @return
     * ExtExtraResponseInfo @throws
     */
    private static ExtExtraResponseInfo miguDdoVerify(ExtExtraRequestInfo requestInfo) {
	final String body = JsonUtils.bean2Json(requestInfo);
	final String needSignStr = body + BestvParams.APPKEY;
	LOGGER.info("needSignStr[" + needSignStr + "]");
	final String bestvSign = SecurityUtils.md5(body + BestvParams.APPKEY);
	LOGGER.info("bestvSign[" + bestvSign + "]");
	final Map<String, String> headers = new HashMap<String, String>();
	headers.put(BESTV_SIGN_HEADER, bestvSign);
	ExtExtraResponseInfo payResponseInfo = null;
	try {
	    final String respBody = HttpUtils.postRstr(EXTRA_HOST, StringUtils.getBytes(body), headers);
	    LOGGER.info(respBody);
	    if (!StringUtils.isEmptyOrNull(respBody)) {
		payResponseInfo = JsonUtils.json2Bean(respBody, ExtExtraResponseInfo.class);
		return payResponseInfo;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;

    }

}
