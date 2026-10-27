package com.example.backend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.backend.service.BookingService;
import com.example.backend.model.Booking;
import java.util.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    public Booking add(@RequestBody Booking booking){
        return service.add(booking);
    }

    @GetMapping
    public List<Booking> getAll(){
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable String id, @RequestBody Booking booking){
        return service.update(id,booking);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id){
        service.deleteById(id);
    }


}
