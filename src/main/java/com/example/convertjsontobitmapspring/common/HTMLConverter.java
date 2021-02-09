package com.example.convertjsontobitmapspring.common;

import com.example.convertjsontobitmapspring.domain.enums.FileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aspose.html.HTMLDocument;
import com.aspose.html.saving.ImageSaveOptions;
import com.aspose.html.rendering.image.ImageFormat;
import com.aspose.html.converters.Converter;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Service
public class HTMLConverter {

    @Value("${htmlConverter.defaultAddress}")
    private String htmlConverterDefaultAddress;

    // TODO: handle exceptions
    public void convertHtmlToBitmap(ModelAndView statementPage) {
        String viewNameHtml = addHtmlExtension(statementPage.getViewName());
        HTMLDocument document = new HTMLDocument(htmlConverterDefaultAddress + viewNameHtml);
        ImageSaveOptions options = new ImageSaveOptions(ImageFormat.Bmp);
        Converter.convertHTML(document, options,htmlConverterDefaultAddress + "output.bmp");
        if (document != null) {
            document.dispose();
        }

    }

    private String addHtmlExtension(String viewName) {
        return viewName + FileType.HTML.getExtension();
    }

}
