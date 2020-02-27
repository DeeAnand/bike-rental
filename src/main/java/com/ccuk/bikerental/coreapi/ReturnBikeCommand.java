package com.ccuk.bikerental.coreapi;

public class ReturnBikeCommand extends BaseCommand<String> {
	
	String location;
	
	public ReturnBikeCommand(String bikeId, String location) {
		super(bikeId);
		this.location = location;
	}

	public String getLocation() {
		return location;
	}
	
}
