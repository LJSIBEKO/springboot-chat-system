package linda.co.za.chat.system.service.account;

import linda.co.za.chat.system.model.account.Account;

import java.util.UUID;

public interface AccountService
{
    Account createAccount(Account account);
    Account getAccountById(UUID id);
    Account getAccountByEmail(String email);
    Account getAccountByMobile(String mobile);
    Account login(String username, String password);
}
