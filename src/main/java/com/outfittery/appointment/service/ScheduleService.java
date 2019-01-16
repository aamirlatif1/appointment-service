package com.outfittery.appointment.service;

import com.outfittery.appointment.dto.AppointmentsFreeSlotsDTO;
import com.outfittery.appointment.model.Schedule;
import com.outfittery.appointment.model.Stylist;
import com.outfittery.appointment.respository.ScheduleRepository;
import com.outfittery.appointment.respository.StylistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ScheduleService {

    @Autowired
    private  ScheduleRepository scheduleRepository;

    @Autowired
    private  StylistRepository stylistRepository;

    @Value("${schedule.day.startTime}")
    private Integer startTime;

    @Value("${schedule.day.endTime}")
    private Integer endTime;

    @Value("${schedule.slot.time}")
    private Integer slotTime;

    @Scheduled(fixedRate = 86400_000)
    public void createSchedules() {
        List<Stylist> allStylists = stylistRepository.findAll();
        Optional<LocalDateTime> latestSchedule = scheduleRepository.findLatestSchedule();
        LocalDateTime lastTime = latestSchedule.map(s -> s.plusDays(1)).orElse(LocalDateTime.now());
        LocalDateTime startSchedule  = LocalDateTime.of(lastTime.getYear(), lastTime.getMonth(), lastTime.getDayOfMonth(), 0, 0, 0);
        List<Schedule> schedules = createScheduleSlots(allStylists, startSchedule);
        scheduleRepository.saveAll(schedules);
    }

    public AppointmentsFreeSlotsDTO getFreeSlots() {
        AppointmentsFreeSlotsDTO freeSlots = new AppointmentsFreeSlotsDTO();
        List<Timestamp> scheduleSlots = scheduleRepository.getFreeScheduleSlots();
        freeSlots.setAvailableTimeSlots(scheduleSlots.stream().map(s -> s.toString()).collect(Collectors.toList()));
        return freeSlots;
    }

    private List<Schedule> createScheduleSlots(List<Stylist> allStylists, LocalDateTime startSchedule) {
        List<Schedule> schedules = new ArrayList<>();
        allStylists.forEach(stylist -> {
            for (int hour = startTime; hour < endTime; hour++) {
                for (int min = 0; min < 60; min += slotTime) {
                    Schedule schedule = Schedule.builder()
                            .from(startSchedule.plusHours(hour).plusMinutes(min))
                            .stylistId(stylist.getId())
                            .build();
                    log.info("schedules added", schedules.size());
                    schedules.add(schedule);
                }
            }

        });
        return schedules;
    }

}
