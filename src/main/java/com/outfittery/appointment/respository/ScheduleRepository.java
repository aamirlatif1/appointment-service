package com.outfittery.appointment.respository;

import com.outfittery.appointment.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    @Query(value = "SELECT distinct sc.from_time FROM schedule sc " +
            "LEFT JOIN appointment ap ON sc.id = ap.schedule_id " +
            "WHERE ap.id is null  ORDER BY sc.from_time",
    nativeQuery = true)
    List<Timestamp> getFreeScheduleSlots();


    @Query("SELECT max(s.from) FROM Schedule s")
    Optional<LocalDateTime> findLatestSchedule();


    @Query(value = "SELECT distinct sc.* FROM schedule sc " +
            "LEFT JOIN appointment ap ON sc.id = ap.schedule_id " +
            "WHERE sc.from_time = ? and ap.id is null limit 1",
            nativeQuery = true)
    Optional<Schedule> findFirstByFrom(LocalDateTime time);

}
