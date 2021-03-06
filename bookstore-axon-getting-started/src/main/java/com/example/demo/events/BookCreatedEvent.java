package com.example.demo.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class BookCreatedEvent {
	@TargetAggregateIdentifier
	private final Integer libraryId;
	private final String isbn;
	private final String title;
}
