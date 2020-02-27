package com.ccuk.bikerental.coreapi;

public class BaseEvent<T> {
	public final T bikeId;

	public BaseEvent(T bikeId) {
		this.bikeId = bikeId;
	}

	public T getBikeId() {
		return bikeId;
	}
	
}
