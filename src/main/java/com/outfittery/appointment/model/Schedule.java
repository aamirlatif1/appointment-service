package com.outfittery.appointment.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long stylistId;

    @Column(name = "from_time")
    private LocalDateTime from;

    @Column(name = "to_time")
    private LocalDateTime to;
}
