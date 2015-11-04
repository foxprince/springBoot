package cn.anthony.boot.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.Busi;
import cn.anthony.boot.doman.BusiCode;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.BusiCodeService;
import cn.anthony.boot.service.BusiService;

@Controller
@RequestMapping(value = "/busiCode")
@SessionAttributes({ "busiCode" })
public class BusiCodeController {
    @Resource
    BusiService busiService;
    @Resource
    BusiCodeService service;

    @ModelAttribute
    public BusiCode setUpForm(@RequestParam(required = false) Long busiId, @RequestParam(required = false) Long id, Model m) {
	m.addAttribute("activeMap", busiService.getActiveMap());
	m.addAttribute("busiMap", busiService.getBusiMap());
	if (id == null) {
	    BusiCode item = new BusiCode();
	    if (busiId != null) {
		Busi busi = busiService.findById(busiId);
		m.addAttribute("busi", busi);
		item.setBusi(busi);
	    }
	    return item;
	} else {
	    return service.findById(id);
	}
    }

    @RequestMapping(value = { "/add" })
    public String adds(BusiCode busiCode, final RedirectAttributes redirectAttributes, Model model, SessionStatus status) {
	service.create(busiCode);
	status.setComplete();
	redirectAttributes.addFlashAttribute("message", "添加成功 :" + busiCode.getCode());
	return "redirect:list?busiId=" + busiCode.getBusi().getId();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam Long id, Model m) {
	BusiCode item = service.findById(id);
	m.addAttribute(item);
	return list(item.getBusi().getId(), m);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute BusiCode busiCode, final RedirectAttributes redirectAttributes, SessionStatus status) throws EntityNotFound {
	String message = "修改成功";
	service.update(busiCode);
	status.setComplete();
	redirectAttributes.addFlashAttribute("message", message);
	return "redirect:list?busiId=" + busiCode.getBusi().getId();
    }

    @RequestMapping(value = { "/" })
    public String index(@RequestParam(required = false) Long busiId, Model m, SessionStatus status) {
	status.setComplete();
	return list(busiId, m);
    }

    @RequestMapping(value = { "/index", "/list" })
    public String list(@RequestParam(required = false) Long busiId, Model m) {
	if (busiId != null)
	    m.addAttribute("itemList", service.findByBusi(busiId));// busiService.findById(busiId).getCodeList());
	return "/dr/busiCode";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Long id, final RedirectAttributes redirectAttributes) throws EntityNotFound {
	BusiCode item = service.delete(id);
	String message = "业务代码： " + item.getCode() + " 成功删除";
	redirectAttributes.addFlashAttribute("message", message);
	return "redirect:list?busiId=" + item.getBusi().getId();
    }

}
