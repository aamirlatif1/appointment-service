package com.outfittery.appointment.service;

import com.outfittery.appointment.dto.AppointmentRequestDTO;
import com.outfittery.appointment.exception.ResourceNotFoundException;
import com.outfittery.appointment.model.Appointment;
import com.outfittery.appointment.model.Schedule;
import com.outfittery.appointment.respository.AppointmentRepository;
import com.outfittery.appointment.respository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final ScheduleRepository scheduleRepository;

    public Appointment bookAppointment(AppointmentRequestDTO appointmentRequest) {
        Schedule schedule = scheduleRepository.findFirstByFrom(appointmentRequest.getTime())
                .orElseThrow(() -> new ResourceNotFoundException("slot is not available"));
        Appointment appointment = Appointment.builder()
                .customerId(appointmentRequest.getCustomerId())
                .scheduleId(schedule.getId())
                .stylistId(schedule.getStylistId())
                .build();

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }


}
