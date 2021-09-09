package com.ideas2it.vitalsignservicemongo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ideas2it.vitalsignservicemongo.entity.PatientEntity;


/**
 * PatientClient is act as patient endpoint client
 * 
 * @author Vigneshwaran N
 */
@FeignClient(url = "${healthcare.management.patient.uri}", name = "${healthcare.management.patient.name}")
public interface PatientClient {
	@GetMapping("/{patientId}")
	public PatientEntity getById(@PathVariable String patientId);
}
