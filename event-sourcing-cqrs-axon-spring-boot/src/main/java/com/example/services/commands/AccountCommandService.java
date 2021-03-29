package com.example.services.commands;

import java.util.concurrent.CompletableFuture;

import com.example.dto.commands.AccountCreateDTO;
import com.example.dto.commands.MoneyCreditDTO;
import com.example.dto.commands.MoneyDebitDTO;

public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
