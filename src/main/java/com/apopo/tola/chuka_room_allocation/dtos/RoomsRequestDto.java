package com.apopo.tola.chuka_room_allocation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomsRequestDto {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "building_id")
    private Long buildingsId;

    @JsonProperty(value = "capacity")
    private Long capacity;
}
