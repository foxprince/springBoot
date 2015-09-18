package cn.anthony.boot.web;

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
public class MrController extends BaseController {
	@Autowired
	DrPushService pushService;
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
		pushService.push(Resource.LLTX_URL, toPushModelFromHzpz(item));
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
}    
