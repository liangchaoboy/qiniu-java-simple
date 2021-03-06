package com.qiniu.demo.rs;

import java.io.IOException;

import org.junit.Test;

import com.qiniu.config.Config;
import com.qiniu.util.Auth;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Mkbucket {
	
	public Auth auth = Auth.create(Config.ak, Config.sk);	
	
	//使用mkbucket接口，mkbucket接口与qiniudn域名绑定在一起了
	public void test2(String bucketName) throws IOException{
		
		
		String path = "/mkbucket/"+bucketName+"/public/0\n";
	
		String access_token = auth.sign(path);
		
		System.out.println(access_token);
		
		String url = "http://rs.qiniu.com/mkbucket/"+bucketName+"/public/0";				
		
		OkHttpClient client = new OkHttpClient();		

		Request request = new Request.Builder().url(url)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "QBox " + access_token).build();

		Response re = client.newCall(request).execute();

		if (re.isSuccessful() == true) {
			System.out.println(re.code());
			System.out.println(re.toString());
		} else {
			System.out.println(re.code());
		}		
	}	
	@Test
	public void test(){
		try {
			test2("test2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
