package cn.anthony.boot.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.anthony.boot.doman.ChannelCode;
import cn.anthony.boot.exception.EntityNotFound;
import cn.anthony.boot.service.BusiCodeService;
import cn.anthony.boot.service.BusiService;
import cn.anthony.boot.service.ChannelCodeService;
import cn.anthony.boot.service.ChannelService;
import cn.anthony.boot.service.GenericService;

@Controller
@RequestMapping(value = "/channelCode")
public class ChannelCodeController extends GenericController<ChannelCode> {
    @Resource
    ChannelCodeService service;
    @Resource
    BusiService busiService;
    @Resource
    BusiCodeService codeService;
    @Resource
    ChannelService channelService;

    @Override
    public ChannelCode init(Model m) {
	m.addAttribute("busiList", busiService.findAllWithCode());
	m.addAttribute("channelMap", channelService.getChannelMap());
	m.addAttribute(new ChannelCodeForm());
	return new ChannelCode();
    }

    @Override
    protected String getPageView() {
	return "/dr/channelCode";
    }

    @Override
    GenericService<ChannelCode> getService() {
	return this.service;
    }

    @Override
    protected void listByRelate(Model m, Long... relateId) {
	m.addAttribute("channel", channelService.findById(relateId[0]));
	m.addAttribute(new ChannelCodeForm(relateId[0], service.findCheckCodeList(relateId[0])));
    }

    @RequestMapping(value = { "/addBatch", "/editBatch" }, method = RequestMethod.POST)
    public String edit(@ModelAttribute ChannelCodeForm t, final RedirectAttributes redirectAttributes, Model m) throws EntityNotFound {
	service.addBatch(t.getChannelId(), t.getCodeList());
	redirectAttributes.addFlashAttribute("message", "设置成功");
	return "redirect:?relateId=" + t.getChannelId();
    }
}
