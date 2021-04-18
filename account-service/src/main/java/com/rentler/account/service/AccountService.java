package com.rentler.account.service;

import com.rentler.account.client.AuthServiceClient;
import com.rentler.account.dto.AccountDto;
import com.rentler.account.dto.UserDto;
import com.rentler.account.entity.Account;
import com.rentler.account.exception.exceptions.AccountAlreadyExistsException;
import com.rentler.account.exception.exceptions.AccountNotFoundException;
import com.rentler.account.mapper.AccountMapper;
import com.rentler.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AuthServiceClient authServiceClient;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          AccountMapper accountMapper,
                          AuthServiceClient authServiceClient) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.authServiceClient = authServiceClient;
    }

    public AccountDto create(UserDto user) {
        Optional<Account> existing = accountRepository.findByUsername(user.getUsername());

        existing.ifPresent(account -> {
            throw new AccountAlreadyExistsException("Account already exists: " + account.getUsername());
        });

        authServiceClient.createUser(user);

        Account account = Account.builder()
                .username(user.getUsername())
                .build();

        return accountMapper.toDto(accountRepository.save(account));
    }

    public AccountDto getByUsername(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Account with such username not found"));
        return accountMapper.toDto(account);
    }

    public AccountDto getById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with such id not found"));
        return accountMapper.toDto(account);
    }

    public List<AccountDto> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }
}

