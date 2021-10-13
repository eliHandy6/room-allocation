package com.apopo.tola.chuka_room_allocation.controller;

import com.apopo.tola.chuka_room_allocation.apiResponse.ApiResponse;
import com.apopo.tola.chuka_room_allocation.dtos.BuildingRequestDto;
import com.apopo.tola.chuka_room_allocation.dtos.BuildingResponseDto;
import com.apopo.tola.chuka_room_allocation.service.BuilingsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/apis/v1/buildings")
@AllArgsConstructor
public class BuildingController {

    private final BuilingsService buildingService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveBuildingDetails(@RequestBody BuildingRequestDto buildingRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to save Building details")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            BuildingResponseDto returnDto = buildingService.createBuilding(buildingRequestDto);
            response.setMessage("Building details saved succesfully");
            response.setData(returnDto);
            response.setSuccess(true);
            response.setStatus(HttpStatus.CREATED);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping
    public ResponseEntity<ApiResponse> fetchBuildingRecords() {

        ApiResponse response = ApiResponse.builder()
                .message("Failed to get list of Buildings")
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            List<BuildingResponseDto> returnDtos = buildingService.getAllBuilding();
            response.setMessage("Building list retrieved successfully");
            response.setData_list(Collections.singletonList(returnDtos));
            response.setSuccess(true);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.status(response.getStatus()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBuildingDetails(@PathVariable("id") Long id, BuildingRequestDto buildingRequestDto) {
        ApiResponse response = ApiResponse.builder()
                .message("Failed to update building with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            BuildingResponseDto returnDto = buildingService.updateBuilding(id, buildingRequestDto);
            response.setMessage("Building updated succesfully");
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
                .message("Failed to delete building with id" + " " + id)
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(new Date())
                .success(false)
                .build();
        try {
            Boolean status = buildingService.deleteId(id);
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
