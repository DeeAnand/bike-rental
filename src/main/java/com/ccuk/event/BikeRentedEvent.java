package com.ccuk.event;

import com.cccuk.base.event.BaseEvent;

public class BikeRentedEvent extends BaseEvent<String>{
	
	String renter;
	
	public BikeRentedEvent(String id, String renter) {
		super(id);
		this.renter = renter;
	}

	public String getRenter() {
		return renter;
	}

}
