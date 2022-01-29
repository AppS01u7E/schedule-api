package com.appsolute.soom.scheduleapi.service;

import com.appsolute.soom.scheduleapi.data.dto.TaskDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    TaskDto addTask(String AccountUUID, LocalDate date, String message);
    TaskDto addTask(String accountUUID, LocalDate date, String message, Integer period);

    List<TaskDto> getMonthlySchedule(Integer year, Integer month, Integer pageSize, Integer pageNum);
    List<TaskDto> getWeeklySchedule(Integer year, Integer month, Integer pageSize, Integer pageNum);
    List<TaskDto> getDailySchedule(Integer year, Integer month, Integer pageSize, Integer pageNum);

    void removeTask(String uuid);

    TaskDto updateTask(String uuid, String message, LocalDate date, Integer period);
}
