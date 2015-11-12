package cn.anthony.boot.web;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.PhoneHead;
import cn.anthony.boot.service.PhoneHeadService;
import cn.anthony.boot.util.ControllerUtil;

@Controller
@RequestMapping(value = "/phoneHead")
public class PhoneHeadController {
    @Resource
    PhoneHeadService service;

    @ModelAttribute
    public void init(@RequestParam(required = false) String province, Model m) {
	m.addAttribute("provinceMap", service.getAllProvince());
	m.addAttribute("cityMap", StringUtils.isEmpty(province) ? new HashMap<String, String>() : service.getAllCities(province));
    }

    @RequestMapping(value = { "/addPhoneHead" })
    public String processUpload(PhoneHead phoneHead, final RedirectAttributes redirectAttributes, Model model) {
	service.addPhoneHead(phoneHead);
	redirectAttributes.addFlashAttribute("message", "成功添加号段 :" + phoneHead.getHead());
	return "redirect:list";
    }

    @RequestMapping(value = { "/", "/list" })
    public String listPage(@ModelAttribute("pageRequest") PhoneHeadSearch pageRequest, Model m) {
	Page<PhoneHead> drPage = service.find(pageRequest.province, pageRequest.city, pageRequest.phone, pageRequest.page, pageRequest.size);
	ControllerUtil.setPageVariables(m, drPage);
	return "/dr/phoneHead";
    }

}
