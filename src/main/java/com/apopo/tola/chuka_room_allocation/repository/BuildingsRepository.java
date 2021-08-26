package com.apopo.tola.chuka_room_allocation.repository;

import com.apopo.tola.chuka_room_allocation.entity.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingsRepository extends JpaRepository<Buildings,Long> {
}
