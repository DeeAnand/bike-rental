package com.ccuk.command;

import com.cccuk.base.command.BaseCommand;

public class RentBikeCommand extends BaseCommand<String> {
	
	public String renter;
	
	public RentBikeCommand(String id, String renter) {
		super(id);
		this.renter = renter;
	}
	
	public RentBikeCommand() {
		// TODO Auto-generated constructor stub
	}

	public String getRenter() {
		return renter;
	}
}
