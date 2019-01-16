package com.outfittery.appointment.controller;


import com.outfittery.appointment.dto.AppointmentRequestDTO;
import com.outfittery.appointment.dto.AppointmentsFreeSlotsDTO;
import com.outfittery.appointment.model.Appointment;
import com.outfittery.appointment.service.AppointmentService;
import com.outfittery.appointment.service.ScheduleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Appointment Management", description = "APIs pertaining to appointments in Appointment Service")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value = "View a list of available appointment slots", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")})
    @GetMapping("/appointments/slots")
    public AppointmentsFreeSlotsDTO getFreeFreeSlots() {
        return scheduleService.getFreeSlots();
    }

    @ApiOperation(value = "View a list of all booked appointments", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")})
    @GetMapping("/appointments")
    public List<Appointment> getBookedAppoints() {
        return appointmentService.findAllAppointments();
    }

    @ApiOperation(value = "Book and appointment in a selected slot")
    @PostMapping("/appointments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created appointment"),
            @ApiResponse(code = 404, message = "Appointment slot is not available")
    })
    public Appointment createAppointment(
            @ApiParam(value = "create appointment for give slot", required = true)
            @Valid @RequestBody AppointmentRequestDTO appointmentRequest) {
        return appointmentService.bookAppointment(appointmentRequest);
    }


}
