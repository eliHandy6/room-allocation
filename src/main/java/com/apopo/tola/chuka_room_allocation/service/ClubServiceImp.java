package com.apopo.tola.chuka_room_allocation.service;

import com.apopo.tola.chuka_room_allocation.dtos.ClubRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.ClubResponseDto;
import com.apopo.tola.chuka_room_allocation.entity.Clubs;
import com.apopo.tola.chuka_room_allocation.repository.ClubsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubServiceImp implements ClubService {

    private final ClubsRepository repository;

    @Override
    public ClubResponseDto createClub(ClubRequestDto clubRequestDto) {
        Clubs club = Clubs.builder()
                .clubEmail(clubRequestDto.getClubEmail())
                .contactPerson(clubRequestDto.getContactPerson())
                .contactPersonPhoneNo(clubRequestDto.getContactPersonPhoneNo())
                .name(clubRequestDto.getName())
                .description(clubRequestDto.getDescription())
                .build();

        Clubs returnedClub = repository.save(club);

        ClubResponseDto clubResponseDto = ClubResponseDto.builder()
                .id(returnedClub.getId())
                .clubEmail(returnedClub.getClubEmail())
                .contactPerson(returnedClub.getContactPerson())
                .name(returnedClub.getName())
                .description(returnedClub.getDescription())
                .build();
        return clubResponseDto;
    }

    @Override
    public List<ClubResponseDto> getAllClubs() {
        List<Clubs> returnedList = repository.findAll();
        return returnedList.stream().map(clubs -> {
            ClubResponseDto clubResponseDto = ClubResponseDto.builder()
                    .id(clubs.getId())
                    .clubEmail(clubs.getClubEmail())
                    .contactPerson(clubs.getContactPerson())
                    .name(clubs.getName())
                    .description(clubs.getDescription())
                    .contactPersonPhoneNo(clubs.getContactPersonPhoneNo())
                    .createdAt(clubs.getCreatedAt())
                    .build();
            return clubResponseDto;
        }).collect(Collectors.toList());
    }

    @Override
    public ClubResponseDto updateClub(long id, ClubRequestDto clubRequestDto) {
        Optional<Clubs> clubs = repository.findById(id);
        Clubs clubs1 = clubs.get();
        if (clubs.isPresent()) {
            clubs.get().setClubEmail(clubRequestDto.getClubEmail());
            clubs.get().setContactPerson(clubRequestDto.getContactPerson());
            clubs.get().setName(clubRequestDto.getName());
            clubs.get().setDescription(clubRequestDto.getDescription());
            clubs1 = repository.save(clubs.get());
        }
        ClubResponseDto clubResponseDto = ClubResponseDto.builder()
                .id(clubs1.getId())
                .clubEmail(clubs1.getClubEmail())
                .contactPerson(clubs1.getContactPerson())
                .name(clubs1.getName())
                .description(clubs1.getDescription())
                .build();

        return clubResponseDto;
    }

    @Override
    public boolean deleteId(long id) {
        boolean status = false;
        Optional<Clubs> clubs = repository.findById(id);
        if (clubs.isPresent()) {
            repository.deleteById(id);
            status = true;
        }
        return status;
    }
}
