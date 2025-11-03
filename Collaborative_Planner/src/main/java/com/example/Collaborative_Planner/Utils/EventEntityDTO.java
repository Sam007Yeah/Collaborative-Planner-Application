package com.example.Collaborative_Planner.Utils;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventEntityDTO {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long ownerId;
    private EventVisibility visibility;
    private List<Long> inviteeIds; // Only needed for SHARED events
}