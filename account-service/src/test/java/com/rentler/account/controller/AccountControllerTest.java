package com.rentler.account.controller;

import com.rentler.account.dto.AccountCreateDto;
import com.rentler.account.dto.AccountDto;
import com.rentler.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void getAccounts_okResponse() {
        List<AccountDto> accounts = List.of(AccountDto.builder().username("1").build(),
                AccountDto.builder().username("2").build());

        when(accountService.getAll()).thenReturn(accounts);

        ResponseEntity<List<AccountDto>> response = accountController.getAccounts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertIterableEquals(accounts, response.getBody());
    }

    @Test
    public void getAccount_okResponse() {
        AccountDto account = AccountDto.builder().username("1").build();

        when(accountService.getByUsername("1")).thenReturn(account);

        ResponseEntity<AccountDto> response = accountController.getAccount("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account, response.getBody());
    }

    @Test
    public void createAccount_createsAccount_okResponse() {
        AccountDto account = AccountDto.builder().username("1").build();
        AccountCreateDto accountCreateDto = AccountCreateDto.builder().build();

        when(accountService.create(accountCreateDto)).thenReturn(account);

        ResponseEntity<AccountDto> response = accountController.createAccount(accountCreateDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(account, response.getBody());
    }

}
