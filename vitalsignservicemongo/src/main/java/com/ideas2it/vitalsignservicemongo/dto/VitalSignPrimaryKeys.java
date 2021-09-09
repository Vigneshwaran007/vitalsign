package com.ideas2it.vitalsignservicemongo.dto;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * VitalSignPrimaryKeys represent keys for vital sign table.
 * 
 * @author Vigneshwaran N
 */
public class VitalSignPrimaryKeys implements Serializable {
	private String patientId;
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
	private String lastVisitDate;

	public VitalSignPrimaryKeys() {
		super();
	}

	public VitalSignPrimaryKeys(String patientId, String lastVisitDate) {
		super();
		this.patientId = patientId;
		this.lastVisitDate = lastVisitDate;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

}
