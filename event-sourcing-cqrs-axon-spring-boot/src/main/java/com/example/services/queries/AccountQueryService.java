package com.example.services.queries;

import java.util.List;

import com.example.entities.AccountQueryEntity;

public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);
    public AccountQueryEntity getAccount(String accountNumber);
}
