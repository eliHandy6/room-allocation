package com.apopo.tola.chuka_room_allocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ChukaRoomAllocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChukaRoomAllocationApplication.class, args);
    }

}
