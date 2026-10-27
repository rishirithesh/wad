package com.example.backend.controller;
import com.example.backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.backend.model.Room;
import java.util.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService service;

    @PostMapping
    public Room add(@RequestBody Room room){
        return service.add(room);
    }

    @GetMapping
    public List<Room> getAll(){
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Room update(@PathVariable String id, @RequestBody Room room){
        return service.update(id,room);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        service.deleteById(id);
    }

    @GetMapping("/booking-status")
    public String getBookingStatus() {
        return service.getBookingDetails();
    }


}
