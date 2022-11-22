package com.iskhakovalilia.eventproject.dto.mapper;

import com.iskhakovalilia.eventproject.dto.GuestDTO;
import com.iskhakovalilia.eventproject.entity.Guest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestDTO toDto(Guest guest);
    Guest toEntity(GuestDTO guestDTO);
}
