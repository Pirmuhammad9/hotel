package com.example.lesson10task1.repository;

import com.example.lesson10task1.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
