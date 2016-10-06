package cn.anthony.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.anthony.util.http.HTTPUtil;
import com.google.gson.Gson;

public class Snippet {

    private static String login_url = "https://218.207.208.43:9083/bicp/login.action";
    private static String auth_url = "https://218.207.208.43:9080/bicp/authenticate.action";

    public static void main(String[] args) throws Exception {
	Snippet snippet = new Snippet();
	// System.out.println(HttpClientSendPost.sendXMLDataByGet(login_url,
	// ""));

	// System.out.println(SSLUtils.httpGet(login_url));
	// snippet.bicp();
	// System.out.println(HTTPUtil.get(login_url, null));
	String jsonStr = "{username:CP0078BICP, password:Mhmp0501@606501, vcode:0000, dstInfo:dstInfo, language:zn_CN, name:default,logintype:0,smscode:,msisdn:CP0078BICP}";
	// System.out.println(HttpClientSendPost.postJson(auth_url, jsonStr));
	// HTTPUtil.postJson(auth_url, jsonStr);
	System.out.println(SSLUtils.httpPostJson(auth_url, jsonStr));
    }

    public void bicp() throws ClientProtocolException, IOException, URISyntaxException {
	String jsonStr = "{username:CP0078BICP, password:Mhmp0501@606501, vcode:0000, dstInfo:dstInfo, language:zn_CN, name:default,logintype:0,smscode:,msisdn:CP0078BICP}";
	HTTPUtil.postJson(auth_url, jsonStr);
	String url = "https://218.207.208.43:9080/bicp/authenticate.action";
	String ytsUrl = "http://218.207.208.43:9080/bicp/business.action?BMEBusiness=resourceList";
	String mailUrl = "http://218.207.208.43:9080/bicp/query.action?BMEBusiness=sys_usm_login_confirm";
	LoginModel model = new LoginModel("CP0078BICP", "Mhmp0501@606501");
	jsonStr = new Gson().toJson(model);
	System.out.println(jsonStr);

	BasicCookieStore cookieStore = new BasicCookieStore();
	CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	HttpUriRequest login = RequestBuilder.post().setUri(new URI(url)).addParameter("username", "CP0078BICP")
		.addParameter("password", "Mhmp0501@606501").addParameter("vcode", "0000").addParameter("logintype", "0")
		.addParameter("name", "default").build();
	CloseableHttpResponse response2 = httpclient.execute(login);
	try {
	    // HttpEntity entity = response2.getEntity();
	    // System.out.println(EntityUtils.toString(entity));
	    // System.out.println("Login form get: " +
	    // response2.getStatusLine());
	    // EntityUtils.consume(entity);
	    // System.out.println("Post logon cookies:");
	    // List<Cookie> cookies = cookieStore.getCookies();
	    // if (cookies.isEmpty()) {
	    // System.out.println("None");
	    // } else {
	    // for (int i = 0; i < cookies.size(); i++) {
	    // System.out.println("- " + cookies.get(i).toString());
	    // }
	    // }
	    CloseableHttpClient hc = new DefaultHttpClient();
	    SSLUtils.enableSSL(hc);
	    HttpGet httpget = new HttpGet(login_url);
	    System.out.println("Executing request " + httpget.getRequestLine());
	    HttpResponse response = hc.execute(httpget);
	    HttpEntity entity = response.getEntity();
	    System.out.println("----------------------------------------");
	    System.out.println(response.getStatusLine());

	    System.out.println(EntityUtils.toString(entity));
	    EntityUtils.consume(entity);
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		httpclient.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public void sslLogin() throws Exception {
	File f = new File("d:\\tmp\\my.keystore");
	// f.createNewFile();
	// Trust own CA and all self-signed certs
	SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(f, "nopassword".toCharArray(), new TrustSelfSignedStrategy()).build();
	// Allow TLSv1 protocol only
	SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
		SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	CloseableHttpClient httpclient = SSLUtils.createSSLInsecureClient();// HttpClients.custom().setSSLSocketFactory(sslsf).build();
	try {
	    HttpGet httpget = new HttpGet(login_url);
	    System.out.println("Executing request " + httpget.getRequestLine());
	    CloseableHttpResponse response = httpclient.execute(httpget);
	    try {
		HttpEntity entity = response.getEntity();
		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		EntityUtils.consume(entity);
		System.out.println(EntityUtils.toString(entity));
	    } finally {
		response.close();
	    }
	} finally {
	    httpclient.close();
	}
    }

    class LoginModel {
	String username;
	String password;
	String vcode = "0000";
	String dstInfo = "";
	String language = "zh_CN";
	String name = "default";
	String logintype = "0";
	String smscode = "";
	String msisdn = "CP0078BICP";

	public LoginModel(String username, String password) {
	    super();
	    this.username = username;
	    this.password = password;
	}

    }

    private static void passwd() {
	String password = "moscreen";
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String hashedPassword = passwordEncoder.encode(password);
	System.out.println(hashedPassword);
    }
}
