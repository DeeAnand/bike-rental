package com.ccuk.bikerental.query;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.ccuk.bikerental.coreapi.BikeRegisteredEvent;
import com.ccuk.bikerental.coreapi.BikeRentedEvent;
import com.ccuk.bikerental.coreapi.BikeReturnedEvent;

@Profile("query")
@Component
public class BikeStatusProjection {

    private final BikeStatusRepository bikeStatusRepository;

    public BikeStatusProjection(BikeStatusRepository bikeStatusRepository) {
        this.bikeStatusRepository = bikeStatusRepository;
    }

    @EventHandler
    public void on(BikeRegisteredEvent event) {
        bikeStatusRepository.save(new BikeStatus(event.getBikeId(), event.getBikeType(), event.getLocation()));
    }

    @EventHandler
    public void on(BikeRentedEvent event) {
        bikeStatusRepository.findById(event.getBikeId()).map(bs -> {
            bs.setRenter(event.getRenter());
            return bs;
        });
    }

    @EventHandler
    public void on(BikeReturnedEvent event) {
        bikeStatusRepository.findById(event.getBikeId()).map(bs -> {
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
