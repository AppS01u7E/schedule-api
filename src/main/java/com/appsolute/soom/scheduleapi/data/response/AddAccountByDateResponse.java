package com.appsolute.soom.scheduleapi.data.response;

import java.time.LocalDate;

public record AddAccountByDateResponse(
        String UUID,
        LocalDate date,
        String message
) {}
