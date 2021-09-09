package com.ideas2it.vitalsignservicemongo.util;

import com.ideas2it.vitalsignservicemongo.dto.VitalSignDto;
import com.ideas2it.vitalsignservicemongo.entity.VitalSignEntity;

/**
 * ConverterDto which converts given entity to Dto.
 * 
 * @author Vigneshwaran N
 */
@FunctionalInterface
public interface ConverterDto {

	VitalSignDto entityToDto(VitalSignEntity entity);
}
