package com.apopo.tola.chuka_room_allocation.entity;

import com.apopo.tola.chuka_room_allocation.identifiable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Clubs extends Auditable {

    private String name;

    private String description;

    private String contactPerson;

    private String contactPersonPhoneNo;

    private String clubEmail;

}
