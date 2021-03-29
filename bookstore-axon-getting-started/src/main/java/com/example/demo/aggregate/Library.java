package com.example.demo.aggregate;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.util.Assert;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.demo.commands.RegisterBookCommand;
import com.example.demo.commands.RegisterLibraryCommand;
import com.example.demo.events.BookCreatedEvent;
import com.example.demo.events.LibraryCreatedEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Aggregate
public class Library {
	@AggregateIdentifier
	private Integer libraryId;

	private String name;

	private List<String> isbnBooks;

	@CommandHandler
	public Library(RegisterLibraryCommand cmd) {
		Assert.notNull(cmd.getLibraryId(), "ID should not be null");
		Assert.notNull(cmd.getName(), "Name should not be null");

		LibraryCreatedEvent event = new LibraryCreatedEvent(cmd.getLibraryId(), cmd.getName());
		AggregateLifecycle.apply(event);
	}

	@EventSourcingHandler
	public void handleCreatedEvent(LibraryCreatedEvent event) {
		libraryId = event.getLibraryId();
		name = event.getName();
		isbnBooks = new ArrayList<>();
	}

	@CommandHandler
	public void addBook(RegisterBookCommand cmd) {
		Assert.notNull(cmd.getLibraryId(), "ID should not be null");
		Assert.notNull(cmd.getIsbn(), "Book ISBN should not be null");

		BookCreatedEvent event = new BookCreatedEvent(cmd.getLibraryId(), cmd.getIsbn(), cmd.getTitle());
		AggregateLifecycle.apply(event);
	}

	@EventSourcingHandler
	public void addBook(BookCreatedEvent event) {
		isbnBooks.add(event.getIsbn());
	}
}
