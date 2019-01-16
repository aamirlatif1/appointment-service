package com.outfittery.appointment.service;

import com.outfittery.appointment.model.Stylist;
import com.outfittery.appointment.respository.StylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StylistService {

    private final StylistRepository stylistRepository;

    public Stylist saveStylist(Stylist stylist) {
        return stylistRepository.save(stylist);
    }

    public List<Stylist> findAllStylists() {
        return stylistRepository.findAll();
    }
}
