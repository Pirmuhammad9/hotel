package com.example.lesson10task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto {
    private Integer hotelId;
    private Integer number;
    private Integer floor;
}
