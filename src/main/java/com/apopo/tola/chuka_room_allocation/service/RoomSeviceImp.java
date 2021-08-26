package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.BuildingResponseDto;
import com.apopo.tola.chuka_room_allocation.dtos.RoomResponseDto;
import com.apopo.tola.chuka_room_allocation.dtos.RoomsRequestDto;
import com.apopo.tola.chuka_room_allocation.entity.Buildings;
import com.apopo.tola.chuka_room_allocation.entity.Rooms;
import com.apopo.tola.chuka_room_allocation.repository.BuildingsRepository;
import com.apopo.tola.chuka_room_allocation.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomSeviceImp implements RoomService {

    private final BuildingsRepository repository;
    private final RoomsRepository roomsRepository;

    @Override
    public RoomResponseDto createRoom(RoomsRequestDto roomsRequestDto) {
        Optional<Buildings> building = repository.findById(roomsRequestDto.getBuildingsId());
        Rooms returnedRoom = Rooms.builder().build();
        if (building.isPresent()) {
            Rooms rooms = Rooms.builder()
                    .buildings(building.get())
                    .capacity(roomsRequestDto.getCapacity())
                    .name(roomsRequestDto.getName())
                    .build();
            returnedRoom = roomsRepository.save(rooms);
        }

        BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                .department(returnedRoom.getBuildings().getDepartment())
                .name(returnedRoom.getBuildings().getName())
                .build();
        RoomResponseDto responseDto = RoomResponseDto.builder()
                .id(returnedRoom.getId())
                .capacity(returnedRoom.getCapacity())
                .building(buildingResponseDto)
                .build();
        return responseDto;

    }

    @Override
    public List<RoomResponseDto> getAllRooms(PageRequest pageRequest) {
        List<Rooms> roomList = roomsRepository.findAll(pageRequest).toList();
        return roomList.stream().map(rooms -> {
            BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                    .department(rooms.getBuildings().getDepartment())
                    .name(rooms.getBuildings().getName())
                    .build();
            RoomResponseDto responseDto = RoomResponseDto.builder()
                    .id(rooms.getId())
                    .capacity(rooms.getCapacity())
                    .building(buildingResponseDto)
                    .build();
            return responseDto;
        }).collect(Collectors.toList());

    }

    @Override
    public RoomResponseDto updateRoom(long id, RoomsRequestDto roomsRequestDto) {
        Optional<Rooms> rooms = roomsRepository.findById(id);
        Rooms returnedRoom;
        RoomResponseDto responseDto = RoomResponseDto.builder().build();
        if (rooms.isPresent()) {
            rooms.get().setCapacity(roomsRequestDto.getCapacity());
            rooms.get().setName(roomsRequestDto.getName());
            returnedRoom = roomsRepository.save(rooms.get());
            responseDto = RoomResponseDto.builder()
                    .id(returnedRoom.getId())
                    .capacity(returnedRoom.getCapacity())
                    .name(returnedRoom.getName())
                    .build();
        }
        return responseDto;

    }

    @Override
    public boolean deleteId(long id) {
        boolean status = false;
        Optional<Rooms> rooms = roomsRepository.findById(id);
        if (rooms.isPresent()) {
            repository.deleteById(id);
            status = true;
        }
        return status;
    }
}
