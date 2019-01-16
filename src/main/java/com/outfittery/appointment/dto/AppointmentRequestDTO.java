package com.outfittery.appointment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestDTO {

    private Long customerId;

    private LocalDateTime time;

}
