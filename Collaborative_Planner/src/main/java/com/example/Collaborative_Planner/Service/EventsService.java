package com.example.Collaborative_Planner.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Collaborative_Planner.Entities.EventEntity;
import com.example.Collaborative_Planner.Entities.EventInvite;
import com.example.Collaborative_Planner.Entities.UserEntity;
import com.example.Collaborative_Planner.Repository.EventsRepository;
import com.example.Collaborative_Planner.Repository.UserRepository;
import com.example.Collaborative_Planner.Utils.EventEntityDTO;
import com.example.Collaborative_Planner.Utils.EventVisibility;
import com.example.Collaborative_Planner.Utils.InvitationStatus;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private UserRepository userRepository;

    public List<EventEntity> getAllEventsByUser(Long id) {
        return eventsRepository.findAllByOwnerId(id);
    }

    public EventEntity getByEventId(Long eventId) {
        return eventsRepository.findById(eventId).orElse(null);
    }

    public EventEntity createEvent(EventEntityDTO dto) {
        EventEntity event = new EventEntity();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setStartTime(dto.getStartTime());
        event.setEndTime(dto.getEndTime());
        event.setVisibility(dto.getVisibility());
        event.setOwner(userRepository.findById(dto.getOwnerId()).orElseThrow());

        if (dto.getVisibility() == EventVisibility.SHARED && dto.getInviteeIds() != null) {
            // Logic to handle invites can be added here
            for (Long inviteeId : dto.getInviteeIds()) {
                UserEntity invitee = userRepository.findById(inviteeId).orElseThrow();
                EventInvite invite = new EventInvite();
                invite.setEvent(event);
                invite.setInvitee(invitee);
                invite.setStatus(InvitationStatus.PENDING);
                invite.setInvitedAt(LocalDateTime.now());
                event.getInvites().add(invite);
            }
        }
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
