package com.ideas2it.vitalsignservicemongo.consumer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ideas2it.vitalsignservicemongo.entity.VitalSignEntity;

/**
 * VitalSignConsumer is used to consume queued value
 * 
 * @author Vigneshwaran N
 */
@Component
public class VitalSignConsumer {

	private static final Logger logger = LogManager.getLogger(VitalSignConsumer.class);

	/**
	 * consumeMessageFromQueue is used to consume queued value
	 * 
	 * @param entity
	 * @author Vigneshwaran N
	 */
	@RabbitListener(queues = "${healthcare.management.queue}")
	public void consumeMessageFromQueue(VitalSignEntity entity) {
		logger.log(Level.ALL, "Message received from QUEUE " + entity);
	}

}
