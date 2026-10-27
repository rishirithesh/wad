
package com.example.backend.service;

import lombok.RequiredArgsConstructor;
import com.example.backend.model.Room;
import com.example.backend.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
@RequiredArgsConstructor
public class
    RoomService {

    private final RoomRepository repo;
    private final MongoTemplate mongoTemplate;

    public Room add(Room room) {
        return repo.save(room);
    }

    public List<Room> getAll() {
        return repo.findAll();
    }

    public Room update(String roomId, Room room) {
        room.setId(roomId);
        return repo.save(room);
    }

    public void deleteById(String id) {
        repo.deleteById(id);
    }

    public String getBookingDetails() {

        List<Document> result = mongoTemplate.aggregate(
                newAggregation(
                        lookup("Booking", "roomId", "roomId", "roomid")
                ),
                "Room",
                Document.class
        ).getMappedResults();

        if (result.isEmpty()) {
            return "No rooms found";
        }

        Document doc = result.get(0);

        List<Document> bookings =
                (List<Document>) doc.get("roomid");

        if (bookings == null || bookings.isEmpty()) {
            return "No bookings found";
        }

        return bookings.get(0).getString("status");
    }

}

