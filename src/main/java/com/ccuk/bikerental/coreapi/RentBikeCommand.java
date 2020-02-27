package com.ccuk.bikerental.coreapi;

public class RentBikeCommand extends BaseCommand<String> {
	
	String renter;
	
	public RentBikeCommand(String bikeId, String renter) {
		super(bikeId);
		this.renter = renter;
	}
	
	public RentBikeCommand() {
		// TODO Auto-generated constructor stub
	}

	public String getRenter() {
		return renter;
	}
}
