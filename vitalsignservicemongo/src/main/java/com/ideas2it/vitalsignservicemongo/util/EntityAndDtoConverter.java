package com.ideas2it.vitalsignservicemongo.util;

import java.util.List;
import java.util.stream.Collectors;

import com.ideas2it.vitalsignservicemongo.dto.VitalSignDto;
import com.ideas2it.vitalsignservicemongo.dto.VitalSignPrimaryKeys;
import com.ideas2it.vitalsignservicemongo.entity.VitalSignEntity;

/**
 * EntityAndDtoConverter is used for convert Dto to Entity and Entity to Dto.
 * 
 * @author Vigneshwaran N
 */
public class EntityAndDtoConverter {

	/**
	 * formEntity which converts vitalSignDto to VitalSignEntity.
	 * 
	 * @param vitalSignDto
	 * @author Vigneshwaran N
	 */
	public static VitalSignEntity formEntity(VitalSignDto vitalSignDto1) {
		ConverterEntity cons = (vitalSignDto) -> {
			VitalSignEntity vitalSignEntity = new VitalSignEntity.VitalSignEntityBuilder()
					.patientId(vitalSignDto.getKeys().getPatientId().toString())
					.lastVisitDate(vitalSignDto.getKeys().getLastVisitDate()).temperatute(vitalSignDto.getTemperatute())
					.bloodPressure(vitalSignDto.getBloodPressure()).sugerLevel(vitalSignDto.getSugerLevel())
					.visitReason(vitalSignDto.getVisitReason()).build();
			return vitalSignEntity;
		};
		return cons.dtoToEntity(vitalSignDto1);
	}

	/**
	 * formDto which converts vitalSignEntity to VitalSignDto.
	 * 
	 * @param vitalSignEntity
	 * @author Vigneshwaran N
	 */
	public static VitalSignDto formDto(VitalSignEntity vitalSignEntity1) {

		ConverterDto con = (vitalSignEntity) -> {
			VitalSignDto vitalSignDto = new VitalSignDto();
			VitalSignPrimaryKeys vitalSignPrimaryKeys = new VitalSignPrimaryKeys();
			vitalSignPrimaryKeys.setPatientId(vitalSignEntity.getPatientId().toString());
			vitalSignPrimaryKeys.setLastVisitDate(vitalSignEntity.getLastVisitDate());
			vitalSignDto.setKeys(vitalSignPrimaryKeys);
			vitalSignDto.setTemperatute(vitalSignEntity.getTemperatute());
			vitalSignDto.setBloodPressure(vitalSignEntity.getBloodPressure());
			vitalSignDto.setSugerLevel(vitalSignEntity.getSugerLevel());
			vitalSignDto.setVisitReason(vitalSignEntity.getVisitReason());
			return vitalSignDto;
		};
		return con.entityToDto(vitalSignEntity1);
	}

	/**
	 * formEntity which converts vitalSignDto to VitalSignEntity.
	 * 
	 * @param vitalSignDto
	 * @author Vigneshwaran N
	 */
	public static List<VitalSignEntity> formEntityList(List<VitalSignDto> vitalSignDto1) {
		ConverterEntity cons = (vitalSignDto) -> {
			VitalSignEntity vitalSignEntity = new VitalSignEntity.VitalSignEntityBuilder()
					.patientId(vitalSignDto.getKeys().getPatientId().toString())
					.lastVisitDate(vitalSignDto.getKeys().getLastVisitDate()).temperatute(vitalSignDto.getTemperatute())
					.bloodPressure(vitalSignDto.getBloodPressure()).sugerLevel(vitalSignDto.getSugerLevel())
					.visitReason(vitalSignDto.getVisitReason()).build();
			return vitalSignEntity;
		};
		List<VitalSignEntity> liEntity = vitalSignDto1.stream().map(dto -> cons.dtoToEntity(dto))
				.collect(Collectors.toList());
		return liEntity;
	}

}
