package com.example.demo.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Data;

@Data
public class RegisterLibraryCommand {
	@TargetAggregateIdentifier
	private final Integer libraryId;

	private final String name;
}
