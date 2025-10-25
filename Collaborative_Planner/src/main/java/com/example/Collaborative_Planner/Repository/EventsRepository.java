package com.example.Collaborative_Planner.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Collaborative_Planner.Entities.EventEntity;

public interface EventsRepository extends JpaRepository<EventEntity, Long> {

    public List<EventEntity> findAllByOwnerId(long id);
}
