package com.ideas2it.vitalsignservicemongo.controller;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.vitalsignservicemongo.advice.TimeTrackerAnnotation;
import com.ideas2it.vitalsignservicemongo.entity.ResultEntity;
import com.ideas2it.vitalsignservicemongo.entity.VitalSignEntity;
import com.ideas2it.vitalsignservicemongo.service.VitalSignService;

/**
 * VitalSignController which is used for VitalSign end point.
 * 
 * @author Vigneshwaran N
 */
@RestController
@RequestMapping("/vitalSign")
@CacheConfig(cacheNames = { "vitalData" })
@RefreshScope
public class VitalSignController {

	@Autowired
	public VitalSignController(VitalSignService vitalSignService, RabbitTemplate rabbitTemplate) {
		this.vitalSignService = vitalSignService;
		this.rabbitTemplate = rabbitTemplate;
	}

	private final VitalSignService vitalSignService;
	private final RabbitTemplate rabbitTemplate;

	@Value("${healthcare.management.queue}")
	String queue;
	@Value("${healthcare.management.exchange}")
	String exchange;
	@Value("${healthcare.management.routingqueue}")
	String routingQueue;

	private static final Logger logger = LogManager.getLogger(VitalSignController.class);

	/**
	 * register method is used to register VitalSign for each patients.
	 * 
	 * @param vitalSignEntity
	 * @return Boolean
	 */
	@PostMapping("/")
	@TimeTrackerAnnotation
	public ResultEntity register(@RequestBody VitalSignEntity vitalSignEntity) {
		logger.log(Level.ALL, "vitalSignEntity.getLastVisitDate()" + vitalSignEntity.getLastVisitDate());
		rabbitTemplate.convertAndSend(exchange, routingQueue, vitalSignEntity);
		return vitalSignService.save(vitalSignEntity);
	}

	/**
	 * getVitalSignById method is used to get VitalSign for particular patients.
	 * 
	 * @param patientId
	 * @param lastVisitDate
	 * @return VitalSignEntity
	 */
	@GetMapping("/getVitalSign/{patientId}/{lastVisitDate}")
	@TimeTrackerAnnotation
	@Cacheable(value = "vitalData", key = "{#patientId,#lastVisitDate}")
	public VitalSignEntity getVitalSignById(@PathVariable String patientId, @PathVariable String lastVisitDate) {
		logger.log(Level.ALL, "patientId" + patientId);
		return vitalSignService.getVitalSignById(patientId, lastVisitDate);
	}

	/**
	 * deleteById method is used to delete particular patients vitalSign detail.
	 * 
	 * @param patientId
	 * @param lastVisitDate
	 * @return deleteById
	 */
	@DeleteMapping("/{patientId}")
	public ResultEntity deleteById(@PathVariable String patientId) {
		return vitalSignService.deleteById(patientId);
	}

	/**
	 * searchTxt method is used to search particular string in vitalSign Entity.
	 * 
	 * @param reason
	 * @return VitalSignEntity
	 */
	@GetMapping("/textSearch/{reason}")
	public List<VitalSignEntity> searchTxt(@PathVariable String reason) {
		return vitalSignService.searchTxt(reason);
	}
}
