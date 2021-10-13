package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.BuildingRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.BuildingResponseDto;
import com.apopo.tola.chuka_room_allocation.entity.Buildings;
import com.apopo.tola.chuka_room_allocation.repository.BuildingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuildingServiceImp implements BuilingsService {

    private final BuildingsRepository repository;

    @Override
    public BuildingResponseDto createBuilding(BuildingRequestDto buildingRequestDto) {
        Buildings buildings = Buildings.builder()
                .department(buildingRequestDto.getDepartment())
                .name(buildingRequestDto.getName())
                .build();
        Buildings returnedBuilding = repository.save(buildings);

        BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                .department(returnedBuilding.getDepartment())
                .name(returnedBuilding.getName())
                .build();
        return buildingResponseDto;
    }

    @Override
    public List<BuildingResponseDto> getAllBuilding() {
        List<Buildings> returnedList = repository.findAll();
        return returnedList.stream().map(buildings -> {
            BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                    .department(buildings.getDepartment())
                    .name(buildings.getName())
                    .id(buildings.getId())
                    .created_at(buildings.getCreatedAt())
                    .build();
            return buildingResponseDto;

        }).collect(Collectors.toList());

    }

    @Override
    public BuildingResponseDto updateBuilding(long id, BuildingRequestDto buildingRequestDto) {
        Optional<Buildings> buildings = repository.findById(id);
        Buildings buildings1 = buildings.get();
        if (buildings.isPresent()) {
            buildings.get().setDepartment(buildingRequestDto.getDepartment());
            buildings.get().setName(buildingRequestDto.getName());
            buildings1 = repository.save(buildings.get());

        }
        BuildingResponseDto buildingResponseDto = BuildingResponseDto.builder()
                .department(buildings1.getDepartment())
                .name(buildings1.getName())
                .build();
        return buildingResponseDto;

    }

    @Override
    public boolean deleteId(long id) {
        boolean status = false;
        Optional<Buildings> buildings = repository.findById(id);
        if (buildings.isPresent()) {
            repository.deleteById(id);
            status = true;
        }
        return status;
    }
}
