package com.example.convertjsontobitmapspring.application.presentation;

import com.example.convertjsontobitmapspring.application.presentation.representation.StatementRepresentation;
import com.example.convertjsontobitmapspring.application.presentation.representation.StatementRepresentationWithFile;
import com.example.convertjsontobitmapspring.domain.domain.Account;
import com.example.convertjsontobitmapspring.domain.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @PutMapping("/encode-image-to-base64")
    public void encodeImageToBase64AndSave(@Valid StatementRepresentationWithFile statementRepresentation) throws IOException {
        accountService.encodeImageToBase64AndSaveFile(statementRepresentation);
    }

    @PutMapping("/decode-base64-to-image")
    public void decodeBase64ToImageAndSaveFile(@Valid StatementRepresentationWithFile statementRepresentation) throws IOException {
        accountService.decodeBase64ToImageAndSaveFile(statementRepresentation);
    }

    @GetMapping("/{id}/statementview")
    public ModelAndView getStatement(@PathVariable Long id, Model model, HttpServletRequest request) throws Exception {
        StatementRepresentation statementRepresentation = accountService.fillStatement(id);
        model.addAttribute("statement", statementRepresentation);
        ModelAndView statementPage = new ModelAndView("statement");
        // accountService.convertHTMLtoBitmapAndSaveFile(statementPage, model, request);
        return statementPage;
    }

    /*
        O Model e HttpServletRequest são inseridos na chamada para que o spring automaticamente
        injete os dois ao método através do conceito de injeção de dependencias
     */
    @GetMapping("/{id}/statementbase64")
    public String getStatementEncodeToBase64(@PathVariable Long id, Model model, HttpServletRequest request, @RequestParam int width, @RequestParam int height) throws Exception {
        StatementRepresentation statementRepresentation = accountService.fillStatement(id);
        model.addAttribute("statement", statementRepresentation);
        ModelAndView statementPage = new ModelAndView("statement");
        return accountService.convertHTMLtoBitmapAndEncodeToBase64(statementPage, model, request, width, height);
    }

}
