package com.apopo.tola.chuka_room_allocation.controller;

import com.apopo.tola.chuka_room_allocation.apiResponse.ApiResponse;
import com.apopo.tola.chuka_room_allocation.dtos.ClubRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.ClubResponseDto;
import com.apopo.tola.chuka_room_allocation.service.ClubService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/clubs")
@AllArgsConstructor
public class ClubsController {

    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveClub(@RequestBody ClubRequestDto clubRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to save club details")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            ClubResponseDto returnDto = clubService.createClub(clubRequestDto);
            response.setMessage("Club details saved succesfully");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.CREATED);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping
    public ResponseEntity<ApiResponse> fetchAllClubs(@RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of clubs")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            List<ClubResponseDto> returnDtos = clubService.getAllClubs(pageRequest);
            response.setMessage("Clubs list retrieved successfully");
            response.setData_list(Collections.singletonList(returnDtos));
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateClub(@PathVariable("id") Long id, ClubRequestDto clubRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to update club with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            ClubResponseDto returnDto = clubService.updateClub(id, clubRequestDto);
            response.setMessage("club updated succesfully");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteClub(@PathVariable("id") Long id) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to delete club with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            Boolean status = clubService.deleteId(id);
            String message = status ? "Deleted" : "Not Deleted";
            response.setMessage(message);
            response.setData(status);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }


}
