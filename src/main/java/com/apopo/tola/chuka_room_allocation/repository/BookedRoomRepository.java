package com.apopo.tola.chuka_room_allocation.repository;

import com.apopo.tola.chuka_room_allocation.entity.BookedRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookedRoomRepository  extends JpaRepository<BookedRooms,Long> {

    BookedRooms findByVenueDateAndTimeFromAndTimeTo(Date date, String time_from, String time_to);
}
