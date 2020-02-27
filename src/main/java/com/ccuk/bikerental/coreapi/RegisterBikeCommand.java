package com.ccuk.bikerental.coreapi;

public class RegisterBikeCommand extends BaseCommand<String> {
	
	String bikeType;
	String location;
	
	public RegisterBikeCommand(String bikeId, String bikeType, String location) {
		super(bikeId);
		this.bikeType = bikeType;
		this.location = location;
	}
	
	public RegisterBikeCommand() {
		// TODO Auto-generated constructor stub
	}

	public String getBikeType() {
		return bikeType;
	}

	public String getLocation() {
		return location;
	}
}
