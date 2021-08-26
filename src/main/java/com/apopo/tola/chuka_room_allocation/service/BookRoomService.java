package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.BookRoomRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.BookedRoomResponseDto;
import com.apopo.tola.chuka_room_allocation.dtos.BuildingRequestDto;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public interface BookRoomService {

    BookedRoomResponseDto bookroom(BookRoomRequestDto bookRoomRequestDto);
    List<BookedRoomResponseDto> getAllRooms(PageRequest pageRequest);
    BookedRoomResponseDto updateRoomBooking(long id, BuildingRequestDto buildingRequestDto);
    BookedRoomResponseDto checkIfRoomIsBooked(Date date,String time_from,String time_to);
    boolean deleteId(long id);
}
