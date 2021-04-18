package com.rentler.account.service;

import com.rentler.account.client.AuthServiceClient;
import com.rentler.account.dto.AccountCreateDto;
import com.rentler.account.dto.AccountDto;
import com.rentler.account.dto.AccountUpdateDto;
import com.rentler.account.entity.Account;
import com.rentler.account.exception.AccountAlreadyExistsException;
import com.rentler.account.exception.AccountNotFoundException;
import com.rentler.account.mapper.AccountMapper;
import com.rentler.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public AccountDto create(AccountCreateDto accountCreateDto) {
        Optional<Account> existing = accountRepository
                .findByUsernameOrEmail(accountCreateDto.getUsername(), accountCreateDto.getEmail());

        existing.ifPresent(account -> {
            throw new AccountAlreadyExistsException("Account already exists");
        });

        authServiceClient.createUser(accountCreateDto);

        Account account = Account.builder()
                .username(accountCreateDto.getUsername())
                .email(accountCreateDto.getEmail())
                .dateOfRegistration(LocalDateTime.now())
                .build();

        return accountMapper.toDto(accountRepository.save(account));
    }

    public AccountDto getByUsername(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Account with such username not found: " + username));
        return accountMapper.toDto(account);
    }

    public List<AccountDto> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    public AccountDto update(AccountUpdateDto updateDto, String username) {
        Account account = accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("Account with such username not found: " + username));

        Optional<Account> existing = accountRepository
                .findByPhoneNumber(updateDto.getPhoneNumber());

        existing.ifPresent(acc -> {
            throw new AccountAlreadyExistsException("Account with such phone number already exists: " + updateDto.getPhoneNumber());
        });

        account.setFirstName(updateDto.getFirstName());
        account.setLastName(updateDto.getLastName());
        account.setPhoneNumber(updateDto.getPhoneNumber());

        return accountMapper.toDto(accountRepository.save(account));
    }
}

