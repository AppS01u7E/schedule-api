package com.appsolute.soom.scheduleapi.data.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UpdateTaskResponse(
        String uuid,
        @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date,
        Integer period,
        String message) {
}
