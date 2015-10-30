package cn.anthony.boot.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import cn.anthony.boot.doman.SpecialPhone;
import cn.anthony.boot.service.SpecialPhoneService;
import cn.anthony.boot.util.ControllerUtil;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {
	@Resource
	SpecialPhoneService service;
	
	@RequestMapping(value={"/list"})
	public String listPage(@Valid PageRequest pageRequest,BindingResult result,Model m,SessionStatus status) {
		if(result.hasErrors()) 
            return "/dr/phoneList";
        Page<SpecialPhone> drPage = service.findAll(pageRequest.page,pageRequest.size);
		ControllerUtil.setPageVariables(m, drPage);
		return "/dr/phoneList";
	}
	
	@RequestMapping(value = "/addSpecialPhone", method = RequestMethod.GET)
	public String showFormCustomTag(Model model) {
		model.addAttribute("codeConfig", new SpecialPhone());
		return "dr/codeForm";
	}

	@RequestMapping(value = "/codeConfig.json", method = RequestMethod.POST)
	public @ResponseBody
	ValidationResponse processFormAjaxJson(@ModelAttribute(value = "codeConfig") @Valid SpecialPhone cconfig, BindingResult result) {
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
	public String processFormAjax(@ModelAttribute(value = "codeConfig") @Valid SpecialPhone cconfig, BindingResult result){
		if(result.hasErrors()) {
			return "dr/codeForm";
		} 
		else {
			return "dr/codeList";
		}
	}

	public void validate(SpecialPhone cconfig, Errors errors) {
		if (StringUtils.isEmpty(cconfig.getPhone())) {
			errors.rejectValue("spId", "spId.null", "spId非法");
			return;
		}
	}

}
