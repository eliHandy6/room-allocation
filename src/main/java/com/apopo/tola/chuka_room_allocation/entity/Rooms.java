package com.apopo.tola.chuka_room_allocation.entity;

import com.apopo.tola.chuka_room_allocation.identifiable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Rooms extends Auditable {

    private String name;

    @ManyToOne
    @JoinColumn
    private Buildings buildings;

    private Long capacity;


}
