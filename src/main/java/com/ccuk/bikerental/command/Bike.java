package com.ccuk.bikerental.command;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

import com.ccuk.bikerental.coreapi.BikeRegisteredEvent;
import com.ccuk.bikerental.coreapi.BikeRentedEvent;
import com.ccuk.bikerental.coreapi.BikeReturnedEvent;
import com.ccuk.bikerental.coreapi.RegisterBikeCommand;
import com.ccuk.bikerental.coreapi.RentBikeCommand;
import com.ccuk.bikerental.coreapi.ReturnBikeCommand;

@Profile("command")
@Aggregate(snapshotTriggerDefinition = "bikeSnapshotDefinition")
public class Bike {

    @AggregateIdentifier
    private String bikeId;

    private boolean isAvailable;

    public Bike() {
    }

    @CommandHandler
    public Bike(RegisterBikeCommand command) {
        apply(new BikeRegisteredEvent(command.getBikeId(), command.getBikeType(), command.getLocation()));
    }

    @CommandHandler
    public void handle(RentBikeCommand command) {
        if (!this.isAvailable) {
            throw new IllegalStateException("Bike is already rented");
        }
        apply(new BikeRentedEvent(command.getBikeId(), command.getRenter()));
    }

    @CommandHandler
    public void handle(ReturnBikeCommand command) {
        if (this.isAvailable) {
            throw new IllegalStateException("Bike was already returned");
        }
        apply(new BikeReturnedEvent(command.getBikeId(), command.getLocation()));
    }

    @EventSourcingHandler
    protected void handle(BikeRegisteredEvent event) {
        this.bikeId = event.getBikeId();
        this.isAvailable = true;
    }

    @EventSourcingHandler
    protected void handle(BikeReturnedEvent event) {
        this.isAvailable = true;
    }

    @EventSourcingHandler
    protected void handle(BikeRentedEvent event) {
        this.isAvailable = false;
    }
}
