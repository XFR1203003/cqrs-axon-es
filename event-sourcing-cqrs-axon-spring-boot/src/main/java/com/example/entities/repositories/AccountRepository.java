package com.example.entities.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.AccountQueryEntity;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
	
}
