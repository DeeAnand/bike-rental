package com.ccuk.command;

import com.cccuk.base.command.BaseCommand;

public class RegisterBikeCommand extends BaseCommand<String> {
	
	public String bikeType;
	public String location;
	
	public RegisterBikeCommand(String id, String bikeType, String location) {
		super(id);
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
