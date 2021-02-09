package com.example.convertjsontobitmapspring.common;

import com.example.convertjsontobitmapspring.domain.enums.FileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Service
public class HTMLConverter {

    @Value("${default.htmlTemplatePath}")
    private String defaultHTMLTemplatePath;

    @Value("${default.imagePath}")
    private String defaultImagePath;

    private String addHtmlExtension(String viewName) {
        return viewName + FileType.HTML.getExtension();
    }

}
