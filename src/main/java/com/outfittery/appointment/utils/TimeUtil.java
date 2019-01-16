package com.outfittery.appointment.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class TimeUtil {


    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Current Date="+today);

        //Current Time
        LocalTime time = LocalTime.now();
        System.out.println("Current Time="+time);


        LocalTime specificTime = LocalTime.of(12,20);
        System.out.println("Specific Time of Day="+specificTime);


        //Creating LocalDate by providing input arguments
        LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
        System.out.println("Specific Date="+firstDay_2014);
    }
}
