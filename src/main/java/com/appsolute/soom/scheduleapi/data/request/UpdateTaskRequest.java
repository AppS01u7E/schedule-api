package com.appsolute.soom.scheduleapi.data.request;

import com.appsolute.soom.scheduleapi.data.type.PeriodType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 설명을 입력해주세요!
 *
 * @author JeeInho
 * @version 0.0.1alpha-RELEASE
 * @see
 * @since 0.0.1alpha-RELEASE
 */
public record UpdateTaskRequest(
        @DateTimeFormat(pattern = "yyyyMMdd")
        LocalDate date,
        Integer period,
        String message
) {}
