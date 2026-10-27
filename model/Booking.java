package com.example.backend.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "Booking")
public class Booking {
    @Id
    private String id;
    private String bookingId;
    private String guestName;
    private String roomId;
    private String checkin;
    private String checkout;
    private int guestCount;
    private String status;
}
