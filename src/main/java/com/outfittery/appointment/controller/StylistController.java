package com.outfittery.appointment.controller;

import com.outfittery.appointment.model.Stylist;
import com.outfittery.appointment.respository.StylistRepository;
import com.outfittery.appointment.service.StylistService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Stylist Management", description = "APIs pertaining to stylists in Appointment Service")
public class StylistController {

    @Autowired
    private StylistService stylistService;


    @ApiOperation(value = "View a list of available stylists", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/stylists")
    public List<Stylist> getAllStylists() {
        return stylistService.findAllStylists();
    }

    @ApiOperation(value = "Add an stylist")
    @PostMapping("/stylists")
    public Stylist createStylist(
            @ApiParam(value = "Stylist object store in database table", required = true)
            @Valid @RequestBody Stylist stylist) {
        return stylistService.saveStylist(stylist);
    }
}
