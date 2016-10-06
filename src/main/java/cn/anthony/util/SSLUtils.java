package cn.anthony.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;

/**
 * 
 * @author Sayi
 *
 */
public abstract class SSLUtils {

    public static CloseableHttpClient createSSLInsecureClient() {
	try {
	    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
		@Override
		// 信任所有
		public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		    return true;
		}
	    }).build();
	    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
	    return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	} catch (KeyManagementException e) {
	    e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (KeyStoreException e) {
	    e.printStackTrace();
	}
	return HttpClients.createDefault();
    }

    public static String httpPostJson(String httpUrl, String json) {
	BufferedReader input = null;
	StringBuilder sb = null;
	URL url = null;
	HttpURLConnection con = null;
	try {
	    url = new URL(httpUrl);
	    try {
		// trust all hosts
		trustAllHosts();
		HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
		https.setRequestProperty("Content-Type", "application/json");
		https.setRequestProperty("Accept", "application/json");
		https.setRequestMethod("POST");
		if (url.getProtocol().toLowerCase().equals("https")) {
		    https.setHostnameVerifier(DO_NOT_VERIFY);
		    con = https;
		} else {
		    con = (HttpURLConnection) url.openConnection();
		}
		https.setDoOutput(true);
		OutputStream os = https.getOutputStream();
		os.write(json.getBytes("UTF-8"));
		os.close();
		input = new BufferedReader(new InputStreamReader(con.getInputStream()));
		sb = new StringBuilder();
		String s;
		while ((s = input.readLine()) != null) {
		    sb.append(s).append("\n");
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} catch (MalformedURLException e1) {
	    e1.printStackTrace();
	} finally {
	    // close buffered
	    if (input != null) {
		try {
		    input.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    // disconnecting releases the resources held by a connection so they
	    // may be closed or reused
	    if (con != null) {
		con.disconnect();
	    }
	}
	return sb == null ? null : sb.toString();
    }

    public static String httpGet(String httpUrl) {
	BufferedReader input = null;
	StringBuilder sb = null;
	URL url = null;
	HttpURLConnection con = null;
	try {
	    url = new URL(httpUrl);
	    try {
		// trust all hosts
		trustAllHosts();
		HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
		if (url.getProtocol().toLowerCase().equals("https")) {
		    https.setHostnameVerifier(DO_NOT_VERIFY);
		    con = https;
		} else {
		    con = (HttpURLConnection) url.openConnection();
		}
		input = new BufferedReader(new InputStreamReader(con.getInputStream()));
		sb = new StringBuilder();
		String s;
		while ((s = input.readLine()) != null) {
		    sb.append(s).append("\n");
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} catch (MalformedURLException e1) {
	    e1.printStackTrace();
	} finally {
	    // close buffered
	    if (input != null) {
		try {
		    input.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    // disconnecting releases the resources held by a connection so they
	    // may be closed or reused
	    if (con != null) {
		con.disconnect();
	    }
	}

	return sb == null ? null : sb.toString();
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {

	public boolean verify(String hostname, SSLSession session) {
	    return true;
	}
    };
    private final static TrustManager truseAllManager = new X509TrustManager() {

	public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
	    // TODO Auto-generated method stub

	}

	public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
	    // TODO Auto-generated method stub

	}

	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	    // TODO Auto-generated method stub
	    return null;
	}

    };

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
	final String TAG = "trustAllHosts";
	// Create a trust manager that does not validate certificate chains
	TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

	    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		return new java.security.cert.X509Certificate[] {};
	    }

	    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		System.out.println(TAG + " checkClientTrusted");
	    }

	    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		System.out.println(TAG + " checkServerTrusted");
	    }
	} };

	// Install the all-trusting trust manager
	try {
	    SSLContext sc = SSLContext.getInstance("TLS");
	    sc.init(null, trustAllCerts, new java.security.SecureRandom());
	    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void enableSSL(CloseableHttpClient httpclient) {
	// 调用ssl
	try {
	    SSLContext sslcontext = SSLContext.getInstance("TLS");
	    sslcontext.init(null, new TrustManager[] { truseAllManager }, null);
	    SSLSocketFactory sf = new SSLSocketFactory(sslcontext);
	    sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	    Scheme https = new Scheme("https", sf, 443);
	    httpclient.getConnectionManager().getSchemeRegistry().register(https);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static HttpClient getNewHttpClient() {
	try {
	    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	    trustStore.load(null, null);

	    MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
	    sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	    HttpParams params = new BasicHttpParams();
	    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	    HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

	    SchemeRegistry registry = new SchemeRegistry();
	    registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	    registry.register(new Scheme("https", sf, 443));

	    ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

	    return new DefaultHttpClient(ccm, params);
	} catch (Exception e) {
	    return new DefaultHttpClient();
	}
    }
}
