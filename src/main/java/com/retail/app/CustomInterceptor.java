package com.retail.app;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;


public class CustomInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomInterceptor.class);

	/**
	 * 
	 *
	 * Description : Constructor to set service configuration and method configurations
	 * 
	 * @param serviceConfiguration
	 *
	 */
	@Autowired
	public CustomInterceptor(ServiceConfiguration serviceConfiguration) {
		try {
			LOGGER.debug("Inside the CustomInterceptor class!");
		} catch (Exception e) {
			LOGGER.error("Exception in CustomInterceptor constructor " + e);
		}
	}

	
	/**
	 * @param request
	 */
	private void populateRequestContext(String request) {
		try {
			LOGGER.info("Inside the populateRequestContext");
		} catch (Exception e) {
			LOGGER.error("Exception in populateRequestContext " + e);
		}
	}

	
	/**
	 * @param response
	 */
	private void populateResponseContext(String response) {
		try {
			LOGGER.debug("Inside the populateRequestContext");
		} catch (Exception e) {
			LOGGER.error("Exception in populateResponseContext " + e);
		}
	}


	/* (non-Javadoc)
	 * @see org.springframework.http.client.ClientHttpRequestInterceptor#intercept(org.springframework.http.HttpRequest, byte[], org.springframework.http.client.ClientHttpRequestExecution)
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		ClientHttpResponse response = null;
		try {
			LOGGER.info("Inside the intercept!");
			populateRequestContext(new String(body));
			response = execution.execute(request, body);
			//populateResponseContext(getResponseBody(response));
		} catch (Exception e) {
			LOGGER.error("Exception in intercept " + e);
		}
		return response;
	}

	/**
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private String getResponseBody(ClientHttpResponse response) throws IOException {
		InputStream responseStream = response.getBody();
		String responseBody = CharStreams.toString(new InputStreamReader(responseStream, Charsets.UTF_8));
		//Closeables.closeQuietly(responseStream);
		return responseBody;
	}

	
}
