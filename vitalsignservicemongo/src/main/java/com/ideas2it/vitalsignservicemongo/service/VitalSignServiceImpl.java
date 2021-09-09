package com.ideas2it.vitalsignservicemongo.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.ideas2it.vitalsignservicemongo.client.PatientClient;
import com.ideas2it.vitalsignservicemongo.dto.VitalSignDto;
import com.ideas2it.vitalsignservicemongo.dto.VitalSignPrimaryKeys;
import com.ideas2it.vitalsignservicemongo.entity.ResultEntity;
import com.ideas2it.vitalsignservicemongo.entity.VitalSignEntity;
import com.ideas2it.vitalsignservicemongo.entity.VitalSignStatus;
import com.ideas2it.vitalsignservicemongo.repository.VitalSignRepository;
import com.ideas2it.vitalsignservicemongo.util.EntityAndDtoConverter;

/**
 * VitalSignServiceImpl which implement VitalSignService.
 * 
 * @author Vigneshwaran N
 */
@Service
public class VitalSignServiceImpl implements VitalSignService {

	@Autowired
	public VitalSignServiceImpl(VitalSignRepository vitalSignRepository, PatientClient patientClient,
			RabbitTemplate template, MongoTemplate mongoTemplate) {
		super();
		this.vitalSignRepository = vitalSignRepository;
		this.patientClient = patientClient;
		this.template = template;
		this.mongoTemplate = mongoTemplate;
	}

	private final VitalSignRepository vitalSignRepository;
	private final PatientClient patientClient;
	private final RabbitTemplate template;
	private final MongoTemplate mongoTemplate;

	@Value("${healthcare.management.queue}")
	String queue;
	@Value("${healthcare.management.exchange}")
	String exchange;
	@Value("${healthcare.management.routingqueue}")
	String routingQueue;

	private static final Logger logger = LogManager.getLogger(VitalSignServiceImpl.class);

	@Override
	public ResultEntity save(VitalSignEntity vitalSignEntity) {
		ResultEntity result = new ResultEntity();
		result.setResult(false);
		VitalSignStatus status = new VitalSignStatus(vitalSignEntity, "PROCESS", "Your VitalSign updated successfuly");
		template.convertAndSend(exchange, routingQueue, status);
		if (patientClient.getById(vitalSignEntity.getPatientId().toString()) != null) {
			vitalSignRepository.save(EntityAndDtoConverter.formDto(vitalSignEntity));
			result.setResult(true);
			return result;
		}
		logger.log(Level.ALL, "Patient not found");
		return result;
	}

	@Override
	public VitalSignEntity getVitalSignById(String patientId, String lastVisitDate) {
		VitalSignPrimaryKeys vitalSignPrimaryKeys = new VitalSignPrimaryKeys();
		vitalSignPrimaryKeys.setPatientId(patientId);
		vitalSignPrimaryKeys.setLastVisitDate(lastVisitDate);
		Optional<VitalSignDto> vitalSignDto = vitalSignRepository.findById(vitalSignPrimaryKeys);
		logger.log(Level.ALL, vitalSignDto + "----------------------????");
		try {
			vitalSignDto.orElseThrow(() -> new Exception("Please enter correct patientId/date"));
			return EntityAndDtoConverter.formEntity(vitalSignDto.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultEntity deleteById(String patientId) {
		VitalSignPrimaryKeys vitalSignPrimaryKeys = new VitalSignPrimaryKeys();
		vitalSignPrimaryKeys.setPatientId(patientId);
		List<VitalSignDto> vitalSignDtoList = vitalSignRepository.findById(patientId);
		logger.log(Level.ALL, "patientId" + patientId);
		ResultEntity result = new ResultEntity();
		result.setResult(false);
		if (!vitalSignDtoList.isEmpty()) {
			vitalSignRepository.deleteById(patientId);
			result.setResult(true);
			return result;
		}
		return result;
	}

	@Override
	public List<VitalSignEntity> searchTxt(String reason) {
		List<VitalSignDto> liUserDto1 = vitalSignRepository.search(reason);
		logger.log(Level.ALL, liUserDto1);
		return EntityAndDtoConverter.formEntityList(liUserDto1);
	}

}
