package com.example.backend.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Room")
public class Room {
    @Id
    private String id;
    private String roomId;
    private int roomNumber;
    private String roomType;
    private int capacity;
    private boolean availability;
}

