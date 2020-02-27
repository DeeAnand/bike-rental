package com.ccuk.bikerental.coreapi;

public class BikeRegisteredEvent extends BaseEvent<String>{

	String bikeType;
	String location;
	
	public BikeRegisteredEvent(String bikeId, String bikeType, String location) {
		super(bikeId);
		this.bikeType = bikeType;
		this.location = location;
	}

	public String getBikeType() {
		return bikeType;
	}

	public String getLocation() {
		return location;
	}
	
}
