package com.example.kafkaProducer;

import com.example.kafkaProducer.service.DataProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(KafkaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Autowired
	private DataProcessorService dataProcessorService;

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting...");

		dataProcessorService.Run(10);

		log.info("Finished");
	}
}
