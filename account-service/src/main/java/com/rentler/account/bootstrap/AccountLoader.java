package com.rentler.account.bootstrap;

import com.rentler.account.entity.Account;
import com.rentler.account.mapper.AccountMapper;
import com.rentler.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountLoader implements ApplicationListener<ApplicationReadyEvent> {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountLoader(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Account account = Account.builder()
                .username("andriy")
                .email("andriy@gmail.com")
                .password("1111")
                .build();

        accountRepository.save(account);
    }
}

