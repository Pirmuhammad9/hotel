package com.example.lesson10task1.controller;

import com.example.lesson10task1.entity.Hotel;
import com.example.lesson10task1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping()
    public Page<Hotel> getAll(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        return hotelPage;
    }

    @GetMapping("/{id}")
    public Hotel getOne(@PathVariable Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Hotel();
    }

    @PostMapping
    public void addHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = new Hotel();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        boolean b = hotelRepository.existsById(id);
        if (b) {
            hotelRepository.deleteById(id);
            return "deleted";
        }
        return "not found";
    }

    @PutMapping("/{id}")
    public String updateHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        if (!hotelRepository.existsById(id)) {
            return "not found";
        }
        Hotel hotel1 = hotelRepository.findById(id).get();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return "updated";
    }

}
