package com.ideas2it.vitalsignservicemongo.service;

import java.util.List;

import com.ideas2it.vitalsignservicemongo.entity.ResultEntity;
import com.ideas2it.vitalsignservicemongo.entity.VitalSignEntity;

/**
 * VitalSignService is used for VitalSign CRUD.
 * 
 * @author Vigneshwaran N
 */
public interface VitalSignService {

	/**
	 * save method is used to save vitalSign.
	 * 
	 * @param vitalSignEntity
	 * @return ResultEntity
	 */
	public ResultEntity save(VitalSignEntity vitalSignEntity);

	/**
	 * getVitalSignById method is used get vitalSign.
	 * 
	 * @param userId
	 * @param lastVisitDate
	 * @return VitalSignEntity
	 */
	public VitalSignEntity getVitalSignById(String patientId, String lastVisitDate);

	/**
	 * deleteById method is used delete vitalSign by PatientId.
	 * 
	 * @param patientId
	 * @return VitalSignEntity
	 */
	public ResultEntity deleteById(String patientId);

	/**
	 * searchTxt method is used search vitalSign by reason text.
	 * 
	 * @param reason
	 * @return VitalSignEntity
	 */
	List<VitalSignEntity> searchTxt(String reason);

}
