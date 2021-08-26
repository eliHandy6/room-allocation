package com.apopo.tola.chuka_room_allocation.controller;

import com.apopo.tola.chuka_room_allocation.apiResponse.ApiResponse;
import com.apopo.tola.chuka_room_allocation.dtos.*;
import com.apopo.tola.chuka_room_allocation.service.BookRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/apis/v1/bookings")
@AllArgsConstructor
public class BookingsController {

    private final BookRoomService bookingService;

    @PostMapping
    public ResponseEntity<ApiResponse> book(@RequestBody BookRoomRequestDto bookRoomRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to book a room")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            BookedRoomResponseDto returnDto = bookingService.bookroom(bookRoomRequestDto);
            response.setMessage("Room booking succesfully placed");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.CREATED);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<ApiResponse> confirmBooking(@PathVariable("id") Long id, BookRoomRequestDto bookRoomRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to confirm room booking with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            BookedRoomResponseDto returnDto = bookingService.updateRoomBooking(id, bookRoomRequestDto);
            response.setMessage("Room booking confirmed succesfully");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBookings(@PathVariable("id") Long id) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to delete club with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            Boolean status = bookingService.deleteId(id);
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

    @GetMapping("/rooms/{id}")
    public ResponseEntity<ApiResponse> roomBookingData(@PathVariable Long roomId, @RequestParam Date date) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of rooms data")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            List<BookedRoomResponseDto> returnDtos = bookingService.getRoomDailyBookingData(roomId, date);
            response.setMessage("Room booking data retrieved successfully");
            response.setData_list(Collections.singletonList(returnDtos));
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/buildings/{id}/booked")
    public ResponseEntity<ApiResponse> getBuildingBookedRooms(@PathVariable Long buildingId, @RequestParam Date date) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of rooms data")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            List<BookedRoomResponseDto> returnDtos = bookingService.getBookedRoomsInABuilding(buildingId, date);
            response.setMessage("Room booking data retrieved successfully");
            response.setData_list(Collections.singletonList(returnDtos));
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/buildings/{id}/pending")
    public ResponseEntity<ApiResponse> getBuildingPendingRooms(@PathVariable Long buildingId, @RequestParam Date date) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of rooms data")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            List<BookedRoomResponseDto> returnDtos = bookingService.getPendingRoomsInABuilding(buildingId, date);
            response.setMessage("Room pending booking data retrieved successfully");
            response.setData_list(Collections.singletonList(returnDtos));
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse> cancelBooking(@PathVariable("id") Long id, BookRoomRequestDto bookRoomRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to cancel room booking with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            BookedRoomResponseDto returnDto = bookingService.cancelBooking(id, bookRoomRequestDto);
            response.setMessage("Room booking cancelled succesfully");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/rooms/{id}/history")
    public ResponseEntity<ApiResponse> roomBookingDataHistory(@PathVariable Long roomId) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of rooms data")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            List<BookedRoomResponseDto> returnDtos = bookingService.getRoomBookingHistory(roomId);
            response.setMessage("Room booking data retrieved successfully");
            response.setData_list(Collections.singletonList(returnDtos));
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/rooms/{id}/status")
    public ResponseEntity<ApiResponse> checkIfRoomIsAvailable(@PathVariable Long roomId,
                                                              @RequestParam Date date,
                                                              @RequestParam String time_from,
                                                              @RequestParam String time_to) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of rooms data")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            BookedRoomResponseDto returnDto = bookingService.checkRoomStatus(roomId, date, time_from, time_to);
            response.setMessage("Room booking data retrieved successfully");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}
