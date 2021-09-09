package com.ideas2it.vitalsignservicemongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ideas2it.vitalsignservicemongo.dto.VitalSignDto;
import com.ideas2it.vitalsignservicemongo.dto.VitalSignPrimaryKeys;

/**
 * VitalSignRepository used as a repository for vitalSignDto table.
 * 
 * @author Vigneshwaran N
 */
public interface VitalSignRepository extends MongoRepository<VitalSignDto, VitalSignPrimaryKeys> {

	/**
	 * findById method is used to get vitalSign for a particular id
	 * 
	 * @param patientId
	 * @return VitalSignDto
	 */
	@Query("{patient_id:?0}")
	List<VitalSignDto> findById(String patientId);

	/**
	 * deleteById method is used to delete vitalSign for a particular patient
	 * 
	 * @param patientId
	 * @return VitalSignDto
	 */
	@Query(value = "{'patient_id' : $0}", delete = true)
	void deleteById(String patientId);

	/**
	 * search method is used to seacrh text for a particular patient
	 * 
	 * @param name
	 * @return VitalSignDto
	 */
	@Query("{$text: { $search: ?0}}}")
	List<VitalSignDto> search(String name);

}
