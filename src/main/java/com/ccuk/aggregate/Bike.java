package com.ccuk.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

import com.ccuk.command.RegisterBikeCommand;
import com.ccuk.command.RentBikeCommand;
import com.ccuk.command.ReturnBikeCommand;
import com.ccuk.event.BikeRegisteredEvent;
import com.ccuk.event.BikeRentedEvent;
import com.ccuk.event.BikeReturnedEvent;

@Aggregate
public class Bike {

    @AggregateIdentifier
    private String id;

    private boolean isAvailable;

    public Bike() {
    }

    @CommandHandler
    public Bike(RegisterBikeCommand command) {
        apply(new BikeRegisteredEvent(command.getId(), command.getBikeType(), command.getLocation()));
    }

    @CommandHandler
    public void handle(RentBikeCommand command) {
        if (!this.isAvailable) {
            throw new IllegalStateException("Bike is already rented");
        }
        apply(new BikeRentedEvent(command.getId(), command.getRenter()));
    }

    @CommandHandler
    public void handle(ReturnBikeCommand command) {
        if (this.isAvailable) {
            throw new IllegalStateException("Bike was already returned");
        }
        apply(new BikeReturnedEvent(command.getId(), command.getLocation()));
    }

    @EventSourcingHandler
    protected void handle(BikeRegisteredEvent event) {
        this.id = event.getId();
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
