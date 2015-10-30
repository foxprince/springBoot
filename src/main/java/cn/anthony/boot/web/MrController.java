package cn.anthony.boot.web;

import java.text.ParseException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.anthony.boot.doman.DrEntity;
import cn.anthony.boot.doman.DrPushModel;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.DrPushService;
import cn.anthony.boot.service.DrService;
import cn.anthony.boot.util.Constant;
import cn.anthony.boot.util.RefactorUtil;

import com.bestv.sdk.bean.PayResultReq;

@Controller
public class MrController {
	@Autowired
	private DrPushService pushService;
	@Resource
	private DrService drService;
	private Logger debugLogger = LoggerFactory.getLogger(MrController.class);
	private Logger hzpzLogger = LoggerFactory.getLogger("hzpz");
	private Logger zxtLogger = LoggerFactory.getLogger("zxt");
	private Logger rmwLogger = LoggerFactory.getLogger("rmw");
	private Logger fhLogger = LoggerFactory.getLogger("fh");
	private Logger lltxLogger = LoggerFactory.getLogger("lltx");

	private static int deductBase = 0;

	private static final ExecutorService processService = Executors.newCachedThreadPool();
	
	public static void main(String[] args) {
		int i = 1;
		while (deductBase < 100) {
			deductBase++;final int j = i++;
			if(deductBase>Integer.MAX_VALUE)
				deductBase = 20;
			//System.out.print(j+"\t");
			if(deductBase<20||(deductBase>20&&(deductBase-20)%7!=0)) {
				System.out.println(j+"\t"+deductBase);
				processService.execute(new Runnable() { @Override public void run() {
				}});
			}
			else
				System.out.println();
		}
	}
	
	/**
	 * 发送 by8#a1101（模糊） 到 10655562 
	 * 发送 020#0402（模糊） 到 1065800883246
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/hzpz", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String hzpz(@ModelAttribute("hzpzMr") final HzpzMr item,HttpServletRequest request) {
		debugLogger.info(request.getRemoteAddr());
		hzpzLogger.info(RefactorUtil.getObjectParaMap(item).toString());
		String spId = "杭州平治";
		final DrEntity drEntity = new DrEntity(spId, item.mobile, item.mo, item.port,item.linkid, item.status, item.time, item.price);
		drEntity.setDeductFlag(1);
		drService.create(drEntity);
		forwardToChannel(toPushModelFromHzpz(item), drEntity,1);
		return "OK";
	}
	private void forwardToChannel(final DrPushModel item, final DrEntity drEntity,final Integer channel) {
		deductBase++;
		if(deductBase>Integer.MAX_VALUE)
			deductBase = 20;
		if(deductBase<20||(deductBase>20&&(deductBase-20)%7!=0)) {
			processService.execute(new Runnable() { @Override public void run() {
				switch(channel) {
					case 1:toLltx(item, drEntity); 
				}
			}});
		}
	}
	private void toLltx(final DrPushModel drPushModel,final DrEntity drEntity) {
		final String channelId = "乐浪通信";
		boolean bool = pushService.push(Constant.LLTX_URL,drPushModel);
		lltxLogger.info(RefactorUtil.getObjectParaMap(drPushModel).toString()+":" + bool);
		drEntity.setChannelId(channelId);
		drEntity.setForwardStatus(bool ? 0 : 1);
		drEntity.setDeductFlag(0);
		drEntity.setForwardTime(Calendar.getInstance().getTime());
		try {
			drService.update(drEntity);
		} catch (EntityNotFound e) {
			e.printStackTrace();
		}
	}
	private DrPushModel toPushModelFromHzpz(HzpzMr item) {
		return new DrPushModel(item.mobile, item.mo, item.port, item.linkid,item.time, item.status, item.price);
	}

	@RequestMapping(value = "/zxt", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String zxt(@ModelAttribute("zxtMr") ZxtMr item,HttpServletRequest request) {
		debugLogger.info(request.getRemoteAddr());
		zxtLogger.info(RefactorUtil.getObjectParaMap(item).toString());
		String spId = "政信通";
		final DrEntity drEntity = new DrEntity(spId, item.mobile, item.cmdid, item.orderdest,
				item.linkid, "DELIVRD", DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"), item.fee);
		drEntity.setDeductFlag(1);
		drService.create(drEntity);
		//forwardToChannel(toPushModelFromHzpz(item), drEntity,1);
		return "OK";
	}
	
	@RequestMapping(value = "/rmwmo", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String rmwmo(@ModelAttribute("rmwMo") RmwMo item,HttpServletRequest request) {
		debugLogger.info(request.getRemoteAddr());
		rmwLogger.info(RefactorUtil.getObjectParaMap(item).toString());
		String spId = "人民网";
		final DrEntity drEntity = new DrEntity(spId, item.usernumber, item.mocontent, item.spnumber,item.linkid);
		drEntity.setDeductFlag(0);
		drService.create(drEntity);
		//forwardToChannel(toPushModelFromHzpz(item), drEntity,1);
		return "result=0";
	}
	@RequestMapping(value = "/rmwdr", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String rmwdr(@ModelAttribute("rmwDr") RmwDr item,HttpServletRequest request) {
		debugLogger.info(request.getRemoteAddr());
		rmwLogger.info(RefactorUtil.getObjectParaMap(item).toString());
		String spId = "人民网";
		final DrEntity drEntity = drService.findByLinkId(spId, item.linkid);
		if(drEntity != null) {
			drEntity.setStatus(item.stat);
			drEntity.setFee(item.feecode);
			drEntity.setRecvTime(Calendar.getInstance().getTime());
			drEntity.setDeductFlag(0);
			try {
				drService.update(drEntity);
				//forwardToChannel(toPushModelFromHzpz(item), drEntity,1);
			} catch (EntityNotFound e) {
				e.printStackTrace();
			}
		}
		return "result=0";
	}
	
	@RequestMapping(value = "/rmwivr", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String rmwivr(@ModelAttribute("rmwIvr") RmwIvr item,HttpServletRequest request) {
		debugLogger.info(request.getRemoteAddr());
		rmwLogger.info(RefactorUtil.getObjectParaMap(item).toString());
		return "result=0";
	}
	
	/**
	 * 百视通支付结果回调通知
	 * @param item
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bestpr", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String bestpr(@ModelAttribute("payResultReq") PayResultReq item,HttpServletRequest request) {
		debugLogger.info(request.getRemoteAddr());
		rmwLogger.info(RefactorUtil.getObjectParaMap(item).toString());
		return "true";
	}
	
	/**
	 * 泛海ddo
	 */
	@RequestMapping(value = "/fhddo", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String fhddo(@ModelAttribute("payResultReq") final FhDdo item,HttpServletRequest request) {
		debugLogger.info(request.getRemoteAddr());
		fhLogger.info(RefactorUtil.getObjectParaMap(item).toString());
		String spId = "泛海";
		final DrEntity drEntity = new DrEntity();
		drEntity.setSpId(spId);
		drEntity.setLinkId(item.order_id);
		drEntity.setStatus("DELIVRD");
		drEntity.setPhone(item.mobile);
		drEntity.setFee(item.fee);
		try {
			drEntity.setRecvTime(DateUtils.parseDate(item.time, "yyyyMMddHHmmss"));
			drEntity.setDeductFlag(0);
			drService.create(drEntity);
			forwardToChannel(new DrPushModel(item.mobile, "", "", item.order_id,DateFormatUtils.format(drEntity.getRecvTime(), "yyyy-MM-dd HH:mm:ss"), "DELIVRD", item.fee), drEntity,1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "ok";
	}

}
class FhDdo {
	String order_id;//	String	否	我方系统唯一交易id
	Integer fee;//	String	否	请求金额 单位分 2元=200分
	String ext;//	String	是	透传参数
	String time;//	String	否	交易时间，格式：20141204180601
	String mobile;//	String	是	手机号码，不一定有该参数
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}
class RmwIvr {
	String msgtype;//	消息类型	ivr	
	String usernumber;//	手机号码	13711111111	
	String callednumber;//	被叫号码	12590623XXXX	
	Integer feecode;	//费率代码	200	单位:分
	String starttime;//	计费起始时间	2013-03-05+16%3A57%3A01.0	
	String endtime;//	计费结束时间	2013-03-05+16%3A57%3A01.0	
	String gateway;//	对应网关	cmcc	
	String productid;//	产品编号	1001	
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}
	public String getCallednumber() {
		return callednumber;
	}
	public void setCallednumber(String callednumber) {
		this.callednumber = callednumber;
	}
	public Integer getFeecode() {
		return feecode;
	}
	public void setFeecode(Integer feecode) {
		this.feecode = feecode;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	

}
class RmwMo {
	String msgtype;//	消息类型	mo
	String usernumber;//	手机号码	13711111111
	String spnumber;//	长号码	10661111
	String mocontent;//	MO消息内容	T你好
	String linkid;//	鉴权标示	1478451247
	String motime;//	上行时间	2013-03-05+16%3A56%3A47.0
	String serviceid;//	业务代码	AABBCC
	String productid;//	产品代码	1001
	String gateway;//	网关代码	cmcc
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}
	public String getSpnumber() {
		return spnumber;
	}
	public void setSpnumber(String spnumber) {
		this.spnumber = spnumber;
	}
	public String getMocontent() {
		return mocontent;
	}
	public void setMocontent(String mocontent) {
		this.mocontent = mocontent;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public String getMotime() {
		return motime;
	}
	public void setMotime(String motime) {
		this.motime = motime;
	}
	public String getServiceid() {
		return serviceid;
	}
	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

}
class RmwDr {
	String msgtype;//	消息类型	report	
	String usernumber;//	手机号码	13711111111	
	String linkid;//	鉴权标识	1478451247	
	Integer feecode;//	费率代码	200	单位:分
	String stat;//	状态报告	DELIVRD	DELIVRD 表示成功
	String reporttime;//	状态报告时间	2013-03-05+16%3A57%3A01.0	
	String gateway;//	对应网关	cmcc	
	String productid;//	产品编号	1001	
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}
	public String getLinkid() {
		return linkid;
	}
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}
	public Integer getFeecode() {
		return feecode;
	}
	public void setFeecode(Integer feecode) {
		this.feecode = feecode;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getReporttime() {
		return reporttime;
	}
	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}

}
class HzpzMr {
	String mobile;
	String mo;
	String port;
	String linkid;
	String time;
	String status = "DELIVRD";
	int price;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public HzpzMr(String mobile, String mo, String port, String linkid,
			String time, String status, int price) {
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
		super();
	}
}

class ZxtMr {
	String mobile;
	String cmdid;
	String orderdest;
	String linkid;
	int fee;

	public ZxtMr() {
		super();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCmdid() {
		return cmdid;
	}

	public void setCmdid(String cmdid) {
		this.cmdid = cmdid;
	}

	public String getOrderdest() {
		return orderdest;
	}

	public void setOrderdest(String orderdest) {
		this.orderdest = orderdest;
	}

	public String getLinkid() {
		return linkid;
	}

	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

}
