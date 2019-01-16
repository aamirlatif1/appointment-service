package com.outfittery.appointment.respository;

import com.outfittery.appointment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
