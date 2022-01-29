package com.appsolute.soom.scheduleapi.data.dto;


import com.appsolute.soom.scheduleapi.data.type.PeriodType;

import java.time.LocalDate;

public record TaskDto(
        String UUID,
        String scheduleUUID,
        LocalDate date,
        PeriodType period,
        String message
) {}
