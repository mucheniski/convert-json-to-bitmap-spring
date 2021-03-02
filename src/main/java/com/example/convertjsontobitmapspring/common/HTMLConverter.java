package com.example.convertjsontobitmapspring.common;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.xhtmlrenderer.swing.Java2DRenderer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

@Service
public class HTMLConverter {

    @Value("${default.htmlTemplatePath}")
    private String defaultHTMLTemplatePath;

    @Value("${default.imagePath}")
    private String defaultImagePath;

    @Autowired
    private ViewResolver viewResolver;

    // TODO: ver como passar o logo no bitmap renderizado
    public BufferedImage convertHTMLToBitmap(ModelAndView statementPage, Model model, HttpServletRequest request, int width, int height) throws Exception  {
        byte[] pageHTML = getHtmlCodeByte(statementPage, model, request);
        Path tempFile = Files.createTempFile("sampleTempFile", "html");
        FileUtils.writeByteArrayToFile(tempFile.toFile(), pageHTML);
        Java2DRenderer renderer = new Java2DRenderer(tempFile.toFile(), width, height);
        return renderer.getImage();
    }

    public void convertToBitmapAndSaveFile(ModelAndView statementPage, Model model, HttpServletRequest request) throws Exception {
        byte[] pageHTML = getHtmlCodeByte(statementPage, model, request);
        Path tempFile = Files.createTempFile("sampleTempFile", "html");
        FileUtils.writeByteArrayToFile(tempFile.toFile(), pageHTML);
        int width = 200;
        int height = 300;
        Java2DRenderer renderer = new Java2DRenderer(tempFile.toFile(), width, height);
        BufferedImage image = renderer.getImage();
        File fileBitmap = new File(defaultImagePath + "teste.bmp");
    }

    private String getHtmlCodeString(ModelAndView statementPage, Model model, HttpServletRequest request) throws Exception {
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        View resolvedView = viewResolver.resolveViewName(statementPage.getViewName(), new Locale("pt", "BR"));
        resolvedView.render(model.asMap(), request, mockHttpServletResponse);
        return mockHttpServletResponse.getContentAsString();
    }

    private byte[] getHtmlCodeByte(ModelAndView statementPage, Model model, HttpServletRequest request) throws Exception {
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        View resolvedView = viewResolver.resolveViewName(statementPage.getViewName(), new Locale("pt", "BR"));
        resolvedView.render(model.asMap(), request, mockHttpServletResponse);
        return mockHttpServletResponse.getContentAsByteArray();
    }

    private void removeOlderPage(File fileHtml) {
        if(fileHtml.exists()) {
            fileHtml.delete();
        }
    }

}
