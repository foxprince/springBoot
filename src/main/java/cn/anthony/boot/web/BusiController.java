package cn.anthony.boot.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.anthony.boot.doman.Busi;
import cn.anthony.boot.service.BusiService;
import cn.anthony.boot.service.GenericService;

@Controller
@RequestMapping(value = "/busi")
// @SessionAttributes("busi")
public class BusiController extends GenericController<Busi> {
    @Resource
    BusiService service;

    @Override
    public Busi init() {
	return new Busi();
    }

    @Override
    GenericService<Busi> getService() {
	return this.service;
    }

    @Override
    protected String getPageView() {
	return "/dr/busiList";
    }

    @Override
    String updateHint(Busi t) {
	return "成功：" + t.getName();
    }

    @Override
    String deleteHint(Busi t) {
	return "成功删除：" + t.getName();
    }

    @ResponseBody
    @RequestMapping("list.json")
    public List<Busi> list() {
	return getService().findAll();
    }
}
