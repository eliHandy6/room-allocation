package com.apopo.tola.chuka_room_allocation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "building")
    private BuildingResponseDto building;

    @JsonProperty(value = "capacity")
    private Long capacity;

    @JsonProperty(value = "room_created_at")
    private Instant roomCreatedAt;
}
