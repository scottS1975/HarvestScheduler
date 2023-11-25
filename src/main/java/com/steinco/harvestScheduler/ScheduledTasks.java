package com.steinco.harvestScheduler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.steinco.harvestScheduler.tasks.OpenWeatherProcessing;

import org.slf4j.Logger;

@Component
public class ScheduledTasks {
	
	@Autowired
	private OpenWeatherProcessing openWeatherProcessing;
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 50000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
	
	@Scheduled(fixedRate = 5000)
	public void getForecast() {
		try {
			openWeatherProcessing.MakeAndProcessCall();
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
	}
}
