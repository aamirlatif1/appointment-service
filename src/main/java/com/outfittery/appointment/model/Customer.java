package com.outfittery.appointment.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phone;


}
