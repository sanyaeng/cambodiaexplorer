/**
 * 
 */
package com.ce.service.web;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sanyaeng
 * 
 */
@Controller
public class WebPurifyController {
	private HttpClient httpClient = null;

	@RequestMapping(value = "/webpurify.html", method = RequestMethod.GET)
	public void webpurifyResponse(
			@RequestParam(value = "imgid", required = false) String imageId,
			@RequestParam(value = "status", required = false) String status) {
		System.out.println("****** imgid = " + imageId);
		System.out.println("****** status = " + status);
	}

	@RequestMapping(value = "webpurifyrequest.html", method = RequestMethod.GET)
	public void webpurify() throws ClientProtocolException, IOException {
		this.httpClient = new DefaultHttpClient();

		String key = "db01cebc0ba5835eebc3ea9be4ffd3f7";

		String urlVerify = "https://api1.webpurify.com/services/rest/moderate?method=webpurify.live.imgcheck&api_key="
				+ key
				+ "&imgurl=http://img.allvoices.com/thumbs/event/609/480/34172616-violance-iran.jpg";

		HttpEntity entity = getEntity(urlVerify);

	}

	private HttpEntity getEntity(String url) throws ClientProtocolException,
			IOException {
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpget);
		response.addHeader("Content-Type", "application/xml");
		HttpEntity entity = response.getEntity();
		return entity;

	}
}
