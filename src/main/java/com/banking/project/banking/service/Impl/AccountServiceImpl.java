package com.banking.project.banking.service.Impl;

import com.banking.project.banking.dto.AccountDto;
import com.banking.project.banking.entity.Account;
import com.banking.project.banking.mapper.AccountMapper;
import com.banking.project.banking.repository.AccountRepository;
import com.banking.project.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {
        Account account =  accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(long id, double amount) {
        Account account =  accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account =  accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
        if (account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts =  accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(long id) {
       accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));
       accountRepository.deleteById(id);
    }
}
