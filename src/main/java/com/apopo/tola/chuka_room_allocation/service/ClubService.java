package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.ClubRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.ClubResponseDto;
import org.springframework.data.domain.PageRequest;


import java.util.List;

public interface ClubService {

    ClubResponseDto createClub(ClubRequestDto clubRequestDto);

    List<ClubResponseDto> getAllClubs(PageRequest pageRequest);

    ClubResponseDto updateClub(long id, ClubRequestDto clubRequestDto);

    boolean deleteId(long id);

}
