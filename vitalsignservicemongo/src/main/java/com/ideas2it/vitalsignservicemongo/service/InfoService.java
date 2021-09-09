package com.ideas2it.vitalsignservicemongo.service;

import java.util.HashMap;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * InfoService used to provide data modification count.
 * 
 * @author Vigneshwaran N
 */
@Component
public class InfoService implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		HashMap<String, Integer> userCount = new HashMap<>();
		userCount.put("ActiveUser", 10);
		userCount.put("InActiveUser", 9);
		builder.withDetail("UserMetrics", userCount);

	}

}
