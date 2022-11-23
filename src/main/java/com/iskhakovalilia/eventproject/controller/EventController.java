package com.iskhakovalilia.eventproject.controller;

import com.iskhakovalilia.eventproject.dto.EventDTO;
import com.iskhakovalilia.eventproject.dto.mapper.EventMapper;
import com.iskhakovalilia.eventproject.entity.Event;
import com.iskhakovalilia.eventproject.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @GetMapping("/{id}")
    ResponseEntity<EventDTO> getEvent(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(event -> ResponseEntity.ok(eventMapper.toDto(event)))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/create")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        Event eventToSave = eventMapper.toEntity(eventDTO);
        Event savedEvent = eventService.saveEvent(eventToSave);

        return ResponseEntity.ok(eventMapper.toDto(savedEvent));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvent(@PathVariable Long id) {
        if (eventService.deleteEventById(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> result = eventService.getAllEvents().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
}
