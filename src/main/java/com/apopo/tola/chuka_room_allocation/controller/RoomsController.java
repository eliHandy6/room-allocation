package com.apopo.tola.chuka_room_allocation.controller;

import com.apopo.tola.chuka_room_allocation.apiResponse.ApiResponse;
import com.apopo.tola.chuka_room_allocation.dtos.RoomResponseDto;
import com.apopo.tola.chuka_room_allocation.dtos.RoomsRequestDto;
import com.apopo.tola.chuka_room_allocation.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/rooms")
@AllArgsConstructor
public class RoomsController {

    private final RoomService roomService;


    @PostMapping
    public ResponseEntity<ApiResponse> saveRoomdetails(@RequestBody RoomsRequestDto roomsRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to save room details")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            RoomResponseDto returnDto = roomService.createRoom(roomsRequestDto);
            response.setMessage("Room details saved succesfully");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.CREATED);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping
    public ResponseEntity<ApiResponse> fetchAllRooms(@RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of rooms")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            List<RoomResponseDto> returnDtos = roomService.getAllRooms(pageRequest);
            response.setMessage("Room list retrieved successfully");
            response.setData_list(Collections.singletonList(returnDtos));
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateRoom(@PathVariable("id") Long id, RoomsRequestDto roomsRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to update room with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            RoomResponseDto returnDto = roomService.updateRoom(id, roomsRequestDto);
            response.setMessage("Room updated succesfully");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBuilding(@PathVariable("id") Long id) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to delete room with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            Boolean status = roomService.deleteId(id);
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

    @GetMapping("/buildings/{id}")
    public ResponseEntity<ApiResponse> fetchAllRoomsInABuilding(@PathVariable Long id) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of rooms")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            List<RoomResponseDto> returnDtos = roomService.getAllRoomsInABuilding(id);
            response.setMessage(" Building Rooms list retrieved successfully");
            response.setData_list(Collections.singletonList(returnDtos));
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
