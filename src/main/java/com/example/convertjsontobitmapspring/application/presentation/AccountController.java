package com.example.convertjsontobitmapspring.application.presentation;

import com.example.convertjsontobitmapspring.application.presentation.representation.StatementRepresentation;
import com.example.convertjsontobitmapspring.domain.domain.Account;
import com.example.convertjsontobitmapspring.domain.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @PutMapping("/{id}/encode-image-to-base64")
    public String encodeImageToBase64(@PathVariable Long id, StatementRepresentation statementRepresentation) throws IOException {
        return accountService.encodeImageToBase64(id, statementRepresentation);
    }

    @PutMapping("/{id}/decode-base64-to-image")
    public void decodeBase64ToImage(@PathVariable Long id, StatementRepresentation statementRepresentation) throws IOException {
        accountService.decodeBase64ToImage(id, statementRepresentation);
    }

}
