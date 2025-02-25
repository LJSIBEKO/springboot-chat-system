package linda.co.za.chat.system.controller;

import linda.co.za.chat.system.dtos.account.LoginRequest;
import linda.co.za.chat.system.model.account.Account;
import linda.co.za.chat.system.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController
{

    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(accountService.login(request.getEmail(), request.getPassword()));
    }
}
