package cn.anthony.boot.web;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

@Controller
public class MrController {
	@Autowired
	DrPushService pushService;
	@Resource
	DrService drService;

	private final ExecutorService processService = Executors
			.newCachedThreadPool();

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	/**
	 * 该Controller的所有方法在调用前，先执行此@ModelAttribute方法
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * 发送 by8#a1101（模糊） 到 10655562 发送 020#0402（模糊） 到 1065800883246
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/hzpz", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String hzpz(@ModelAttribute("hzpzMr") final HzpzMr item) {
		System.out.println(request.getRemoteAddr());
		System.out.println(RefactorUtil.getObjectParaMap(item));
		String spId = "杭州平治";
		final String channelId = "乐浪通信";
		final DrEntity drEntity = new DrEntity(spId, item.mobile, item.mo, item.port,
				item.linkid, item.status, item.time, item.price);
		drService.create(drEntity);
		processService.execute(new Runnable() {
			@Override
			public void run() {
				boolean bool = pushService.push(Constant.LLTX_URL,
						toPushModelFromHzpz(item));
				System.out.println("forward:" + bool);
				drEntity.setChannelId(channelId);
				drEntity.setForwardStatus(bool ? "0" : "1");
				drEntity.setForwardTime(Calendar.getInstance().getTime());
				try {
					drService.update(drEntity);
				} catch (EntityNotFound e) {
					e.printStackTrace();
				}
			}
		});
		return "OK";
	}

	private DrPushModel toPushModelFromHzpz(HzpzMr item) {
		return new DrPushModel(item.mobile, item.mo, item.port, item.linkid,item.time, item.status, item.price);
	}

	@RequestMapping(value = "/zxt", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String zxt(@ModelAttribute("zxtMr") ZxtMr item) {
		System.out.println(request.getRemoteAddr());
		System.out.println(RefactorUtil.getObjectParaMap(item));
		return "OK";
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
