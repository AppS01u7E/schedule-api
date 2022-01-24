package com.appsolute.soom.scheduleapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    @PostMapping("/{date}")
    public ResponseEntity<?> addSchedule(@DateTimeFormat(pattern = "yyyyMMdd")
                                             @PathVariable LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{date}/{period}")
    public ResponseEntity<?> addSchedule(@DateTimeFormat(pattern = "yyyyMMdd")
                                             @PathVariable LocalDate date,
                                         @PathVariable Integer period) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/monthly/{date}")
    public ResponseEntity<?> addScheduleByDay(@DateTimeFormat(pattern = "yyyyMMdd")
                                                  @PathVariable LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/weekly/{year}/{month}/{week}")
    public ResponseEntity<?> addScheduleByPeriod(@PathVariable Integer year,
                                                 @PathVariable Integer month,
                                                 @PathVariable Integer week) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/daily/{year}/{month}/{day}")
    public ResponseEntity<?> getSchedule(@PathVariable Integer year,
                                         @PathVariable Integer month,
                                         @PathVariable Integer day) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{date}")
    public ResponseEntity<?> removeSchedule(@DateTimeFormat(pattern = "yyyyMMdd")
                                                @PathVariable LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{date}")
    public ResponseEntity<?> updateSchedule(@DateTimeFormat(pattern = "yyyyMMdd")
                                                @PathVariable LocalDate date) {
        return ResponseEntity.ok().build();
    }
}
