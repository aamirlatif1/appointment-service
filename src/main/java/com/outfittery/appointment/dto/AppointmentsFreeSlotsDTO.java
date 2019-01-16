package com.outfittery.appointment.dto;

import lombok.Data;

import java.util.List;

@Data
public class AppointmentsFreeSlotsDTO {

    private List<String> availableTimeSlots;
}
