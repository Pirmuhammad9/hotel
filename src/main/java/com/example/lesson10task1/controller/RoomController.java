package com.example.lesson10task1.controller;

import com.example.lesson10task1.entity.Hotel;
import com.example.lesson10task1.entity.Room;
import com.example.lesson10task1.payload.RoomDto;
import com.example.lesson10task1.payload.RoomDto2;
import com.example.lesson10task1.repository.HotelRepository;
import com.example.lesson10task1.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping()
    public Page<Room> getAll(@RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }

    @GetMapping("/byHotelId/{id}")
    public Page<Room> getRoomByHotelId(@PathVariable Integer id, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepository.findRoomsByHotelId(id, pageable);
        return roomPage;
    }

    @GetMapping("/{id}")
    public Room getOne(@PathVariable Integer id){
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Room();
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        if (!hotelRepository.existsById(roomDto.getHotelId()) || roomRepository.existsRoomByHotelId(roomDto.getHotelId())){
            return "try again";
        }
        Room room = new Room();
        room.setFloor(roomDto.getFloor());
        room.setHotel(hotelRepository.findById(roomDto.getHotelId()).get());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        roomRepository.save(room);
        return "saved";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        boolean b = roomRepository.existsById(id);
        if (b){
            roomRepository.deleteById(id);
            return "deleted";
        }
        return "not found";
    }

    @PutMapping("/byHotelId/{id}")
    public String updateRoom(@PathVariable Integer id, @RequestBody RoomDto2 roomDto){
        if (!roomRepository.existsById(id)){
            return "not found";
        }
        Room room = roomRepository.findRoomsByHotelId(roomDto.getHotelId());
        room.setSize(roomDto.getSize());
        roomRepository.save(room);
        return "updated";
    }


}
