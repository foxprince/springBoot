package cn.anthony.boot.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import cn.anthony.boot.util.RefactorUtil;

import com.anthony.util.http.HTTPUtil;

/**
 * 接口参考；
参数名称	类型	说明
linkid	String	单条数据唯一ID
mobile	string	点播的手机号
orderdest	string	点播号码（端口号）
cmdid	string	点播业务指令（可为空）
fee	float	单条数据金额
Ivr业务：
starttime 开始时间，格式：2010-10-01 20:00:00
endtime 结束时间，格式同上
total 时长（分钟）
implements CommandLineRunner
 */
@Service
public class ZxtService  {
	private static final String URL = "http://60.191.122.30/sms/sync.jsp";
	public static void main(String[] args) {
		//SpringApplication.run(HzpzService.class, args);
		
	}
	public boolean mo(String mobile,String mo,String destNo,String linkId,int price) {
		boolean bool = false;
		ZxtMr item = new ZxtMr(mobile,mo,destNo,linkId,price);
		try {
			String resp = HTTPUtil.get(URL, HTTPUtil.toNameValuePairs(RefactorUtil.getObjectParaMap(item)));
			if(resp.trim().equalsIgnoreCase("ok"))
				bool = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bool;
	}
	
	class ZxtMr {
	    String mobile;
	    String cmdid;
	    String orderdest;
	    String linkid;
	    int fee;
		/**
		 * @param mobile
		 * @param cmdid
		 * @param orderdest
		 * @param linkid
		 * @param fee
		 */
		public ZxtMr(String mobile, String cmdid, String orderdest,
				String linkid, int fee) {
			super();
			this.mobile = mobile;
			this.cmdid = cmdid;
			this.orderdest = orderdest;
			this.linkid = linkid;
			this.fee = fee;
		}
		

	}

	public void run(String... args) throws Exception {
		try {
			System.out.println(java.net.URLEncoder.encode("上行内容测试", "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(mo("13311111111","上行内容测试","10691234","dfffff",0));
	}
}
