package cn.anthony.boot.service;

import java.io.IOException;

import com.anthony.util.http.HTTPUtil;

public class BicpService {

    private void login() {
	String jsonStr = "{username:CP0078BICP, password:Mhmp0501@606501, vcode:0000, dstInfo:dstInfo, language:zn_CN, name:default,logintype:0,smscode:,msisdn:CP0078BICP}";
	String url = "http://218.207.208.43:9080/bicp/authenticate.action";
	try {
	    System.out.println(HTTPUtil.postJson(url, jsonStr));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
