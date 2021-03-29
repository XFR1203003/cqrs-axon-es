package com.example.demo.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class BookEntity {
	@Id
	private String isbn;
	@Column
	private int libraryId;
	@Column
	private String title;
}