package com.example.convertjsontobitmapspring.domain.service;

import com.example.convertjsontobitmapspring.application.presentation.representation.StatementRepresentation;
import com.example.convertjsontobitmapspring.application.presentation.representation.StatementRepresentationWithFile;
import com.example.convertjsontobitmapspring.common.Base64Converter;
import com.example.convertjsontobitmapspring.common.HTMLConverter;
import com.example.convertjsontobitmapspring.domain.domain.Account;
import com.example.convertjsontobitmapspring.domain.enums.FileType;
import com.example.convertjsontobitmapspring.domain.exception.EntityNotFoundException;
import com.example.convertjsontobitmapspring.domain.port.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Base64Converter base64Converter;

    @Autowired
    private HTMLConverter htmlConverter;

    @Value("${default.imagePath}")
    private String defatulImagePath;

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
    }

    public StatementRepresentation fillStatement(Long id) {
        Account account = findById(id);
        StatementRepresentation statementRepresentation = new StatementRepresentation();
        statementRepresentation.setName(account.getName());
        statementRepresentation.setBankname(account.getBankname());
        statementRepresentation.setAgency(account.getAgency());
        statementRepresentation.setNumber(account.getNumber());
        statementRepresentation.setBalance(account.getBalance());
        return statementRepresentation;
    }

    public String convertHTMLtoBitmapAndEncodeToBase64(ModelAndView statementPage, Model model, HttpServletRequest request, int width, int height) throws Exception {
        BufferedImage imageBitmap = htmlConverter.convertHTMLToBitmap(statementPage, model, request, width, height);
        return base64Converter.encodeImageToBase64(imageBitmap);
    }

    public void encodeImageToBase64AndSaveFile(StatementRepresentationWithFile statementRepresentation) throws IOException {
        String imgPath = getImgPath(statementRepresentation);
        String newName = UUID.randomUUID().toString() + "_" + FileType.TEXT.getExtension();
        String savePath = getFullPath(newName);
        String textBase64 = base64Converter.encodeImageToBase64AndSaveFile(imgPath, savePath);
    }

    public void decodeBase64ToImageAndSaveFile(StatementRepresentationWithFile statementRepresentation) throws IOException {
        String imgPath = getImgPath(statementRepresentation);
        String newName = UUID.randomUUID().toString() + "_" + FileType.BITMAP.getExtension();
        String savePath = getFullPath(newName);
        base64Converter.decodeBase64ToImageAndSaveFile(imgPath, savePath);
    }

    public void convertHTMLtoBitmapAndSaveFile(ModelAndView statementPage, Model model, HttpServletRequest request) throws Exception {
        htmlConverter.convertToBitmapAndSaveFile(statementPage, model, request);
    }

    private String getImgPath(StatementRepresentationWithFile statementRepresentation) {
        String fileName = statementRepresentation.getTemplateFile().getOriginalFilename();
        return getFullPath(fileName);
    }

    private String getFullPath(String fileName) {
        return FileSystems.getDefault().getPath(defatulImagePath, fileName).toString();
    }

}
