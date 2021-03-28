package com.example.services.commands;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.example.commands.CreateAccountCommand;
import com.example.commands.CreditMoneyCommand;
import com.example.commands.DebitMoneyCommand;
import com.example.dto.commands.AccountCreateDTO;
import com.example.dto.commands.MoneyCreditDTO;
import com.example.dto.commands.MoneyDebitDTO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
    	CreateAccountCommand cmd = new CreateAccountCommand(UUID.randomUUID().toString(), accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency());
        return commandGateway.send(cmd);
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
    	CreditMoneyCommand cmd = new CreditMoneyCommand(accountNumber, moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency());
        return commandGateway.send(cmd);
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
    	DebitMoneyCommand cmd = new DebitMoneyCommand(accountNumber, moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency());
        return commandGateway.send(cmd);
    }
}
