package com.appsolute.soom.scheduleapi.service;

import com.appsolute.soom.scheduleapi.data.dto.TaskDto;
import com.appsolute.soom.scheduleapi.data.entity.TaskEntity;
import com.appsolute.soom.scheduleapi.data.type.PeriodType;
import com.appsolute.soom.scheduleapi.repository.ScheduleRepository;
import com.appsolute.soom.scheduleapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.appsolute.soom.scheduleapi.util.DateUtil.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final TaskRepository taskRepository;

    @Override @Transactional
    public TaskDto addTask(String accountUUID, LocalDate date, String message, Integer period) {
        return taskRepository.save(new TaskEntity(
                date, PeriodType.of(period), message,
                scheduleRepository.getOrCreateScheduleByAccountUUID(accountUUID).getUUID())
        ).toDto();
    }

    @Override @Transactional
    public List<TaskDto> getMonthlySchedule(String accountUUID, Integer year, Integer month, Pageable pageable) {
        return getScheduleByAccountUUIDAndDateBetween(
                accountUUID,
                getFirstDayOfMonth(year, month),
                getLastDayOfMonth(year, month),
                pageable
        );
    }

    @Override @Transactional
    public List<TaskDto> getWeeklySchedule(String accountUUID, Integer year, Integer month, Integer week, Pageable pageable) {
        return getScheduleByAccountUUIDAndDateBetween(
                accountUUID,
                getFirstDayOfWeek(year, month, week),
                getLastDayOfWeek(year, month, week),
                pageable
        );
    }

    @Override @Transactional
    public List<TaskDto> getDailySchedule(String accountUUID, Integer year, Integer month, Integer day, Pageable pageable) {
        return toTaskDtoList(
                taskRepository.getTaskEntitiesByScheduleUUIDAndDate(
                        scheduleRepository.getOrCreateScheduleByAccountUUID(accountUUID).getUUID(),
                        LocalDate.of(year, month, day), pageable
        ));
    }

    @Override
    public void removeTask(Long uuid) {
        taskRepository.removeByUUID(uuid);
    }

    @Override @Transactional
    public TaskDto updateTask(Long uuid, String message, LocalDate date, Integer period) {
        return updateTask(taskRepository.getByUUID(uuid), message, date, period).toDto();
    }

    private TaskEntity updateTask(TaskEntity entity, String message, LocalDate date, Integer period) {
        return taskRepository.save(new TaskEntity(
                entity.getUUID(),
                date, PeriodType.of(period),
                message,
                entity.getScheduleUUID()
        ));
    }

    //TODO taskRepository 나 scheduleRepository 에서 다른 하나의 Repository 를 DI 받고 해당 Repository 로 메서드 이전하는 것에 대해 고민해보기
    private List<TaskDto> getScheduleByAccountUUIDAndDateBetween(String accountUUID, LocalDate rangeStart, LocalDate rangeEnd, Pageable pageable) {
        return toTaskDtoList(
                taskRepository.getTaskEntitiesByScheduleUUIDAndDateBetween(
                        scheduleRepository.getOrCreateScheduleByAccountUUID(accountUUID).getUUID(),
                        rangeStart, rangeEnd, pageable
        ));
    }

    private List<TaskDto> toTaskDtoList(Iterable<TaskEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(TaskEntity::toDto).toList();
    }
}
