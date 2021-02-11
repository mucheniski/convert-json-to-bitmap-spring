package com.example.convertjsontobitmapspring.application.presentation;

import com.example.convertjsontobitmapspring.application.presentation.representation.StatementRepresentation;
import com.example.convertjsontobitmapspring.application.presentation.representation.StatementRepresentationWithFile;
import com.example.convertjsontobitmapspring.common.HTMLConverter;
import com.example.convertjsontobitmapspring.domain.domain.Account;
import com.example.convertjsontobitmapspring.domain.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

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
    public StatementRepresentationWithFile encodeImageToBase64AndSave(@PathVariable Long id, @Valid StatementRepresentationWithFile statementRepresentation) throws IOException {
        return accountService.encodeImageToBase64AndSaveFile(id, statementRepresentation);
    }

    @PutMapping("/{id}/decode-base64-to-image")
    public void decodeBase64ToImageAndSaveFile(@PathVariable Long id, @Valid StatementRepresentationWithFile statementRepresentation) throws IOException {
        accountService.decodeBase64ToImageAndSaveFile(statementRepresentation);
    }

    @GetMapping("/{id}/statement")
    public ModelAndView statement(@PathVariable Long id, Model model, HttpServletRequest request) throws Exception {
        StatementRepresentation statementRepresentation = accountService.fillStatement(id);
        model.addAttribute("statement", statementRepresentation);
        ModelAndView statementPage = new ModelAndView("statement");
        accountService.convertHTMLtoBitmap(statementPage, model, request);
        // accountService.convertHTMLtoBitmap(statementPage, model);
        return statementPage;
    }

}
