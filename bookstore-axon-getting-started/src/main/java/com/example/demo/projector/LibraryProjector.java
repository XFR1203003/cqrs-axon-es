package com.example.demo.projector;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.axonframework.modelling.command.Repository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.example.demo.aggregate.Library;
import com.example.demo.query.GetLibraryQuery;

@Service
public class LibraryProjector {
	private final Repository<Library> libraryRepository;

	public LibraryProjector(Repository<Library> libraryRepository) {
		this.libraryRepository = libraryRepository;
	}
	
	@QueryHandler
	public Library getLibrary(GetLibraryQuery query) throws InterruptedException, ExecutionException {
		CompletableFuture<Library> future = new CompletableFuture<>();
		libraryRepository.load("" + query.getLibraryId()).execute(future::complete);
		return future.get();
	}
}
