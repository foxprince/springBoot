package cn.anthony.boot.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.anthony.boot.doman.CodeConfig;
import cn.anthony.boot.service.CodeConfigService;
import cn.anthony.boot.service.DrService;

@Controller
@RequestMapping(value = "/code")
public class CodeController {
	@Resource
	DrService drService;
	@Resource
	CodeConfigService service;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	/**
	 * 该Controller的所有方法在调用前，先执行此@ModelAttribute方法
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, Model m) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		m.addAttribute("spMap", drService.getSpMap());
	}

	@RequestMapping(value={"/","index","/list"})
	public String list(Model m) {
		m.addAttribute("codeConfigList", service.findAll());
		return "dr/codeList";
	}
	
	@RequestMapping(value = "/addCodeConfig", method = RequestMethod.GET)
	public String showFormCustomTag(Model model) {
		model.addAttribute("codeConfig", new CodeConfig());
		return "dr/codeForm";
	}

	@RequestMapping(value = "/codeConfig.json", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse processFormAjaxJson(@ModelAttribute(value = "codeConfig") @Valid CodeConfig cconfig, BindingResult result) {
		//自定义验证
		validate(cconfig, result);
		ValidationResponse res = new ValidationResponse();
		if (!result.hasErrors()) {
			res.setStatus("SUCCESS");
			service.create(cconfig);
		} else {
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);
		}
		return res;
	}
	
	@RequestMapping(value="/codeConfig.htm",method=RequestMethod.POST)
	public String processFormAjax(@ModelAttribute(value = "codeConfig") @Valid CodeConfig cconfig, BindingResult result){
		if(result.hasErrors()) {
			return "dr/codeForm";
		} 
		else {
			return "dr/codeList";
		}
	}

	public void validate(CodeConfig cconfig, Errors errors) {
		if (StringUtils.isEmpty(cconfig.getSpId())) {
			errors.rejectValue("spId", "spId.null", "spId非法");
			return;
		}
	}

}
