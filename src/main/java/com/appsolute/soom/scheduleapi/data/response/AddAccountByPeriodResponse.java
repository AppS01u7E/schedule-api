package com.appsolute.soom.scheduleapi.data.response;

import java.time.LocalDate;

public record AddAccountByPeriodResponse(
        String UUID,
        LocalDate date,
        Integer period,
        String message
) {}
