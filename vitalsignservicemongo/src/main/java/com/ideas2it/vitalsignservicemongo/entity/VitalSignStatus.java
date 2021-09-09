package com.ideas2it.vitalsignservicemongo.entity;

/**
 * VitalSignStatus used as entity for give status.
 * 
 * @author Vigneshwaran N
 */
public class VitalSignStatus {
	private VitalSignEntity vitalSignEntity;
	private String status;
	private String message;

	public VitalSignStatus() {
		super();
	}

	public VitalSignStatus(VitalSignEntity vitalSignEntity, String status, String message) {
		super();
		this.vitalSignEntity = vitalSignEntity;
		this.status = status;
		this.message = message;
	}

	public VitalSignEntity getVitalSignEntity() {
		return vitalSignEntity;
	}

	public void setVitalSignEntity(VitalSignEntity vitalSignEntity) {
		this.vitalSignEntity = vitalSignEntity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
