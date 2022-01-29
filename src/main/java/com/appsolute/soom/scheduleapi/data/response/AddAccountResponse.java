package com.appsolute.soom.scheduleapi.data.response;

import java.time.LocalDate;

public record AddAccountResponse(
        Long UUID,
        LocalDate date,
        Integer period,
        String message
) {}
