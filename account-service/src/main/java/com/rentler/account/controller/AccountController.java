package com.rentler.account.controller;


import com.rentler.account.dto.AccountDto;
import com.rentler.account.dto.UserDto;
import com.rentler.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountService.getById(id));
    }

    @GetMapping("/current")
    public Object getCurrentAccount(Principal principal) {
        return accountService.getByUsername(principal.getName());
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.create(userDto));
    }
}
