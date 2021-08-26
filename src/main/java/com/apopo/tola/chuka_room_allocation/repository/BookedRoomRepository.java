package com.apopo.tola.chuka_room_allocation.repository;

import com.apopo.tola.chuka_room_allocation.entity.BookedRooms;
import com.apopo.tola.chuka_room_allocation.entity.Buildings;
import com.apopo.tola.chuka_room_allocation.entity.Rooms;
import com.apopo.tola.chuka_room_allocation.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookedRoomRepository  extends JpaRepository<BookedRooms,Long> {

    Boolean existsByRoomsAndVenueDateAndTimeFromAndTimeTo(Rooms room, Date date, String time_from, String time_to);
    BookedRooms findByRoomsAndVenueDateAndTimeFromAndTimeTo(Rooms room, Date date, String time_from, String time_to);
    List<BookedRooms> findByRooms(Rooms rooms);
    List<BookedRooms> findByRoomsBuildingsAndStatus(Buildings buildings, Status status);
    List<BookedRooms> findByRoomsAndVenueDate(Rooms rooms, Date date);

}
