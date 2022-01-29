package com.appsolute.soom.scheduleapi.data.response;

import com.appsolute.soom.scheduleapi.data.dto.TaskDto;

import java.util.List;

public record GetWeeklyScheduleResponse(
        Integer year,
        Integer month,
        Integer week,
        Integer pageSize,
        Integer pageNum,
        List<TaskDto> tasks
) {}
