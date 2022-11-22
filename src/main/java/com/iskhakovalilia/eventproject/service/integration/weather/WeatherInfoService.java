package com.iskhakovalilia.eventproject.service.integration.weather;

import com.iskhakovalilia.eventproject.exceptions.NoSuchGeoObjectException;

import java.time.LocalDate;

public interface WeatherInfoService {
    /**
     * Returns temperature at specific city in the country for the date.
     * If there is no information either on date, city or country, null is returned
     * @param date Temperature for the date
     * @param city Temperature in the city
     * @param country City in the country
     * @return Temperature for the date in the city of country or null, if it can not be handled.
     */
    Float getWeatherForDateAndLocation(LocalDate date, String city, String country);

}
