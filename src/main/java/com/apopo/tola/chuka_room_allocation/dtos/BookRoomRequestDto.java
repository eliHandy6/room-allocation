package com.apopo.tola.chuka_room_allocation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRoomRequestDto {

    @JsonProperty(value = "room_id")
    private String roomId;

    @JsonProperty(value = "number_of_guests")
    private int no_of_guests;

    @JsonProperty(value = "venue_date")
    private Date venueDate;

    @JsonProperty(value = "time_from")
    private String timeFrom;

    @JsonProperty(value = "time_to")
    private String timeTo;

    @JsonProperty(value = "bookie_comments")
    private String comments;

    @JsonProperty(value = "club_id")
    private  String clubId;
}
