package com.rentler.account.service;

import com.rentler.account.client.AuthServiceClient;
import com.rentler.account.dto.AccountCreateDto;
import com.rentler.account.dto.AccountUpdateDto;
import com.rentler.account.entity.Account;
import com.rentler.account.exception.AccountAlreadyExistsException;
import com.rentler.account.exception.AccountNotFoundException;
import com.rentler.account.mapper.AccountMapper;
import com.rentler.account.repository.AccountRepository;
import com.rentler.helper.rabbit.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AuthServiceClient authServiceClient;

    @Mock
    private RabbitTemplate template;

    @Spy
    private AccountMapper accountMapper = new AccountMapper();

    @InjectMocks
    private AccountService accountService;

    @Test
    public void create_usernameAlreadyExists_ExceptionThrown() {
        AccountCreateDto accountCreateDto = AccountCreateDto.builder().username("user").build();

        when(accountRepository.findByUsername(accountCreateDto.getUsername()))
                .thenReturn(Optional.of(Account.builder().build()));

        AccountAlreadyExistsException ex = assertThrows(AccountAlreadyExistsException.class, () -> accountService.create(accountCreateDto));

        assertEquals("Account with such username already exists", ex.getMessage());
    }

    @Test
    public void create_mailAlreadyExists_ExceptionThrown() {
        AccountCreateDto accountCreateDto = AccountCreateDto.builder().username("user").email("mail").build();

        when(accountRepository.findByEmail(accountCreateDto.getEmail()))
                .thenReturn(Optional.of(Account.builder().build()));

        AccountAlreadyExistsException ex = assertThrows(AccountAlreadyExistsException.class, () -> accountService.create(accountCreateDto));

        assertEquals("Account with such email already exists", ex.getMessage());
    }

    @Test
    public void create_validUserInfo_success() {
        AccountCreateDto accountCreateDto = AccountCreateDto.builder()
                .username("user")
                .email("mail")
                .password("123")
                .build();

        when(accountRepository.findByUsername(accountCreateDto.getUsername())).thenReturn(Optional.empty());
        when(accountRepository.findByEmail(accountCreateDto.getEmail())).thenReturn(Optional.empty());

        accountService.create(accountCreateDto);

        verify(authServiceClient).createUser(accountCreateDto);
        verify(template).convertAndSend(RabbitConfig.WELCOME_MAILS_QUEUE_NAME, accountCreateDto.getEmail());

        verify(accountRepository).save(any());
    }


    @Test
    public void getAll_returnsAllAccounts() {
        List<Account> accounts = List.of(Account.builder().username("1").email("1").build(),
                Account.builder().username("2").email("2").build());

        when(accountRepository.findAll()).thenReturn(accounts);

        assertIterableEquals(accounts.stream().map(accountMapper::toDto).collect(Collectors.toList()),
                accountService.getAll());
    }

    @Test
    public void update_usernameNotExists_Exception() {

        AccountNotFoundException ex = assertThrows(AccountNotFoundException.class, () -> accountService.update(AccountUpdateDto.builder().build(), "user"));

        assertEquals("Account with such username not found: user", ex.getMessage());
    }

    @Test
    public void update_existingMail_Exception() {
        AccountUpdateDto accountUpdateDto = AccountUpdateDto.builder().email("mail").build();
        when(accountRepository.findByUsername("user")).thenReturn(Optional.of(Account.builder().email("old mail").build()));
        when(accountRepository.findByEmail("mail")).thenReturn(Optional.of(Account.builder().email("old mail").build()));

        AccountAlreadyExistsException ex = assertThrows(AccountAlreadyExistsException.class, () -> accountService.update(accountUpdateDto, "user"));

        assertEquals("Account with such email already exists: mail", ex.getMessage());
    }

    @Test
    public void update_existingPhoneNumber_Exception() {
        AccountUpdateDto accountUpdateDto = AccountUpdateDto.builder().email("mail").phoneNumber("123").build();
        when(accountRepository.findByUsername("user")).thenReturn(Optional.of(Account.builder().email("mail").build()));
        when(accountRepository.findByPhoneNumber("123")).thenReturn(Optional.of(Account.builder().email("mail").build()));

        AccountAlreadyExistsException ex = assertThrows(AccountAlreadyExistsException.class, () -> accountService.update(accountUpdateDto, "user"));

        assertEquals("Account with such phone number already exists: 123", ex.getMessage());
    }

    @Test
    public void update_correctData_success() {
        AccountUpdateDto accountUpdateDto = AccountUpdateDto.builder().email("mail").phoneNumber("123").build();
        when(accountRepository.findByUsername("user")).thenReturn(Optional.of(Account.builder().email("mail").build()));
        when(accountRepository.findByPhoneNumber("123")).thenReturn(Optional.of(Account.builder().email("mail").build()));

        AccountAlreadyExistsException ex = assertThrows(AccountAlreadyExistsException.class, () -> accountService.update(accountUpdateDto, "user"));

        assertEquals("Account with such phone number already exists: 123", ex.getMessage());
    }

}
