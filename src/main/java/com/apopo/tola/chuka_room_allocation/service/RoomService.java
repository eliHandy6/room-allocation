package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.RoomResponseDto;
import com.apopo.tola.chuka_room_allocation.dtos.RoomsRequestDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RoomService {

    RoomResponseDto createRoom(RoomsRequestDto roomsRequestDto);
    List<RoomResponseDto> getAllRooms();
    RoomResponseDto updateRoom(long id, RoomsRequestDto roomsRequestDto);
    boolean deleteId(long id);
    List<RoomResponseDto> getAllRoomsInABuilding(String buildingId);
}
