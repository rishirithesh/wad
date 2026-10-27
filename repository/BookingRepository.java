package com.example.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.backend.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
