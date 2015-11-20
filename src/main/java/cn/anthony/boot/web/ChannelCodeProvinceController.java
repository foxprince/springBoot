package cn.anthony.boot.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.ChannelCodeProvince;
import cn.anthony.boot.doman.PhoneHead;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.ChannelCodeProvinceService;
import cn.anthony.boot.service.ChannelCodeService;
import cn.anthony.boot.service.ChannelService;
import cn.anthony.boot.service.GenericService;
import cn.anthony.boot.service.PhoneHeadService;
import cn.anthony.boot.service.SpecialPhoneService;
import cn.anthony.boot.util.ControllerUtil;

@Controller
@RequestMapping(value = "/channelCodeProvince")
public class ChannelCodeProvinceController extends GenericController<ChannelCodeProvince> {
    @Resource
    ChannelCodeProvinceService service;
    @Resource
    ChannelService channelService;
    @Resource
    ChannelCodeService codeService;
    @Resource
    private PhoneHeadService phoneHeadService;
    @Resource
    private SpecialPhoneService specialPhoneService;

    @Override
    public ChannelCodeProvince init(Model m) {
	m.addAttribute("channelMap", channelService.getChannelMap());
	m.addAttribute("provinceMap", ControllerUtil.getProvinceMap());
	m.addAttribute(new ChannelCodeProvinceForm());
	return new ChannelCodeProvince();
    }

    @Override
    protected String getPageView() {
	return "/dr/channelCodeProvince";
    }

    @Override
    GenericService<ChannelCodeProvince> getService() {
	return this.service;
    }

    @RequestMapping(value = { "/", "/index", "/list" }, params = { "channelId" })
    public String list(@ModelAttribute ChannelCodeProvinceForm t, Model m) {
	m.addAttribute("checkedCodeMap", codeService.findCheckedCodeMap(t.getChannelId()));
	t.setProvinceList(service.findCheckProvinceList(t.getChannelId(), t.getCodeId()));
	return getPageView();
    }

    @Override
    protected void listByRelate(Model m, Long... relateId) {
	m.addAttribute("checkedCodeMap", codeService.findCheckedCodeMap(relateId[0]));
	System.out.println(relateId.length);
	if (relateId.length == 1)
	    m.addAttribute(new ChannelCodeProvinceForm(relateId[0]));
	if (relateId.length == 2)
	    m.addAttribute(new ChannelCodeProvinceForm(relateId[0], relateId[1], service.findCheckProvinceList(relateId[0], relateId[1])));

    }

    @RequestMapping(value = { "/addBatch", "/editBatch" }, method = RequestMethod.POST)
    public String edit(@ModelAttribute ChannelCodeProvinceForm t, final RedirectAttributes redirectAttributes, Model m) throws EntityNotFound {
	service.addBatch(t.getChannelId(), t.getCodeId(), t.getProvinceList());
	redirectAttributes.addFlashAttribute("message", "设置成功");
	return list(t, m);// "redirect:?channelId=" + t.getChannelId();
    }

    @ResponseBody
    @RequestMapping("/phoneCheck.json")
    public boolean phoneCheck(@RequestParam String phone, @RequestParam Long channelId) {
	if (specialPhoneService.isSpecial(phone))
	    return false;
	PhoneHead ph = phoneHeadService.findByPhone(phone);
	if (ph != null) {
	    List<ChannelCodeProvince> l = service.findByProvinceAndChannelId(ph.getProvince(), channelId);
	    if (l != null && l.size() > 0)
		return true;
	}
	return false;
    }
}
