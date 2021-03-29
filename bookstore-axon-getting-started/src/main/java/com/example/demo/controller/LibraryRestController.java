package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.aggregate.Library;
import com.example.demo.commands.RegisterBookCommand;
import com.example.demo.commands.RegisterLibraryCommand;
import com.example.demo.models.BookBean;
import com.example.demo.models.LibraryBean;
import com.example.demo.query.GetBooksQuery;
import com.example.demo.query.GetLibraryQuery;

@RestController
public class LibraryRestController {
	@Autowired
	private CommandGateway commandGateway;
	@Autowired
	private QueryGateway queryGateway;
	
	@PostMapping("/api/library")
	public String addLibrary(@RequestBody LibraryBean library) {
		RegisterLibraryCommand command = new RegisterLibraryCommand(library.getLibraryId(), library.getName());
		commandGateway.send(command);
		return "Saved Library Details !";
	}
	
	@GetMapping("/api/library/{library}")
	public Library getLibrary(@PathVariable Integer library) throws InterruptedException, ExecutionException {
		CompletableFuture<Library> future = queryGateway.query(new GetLibraryQuery(library), Library.class);
		return future.get();
	}
	
	@PostMapping("/api/library/{library}/book")
	public String addBook(@PathVariable Integer library, @RequestBody BookBean book) {
		commandGateway.send(new RegisterBookCommand(library, book.getIsbn(), book.getTitle()));
		return "Saved";
	}

	@GetMapping("/api/library/{library}/book")
	public List<BookBean> addBook(@PathVariable Integer library) throws InterruptedException, ExecutionException {
		return queryGateway.query(new GetBooksQuery(library), ResponseTypes.multipleInstancesOf(BookBean.class)).get();
	}
}
