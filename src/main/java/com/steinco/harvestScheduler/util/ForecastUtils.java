package com.steinco.harvestScheduler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.steinco.pojo.Daily;
import com.steinco.pojo.Forecast;
import com.steinco.pojo.OpenWeatherForecast;

public class ForecastUtils {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<Forecast> MapOpenWeatherForecast2HarvestForecast(OpenWeatherForecast openWeatherForecast) throws ParseException {
		List<Forecast> forecastList = new ArrayList<Forecast>(); 
		
		ArrayList<Daily> lstDaily = (ArrayList<Daily>) openWeatherForecast.getDaily();
		
		Iterator<Daily> itDaily = lstDaily.iterator();
		
		while(itDaily.hasNext()) {
			Daily dailyForecast = itDaily.next();
			Forecast myForecast = new Forecast();
			Instant instant = Instant.ofEpochSecond(dailyForecast.getDt());
			myForecast.setForecastDateTime(dateFormat.parse(instant.toString()));
			myForecast.setMaxTemp(dailyForecast.getTemp().getMax());
			myForecast.setMinTemp(dailyForecast.getTemp().getMin());
			if(dailyForecast.getRain() == null) {dailyForecast.setRain(Double.valueOf(0));}
			myForecast.setRainFall(dailyForecast.getRain());
			forecastList.add(myForecast);
		}
		
		return forecastList;
	}
}
