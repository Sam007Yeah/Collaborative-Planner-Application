package com.example.Collaborative_Planner.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Collaborative_Planner.Entities.EventInvite;

@Repository
public interface EventInviteRepository extends JpaRepository<EventInvite, Long> {
    public List<EventInvite> findByInviteeId(Long inviteeId);

    public List<EventInvite> findAllByEventId(Long eventId);
}
