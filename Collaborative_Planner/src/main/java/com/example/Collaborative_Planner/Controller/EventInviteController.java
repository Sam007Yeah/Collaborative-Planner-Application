package com.example.Collaborative_Planner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Collaborative_Planner.Entities.EventInvite;
import com.example.Collaborative_Planner.Service.EventInviteService;

@RestController
@RequestMapping("/api/v1/event-invites")
public class EventInviteController {

    @Autowired
    private EventInviteService eventInviteService;

    @GetMapping("/getAllInvites/{eventId}")
    public List<EventInvite> getAllInvitesForEvent(@PathVariable Long eventId) {
        return eventInviteService.getAllInvitesForEvent(eventId);
    }

    @GetMapping("/getInvite/{eventId}/{inviteeId}")
    public EventInvite getEventInviteByInviteeIdAndEvent(@PathVariable Long eventId, @PathVariable Long inviteeId) {
        return eventInviteService.getEventInviteByInviteeIdAndEvent(inviteeId, eventId);
    }

    @PostMapping("/createInvite")
    public EventInvite createEventInvite(@RequestBody EventInvite eventInvite) {
        return eventInviteService.createEventInvite(eventInvite);
    }

    @PostMapping("/updateInvite")
    public EventInvite updateEventInvite(@RequestBody EventInvite eventInvite) {
        return eventInviteService.updateEventInvite(eventInvite);
    }
}
