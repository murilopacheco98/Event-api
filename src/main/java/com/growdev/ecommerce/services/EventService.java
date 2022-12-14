package com.growdev.ecommerce.services;

import com.growdev.ecommerce.dto.EventDTO;
import com.growdev.ecommerce.entities.City;
import com.growdev.ecommerce.entities.Event;
import com.growdev.ecommerce.exceptions.DatabaseException;
import com.growdev.ecommerce.exceptions.ResourceNotFoundException;
import com.growdev.ecommerce.repositories.CityRepository;
import com.growdev.ecommerce.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Transactional(readOnly = true)
  public Page<EventDTO> findAll(PageRequest pageRequest) {//pageRequest
    Page<Event> list = eventRepository.findAll(pageRequest);
    return list.map(EventDTO::new);
  }

  public EventDTO save(EventDTO eventDTO) {
    Event event = new Event(eventDTO);
    try {
      eventRepository.save(event);
    } catch (Exception e) {
      throw new DatabaseException("Can't save this event.");
    }
    return new EventDTO(event);
  }

  public EventDTO update(EventDTO eventDTO, Long id) {
    Event event = eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NOT FOUND " + id));
    event.setName(eventDTO.getName());
    event.setDate(eventDTO.getDate());
    event.setUrl(eventDTO.getUrl());
    event.setCity(new City(eventDTO.getCityDTO()));
    try {
      eventRepository.save(event);
    } catch (Exception e) {
      throw new DatabaseException("Can't update this event.");
    }
  return eventDTO;
  }

  public void delete(Long id) {
    try {
      eventRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    } catch (DataIntegrityViolationException e) {
    throw new DatabaseException("Intregrity violation");
    }
  }
}
