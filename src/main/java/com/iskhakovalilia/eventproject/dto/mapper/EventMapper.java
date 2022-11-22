package com.iskhakovalilia.eventproject.dto.mapper;

import com.iskhakovalilia.eventproject.dto.EventDTO;
import com.iskhakovalilia.eventproject.entity.Event;
import org.mapstruct.Mapper;

@Mapper(uses = GuestMapper.class, componentModel = "spring")
public interface EventMapper {
    EventDTO toDto(Event event);
    Event toEntity(EventDTO eventDTO);
}
