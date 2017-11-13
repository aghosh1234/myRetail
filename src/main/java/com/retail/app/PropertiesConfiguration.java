package com.retail.app;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;



@Configuration
public class PropertiesConfiguration {
	@Autowired
	private Environment env;
	
	@Bean(name = "serviceConfiguration")
	public ServiceConfiguration getConfig() {
		return new ServiceConfiguration(env);
	}

	/**
	 * Helper method to get String properties.
	 * 
	 * @param property
	 * @return
	 */
	protected String propStr(String property) {
		return env.getProperty(property);
	}

	/**
	 * Helper method to get Integer properties.
	 * 
	 * @param property
	 * @return
	 */
	protected Integer propInt(String property) {
		Integer val = null;
		String prop = env.getProperty(property);
		if (prop != null) {
			val = Integer.valueOf(prop);
		}
		return val;
	}
	
	 /**
	  * Rest template to call the rest service
	 * @return
	 */
	@Bean(name="restTemplate")
		public RestTemplate getRestTemplate() {
		 RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		 restTemplateBuilder.setConnectTimeout(propInt("rest.client.connection.timeout.ms"));
		 restTemplateBuilder.setReadTimeout(propInt("rest.client.read.timeout.ms"));
		 List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
         interceptors.add(new CustomInterceptor(getConfig()));
         RestTemplate rt= restTemplateBuilder.build();
         rt.setInterceptors(interceptors);         
		 return  rt;
	}
}
