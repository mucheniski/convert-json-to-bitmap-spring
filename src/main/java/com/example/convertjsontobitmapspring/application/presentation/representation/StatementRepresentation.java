package com.example.convertjsontobitmapspring.application.presentation.representation;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class StatementRepresentation {

    @NotNull
    private MultipartFile templateFile;

    private String name;
    private String balance;

}
