package com.iskhakovalilia.eventproject.service;

import com.iskhakovalilia.eventproject.entity.Event;

import java.util.List;
import java.util.Optional;
public interface EventService {

    Event saveEvent(Event event) ;

    Optional<Event> getEventById(Long id);

    boolean deleteEventById(Long id);

    List<Event> getAllEvents();
}
