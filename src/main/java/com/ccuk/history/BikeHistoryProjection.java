package com.ccuk.history;

import java.time.Instant;
import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ccuk.event.BikeRegisteredEvent;
import com.ccuk.event.BikeRentedEvent;
import com.ccuk.event.BikeReturnedEvent;

@Component
public class BikeHistoryProjection {

    private final BikeHistoryRepository bikeHistoryRepository;
    private final QueryUpdateEmitter updateEmitter;

    public BikeHistoryProjection(BikeHistoryRepository bikeHistoryRepository, QueryUpdateEmitter updateEmitter) {
        this.bikeHistoryRepository = bikeHistoryRepository;
        this.updateEmitter = updateEmitter;
    }

    @EventHandler
    public void handle(BikeRegisteredEvent event, @Timestamp Instant timestamp) {
        bikeHistoryRepository.save(new BikeHistory(event.getId(), timestamp,
                                                   "Bike (" + event.getBikeType() + ") registered in " + event.getLocation()));
    }

    @EventHandler
    public void handle(BikeRentedEvent event, @Timestamp Instant timestamp) {
        BikeHistory newEntry = new BikeHistory(event.getId(), timestamp, "Bike rented out to " + event.getRenter());
        bikeHistoryRepository.save(newEntry);

        updateEmitter.emit(m -> "locationHistory".equals(m.getQueryName())
                                   && newEntry.getBikeId().equals(m.getPayload()),
                           newEntry);
    }

    @EventHandler
    public void handle(BikeReturnedEvent event, @Timestamp Instant timestamp) {
        BikeHistory newEntry = new BikeHistory(event.getId(), timestamp, "Bike returned in " + event.getLocation());
        bikeHistoryRepository.save(newEntry);

        updateEmitter.emit(m -> "locationHistory".equals(m.getQueryName())
                                   && newEntry.getBikeId().equals(m.getPayload()),
                           newEntry);
    }

    @QueryHandler(queryName = "locationHistory")
    public List<BikeHistory> findMovements(String bikeId) {
        return bikeHistoryRepository.findByBikeIdOrderById(bikeId);
    }

}
