package com.example.convertjsontobitmapspring.domain.service;

import com.example.convertjsontobitmapspring.application.presentation.representation.StatementRepresentation;
import com.example.convertjsontobitmapspring.common.Base64Converter;
import com.example.convertjsontobitmapspring.domain.domain.Account;
import com.example.convertjsontobitmapspring.domain.enums.FileType;
import com.example.convertjsontobitmapspring.domain.exception.EntityNotFoundException;
import com.example.convertjsontobitmapspring.domain.port.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Base64Converter base64Converter;

    @Value("${base64converter.originalfilepath}")
    private String originalfilepath;

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
    }

    public StatementRepresentation encodeImageToBase64AndSaveFile(Long id, StatementRepresentation statementRepresentation) throws IOException {
        Account account = findById(id);

        String fileName = getOriginalFilename(statementRepresentation);
        String imgPath = getFullPath(fileName);
        String newName = UUID.randomUUID().toString() + "_" + FileType.TEXT.getExtension();
        String savePath = getFullPath(newName);
        String textBase64 = base64Converter.encodeImageToBase64AndSaveFile(imgPath, savePath);

        statementRepresentation.setName(account.getName());
        statementRepresentation.setBalance(account.getBalance());
        statementRepresentation.setTemplateBase64(textBase64);

        return statementRepresentation;
    }

    public void decodeBase64ToImageAndSaveFile(StatementRepresentation statementRepresentation) throws IOException {
        String fileName = getOriginalFilename(statementRepresentation);
        String imgPath = getFullPath(fileName);
        String newName = UUID.randomUUID().toString() + "_" + FileType.BITMAP.getExtension();
        String savePath = getFullPath(newName);
        base64Converter.decodeBase64ToImageAndSaveFile(imgPath, savePath);
    }

    private String getOriginalFilename(StatementRepresentation statementRepresentation) {
        return statementRepresentation.getTemplateFile().getOriginalFilename();
    }

    private String getFullPath(String fileName) {
        return FileSystems.getDefault().getPath(originalfilepath, fileName).toString();
    }

}
