package com.hospitalmgmt.authentication;

import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching

@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class AppointmentServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceApplication.class);

	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(AppointmentServiceApplication.class, args);
	}

	@Value("${server.port}")
	private String serverPort;

	@EventListener
	public void onStartup(ApplicationReadyEvent event) {
		String machineName = Optional.ofNullable(System.getenv("HOSTNAME")).orElse(System.getenv("COMPUTERNAME"));
		logger.info("Service started on: {}:{}", machineName, serverPort);
	}
}