package com.steinco.pojo;

import java.util.Date;

public class Forecast {
	
	private Date forecastDateTime;
	private Double maxTemp;
	private Double minTemp;
	private Double rainFall;
	
	
	public Date getForecastDateTime() {
		return forecastDateTime;
	}
	public void setForecastDateTime(Date forecastDateTime) {
		this.forecastDateTime = forecastDateTime;
	}
	public Double getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(Double maxTemp) {
		this.maxTemp = maxTemp;
	}
	public Double getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(Double minTemp) {
		this.minTemp = minTemp;
	}
	public Double getRainFall() {
		return rainFall;
	}
	public void setRainFall(Double rainFall) {
		this.rainFall = rainFall;
	}

	

}
