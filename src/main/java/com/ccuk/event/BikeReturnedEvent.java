package com.ccuk.event;

import com.cccuk.base.event.BaseEvent;

public class BikeReturnedEvent extends BaseEvent<String>{
	String location;
	
	public BikeReturnedEvent(String id, String location) {
		super(id);
		this.location = location;
	}

	public String getLocation() {
		return location;
	}
	
}
