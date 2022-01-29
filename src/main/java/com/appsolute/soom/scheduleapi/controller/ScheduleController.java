package com.appsolute.soom.scheduleapi.controller;

import com.appsolute.soom.scheduleapi.data.dto.TaskDto;
import com.appsolute.soom.scheduleapi.data.request.AddScheduleByDateRequest;
import com.appsolute.soom.scheduleapi.data.request.AddScheduleByPeriodRequest;
import com.appsolute.soom.scheduleapi.data.request.UpdateTaskRequest;
import com.appsolute.soom.scheduleapi.data.response.*;
import com.appsolute.soom.scheduleapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/{date}")
    public ResponseEntity<?> addTaskByDay(@DateTimeFormat(pattern = "yyyyMMdd")
                                             @PathVariable LocalDate date,
                                          @RequestBody AddScheduleByDateRequest request
    ) {
        TaskDto task = scheduleService.addTask(request.accountUUID(), date, request.message());
        AddAccountByDateResponse response = new AddAccountByDateResponse(task.UUID(), task.date(), task.message());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{date}/{period}")
    public ResponseEntity<?> addTaskByPeriod(@DateTimeFormat(pattern = "yyyyMMdd")
                                             @PathVariable LocalDate date,
                                             @PathVariable Integer period,
                                             @RequestBody AddScheduleByPeriodRequest request) {
        TaskDto task = scheduleService.addTask(request.accountUUID(), date, request.message(), period);
        AddAccountByPeriodResponse response = new AddAccountByPeriodResponse(task.UUID(), task.date(), task.period().getId(), task.message());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/monthly/{year}/{month}")
    public ResponseEntity<?> getMonthlySchedule(@PathVariable Integer year,
                                                @PathVariable Integer month,
                                                @RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<TaskDto> tasks = scheduleService.getMonthlySchedule(year, month, pageSize, pageNum);
        GetMonthlyScheduleResponse response = new GetMonthlyScheduleResponse(year, month, pageSize, pageNum, tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/weekly/{year}/{month}/{week}")
    public ResponseEntity<?> getWeeklySchedule(@PathVariable Integer year,
                                               @PathVariable Integer month,
                                               @PathVariable Integer week,
                                               @RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<TaskDto> tasks = scheduleService.getWeeklySchedule(year, month, pageSize, pageNum);
        GetWeeklyScheduleResponse response = new GetWeeklyScheduleResponse(year, month, week, pageSize, pageNum, tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/daily/{year}/{month}/{day}")
    public ResponseEntity<?> getDailySchedule(@PathVariable Integer year,
                                              @PathVariable Integer month,
                                              @PathVariable Integer day,
                                              @RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        List<TaskDto> tasks = scheduleService.getDailySchedule(year, month, pageSize, pageNum);
        GetDailyScheduleResponse response = new GetDailyScheduleResponse(year, month, day, pageSize, pageNum, tasks);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> removeTask(@PathVariable String uuid) {
        scheduleService.removeTask(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> updateTask(@PathVariable String uuid, @RequestBody UpdateTaskRequest request) {
        TaskDto task = scheduleService.updateTask(uuid, request.message(), request.date(), request.period());
        UpdateTaskResponse response = new UpdateTaskResponse(task.UUID(), task.date(), task.period().getId(), task.message());
        return ResponseEntity.ok(response);
    }
}
