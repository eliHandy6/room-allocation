package com.apopo.tola.chuka_room_allocation.repository;

import com.apopo.tola.chuka_room_allocation.entity.Clubs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubsRepository extends JpaRepository<Clubs,Long> {
}
