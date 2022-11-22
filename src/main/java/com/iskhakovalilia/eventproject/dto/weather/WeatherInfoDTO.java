package com.iskhakovalilia.eventproject.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfoDTO {
    LocalDateTime dateTime;
    Float temp;

    @JsonProperty("dt")
    private void mapDepartmentName(Integer utcTime) {
        this.dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(utcTime), ZoneId.systemDefault());
    }

    @JsonProperty("main")
    private void mapTemperature(Map<String, String> temperature) {
        this.temp = Float.parseFloat(temperature.get("temp"));
    }

    public Float getTemp() {
        return temp;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}