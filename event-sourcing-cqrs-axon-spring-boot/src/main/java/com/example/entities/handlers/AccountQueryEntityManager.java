package com.example.entities.handlers;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.aggregates.AccountAggregate;
import com.example.entities.AccountQueryEntity;
import com.example.entities.repositories.AccountRepository;
import com.example.events.BaseEvent;

@Component
public class AccountQueryEntityManager {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("accountAggregateEventSourcingRepository")
    private EventSourcingRepository<AccountAggregate> accountAggregateESRepository;

    @EventSourcingHandler
    void on(BaseEvent event){
        persistAccount(buildQueryAccount(getAccountFromEvent(event)));
    }


    private AccountAggregate getAccountFromEvent(BaseEvent event){
        return accountAggregateESRepository.load(event.id.toString()).getWrappedAggregate().getAggregateRoot();
    }

    private AccountQueryEntity findExistingOrCreateQueryAccount(String id){
        return accountRepository.findById(id).isPresent() ? accountRepository.findById(id).get() : new AccountQueryEntity();
    }

    private AccountQueryEntity buildQueryAccount(AccountAggregate accountAggregate){
        AccountQueryEntity entity = findExistingOrCreateQueryAccount(accountAggregate.getId());
        entity.setId(accountAggregate.getId());
        entity.setAccountBalance(accountAggregate.getAccountBalance());
        entity.setCurrency(accountAggregate.getCurrency());
        entity.setStatus(accountAggregate.getStatus());

        return entity;
    }

    private void persistAccount(AccountQueryEntity accountQueryEntity){
        accountRepository.save(accountQueryEntity);
    }
}
