package com.apopo.tola.chuka_room_allocation.entity;

import com.apopo.tola.chuka_room_allocation.enums.Status;
import com.apopo.tola.chuka_room_allocation.identifiable.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class BookedRooms extends Auditable {

    @ManyToOne
    @JoinColumn
    private Rooms rooms;

    private Status status;

    private Date venueDate;

    private String timeFrom;

    private String timeTo;

    private String bookieComments;

    private String statusComments;


}
