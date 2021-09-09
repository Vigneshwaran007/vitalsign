package com.ideas2it.vitalsignservicemongo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

/**
 * VitalSignEntity used as entity for VitalSign table.
 * @author Vigneshwaran N
 */
public class VitalSignEntity implements Serializable{
	
	@Id
	private String patientId;
	private String lastVisitDate;
	private Integer temperatute;
	private Integer bloodPressure;
	private Integer sugerLevel;
	private String visitReason;
	
	public String getPatientId() {
		return patientId;
	}

	public String getLastVisitDate() {
		return lastVisitDate;
	}

	public Integer getTemperatute() {
		return temperatute;
	}

	public Integer getBloodPressure() {
		return bloodPressure;
	}

	public Integer getSugerLevel() {
		return sugerLevel;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public VitalSignEntity() {
		
	}
	
	public VitalSignEntity(String patientId, String lastVisitDate, Integer temperatute, Integer bloodPressure,
			Integer sugerLevel, String visitReason) {
		super();
		this.patientId = patientId;
		this.lastVisitDate = lastVisitDate;
		this.temperatute = temperatute;
		this.bloodPressure = bloodPressure;
		this.sugerLevel = sugerLevel;
		this.visitReason = visitReason;
	}

	public VitalSignEntity(VitalSignEntityBuilder builder) {
		this.patientId=builder.patientId;
		this.lastVisitDate=builder.lastVisitDate;
		this.temperatute=builder.temperatute;
		this.bloodPressure=builder.bloodPressure;
		this.sugerLevel=builder.sugerLevel;
		this.visitReason=builder.visitReason;
	}
	
	public static class VitalSignEntityBuilder{
		String patientId;
		String lastVisitDate;
		Integer temperatute;
		Integer bloodPressure;
		Integer sugerLevel;
		String visitReason;
		
		public VitalSignEntityBuilder patientId(String string) {
			this.patientId=string;
			return this;
		}
		
		public VitalSignEntityBuilder lastVisitDate(String lastVisitDate) {
			this.lastVisitDate=lastVisitDate;
			return this;
		}
		
		public VitalSignEntityBuilder temperatute(Integer temperatute) {
			this.temperatute=temperatute;
			return this;
		}
		
		public VitalSignEntityBuilder bloodPressure(Integer bloodPressure) {
			this.bloodPressure=bloodPressure;
			return this;
		}
		
		public VitalSignEntityBuilder sugerLevel(Integer sugerLevel) {
			this.sugerLevel=sugerLevel;
			return this;
		}
		
		public VitalSignEntityBuilder visitReason(String visitReason) {
			this.visitReason=visitReason;
			return this;
		}
		
		public VitalSignEntity build() {
			VitalSignEntity entity=new VitalSignEntity(this);
			return entity;
		}
	}
}
