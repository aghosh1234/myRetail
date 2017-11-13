package com.retail.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.retail.app.ServiceConfiguration;

@Component("retailUtil")
public class RetailUtil {

	@Autowired
	@Qualifier("serviceConfiguration")
	private ServiceConfiguration env;


	/* Helper method to get String properties.
	 * 
	 * @param property
	 * @return
	 */
	public String getProp(String property) {
		return  env.getPropertyValue(property);
	}
	
	/**
	 * Helper method to get Integer properties.
	 * 
	 * @param property
	 * @return
	 */
	public Long propLong(String property) {
		Long val = null;
		String prop = env.getPropertyValue(property);
		if (prop != null) {
			val = Long.valueOf(prop);
		}
		return val;
	}

}
