package com.ccuk.event;

import com.cccuk.base.event.BaseEvent;

public class BikeRegisteredEvent extends BaseEvent<String>{

	String bikeType;
	String location;
	
	public BikeRegisteredEvent(String id, String bikeType, String location) {
		super(id);
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
