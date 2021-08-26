package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.BookRoomRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.BookedRoomResponseDto;
import com.apopo.tola.chuka_room_allocation.dtos.BuildingRequestDto;


import java.util.Date;
import java.util.List;

public interface BookRoomService {

    BookedRoomResponseDto bookroom(BookRoomRequestDto bookRoomRequestDto);
    BookedRoomResponseDto updateRoomBooking(long id,  BookRoomRequestDto bookRoomRequestDto );
    boolean deleteId(long id);
    List<BookedRoomResponseDto> getRoomDailyBookingData(Long roomId,Date date);
    List<BookedRoomResponseDto> getBookedRoomsInABuilding(Long buildingId);
    List<BookedRoomResponseDto> getPendingRoomsInABuilding(Long buildingId);
    List<BookedRoomResponseDto> getRoomBookingHistory(Long roomId);
    BookedRoomResponseDto checkRoomStatus(Long  roomId,Date date,String time_from,String time_to);

}
