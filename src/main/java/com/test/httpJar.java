package com.test;
import java.util.HashMap;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.jperfman.script.ScriptDefine;
import com.util.HttpClientUtil;


public class httpJar extends ScriptDefine implements Runnable{

	private CloseableHttpClient httpclient;
	private HttpGet httpget;
	private HttpPost httpPost;

	private String url;			//the request url you should set

	public httpJar() {
		url="http://192.168.115.130:12345/constructor";
		data = new HashMap<String, String>();
		httpclient = HttpClients.createDefault();
	}

	private void buildHttpGet() {
		httpget = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000)
				.setConnectTimeout(5000)
				.build();
		httpget.setConfig(requestConfig);
		httpget.setHeader("Connection","Close");
		
		JSONObject response=HttpClientUtil.sendHttpGet(url);
		System.out.println(response.toString());
	}

	public void run() {
		while (true){
			//buildHttpPost();
			buildHttpGet();
		}
	}
	
	private void buildHttpPost() {
		
	}


	@Override
	public void setup() {
		
	}

	@Override
	public void runtest() {
		
		this.buildHttpGet();
	}



	@Override
	public void clean() {
		httpget.releaseConnection();
	}

	public static void main(String[] args) {
		httpJar demo = new httpJar();
		try {
			demo.runtest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		demo.clean();
	}


}
