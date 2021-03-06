/* 
 * @author anthony
 * 2008-4-2
 * HTTPUtil.java
 *
 */
package com.anthony.util.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.anthony.util.SSLUtils;

public class HTTPUtil {
    public static void main(String[] args) {
	Map<String, String> m = new HashMap<String, String>();
	m.put("loginName", "1111");
	m.put("loginPwd", "232323");
	m.put("objNo", "sdfsdfsdf");
	m.put("shortMsg", "dfsdfsdf");
	// String url = "http://sms.ceeg.cn/receive.aspx";

    }

    String encoding = System.getProperty("sun.jnu.encoding");

    public static List<NameValuePair> toNameValuePairs(Map<String, String> params) {
	List<NameValuePair> valuePairs = new ArrayList<NameValuePair>(params.size());
	for (Map.Entry<String, String> entry : params.entrySet()) {
	    NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
	    valuePairs.add(nameValuePair);
	}
	return valuePairs;
    }

    /**
     * Get请求
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String get(String url, List<NameValuePair> params) throws IOException {
	// CloseableHttpClient httpclient = HttpClients.createDefault();//
	// SSLUtils.createSSLInsecureClient();
	CloseableHttpClient httpclient = new DefaultHttpClient();
	SSLUtils.enableSSL(httpclient);
	String responseBody = null;
	try {
	    HttpGet httpget = new HttpGet(url);
	    // 设置参数
	    String str = "";
	    if (params != null && params.size() > 0) {
		EntityUtils.toString(new UrlEncodedFormEntity(params, "utf-8"));
		httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
	    }
	    // Create a custom response handler
	    ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		@Override
		public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
		    int status = response.getStatusLine().getStatusCode();
		    if (status >= 200 && status < 300) {
			HttpEntity entity = response.getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		    } else {
			throw new ClientProtocolException("Unexpected response status: " + status);
		    }
		}

	    };
	    responseBody = httpclient.execute(httpget, responseHandler);
	} catch (URISyntaxException e) {
	    e.printStackTrace();
	} finally {
	    httpclient.close();
	}
	return responseBody;
    }

    /**
     * // Post请求
     * 
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String post(String url, List<NameValuePair> params) throws IOException {
	// CloseableHttpClient httpclient = HttpClients.createDefault();
	CloseableHttpClient httpclient = new DefaultHttpClient();
	SSLUtils.enableSSL(httpclient);
	String responseBody = null;
	try {
	    // Post请求
	    HttpPost httppost = new HttpPost(url);
	    // 设置参数
	    httppost.setEntity(new UrlEncodedFormEntity(params));
	    // 发送请求
	    HttpResponse httpresponse = httpclient.execute(httppost);
	    // 获取返回数据
	    HttpEntity entity = httpresponse.getEntity();
	    responseBody = EntityUtils.toString(entity);
	} catch (ParseException | IOException e) {
	    e.printStackTrace();
	} finally {
	    httpclient.close();
	}
	return responseBody;
    }

    public static String postJson(String url, String jsonStr) throws IOException {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	String responseBody = null;
	try {
	    HttpPost httppost = new HttpPost(url);
	    httppost.setEntity(new StringEntity(jsonStr, ContentType.APPLICATION_JSON));
	    HttpResponse httpresponse = httpclient.execute(httppost);
	    HttpEntity entity = httpresponse.getEntity();
	    responseBody = EntityUtils.toString(entity);
	} catch (ParseException | IOException e) {
	    e.printStackTrace();
	} finally {
	    httpclient.close();
	}
	return responseBody;
    }

}
