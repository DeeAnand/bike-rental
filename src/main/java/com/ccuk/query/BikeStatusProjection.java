package com.ccuk.query;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ccuk.event.BikeRegisteredEvent;
import com.ccuk.event.BikeRentedEvent;
import com.ccuk.event.BikeReturnedEvent;

@Component
public class BikeStatusProjection {

    private final BikeStatusRepository bikeStatusRepository;

    public BikeStatusProjection(BikeStatusRepository bikeStatusRepository) {
        this.bikeStatusRepository = bikeStatusRepository;
    }

    @EventHandler
    public void on(BikeRegisteredEvent event) {
        bikeStatusRepository.save(new BikeStatus(event.getId(), event.getBikeType(), event.getLocation()));
    }

    @EventHandler
    public void on(BikeRentedEvent event) {
        bikeStatusRepository.findById(event.getId()).map(bs -> {
            bs.setRenter(event.getRenter());
            return bs;
        });
    }

    @EventHandler
    public void on(BikeReturnedEvent event) {
        bikeStatusRepository.findById(event.getId()).map(bs -> {
            bs.setRenter(null);
            bs.setLocation(event.getLocation());
            return bs;
        });
    }

    @QueryHandler(queryName = "findAll")
    public Iterable<BikeStatus> findAll() {
        return bikeStatusRepository.findAll();
    }

    @QueryHandler(queryName = "findOne")
    public BikeStatus findOne(String bikeId) {
        return bikeStatusRepository.findById(bikeId).orElse(null);
    }
}
