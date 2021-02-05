package com.example.convertjsontobitmapspring.application.presentation.representation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class StatementRepresentation {

    private MultipartFile templateFile;

    private String name;
    private String balance;

}
