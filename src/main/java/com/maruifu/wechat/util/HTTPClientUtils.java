package com.maruifu.wechat.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HTTPClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HTTPClientUtils.class);


	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000).build();

	/**
	 * @Description：联动支持的HTTPPOST请求封装，调用人只需传递json格式的参数和要调用的接口名便可使用。
	 * 				为符合银联规范，所以HTTPS里的密钥套件我们使用TLSv1.2
	 * 创建人：youhuansky ,  2017年9月14日  下午4:16:11
	 * 修改人：youhuansky ,  2017年9月14日  下午4:16:11
	 *
	 * @param json:根据接口文档，向联动传递的参数map转成的json串
	 * @param apiName:根据接口文档，要调用的接口名
	 * @return
	 * @throws Exception
	 * String
	 */
	public static String postToHsapi(String json,String apiName) throws Exception {
		SSLContext sslcontext = SSLContexts.custom()
				.build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext,
				new String[] { "TLSv1.2" },
				null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.build();
		HttpPost request = new HttpPost(apiName);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("context", json));

		request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpclient.execute(request);

		HttpEntity rephttpEntity = response.getEntity();

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(rephttpEntity, "UTF-8");
			logger.info("收到响应:" + result);
			return result;
		} else {
			logger.error("err:" + response.getStatusLine());
			return String.valueOf(response.getStatusLine());

		}

	}


	public static String getJsonStrByQueryUrl(String paramStr) {
		//String paramStr = "a=a1&b=b1&c=c1";

//		try {
//			paramStr = URLDecoder.decode(paramStr,"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			logger.info("URL解码Exception:" + e.getMessage());
//		}



		String[] params = paramStr.split("&");
		JSONObject obj = new JSONObject();
		for (int i = 0; i < params.length; i++) {
			String[] param = params[i].split("=");
			if (param.length >= 2) {
				String key = param[0];
				String value = param[1];
				for (int j = 2; j < param.length; j++) {
					value += "=" + param[j];
				}
				try {
					obj.put(key,value);
				} catch (JSONException e) {
					logger.info("转换失败JSONException:" + e.getMessage());
				}
			}
		}


		return obj.toString();
	}

	public static String sendHttpPost(String httpUrl, Map<String, String> maps) {
		HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
		// 创建参数队列
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : maps.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
		}
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendHttpPost(httpPost);
	}
	public static String sendHttpPost(HttpPost httpPost) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpPost.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpPost);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return responseContent;
	}

	/**
	 * 以jsonString形式发送HttpPost的Json请求，String形式返回响应结果
	 *
	 * @param url
	 * @param jsonString
	 * @return
	 */
	public static String sendPostJsonStr(String url, String jsonString) throws IOException {
		if (jsonString == null || jsonString.isEmpty()) {
			return sendPost1(url);
		}
		String resp = "";
		StringEntity entityStr = new StringEntity(jsonString,
				ContentType.create("text/plain", "UTF-8"));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entityStr);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			resp = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		if (resp == null || resp.equals("")) {
			return "";
		}
		return resp;
	}


	public static String sendPostJson(String url, String jsonString) throws IOException {
		if (jsonString == null || jsonString.isEmpty()) {
			return sendPost1(url);
		}
		String resp = "";
		StringEntity entityStr = new StringEntity(jsonString,
				ContentType.create("application/json", "UTF-8"));
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entityStr);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			resp = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		if (resp == null || resp.equals("")) {
			return "";
		}
		return resp;
	}
	/**
	 * 发送不带参数的HttpPost请求
	 *
	 * @param url
	 * @return
	 */
	public static String sendPost1(String url) throws IOException {
		// 1.获得一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 2.生成一个post请求
		HttpPost httppost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			// 3.执行get请求并返回结果
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// 4.处理结果，这里将结果返回为字符串
		HttpEntity entity = response.getEntity();
		String result = null;
		try {
			result = EntityUtils.toString(entity);
		} catch (ParseException | IOException e) {
			logger.error(e.getMessage());
		}
		return result;
	}



	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			logger.info("sendGet -->:{}" + urlNameString);
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				logger.error(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			logger.error("发送GET请求Exception出现异常！" + e.getMessage());
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				logger.error("发送GET请求Exception出现异常！" + e2.getMessage());
			}
		}
		return result;
	}
}
