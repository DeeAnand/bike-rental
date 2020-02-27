package com.ccuk.bikerental.coreapi;

public class BikeRentedEvent extends BaseEvent<String>{
	
	String renter;
	
	public BikeRentedEvent(String bikeId, String renter) {
		super(bikeId);
		this.renter = renter;
	}

	public String getRenter() {
		return renter;
	}

}
