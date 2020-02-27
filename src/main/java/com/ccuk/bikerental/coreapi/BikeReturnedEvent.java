package com.ccuk.bikerental.coreapi;

public class BikeReturnedEvent extends BaseEvent<String>{
	String location;
	
	public BikeReturnedEvent(String bikeId, String location) {
		super(bikeId);
		this.location = location;
	}

	public String getLocation() {
		return location;
	}
	
}
