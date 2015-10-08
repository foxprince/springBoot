package cn.anthony.boot.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
	@Value("${application.message:Hello World}")
	private String message;
	@Value("${application.globle}")
	private String globle;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		mav.addObject("message", message + "," + globle);
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
	}

	@RequestMapping(value = "/simple", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String simple() {
		return "Hello world!你好世界";
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		model.addAttribute("message",
				"你好。Maven Web Project + Spring 3 MVC - welcome()");
		return "index";
	}

	/**
	 * spring MVC的标准写法，避免其他问题，建议这样写
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/t", method = RequestMethod.GET)
	public ModelAndView test(@RequestParam Map<String, String> allRequestParams) {
		ModelAndView mav = new ModelAndView("t", "message",
				"Spring MVC 他 1 by ");
		// 没有键的情况下,使用Object的类型作为key,String-->string
		mav.addObject("这是变量名为name的值！sss哈哈" + globle);
		mav.addObject("secondParam", "第二个变量");
		return mav;
	}

	@RequestMapping(value = "/m/{name}", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> mo(@PathVariable String name,@RequestParam Map<String, String> allRequestParams) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("phone", "13333333333");
		m.put("mono", "106999999999");
		m.put("msg", "测试内容");
		m.put("name", name);
		m.putAll(allRequestParams);
		return m;
	}

	@RequestMapping(value = "/welcome/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
		model.addAttribute("message", "你好。Maven Web Project + Spring 3 MVC - "
				+ name);
		return "index";
	}

}