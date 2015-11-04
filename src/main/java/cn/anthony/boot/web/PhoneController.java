package cn.anthony.boot.web;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.SpecialPhone;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.SpecialPhoneService;
import cn.anthony.boot.util.ControllerUtil;
import cn.anthony.util.FileTools;
import cn.anthony.util.PhoneTools;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {
    private String PHONE_FILE_DIR = System.getProperty("user.home") + "/data/phone/";
    @Resource
    SpecialPhoneService service;

    @RequestMapping(value = { "/addPhone" })
    public String processUpload(@RequestParam String stype, @RequestParam String phoneStr, @RequestBody MultipartFile file,
	    final RedirectAttributes redirectAttributes, Model model) {
	Set<String> phoneSet = PhoneTools.getPhonesFromString(phoneStr);
	if (file != null && !file.isEmpty()) {
	    String filePath = PHONE_FILE_DIR + file.getOriginalFilename();
	    File dest = new File(filePath);
	    try {
		file.transferTo(dest);
	    } catch (IllegalStateException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    phoneSet.addAll(FileTools.quickReadFile(filePath));
	}
	redirectAttributes.addFlashAttribute("message", "成功导入记录数 :" + service.batchAdd(stype, phoneSet));
	return "redirect:list";
    }

    @RequestMapping(value = { "/list" })
    public String listPage(@ModelAttribute("pageRequest") @Valid PhoneSearch pageRequest, BindingResult result, Model m) {
	if (result.hasErrors())
	    return "/dr/phoneList";
	Page<SpecialPhone> drPage = service.findAll(pageRequest.phone, pageRequest.page, pageRequest.size);
	ControllerUtil.setPageVariables(m, drPage);
	return "/dr/phoneList";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteBaseEntity(@PathVariable Long id, final RedirectAttributes redirectAttributes) throws EntityNotFound {
	ModelAndView mav = new ModelAndView("redirect:/phone/list");
	SpecialPhone baseEntity = service.delete(id);
	String message = "手机号码： " + baseEntity.getPhone() + " 成功删除";
	redirectAttributes.addFlashAttribute("message", message);
	return mav;
    }

    public void validate(SpecialPhone cconfig, Errors errors) {
	if (StringUtils.isEmpty(cconfig.getPhone())) {
	    errors.rejectValue("spId", "spId.null", "spId非法");
	    return;
	}
    }

}
