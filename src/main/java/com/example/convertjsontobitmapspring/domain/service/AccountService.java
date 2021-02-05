package com.example.convertjsontobitmapspring.domain.service;

import com.example.convertjsontobitmapspring.common.Base64Converter;
import com.example.convertjsontobitmapspring.domain.domain.Account;
import com.example.convertjsontobitmapspring.domain.port.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Base64Converter base64Converter;

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found: " + id));
    }

    public String encodeImageToBase64(String imgPath, String savePath) {
        return base64Converter.encodeImageToBase64(imgPath, savePath);
    }

    public void decodeBase64ToImage(String imgPath, String savePath) {
        base64Converter.decodeBase64ToImage(imgPath, savePath);
    }

}
