package com.steinco.harvestScheduler.tasks;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.steinco.harvestScheduler.util.ForecastUtils;
import com.steinco.pojo.Forecast;
import com.steinco.pojo.OpenWeatherForecast;

@Component
public class OpenWeatherProcessing {

	private static ForecastUtils forecastUtils = new ForecastUtils();
	
	private static String openWeatherAPIURI = "https://api.openweathermap.org/data/2.5/onecall?lat=40.356&lon=-76.011&units=imperial&APPID=21c738c7801c4a6585a43faa1c15aa54";
	
	@Autowired
	private RestTemplate restTemplate;

	public void MakeAndProcessCall() throws StreamReadException, DatabindException, IOException, ParseException {
		/*ObjectMapper om = new ObjectMapper();
		OpenWeatherForecast owForecast = om.readValue(
				new File("/home/scott/eclipse-workspace/harvestScheduler/openweatherresponse.json"),
				OpenWeatherForecast.class);*/

		ResponseEntity<OpenWeatherForecast> owRsp = restTemplate.exchange(openWeatherAPIURI, HttpMethod.POST, null, OpenWeatherForecast.class );
		OpenWeatherForecast owForecast = owRsp.getBody();
		
		
		List<Forecast> lstForecast = forecastUtils.MapOpenWeatherForecast2HarvestForecast(owForecast);
		
		Iterator<Forecast> itForecast = lstForecast.iterator();
		
		while(itForecast.hasNext()) {
			ResponseEntity<String> rsp = restTemplate.postForEntity("http://localhost:8082/forecasts", itForecast.next(), String.class );
			System.out.println(rsp);
		}
		
		System.out.println("Made it");
	}

}
