package com.example.Collaborative_Planner.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Collaborative_Planner.Entities.EventEntity;
import com.example.Collaborative_Planner.Repository.EventsRepository;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    public List<EventEntity> getAllEventsByUser(Long id) {
        return eventsRepository.findAllByOwnerId(id);
    }

    public EventEntity getByEventId(Long eventId) {
        return eventsRepository.findById(eventId).orElse(null);
    }

    public EventEntity createEvent(EventEntity event) {
        return eventsRepository.save(event);
    }

    public EventEntity updateEvent(Long eventId, EventEntity updatedEvent) {
        EventEntity existingEvent = eventsRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        // Only update fields if they're not null
        if (updatedEvent.getTitle() != null) {
            existingEvent.setTitle(updatedEvent.getTitle());
        }
        if (updatedEvent.getDescription() != null) {
            existingEvent.setDescription(updatedEvent.getDescription());
        }
        if (updatedEvent.getStartTime() != null) {
            existingEvent.setStartTime(updatedEvent.getStartTime());
        }
        if (updatedEvent.getEndTime() != null) {
            existingEvent.setEndTime(updatedEvent.getEndTime());
        }

        return eventsRepository.save(existingEvent);
    }

    public void deleteEvent(Long eventId) {
        eventsRepository.deleteById(eventId);
    }

}
