package com.example.convertjsontobitmapspring.common;

import com.example.convertjsontobitmapspring.domain.enums.FileType;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Service
public class HTMLConverter {

    @Value("${default.htmlTemplatePath}")
    private String defaultHTMLTemplatePath;

    @Value("${default.imagePath}")
    private String defaultImagePath;

    public void convertToBitmap(ModelAndView modelAndView) throws IOException {

        File file = new File(defaultHTMLTemplatePath + "statement.html");
        int width = 200;
        int height = 300;
        Java2DRenderer renderer = new Java2DRenderer(file, width, height);
        BufferedImage image = renderer.getImage();
        File newFile = new File(defaultImagePath + "teste.bmp");

        ImageIO.write(image, "bmp", newFile);
    }

    private String addHtmlExtension(String viewName) {
        return viewName + FileType.HTML.getExtension();
    }

}
