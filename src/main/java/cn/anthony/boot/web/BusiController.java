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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.Busi;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.BusiService;

@Controller
@RequestMapping(value = "/busi")
@SessionAttributes("busi")
public class BusiController {
    @Resource
    BusiService service;

    @ModelAttribute
    public Busi setUpForm(@RequestParam(value = "id", required = false) Long id, Model m) {
	m.addAttribute("activeMap", service.getActiveMap());
	if (id == null) {
	    return new Busi();
	} else {
	    return service.findById(id);
	}
    }

    @RequestMapping(value = { "/add" })
    public String addBusi(Busi busi, final RedirectAttributes redirectAttributes, Model model) {
	service.create(busi);
	redirectAttributes.addFlashAttribute("message", "添加成功 :" + busi.getName());
	return "redirect:list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam Long id, Model m) {
	m.addAttribute(service.findById(id));
	return list(m);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute Busi busi, final RedirectAttributes redirectAttributes, SessionStatus status) throws EntityNotFound {
	String message = "修改成功";
	service.update(busi);
	status.setComplete();
	redirectAttributes.addFlashAttribute("message", message);
	return "redirect:list";
    }

    @RequestMapping(value = { "/" })
    public String index(Model m, SessionStatus status) {
	status.setComplete();
	return list(m);
    }

    @RequestMapping(value = { "/index", "/list" })
    public String list(Model m) {
	m.addAttribute("itemList", service.findAll());
	return "/dr/busiList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteBaseEntity(@RequestParam Long id, final RedirectAttributes redirectAttributes, SessionStatus status)
	    throws EntityNotFound {
	ModelAndView mav = new ModelAndView("redirect:/busi/list");
	Busi baseEntity = service.delete(id);
	status.setComplete();
	String message = "业务： " + baseEntity.getName() + " 成功删除";
	redirectAttributes.addFlashAttribute("message", message);
	return mav;
    }

}
