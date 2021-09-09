package com.ideas2it.vitalsignservicemongo.entity;

import java.util.UUID;

/**
 * PatientEntity used as entity for patient table.
 * 
 * @author Vigneshwaran N
 */
public class PatientEntity {
	private UUID patientID;
	private String patientName;

	public PatientEntity(patientEntityBuiler patientEntityBuiler) {
		this.patientID = patientEntityBuiler.patientID;
		this.patientName = patientEntityBuiler.patientName;
	}

	public PatientEntity() {
		super();
	}

	public UUID getPatientID() {
		return patientID;
	}

	public String getPatientName() {
		return patientName;
	}

	public static class patientEntityBuiler {
		UUID patientID;
		String patientName;

		public patientEntityBuiler patientID(UUID patientID) {
			this.patientID = patientID;
			return this;
		}

		public patientEntityBuiler patientName(String patientName) {
			this.patientName = patientName;
			return this;
		}

		public PatientEntity build() {
			PatientEntity entity = new PatientEntity(this);
			return entity;
		}
	}
}
