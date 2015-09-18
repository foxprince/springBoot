package cn.anthony.boot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.anthony.boot.doman.DrPushModel;
import cn.anthony.boot.service.DrPushService;
import cn.anthony.boot.util.RefactorUtil;
import cn.anthony.boot.util.Resource;

@Controller
public class MrController {
	@Autowired
	DrPushService pushService;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	/**
	 * 该Controller的所有方法在调用前，先执行此@ModelAttribute方法
	 */
	@ModelAttribute
    public void preRun() {
        System.out.println("Test Pre-Run");
    }
	
	@RequestMapping(value="/hzpz",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String hzpz(@ModelAttribute("hzpzMr") HzpzMr item)  {
		System.out.println(request.getRemoteAddr());
		System.out.println(RefactorUtil.getObjectParaMap(item));
		boolean bool = pushService.push(Resource.LLTX_URL, toPushModelFromHzpz(item));
		System.out.println("forward:"+bool);
		return "OK";
    }
	private DrPushModel toPushModelFromHzpz(HzpzMr item) {
		return new DrPushModel(item.mobile,item.mo,item.port,item.linkid,item.time,item.status,item.price);
	}

	@RequestMapping(value="/zxt",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String zxt(@ModelAttribute("zxtMr") ZxtMr item)  {
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
