package cn.anthony.boot.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public Busi init(Model m) {
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

}
