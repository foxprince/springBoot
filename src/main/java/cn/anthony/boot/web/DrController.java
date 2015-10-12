package cn.anthony.boot.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import cn.anthony.boot.doman.DrEntity;
import cn.anthony.boot.service.DrPushService;
import cn.anthony.boot.service.DrService;
import cn.anthony.boot.util.ControllerUtil;
import cn.anthony.util.StringTools;

@Controller
@RequestMapping(value="/dr")
@SessionAttributes(value="drPageRequest")
public class DrController {
	@Autowired
	DrPushService pushService;
	@Resource
	DrService drService;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	/**
	 * 该Controller的所有方法在调用前，先执行此@ModelAttribute方法
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,HttpServletResponse response,Model m) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		m.addAttribute("spMap", drService.getSpMap());
		m.addAttribute("channelMap",drService.getChannelMap());
	}
	
	@RequestMapping(value={"/","index"})
	public String initPage(DrPageRequest pageRequest,BindingResult result,Model m,SessionStatus status) {
		status.setComplete();
		session.removeAttribute("drPageRequest");
		return listPage(new DrPageRequest(), result, m, status);
	}
	
	@RequestMapping(value={"/list"})
	public String listPage(@Valid DrPageRequest pageRequest,BindingResult result,Model m,SessionStatus status) {
		validate(pageRequest,result);
		if(result.hasErrors()) {
            return "/dr/list";
        }
		Page<DrEntity> drPage = drService.find(pageRequest.spId,pageRequest.channelId,pageRequest.phoneStr,pageRequest.beginTime,pageRequest.endTime,pageRequest.page,pageRequest.size);
		ControllerUtil.setPageVariables(m, drPage);
		return "/dr/list";
	}
	
	public void validate(DrPageRequest request, Errors errors) {
		if(!StringUtils.isEmpty(request.getPhoneStr()))
			for(String phone :StringTools.splitString(request.getPhoneStr(), " \n\t,")) 
				if(!StringTools.isValidPhone(phone)) {
					errors.rejectValue("phoneStr", "validation.negative", "手机号码非法");
					return;
				}
	}
	
}
