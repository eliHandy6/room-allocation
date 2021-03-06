package com.apopo.tola.chuka_room_allocation.repository;

import com.apopo.tola.chuka_room_allocation.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms,Long> {

    List<Rooms> findByBuildingsId(Long id);
}
