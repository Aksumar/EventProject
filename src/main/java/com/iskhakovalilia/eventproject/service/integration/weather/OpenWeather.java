package com.iskhakovalilia.eventproject.service.integration.weather;

import com.iskhakovalilia.eventproject.dto.weather.WeatherDTO;
import com.iskhakovalilia.eventproject.dto.weather.WeatherInfoDTO;
import com.iskhakovalilia.eventproject.service.integration.geo.GeoInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpenWeather implements WeatherInfoService {
    @Value("${openweathermap.key}")
    private String API_KEY;

    @Value("${openweathermap.url}")
    private String BASE_URL;

    private static final String QP_LATITUDE = "lat";
    private static final String QP_LONGITUDE = "lon";
    private static final String QP_APPID = "appid";
    private static final String QP_UNITS = "units";
    private static final String METRIC_UNIT = "metric";

    private final RestTemplate restTemplate;
    private final GeoInfoService geoInfoService;

    OpenWeather(RestTemplate restTemplate, GeoInfoService geoInfoService) {
        this.restTemplate = restTemplate;
        this.geoInfoService = geoInfoService;
    }

    private List<WeatherInfoDTO> getWeatherForDateAndLocationForNext5Days(float latitude, float longitude) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam(QP_LATITUDE, latitude)
                .queryParam(QP_LONGITUDE, longitude)
                .queryParam(QP_UNITS, METRIC_UNIT)
                .queryParam(QP_APPID, API_KEY)
                .build()
                .toUriString();

        WeatherDTO result = restTemplate.getForObject(url, WeatherDTO.class);

        if (result == null)
            return null;

        return result.weatherInfoDTO;
    }

    @Override
    public Float getWeatherForDateAndLocation(LocalDate date, String city, String country)  {
        List<Float> geo = geoInfoService.getLatitudeAndLongitudeByCityName(city, country);
        Float lat = geo.get(0);
        Float lon = geo.get(1);

        List<WeatherInfoDTO> infoFor5Days = getWeatherForDateAndLocationForNext5Days(lat, lon);

        if (infoFor5Days == null)
            return null;

        List<WeatherInfoDTO> weatherInfoThroughDay = infoFor5Days.stream()
                .filter(periodInfo -> periodInfo.getDateTime().toLocalDate().isEqual(date)).collect(Collectors.toList());

        if (weatherInfoThroughDay.isEmpty())
            return null;

        int middleIndex = weatherInfoThroughDay.size() / 2;

        WeatherInfoDTO middleOfTheDay = weatherInfoThroughDay.get(middleIndex);

        return middleOfTheDay.getTemp();
    }
}

