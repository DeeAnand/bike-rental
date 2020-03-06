package com.cccuk.base.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {
	@TargetAggregateIdentifier
	public T id;

	public BaseCommand(T id) {
		this.id = id;
	}
	
	public BaseCommand() {
		// TODO Auto-generated constructor stub
	}

	public T getId() {
		return id;
	}
	
}
