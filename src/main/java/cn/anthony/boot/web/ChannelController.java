package cn.anthony.boot.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.anthony.boot.doman.Channel;
import cn.anthony.boot.service.ChannelService;
import cn.anthony.boot.service.GenericService;

@Controller
@RequestMapping(value = "/channel")
// @SessionAttributes("channel")
public class ChannelController extends GenericController<Channel> {
    @Resource
    ChannelService service;

    @Override
    public Channel init() {
	return new Channel();
    }

    public void validate(Channel t, Errors errors) {
	if (t.getName().equalsIgnoreCase("moscreen"))
	    errors.rejectValue("name", "name.invalid", "这个名称还是不要取吧");
    }

    @Override
    String getFormView() {
	return "/dr/channelForm";
    }

    @Override
    protected String getPageView() {
	return "/dr/channelList";
    }

    @Override
    GenericService<Channel> getService() {
	return this.service;
    }

    @ResponseBody
    @RequestMapping("list2.json")
    public List<Channel> list() {
	System.out.println("fffff");
	return getService().findAll();
    }
}
