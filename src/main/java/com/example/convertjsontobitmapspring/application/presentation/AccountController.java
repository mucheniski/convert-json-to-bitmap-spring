package com.example.convertjsontobitmapspring.application.presentation;

import com.example.convertjsontobitmapspring.domain.domain.Account;
import com.example.convertjsontobitmapspring.domain.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @GetMapping("/{id}/encode-image-to-base64")
    public String encodeImageToBase64() {

        String imgPath = "C:\\ws-developer\\convert-json-to-bitmap-spring\\img\\img.png";
        String savePath = "C:\\ws-developer\\convert-json-to-bitmap-spring\\img\\img.txt";

        String base64text = accountService.encodeImageToBase64(imgPath, savePath);
        System.out.println("WORKS: ==== " + base64text);

        return base64text;

    }

    @GetMapping("/{id}/decode-base64-to-image")
    public void decodeBase64ToImage() {

        String imgPath = "C:\\ws-developer\\convert-json-to-bitmap-spring\\img\\img.txt";
        String savePath = "C:\\ws-developer\\convert-json-to-bitmap-spring\\img\\newImage.png";

        accountService.decodeBase64ToImage(imgPath, savePath);
        System.out.println("WORKS: ==== ");

    }

}
