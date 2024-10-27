package com.banking.project.banking.service;

import com.banking.project.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {

    /**
     * createAccount
     * @param accountDto
     * @return
     */
    AccountDto createAccount(AccountDto accountDto);

    /**
     * getAccountById
     * @param id
     * @return
     */
    AccountDto getAccountById(long id);

    /**
     * deposit
     * @param id
     * @param amount
     * @return
     */
    AccountDto deposit(long id, double amount);

    /**
     * withdraw
     * @param id
     * @param amount
     * @return
     */
    AccountDto withdraw(long id, double amount);

    /**
     * getAllAccounts
     * @return
     */
    List<AccountDto> getAllAccounts();

    /**
     * deleteAccount
     * @param id
     */
    void deleteAccount(long id);
}
