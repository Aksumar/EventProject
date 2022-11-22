package com.iskhakovalilia.eventproject.service;

import com.iskhakovalilia.eventproject.entity.Event;
import com.iskhakovalilia.eventproject.repository.EventRepository;
import com.iskhakovalilia.eventproject.service.integration.weather.WeatherInfoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final WeatherInfoService weatherInfoService;

    EventServiceImpl(EventRepository eventRepository, WeatherInfoService weatherInfoService) {
        this.eventRepository = eventRepository;
        this.weatherInfoService = weatherInfoService;
    }

    @Override
    public Event saveEvent(Event event) {
        String city = event.getCity();
        String country = event.getCountry();
        LocalDate dateTime = event.getDate();

        Float temp = weatherInfoService.getWeatherForDateAndLocation(dateTime, city, country);
        event.setTemperature(temp);

        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public boolean deleteEventById(Long id) {
        Optional<Event> optionalEvent = getEventById(id);

        if (optionalEvent.isPresent()) {
            eventRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
