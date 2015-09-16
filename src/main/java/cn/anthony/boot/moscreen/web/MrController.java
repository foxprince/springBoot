package cn.anthony.boot.moscreen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.anthony.moscreen.util.RefactorUtil;

@Controller
public class MrController {

	/**
	 * 该Controller的所有方法在调用前，先执行此@ModelAttribute方法
	 */
	@ModelAttribute
    public void preRun() {
        System.out.println("Test Pre-Run");
    }
	
	@RequestMapping(value="/hzpz",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String hzpz(@ModelAttribute("hzpzMr") HzpzMr item)  {
    	System.out.println(RefactorUtil.getObjectParaMap(item));
		return "OK";
    }
	@RequestMapping(value="/zxt",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String zxt(@ModelAttribute("zxtMr") ZxtMr item)  {
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
