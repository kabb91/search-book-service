package com.kabby.app.springboot.listen.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NaverSearchBookApiHelper {
	
	Logger logger = LoggerFactory.getLogger(NaverSearchBookApiHelper.class);

	@Value("${naver.host}")
	private String API_SERVER_HOST;
	
	@Value("${naver.apibasic}")
	private String SEARCH_BOOK;
	
	@Value("${naver.apidetail}")
	private String SEARCH_BOOK_DETAIL;
	
	@Value("${naver.token1}")
	private String X_NAVER_CLIENT_ID;
	
	@Value("${naver.token2}")
	private String X_NAVER_CLIENT_SECRECT;




	public String getMessageToMeBasic(final Map<String, String> params) {
		return request(HttpMethodType.GET, SEARCH_BOOK, mapToParams(params));
	}
	public String getMessageToMeDetail(final Map<String, String> params) {
		return request(HttpMethodType.GET, SEARCH_BOOK_DETAIL, mapToParams(params));
	}
	
    public String request(final HttpMethodType httpMethod, final String apiPath) {
        return request(httpMethod, apiPath, null);
    }


	public String request(HttpMethodType httpMethod, final String apiPath, final String params) {

		String requestUrl = API_SERVER_HOST + apiPath;
		if (httpMethod == null) {
			httpMethod = HttpMethodType.GET;
		}
		if (params != null && params.length() > 0
				&& (httpMethod == HttpMethodType.GET || httpMethod == HttpMethodType.DELETE)) {
			requestUrl += params;
		}

		HttpsURLConnection conn;
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		InputStreamReader isr = null;

		try {
			final URL url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod(httpMethod.toString());
			conn.setRequestProperty("X-Naver-Client-Id", X_NAVER_CLIENT_ID);
			conn.setRequestProperty("X-Naver-Client-Secret", X_NAVER_CLIENT_SECRECT);

			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("charset", "utf-8");

			if (params != null && params.length() > 0 && httpMethod == HttpMethodType.POST) {
				conn.setDoOutput(true);
				writer = new OutputStreamWriter(conn.getOutputStream());
				writer.write(params);
				writer.flush();
			}

			final int responseCode = conn.getResponseCode();
			
			logger.info(String.format("\nSending '%s' request to URL : %s", httpMethod, requestUrl));
			logger.info(String.format("Response Code", responseCode));

			if (responseCode == 200)
				isr = new InputStreamReader(conn.getInputStream());
			else
				isr = new InputStreamReader(conn.getErrorStream());

			reader = new BufferedReader(isr);
			final StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			logger.info(buffer.toString());
			return buffer.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (Exception ignore) {
					
				}
			if (reader != null)
				try {
					reader.close();
				} catch (Exception ignore) {
					
				}
			if (isr != null)
				try {
					isr.close();
				} catch (Exception ignore) {
					
				}
		}

		return null;
	}
	
	public String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public String mapToParams(Map<String, String > map) {
        StringBuilder paramBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            paramBuilder.append(paramBuilder.length() > 0 ? "&" : "");
            paramBuilder.append(String.format("%s=%s", urlEncodeUTF8(key),
                    urlEncodeUTF8(map.get(key).toString())));
        }
        return paramBuilder.toString();
    }	
}
