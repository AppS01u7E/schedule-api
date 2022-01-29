package com.appsolute.soom.scheduleapi.service;

import com.appsolute.soom.scheduleapi.data.dto.TaskDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

//해당 Service 는 DB 접근이나 Repository 로만 구현가능하지 않다. 그러므로 Transactional 이 강제되지 않는다.
public interface ScheduleService {
    TaskDto addTask(String accountUUID, LocalDate date, String message, Integer period);

    List<TaskDto> getMonthlySchedule(String accountUUID, Integer year, Integer month, Pageable pageable);
    List<TaskDto> getWeeklySchedule(String accountUUID, Integer year, Integer month, Integer week, Pageable pageable);
    List<TaskDto> getDailySchedule(String accountUUID, Integer year, Integer month, Integer day, Pageable pageable);

    void removeTask(Long uuid);

    TaskDto updateTask(Long uuid, String message, LocalDate date, Integer period);
}
