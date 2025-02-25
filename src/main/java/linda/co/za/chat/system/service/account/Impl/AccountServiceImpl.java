package linda.co.za.chat.system.service.account.Impl;

import linda.co.za.chat.system.exception.RequestException;
import linda.co.za.chat.system.model.account.Account;
import linda.co.za.chat.system.repository.account.AccountRepository;
import linda.co.za.chat.system.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class AccountServiceImpl implements AccountService
{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account createAccount(Account account) {

        // Check if email is already taken
        if (accountRepository.findByEmail(account.getEmail()).isPresent())
            throw new RequestException("Email already in use");


        // Check if mobile is already taken
        if (accountRepository.findByMobile(account.getMobile()).isPresent())
            throw new RequestException("Mobile number already in use");


        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RequestException("Account not found"));
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(() -> new RequestException("Account not found"));
    }

    @Override
    public Account getAccountByMobile(String mobile) {
        return accountRepository.findByMobile(mobile).orElseThrow(() -> new RequestException("Account not found"));
    }

    @Override
    public Account login(String username, String password) {
        Optional<Account> accountOpt = accountRepository.findByEmail(username);

        Account account = accountOpt.orElseThrow(() -> new RequestException("Invalid credentials"));

        // Validate password
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new RequestException("Invalid credentials");
        }

        return account;
    }
}
