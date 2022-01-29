package com.appsolute.soom.scheduleapi.data.entity;

import com.appsolute.soom.scheduleapi.data.dto.TaskDto;
import com.appsolute.soom.scheduleapi.data.type.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "task")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UUID;
    @Column(name = "date_")
    private LocalDate date;
    @Enumerated(EnumType.ORDINAL) //String 보다 오히려 가독성이 좋다....
    @Column(name = "period_")
    private PeriodType period;
    @Length(max = 50)
    private String message;
    @Column(name = "schedule_uuid")
    private Long scheduleUUID;

    public TaskEntity(LocalDate date, PeriodType period, String message, Long uuid) {
        this(0L, date, period, message, uuid);
    }

    public TaskDto toDto() {
        return new TaskDto(
                UUID,
                scheduleUUID,
                date,
                period,
                message
        );
    }
}
