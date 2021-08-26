package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.BookRoomRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.BookedRoomResponseDto;
import com.apopo.tola.chuka_room_allocation.dtos.BuildingRequestDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookRoomServiceImpl implements BookRoomService{
    @Override
    public BookedRoomResponseDto bookroom(BookRoomRequestDto bookRoomRequestDto) {
        return null;
    }

    @Override
    public List<BookedRoomResponseDto> getAllRooms(PageRequest pageRequest) {
        return null;
    }

    @Override
    public BookedRoomResponseDto updateRoomBooking(long id, BuildingRequestDto buildingRequestDto) {
        return null;
    }

    @Override
    public BookedRoomResponseDto checkIfRoomIsBooked(Date date, String time_from, String time_to) {
        return null;
    }

    @Override
    public boolean deleteId(long id) {
        return false;
    }
}
