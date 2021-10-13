package com.apopo.tola.chuka_room_allocation.dtos;

import com.apopo.tola.chuka_room_allocation.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookedRoomResponseDto {

    private Long id;

    private RoomResponseDto room;

    private Status status;

    @JsonProperty(value = "venue_date")
    private Date venueDate;

    @JsonProperty(value = "time_from")
    private String timeFrom;

    @JsonProperty(value = "time_to")
    private String timeTo;

    @JsonProperty(value = "bookie_comments")
    private String comments;

    @JsonProperty(value = "admin_comments")
    private String status_comments;

    @JsonProperty(value = "club")
    private ClubResponseDto club;

    @JsonProperty(value = "created_at")
    private Instant createdAt;
}
