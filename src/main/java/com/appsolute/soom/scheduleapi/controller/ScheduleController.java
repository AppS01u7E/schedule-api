package com.appsolute.soom.scheduleapi.controller;

import com.appsolute.soom.scheduleapi.data.dto.TaskDto;
import com.appsolute.soom.scheduleapi.data.request.AddScheduleRequest;
import com.appsolute.soom.scheduleapi.data.request.UpdateTaskRequest;
import com.appsolute.soom.scheduleapi.data.response.*;
import com.appsolute.soom.scheduleapi.data.type.PeriodType;
import com.appsolute.soom.scheduleapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/{accountUUID}")
    public ResponseEntity<AddAccountResponse> addTaskByPeriod(@PathVariable String accountUUID,
                                                              @RequestBody AddScheduleRequest request) {
        TaskDto task = scheduleService.addTask(accountUUID, request.date(), request.message(),
                request.period() == null ? PeriodType.DAY.getId() : request.period());
        AddAccountResponse response = new AddAccountResponse(task.UUID(), task.date(), task.period().getId(), task.message());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountUUID}/monthly/{year}/{month}")
    public ResponseEntity<GetMonthlyScheduleResponse> getMonthlySchedule(@PathVariable String accountUUID,
                                                @PathVariable Integer year,
                                                @PathVariable Integer month,
                                                Pageable pageable) {
        List<TaskDto> tasks = scheduleService.getMonthlySchedule(accountUUID, year, month, pageable);
        GetMonthlyScheduleResponse response = new GetMonthlyScheduleResponse(year, month, pageable.getPageSize(), pageable.getPageNumber(), tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountUUID}/weekly/{year}/{month}/{week}")
    public ResponseEntity<GetWeeklyScheduleResponse> getWeeklySchedule(@PathVariable String accountUUID,
                                               @PathVariable Integer year,
                                               @PathVariable Integer month,
                                               @PathVariable Integer week,
                                               Pageable pageable) {
        List<TaskDto> tasks = scheduleService.getWeeklySchedule(accountUUID, year, month, week, pageable);
        GetWeeklyScheduleResponse response = new GetWeeklyScheduleResponse(year, month, week, pageable.getPageSize(), pageable.getPageNumber(), tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountUUID}/daily/{year}/{month}/{day}")
    public ResponseEntity<GetDailyScheduleResponse> getDailySchedule(@PathVariable String accountUUID,
                                              @PathVariable Integer year,
                                              @PathVariable Integer month,
                                              @PathVariable Integer day,
                                              Pageable pageable) {
        List<TaskDto> tasks = scheduleService.getDailySchedule(accountUUID, year, month, day, pageable);
        GetDailyScheduleResponse response = new GetDailyScheduleResponse(year, month, day, pageable.getPageSize(), pageable.getPageNumber(), tasks);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> removeTask(@PathVariable Long uuid) {
        scheduleService.removeTask(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable Long uuid, @RequestBody UpdateTaskRequest request) {
        TaskDto task = scheduleService.updateTask(uuid, request.message(), request.date(), request.period());
        UpdateTaskResponse response = new UpdateTaskResponse(task.UUID(), task.date(), task.period().getId(), task.message());
        return ResponseEntity.ok(response);
    }
}
