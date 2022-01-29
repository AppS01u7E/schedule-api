package com.appsolute.soom.scheduleapi.repository;

import com.appsolute.soom.scheduleapi.data.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Page<TaskEntity> getTaskEntitiesByScheduleUUIDAndDateBetween(Long scheduleUUID, LocalDate rangeStart, LocalDate rangeEnd, Pageable pageable);
    Page<TaskEntity> getTaskEntitiesByScheduleUUIDAndDate(Long scheduleUUID, LocalDate date, Pageable pageable);
    TaskEntity getByUUID(Long UUID);
    void removeByUUID(Long UUID);
}
