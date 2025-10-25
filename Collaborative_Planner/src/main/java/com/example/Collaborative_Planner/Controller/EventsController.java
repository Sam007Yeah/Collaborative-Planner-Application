package com.example.Collaborative_Planner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Collaborative_Planner.Entities.EventEntity;
import com.example.Collaborative_Planner.Service.EventsService;

@RestController
@RequestMapping("/api/v1/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/getAllByUser/{userId}")
    public List<EventEntity> getAllEventsByUser(@PathVariable Long userId) {
        return eventsService.getAllEventsByUser(userId);
    }

    @GetMapping("getEventById/{eventId}")
    public EventEntity getEventById(@PathVariable Long eventId) {
        return eventsService.getByEventId(eventId);
    }

    @PostMapping("/create")
    public EventEntity createEvent(@RequestBody EventEntity event) {
        return eventsService.createEvent(event);
    }

    @PutMapping("/update/{eventId}")
    public EventEntity updateEvent(@PathVariable Long eventId, @RequestBody EventEntity updatedEvent) {
        return eventsService.updateEvent(eventId, updatedEvent);
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventsService.deleteEvent(eventId);
    }

}
