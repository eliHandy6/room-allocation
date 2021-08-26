package com.apopo.tola.chuka_room_allocation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRoomRequestDto {

    @JsonProperty(value = "room_id")
    private long roomId;

    @JsonProperty(value = "venue_date")
    private LocalDate venueDate;

    @JsonProperty(value = "time_from")
    private String timeFrom;

    @JsonProperty(value = "time_to")
    private String timeTo;

    @JsonProperty(value = "bookie_comments")
    private String comments;

}
