package com.iskhakovalilia.eventproject.repository;

import com.iskhakovalilia.eventproject.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findAll();
}
