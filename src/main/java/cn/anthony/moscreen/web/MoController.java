package cn.anthony.moscreen.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class MoController {
	
	
	@ResponseBody
    @RequestMapping(value = "/mo")
    public Map<String,Object> mo(@RequestParam Map<String,String> allRequestParams) throws IOException {
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("phone","13333333333");
		m.put("mono","106999999999");
		m.put("msg","测试内容");
		m.putAll(allRequestParams);
		return m;
	}
	
}