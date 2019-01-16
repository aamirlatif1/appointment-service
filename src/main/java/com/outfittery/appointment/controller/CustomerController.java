package com.outfittery.appointment.controller;


import com.outfittery.appointment.model.Customer;
import com.outfittery.appointment.respository.CustomerRepository;
import com.outfittery.appointment.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Customer Management", description = "APIs pertaining to customers in Appointment Service")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "View a list of available customers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")})
    @GetMapping("/customers")
    public List<Customer> getAllEmployees() {
        return customerService.findAllCustomers();
    }

    @ApiOperation(value = "Add an customer")
    @PostMapping("/customers")
    public Customer createCustomer(
            @ApiParam(value = "Customer object store in database table", required = true)
            @Valid @RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
