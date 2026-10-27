package com.example.backend.service;


import com.example.backend.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.backend.model.Booking;
import com.example.backend.repository.BookingRepository;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository repo;

    public Booking add(Booking booking){
        return repo.save(booking);
    }

    public List<Booking> getAll(){
        return repo.findAll();
    }

    public Booking update(String bookingId, Booking booking){
        booking.setId(bookingId);
        return repo.save(booking);
    }

    public void deleteById(String id){
        repo.deleteById(id);
    }
}
