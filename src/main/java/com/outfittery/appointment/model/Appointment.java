package com.outfittery.appointment.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "appointment")
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long stylistId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "schedule_id")
    private Long scheduleId;

//    @Basic
//    @Temporal(TemporalType.DATE)
//    private Date startTime;
//
//    @Basic
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date endTime;

}
