package com.bams.service;

import com.bams.entity.AccountType;
import com.bams.entity.User;
import com.bams.repository.AccountRepository;
import com.bams.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bams.dtos.AccountOpen;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    void createAccountTest() {

        AccountOpen accountOpen = new AccountOpen();
        accountOpen.setAccountType(AccountType.SAVINGS);
        accountOpen.setBalance(BigDecimal.valueOf(100));

        User user = new User();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        accountService.createAccount(1,accountOpen);


    }

}
