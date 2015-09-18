package cn.anthony.moscreen.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import cn.anthony.moscreen.domain.DrPushModel;
import cn.anthony.moscreen.util.RefactorUtil;

import com.anthony.util.http.HTTPUtil;

@Service
public class DrPushService {
	
	public boolean push(String url,DrPushModel model) {
		boolean bool = false;
		try {
			String resp = HTTPUtil.get(url, HTTPUtil.toNameValuePairs(RefactorUtil.getObjectParaMap(model)));
			if(resp.trim().equalsIgnoreCase("ok"))
				bool = true;
			System.out.println("push to lltx:"+bool);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bool;
	}
}
