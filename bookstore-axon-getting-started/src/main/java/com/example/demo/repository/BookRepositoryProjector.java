package com.example.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.events.BookCreatedEvent;
import com.example.demo.models.BookBean;
import com.example.demo.query.GetBooksQuery;

@Service
public class BookRepositoryProjector {

	@Autowired
	private BookRepository bookRepository;

	@EventHandler
	public void addBook(BookCreatedEvent event) {
		BookEntity bookEntity = new BookEntity(event.getIsbn(), event.getLibraryId(), event.getTitle());
		bookRepository.save(bookEntity);
	}

	@QueryHandler
	public List<BookBean> getBooks(GetBooksQuery query) {
		List<BookEntity> bookEntities = bookRepository.findByLibraryId(query.getLibraryId());
		return bookEntities.stream().map(e -> {
			BookBean bookBean = new BookBean(e.getIsbn(), e.getTitle());
			return bookBean;
		}).collect(Collectors.toList());
	}
}
