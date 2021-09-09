package com.ideas2it.vitalsignservicemongo.util;

import com.ideas2it.vitalsignservicemongo.dto.VitalSignDto;
import com.ideas2it.vitalsignservicemongo.entity.VitalSignEntity;

/**
 * ConverterEntity which converts given dto to entity.
 * 
 * @author Vigneshwaran N
 */
@FunctionalInterface
public interface ConverterEntity {

	VitalSignEntity dtoToEntity(VitalSignDto dto);	

}
