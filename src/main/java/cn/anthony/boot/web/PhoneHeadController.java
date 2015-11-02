package cn.anthony.boot.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.PhoneHead;
import cn.anthony.boot.service.PhoneHeadService;
import cn.anthony.boot.util.ControllerUtil;

@Controller
@RequestMapping(value = "/phoneHead")
public class PhoneHeadController {
    @Resource
    PhoneHeadService service;

    @RequestMapping(value = { "/addPhoneHead" })
    public String processUpload(PhoneHead phoneHead, final RedirectAttributes redirectAttributes,Model model) {
	service.addPhoneHead(phoneHead);
	redirectAttributes.addFlashAttribute("message", "成功添加号段 :" + phoneHead.getHead());
	return "redirect:list";
    }

    @RequestMapping(value = { "/list" })
    public String listPage(@ModelAttribute("pageRequest") @Valid PhoneHeadSearch pageRequest, BindingResult result, Model m, SessionStatus status) {
	if (result.hasErrors())
	    return "/dr/phoneHead";
	Page<PhoneHead> drPage = service.find(pageRequest.province,pageRequest.city,pageRequest.phone, pageRequest.page, pageRequest.size);
	ControllerUtil.setPageVariables(m, drPage);
	return "/dr/phoneHead";
    }

   
}
