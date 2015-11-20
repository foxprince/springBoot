package cn.anthony.boot.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import cn.anthony.boot.doman.Busi;
import cn.anthony.boot.doman.BusiCode;
import cn.anthony.boot.service.BusiCodeService;
import cn.anthony.boot.service.BusiService;
import cn.anthony.boot.service.GenericService;

@Controller
@RequestMapping(value = "/busiCode")
public class BusiCodeController extends GenericController<BusiCode> {
    @Resource
    BusiService busiService;
    @Resource
    BusiCodeService service;

    @Override
    public BusiCode init(Model m) {
	m.addAttribute("busiMap", busiService.getBusiMap());
	return new BusiCode();
    }

    @Override
    GenericService<BusiCode> getService() {
	return this.service;
    }

    @Override
    protected String getPageView() {
	return "/dr/busiCode";
    }

    @ModelAttribute
    public BusiCode setUpForm(@RequestParam(required = false) Long busiId, @RequestParam(required = false) Long id, Model m) {
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

    @RequestMapping(value = { "/", "/index", "/list" }, params = { "busiId" })
    public String list(@RequestParam(required = false) Long busiId, Model m, SessionStatus status) {
	if (busiId != null)
	    m.addAttribute("itemList", service.findByBusi(busiId));// busiService.findById(busiId).getCodeList());
	return "/dr/busiCode";
    }

    @ResponseBody
    @RequestMapping(value = { "list.json" }, params = { "busiId" })
    public List<BusiCode> list(Long busiId) {
	return service.findByBusi(busiId);
    }

}
