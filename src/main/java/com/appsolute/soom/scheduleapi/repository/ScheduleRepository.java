package com.appsolute.soom.scheduleapi.repository;

import com.appsolute.soom.scheduleapi.data.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

/**
 * 설명을 입력해주세요!
 *
 * @author JeeInho
 * @version 0.0.1alpha-RELEASE
 * @see
 * @since 0.0.1alpha-RELEASE
 */
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    //해당 유저의 일정표를 가져온다 (만약 일정표가 없을 경우 새로 생성하여 가져온다)
    default ScheduleEntity getOrCreateScheduleByAccountUUID(String accountUUID) {
        if(existsByAccountUUID(accountUUID))
            return getByAccountUUID(accountUUID);
        else return save(new ScheduleEntity(accountUUID, new ArrayList<>()));
    }

    boolean existsByAccountUUID(String accountUUID);

    ScheduleEntity getByAccountUUID(String accountUUID);
}
