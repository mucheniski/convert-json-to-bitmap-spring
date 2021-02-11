package com.example.convertjsontobitmapspring.common;

import com.example.convertjsontobitmapspring.domain.enums.FileType;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
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
import java.util.Locale;

@Service
public class HTMLConverter {

    @Value("${default.htmlTemplatePath}")
    private String defaultHTMLTemplatePath;

    @Value("${default.imagePath}")
    private String defaultImagePath;

    @Autowired
    private ViewResolver viewResolver;

    public BufferedImage convertHTMLToBitmap(ModelAndView statementPage, Model model, HttpServletRequest request) throws Exception  {
        String pageHTML = getHtmlCode(statementPage, model, request);
        File fileHTML = new File("page.html");
        FileUtils.writeStringToFile(fileHTML, pageHTML, Charset.forName("UTF-8"));
        int width = 200;
        int height = 300;
        Java2DRenderer renderer = new Java2DRenderer(fileHTML, width, height);
        return renderer.getImage();
    }

    public void convertToBitmapAndSaveFile(ModelAndView statementPage, Model model, HttpServletRequest request) throws Exception {
        String pageHTML = getHtmlCode(statementPage, model, request);
        File fileHTML = new File("page.html");
        FileUtils.writeStringToFile(fileHTML, pageHTML, Charset.forName("UTF-8"));
        int width = 200;
        int height = 300;
        Java2DRenderer renderer = new Java2DRenderer(fileHTML, width, height);
        BufferedImage image = renderer.getImage();
        File fileBitmap = new File(defaultImagePath + "teste.bmp");

        // Write file on disk
        ImageIO.write(image, "bmp", fileBitmap);
    }

    private String getHtmlCode(ModelAndView statementPage, Model model, HttpServletRequest request) throws Exception {
        View resolvedView = viewResolver.resolveViewName(statementPage.getViewName(), new Locale("pt", "BR"));
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        resolvedView.render(model.asMap(), request, mockHttpServletResponse);
        return mockHttpServletResponse.getContentAsString();
    }
}
