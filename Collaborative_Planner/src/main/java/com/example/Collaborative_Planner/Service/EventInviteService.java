package com.example.Collaborative_Planner.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Collaborative_Planner.Entities.EventInvite;
import com.example.Collaborative_Planner.Repository.EventInviteRepository;
import com.example.Collaborative_Planner.Utils.InvitationStatus;

@Service
public class EventInviteService {

    @Autowired
    private EventInviteRepository eventInviteRepository;

    public List<EventInvite> getAllInvitesForEvent(long eventId) {
        return eventInviteRepository.findAllByEventId(eventId);
    }

    public EventInvite getEventInviteByInviteeIdAndEvent(long inviteeId, long eventId) {
        List<EventInvite> invites = eventInviteRepository.findByInviteeId(inviteeId);
        for (EventInvite invite : invites) {
            if (invite.getEvent().getId() == eventId) {
                return invite;
            }
        }
        return null;
    }

    // create a new invite
    public EventInvite createEventInvite(EventInvite eventInvite) {
        eventInvite.setStatus(InvitationStatus.PENDING);
        return eventInviteRepository.save(eventInvite);
    }

    public EventInvite updateEventInvite(EventInvite eventInvite) {
        Optional<EventInvite> existingInvite = eventInviteRepository.findById(eventInvite.getId());
        if (existingInvite.isPresent()) {
            EventInvite inviteToUpdate = existingInvite.get();
            inviteToUpdate.setStatus(eventInvite.getStatus());
            inviteToUpdate.setRespondedAt(eventInvite.getRespondedAt());
            return eventInviteRepository.save(inviteToUpdate);
        }
        return null;
    }

}
