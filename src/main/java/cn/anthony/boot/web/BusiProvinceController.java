package cn.anthony.boot.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.BusiProvince;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.BusiCodeService;
import cn.anthony.boot.service.BusiProvinceService;
import cn.anthony.boot.service.BusiService;
import cn.anthony.boot.service.GenericService;
import cn.anthony.boot.util.ControllerUtil;

@Controller
@RequestMapping(value = "/busiProvince")
public class BusiProvinceController extends GenericController<BusiProvince> {
    @Resource
    private BusiProvinceService service;
    @Resource
    private BusiService busiService;
    @Resource
    private BusiCodeService codeService;

    @Override
    public BusiProvince init(Model m) {
	m.addAttribute("busiMap", busiService.getBusiMap());
	m.addAttribute("provinceMap", ControllerUtil.getProvinceMap());
	m.addAttribute(new BusiProvinceForm());
	return new BusiProvince();
    }

    @Override
    protected String getPageView() {
	return "/dr/busiProvince";
    }

    @Override
    GenericService<BusiProvince> getService() {
	return this.service;
    }

    @Override
    protected void listByRelate(Model m, Long... relateId) {
	m.addAttribute("busi", busiService.findById(relateId[0]));
	m.addAttribute(new BusiProvinceForm(relateId[0], service.findCheckProvinceList(relateId[0])));
    }

    @RequestMapping(value = { "/addBatch", "/editBatch" }, method = RequestMethod.POST)
    public String edit(@ModelAttribute BusiProvinceForm t, final RedirectAttributes redirectAttributes, Model m) throws EntityNotFound {
	service.addBatch(t.getBusiId(), t.getProvinceList());
	redirectAttributes.addFlashAttribute("message", "设置成功");
	return "redirect:?relateId=" + t.getBusiId();
    }
}
