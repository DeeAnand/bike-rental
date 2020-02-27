package com.ccuk.bikerental.coreapi;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {
	@TargetAggregateIdentifier
	public T bikeId;

	public BaseCommand(T bikeId) {
		this.bikeId = bikeId;
	}
	
	public BaseCommand() {
		// TODO Auto-generated constructor stub
	}

	public T getBikeId() {
		return bikeId;
	}
}
