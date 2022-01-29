package com.appsolute.soom.scheduleapi.data.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UpdateTaskRequest(
        @DateTimeFormat(pattern = "yyyyMMdd")
        LocalDate date,
        Integer period,
        String message
) {}
