package com.apopo.tola.chuka_room_allocation.entity;

import com.apopo.tola.chuka_room_allocation.identifiable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Buildings extends Auditable {

    private String name;

    private String department;


}
