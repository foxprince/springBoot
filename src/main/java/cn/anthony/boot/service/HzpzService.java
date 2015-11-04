package cn.anthony.boot.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import com.anthony.util.http.HTTPUtil;

import cn.anthony.boot.util.RefactorUtil;
import cn.anthony.util.DateUtil;

/**
 * 杭州平治短代渠道 Http提交,默认采用Get方法
 * 
 * 接收成功后返回：OK
 * 例子：http://127.0.0.1/sms/sync.jsp?mobile=...&mo=...&port=...&linkid=...&time=.
 * ....&status=DELIVRD&price=...
 * 
 * 同步链接参数说明：
 * 
 * mobile 手机号 mo 上行内容 port 接入号 linkid linkid(唯一) time 接收时间（yyyy-MM-dd HH:mm:ss）
 * status 状态值，(固定值DELIVRD，表示成功) price 计费金额（单位分）
 * 
 * 说明： 1）mo上行内容和time这两个参数传输前做url编码
 * 2）我方服务器ip：60.191.122.30，60.12.140.148，请合作方做好ip访问限制。另外，如果需要模拟渠道方接口，请对60.12.140
 * .94也开放权限。 发送 020#0402（模糊） 到 1065800883246 Created by zj on 15-9-9. implements
 * CommandLineRunner
 */
@Service
public class HzpzService {
    private static final String SPNUM = "1065800883246";
    private static final String STATUS = "DELIVRD";
    private static final String URL = "http://60.191.122.30/sms/sync.jsp";

    public boolean mo(String mobile, String mo, String linkId, int price) {
	boolean bool = false;
	HzpzMr item = new HzpzMr(mobile, mo, SPNUM, linkId, DateUtil.getCurrentTime(), STATUS, price);
	try {
	    String resp = HTTPUtil.get(URL, HTTPUtil.toNameValuePairs(RefactorUtil.getObjectParaMap(item)));
	    if (resp.trim().equalsIgnoreCase("ok"))
		bool = true;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return bool;
    }

    class HzpzMr {
	String mobile;
	String mo;
	String port;
	String linkid;
	String time;
	String status = "DELIVRD";
	int price;

	/**
	 * @param mobile
	 * @param mo
	 * @param port
	 * @param linkid
	 * @param time
	 * @param status
	 * @param price
	 */
	public HzpzMr(String mobile, String mo, String port, String linkid, String time, String status, int price) {
	    super();
	    this.mobile = mobile;
	    this.mo = mo;
	    this.port = port;
	    this.linkid = linkid;
	    this.time = time;
	    this.status = status;
	    this.price = price;
	}

	public HzpzMr() {
	}

    }

    public void run(String... args) throws Exception {
	try {
	    System.out.println(java.net.URLEncoder.encode("上行内容测试", "utf-8"));
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	}
	System.out.println(mo("13311111111", "上行内容测试", "dfffff", 0));
    }
}
