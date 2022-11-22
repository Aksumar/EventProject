package com.iskhakovalilia.eventproject.service.integration.geo;

import com.iskhakovalilia.eventproject.exceptions.NoSuchGeoObjectException;

import java.util.List;

public interface GeoInfoService {
    /**
     * Provides latitude and longitude of a specific city. If there is no such city or country
     * @param city City to find latitude and longitude
     * @param country Country of the city
     * @return An array of size 2. The first object is latitude, the second object is longitude
     * @throws NoSuchGeoObjectException if such city/country can not be handled
     */
    List<Float> getLatitudeAndLongitudeByCityName(String city, String country);
}
