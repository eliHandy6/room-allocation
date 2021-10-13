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
public class ClubResponseDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "contact_person")
    private String contactPerson;

    @JsonProperty(value = "contact_person_phone")
    private String contactPersonPhoneNo;

    @JsonProperty(value = "club_email")
    private String clubEmail;

    @JsonProperty(value = "created_at")
    private Instant createdAt;
}
