package com.ccuk.command;

import com.cccuk.base.command.BaseCommand;

public class ReturnBikeCommand extends BaseCommand<String> {
	
	public String location;
	
	public ReturnBikeCommand(String id, String location) {
		super(id);
		this.location = location;
	}
	
	public ReturnBikeCommand() {
		// TODO Auto-generated constructor stub
	}

	public String getLocation() {
		return location;
	}
	
}
