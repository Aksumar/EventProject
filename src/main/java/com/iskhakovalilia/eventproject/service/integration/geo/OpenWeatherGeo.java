package com.iskhakovalilia.eventproject.service.integration.geo;

import com.iskhakovalilia.eventproject.dto.weather.GeoInfoDTO;
import com.iskhakovalilia.eventproject.exceptions.NoSuchGeoObjectException;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class OpenWeatherGeo implements GeoInfoService {
    @Value("${openweathermap.key}")
    private String API_KEY;

   // @Value("${openweathermap.geo.url}")
    private String BASE_URL;
    private static final String QP_APPID = "appid";
    private static final String QP_CITY = "q";

    private final RestTemplate restTemplate;

    OpenWeatherGeo(RestTemplate restTemplate,
    @Value("${openweathermap.geo.url}") String BASE_URL

    ) {
        this.restTemplate = restTemplate;
        this.BASE_URL = BASE_URL;
    }

    @Override
    public List<Float> getLatitudeAndLongitudeByCityName(String city, String country) {
        String countryCode = fromCountryNameToCountryCode(country);
        String url = createUrl(city, countryCode);
        GeoInfoDTO[] result = restTemplate.getForObject(url, GeoInfoDTO[].class);

        if (result == null || result.length == 0)
            throw new NoSuchGeoObjectException( "There is no city : " + city);

        GeoInfoDTO geoInfo = result[0];
        return List.of(geoInfo.getLat(), geoInfo.getLon());
    }

    private String fromCountryNameToCountryCode(String country)  {
        List<CountryCode> countryCodes = CountryCode.findByName(country);

        if (countryCodes.isEmpty())
            throw new NoSuchGeoObjectException( "There is no country : " + country);

        return countryCodes.iterator().next().getName();
    }

    private String createUrl(String city, String countryCode){
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam(QP_CITY, String.join(",", List.of(city, countryCode)))
                .queryParam(QP_APPID, API_KEY)
                .build()
                .toUriString();
    }
}
