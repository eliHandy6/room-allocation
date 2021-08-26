package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.*;
import com.apopo.tola.chuka_room_allocation.entity.BookedRooms;
import com.apopo.tola.chuka_room_allocation.entity.Buildings;
import com.apopo.tola.chuka_room_allocation.entity.Clubs;
import com.apopo.tola.chuka_room_allocation.entity.Rooms;
import com.apopo.tola.chuka_room_allocation.enums.Status;
import com.apopo.tola.chuka_room_allocation.repository.BookedRoomRepository;
import com.apopo.tola.chuka_room_allocation.repository.BuildingsRepository;
import com.apopo.tola.chuka_room_allocation.repository.ClubsRepository;
import com.apopo.tola.chuka_room_allocation.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookRoomServiceImpl implements BookRoomService {

    private final BookedRoomRepository repository;
    private final RoomsRepository roomsRepository;
    private final ClubsRepository clubsRepository;
    private final BuildingsRepository buildingsRepository;

    @Override
    public BookedRoomResponseDto bookroom(BookRoomRequestDto bookRoomRequestDto) {

        Rooms rooms = roomsRepository.findById(bookRoomRequestDto.getRoomId()).get();
        Clubs clubs = clubsRepository.findById(bookRoomRequestDto.getClubId()).get();
        Boolean exists = repository.existsByRoomsAndVenueDateAndTimeFromAndTimeTo(rooms, bookRoomRequestDto.getVenueDate(),
                bookRoomRequestDto.getTimeFrom(), bookRoomRequestDto.getTimeTo());

        BookedRooms masterBookings = null;

        if (bookRoomRequestDto.getNo_of_guests() == rooms.getCapacity() || exists) {
            //Throw-->
        } else {
            masterBookings = BookedRooms.builder()
                    .rooms(rooms)
                    .noOfGuests(bookRoomRequestDto.getNo_of_guests())
                    .venueDate(bookRoomRequestDto.getVenueDate())
                    .timeFrom(bookRoomRequestDto.getTimeFrom())
                    .timeTo(bookRoomRequestDto.getTimeTo())
                    .bookieComments(bookRoomRequestDto.getComments())
                    .status(Status.PENDING)
                    .clubs(clubs)
                    .build();
            masterBookings = repository.save(masterBookings);

        }
        BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                .department(masterBookings.getRooms().getBuildings().getDepartment())
                .name(masterBookings.getRooms().getBuildings().getName())
                .build();
        RoomResponseDto roomdto = RoomResponseDto.builder()
                .id(masterBookings.getRooms().getId())
                .capacity(masterBookings.getRooms().getCapacity())
                .building(buildingResponseDto)
                .build();

        ClubResponseDto clubdto = ClubResponseDto.builder()
                .id(masterBookings.getClubs().getId())
                .clubEmail(masterBookings.getClubs().getClubEmail())
                .contactPerson(masterBookings.getClubs().getContactPerson())
                .name(masterBookings.getClubs().getName())
                .description(masterBookings.getClubs().getDescription())
                .build();

        BookedRoomResponseDto responseDto = BookedRoomResponseDto.builder()
                .id(masterBookings.getId())
                .room(roomdto)
                .comments(masterBookings.getBookieComments())
                .status(masterBookings.getStatus())
                .timeFrom(masterBookings.getTimeFrom())
                .timeTo(masterBookings.getTimeTo())
                .club(clubdto)
                .build();
        return responseDto;
    }

    @Override
    public BookedRoomResponseDto updateRoomBooking(long id, BookRoomRequestDto bookRoomRequestDto) {

        BookedRooms masterBookings = repository.findById(id).get();
        masterBookings.setStatus(Status.BOOKED);
        masterBookings.setStatusComments(bookRoomRequestDto.getComments());
        masterBookings = repository.save(masterBookings);

        BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                .department(masterBookings.getRooms().getBuildings().getDepartment())
                .name(masterBookings.getRooms().getBuildings().getName())
                .build();
        RoomResponseDto roomdto = RoomResponseDto.builder()
                .id(masterBookings.getRooms().getId())
                .capacity(masterBookings.getRooms().getCapacity())
                .building(buildingResponseDto)
                .build();

        ClubResponseDto clubdto = ClubResponseDto.builder()
                .id(masterBookings.getClubs().getId())
                .clubEmail(masterBookings.getClubs().getClubEmail())
                .contactPerson(masterBookings.getClubs().getContactPerson())
                .name(masterBookings.getClubs().getName())
                .description(masterBookings.getClubs().getDescription())
                .build();

        BookedRoomResponseDto responseDto = BookedRoomResponseDto.builder()
                .id(masterBookings.getId())
                .room(roomdto)
                .comments(masterBookings.getBookieComments())
                .status(masterBookings.getStatus())
                .timeFrom(masterBookings.getTimeFrom())
                .timeTo(masterBookings.getTimeTo())
                .club(clubdto)
                .build();
        return responseDto;
       
    }

    @Override
    public boolean deleteId(long id) {
        boolean status = false;
        Optional<BookedRooms> bookedRooms = repository.findById(id);
        if (bookedRooms.isPresent()) {
            repository.deleteById(id);
            status = true;
        }
        return status;
    }

    @Override
    public List<BookedRoomResponseDto> getRoomDailyBookingData(Long roomId, Date date) {
        Optional<Rooms> rooms = roomsRepository.findById(roomId);
        List<BookedRooms> roomDayBookingData = repository.findByRoomsAndVenueDate(rooms.get(), date);

        return roomDayBookingData.stream().map(masterBookings -> {
            BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                    .department(masterBookings.getRooms().getBuildings().getDepartment())
                    .name(masterBookings.getRooms().getBuildings().getName())
                    .build();
            RoomResponseDto roomdto = RoomResponseDto.builder()
                    .id(masterBookings.getRooms().getId())
                    .capacity(masterBookings.getRooms().getCapacity())
                    .building(buildingResponseDto)
                    .build();

            ClubResponseDto clubdto = ClubResponseDto.builder()
                    .id(masterBookings.getClubs().getId())
                    .clubEmail(masterBookings.getClubs().getClubEmail())
                    .contactPerson(masterBookings.getClubs().getContactPerson())
                    .name(masterBookings.getClubs().getName())
                    .description(masterBookings.getClubs().getDescription())
                    .build();

            BookedRoomResponseDto responseDto = BookedRoomResponseDto.builder()
                    .id(masterBookings.getId())
                    .room(roomdto)
                    .comments(masterBookings.getBookieComments())
                    .status(masterBookings.getStatus())
                    .timeFrom(masterBookings.getTimeFrom())
                    .timeTo(masterBookings.getTimeTo())
                    .club(clubdto)
                    .build();

            return responseDto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<BookedRoomResponseDto> getBookedRoomsInABuilding(Long buildingId) {
        Optional<Buildings> buildings = buildingsRepository.findById(buildingId);
        List<BookedRooms> bookedRooms = repository.findByRoomsBuildingsAndStatus(buildings.get(), Status.BOOKED);
        return bookedRooms.stream().map(masterBookings -> {
            BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                    .department(masterBookings.getRooms().getBuildings().getDepartment())
                    .name(masterBookings.getRooms().getBuildings().getName())
                    .build();
            RoomResponseDto roomdto = RoomResponseDto.builder()
                    .id(masterBookings.getRooms().getId())
                    .capacity(masterBookings.getRooms().getCapacity())
                    .building(buildingResponseDto)
                    .build();

            ClubResponseDto clubdto = ClubResponseDto.builder()
                    .id(masterBookings.getClubs().getId())
                    .clubEmail(masterBookings.getClubs().getClubEmail())
                    .contactPerson(masterBookings.getClubs().getContactPerson())
                    .name(masterBookings.getClubs().getName())
                    .description(masterBookings.getClubs().getDescription())
                    .build();

            BookedRoomResponseDto responseDto = BookedRoomResponseDto.builder()
                    .id(masterBookings.getId())
                    .room(roomdto)
                    .comments(masterBookings.getBookieComments())
                    .status(masterBookings.getStatus())
                    .timeFrom(masterBookings.getTimeFrom())
                    .timeTo(masterBookings.getTimeTo())
                    .club(clubdto)
                    .build();

            return responseDto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<BookedRoomResponseDto> getPendingRoomsInABuilding(Long buildingId) {
        Optional<Buildings> buildings = buildingsRepository.findById(buildingId);
        List<BookedRooms> pendingRooms = repository.findByRoomsBuildingsAndStatus(buildings.get(), Status.PENDING);

        return pendingRooms.stream().map(masterBookings -> {
            BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                    .department(masterBookings.getRooms().getBuildings().getDepartment())
                    .name(masterBookings.getRooms().getBuildings().getName())
                    .build();
            RoomResponseDto roomdto = RoomResponseDto.builder()
                    .id(masterBookings.getRooms().getId())
                    .capacity(masterBookings.getRooms().getCapacity())
                    .building(buildingResponseDto)
                    .build();

            ClubResponseDto clubdto = ClubResponseDto.builder()
                    .id(masterBookings.getClubs().getId())
                    .clubEmail(masterBookings.getClubs().getClubEmail())
                    .contactPerson(masterBookings.getClubs().getContactPerson())
                    .name(masterBookings.getClubs().getName())
                    .description(masterBookings.getClubs().getDescription())
                    .build();

            BookedRoomResponseDto responseDto = BookedRoomResponseDto.builder()
                    .id(masterBookings.getId())
                    .room(roomdto)
                    .comments(masterBookings.getBookieComments())
                    .status(masterBookings.getStatus())
                    .timeFrom(masterBookings.getTimeFrom())
                    .timeTo(masterBookings.getTimeTo())
                    .club(clubdto)
                    .build();

            return responseDto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<BookedRoomResponseDto> getRoomBookingHistory(Long roomId) {
        Optional<Rooms> rooms = roomsRepository.findById(roomId);
        List<BookedRooms> roomHistory = repository.findByRooms(rooms.get());
        return roomHistory.stream().map(masterBookings -> {
            BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                    .department(masterBookings.getRooms().getBuildings().getDepartment())
                    .name(masterBookings.getRooms().getBuildings().getName())
                    .build();
            RoomResponseDto roomdto = RoomResponseDto.builder()
                    .id(masterBookings.getRooms().getId())
                    .capacity(masterBookings.getRooms().getCapacity())
                    .building(buildingResponseDto)
                    .build();

            ClubResponseDto clubdto = ClubResponseDto.builder()
                    .id(masterBookings.getClubs().getId())
                    .clubEmail(masterBookings.getClubs().getClubEmail())
                    .contactPerson(masterBookings.getClubs().getContactPerson())
                    .name(masterBookings.getClubs().getName())
                    .description(masterBookings.getClubs().getDescription())
                    .build();

            BookedRoomResponseDto responseDto = BookedRoomResponseDto.builder()
                    .id(masterBookings.getId())
                    .room(roomdto)
                    .comments(masterBookings.getBookieComments())
                    .status(masterBookings.getStatus())
                    .timeFrom(masterBookings.getTimeFrom())
                    .timeTo(masterBookings.getTimeTo())
                    .club(clubdto)
                    .build();

            return responseDto;
        }).collect(Collectors.toList());

    }

    @Override
    public BookedRoomResponseDto checkRoomStatus(Long roomId, Date date, String time_from, String time_to) {
        Optional<Rooms> rooms = roomsRepository.findById(roomId);
        BookedRooms bookedRoom = repository.findByRoomsAndVenueDateAndTimeFromAndTimeTo(rooms.get(), date, time_from, time_to);

        BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                .department(bookedRoom.getRooms().getBuildings().getDepartment())
                .name(bookedRoom.getRooms().getBuildings().getName())
                .build();
        RoomResponseDto roomdto = RoomResponseDto.builder()
                .id(bookedRoom.getRooms().getId())
                .capacity(bookedRoom.getRooms().getCapacity())
                .building(buildingResponseDto)
                .build();

        ClubResponseDto clubdto = ClubResponseDto.builder()
                .id(bookedRoom.getClubs().getId())
                .clubEmail(bookedRoom.getClubs().getClubEmail())
                .contactPerson(bookedRoom.getClubs().getContactPerson())
                .name(bookedRoom.getClubs().getName())
                .description(bookedRoom.getClubs().getDescription())
                .build();

        BookedRoomResponseDto responseDto = BookedRoomResponseDto.builder()
                .id(bookedRoom.getId())
                .room(roomdto)
                .comments(bookedRoom.getBookieComments())
                .status(bookedRoom.getStatus())
                .timeFrom(bookedRoom.getTimeFrom())
                .timeTo(bookedRoom.getTimeTo())
                .status_comments(bookedRoom.getStatusComments())
                .club(clubdto)
                .build();

        return responseDto;
    }
}
