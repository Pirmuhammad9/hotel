package com.example.lesson10task1.repository;

import com.example.lesson10task1.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Page<Room> findRoomsByHotelId(Integer id, Pageable pageable);
    Room findRoomsByHotelId(Integer hotel_id);
    boolean existsRoomByHotelId(Integer hotel_id);
}
