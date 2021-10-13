package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.BuildingRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.BuildingResponseDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BuilingsService {

    BuildingResponseDto createBuilding( BuildingRequestDto buildingRequestDto);
    List<BuildingResponseDto> getAllBuilding();
    BuildingResponseDto updateBuilding(long id, BuildingRequestDto buildingRequestDto);
    boolean deleteId(long id);
}
