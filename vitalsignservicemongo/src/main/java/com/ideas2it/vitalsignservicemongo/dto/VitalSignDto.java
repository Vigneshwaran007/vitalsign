package com.ideas2it.vitalsignservicemongo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * VitalSignDto represent vital sign table.
 * 
 * @author Vigneshwaran N
 */

@Document(collection = "vital_db")
public class VitalSignDto {

	@Id
	private VitalSignPrimaryKeys keys;
	private Integer temperatute;
	private Integer bloodPressure;
	private Integer sugerLevel;
	@TextIndexed
	private String visitReason;

	public VitalSignDto() {
		super();
	}

	public VitalSignDto(VitalSignPrimaryKeys keys, Integer temperatute, Integer bloodPressure, Integer sugerLevel,
			String visitReason) {
		super();
		this.keys = keys;
		this.temperatute = temperatute;
		this.bloodPressure = bloodPressure;
		this.sugerLevel = sugerLevel;
		this.visitReason = visitReason;
	}

	public VitalSignPrimaryKeys getKeys() {
		return keys;
	}

	public void setKeys(VitalSignPrimaryKeys keys) {
		this.keys = keys;
	}

	public Integer getTemperatute() {
		return temperatute;
	}

	public void setTemperatute(Integer temperatute) {
		this.temperatute = temperatute;
	}

	public Integer getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(Integer bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public Integer getSugerLevel() {
		return sugerLevel;
	}

	public void setSugerLevel(Integer sugerLevel) {
		this.sugerLevel = sugerLevel;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

}
