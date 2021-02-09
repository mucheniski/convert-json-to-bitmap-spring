package com.example.convertjsontobitmapspring.common;

import com.example.convertjsontobitmapspring.domain.enums.FileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.aspose.html.HTMLDocument;
import com.aspose.html.saving.ImageSaveOptions;
import com.aspose.html.rendering.image.ImageFormat;
import com.aspose.html.converters.Converter;
import com.aspose.html.rendering.PageSetup;
import com.aspose.html.drawing.Page;
import com.aspose.html.drawing.Size;
import com.aspose.html.drawing.Length;

import javax.validation.Valid;

@Service
public class HTMLConverter {

    @Value("${htmlConverter.defaultAddress}")
    private String htmlConverterDefaultAddress;

    // TODO: handle exceptions
    public void convertHtmlToBitmap(ModelAndView statementPage) {
        String viewNameHtml = addHtmlExtension(statementPage.getViewName());
        HTMLDocument document = new HTMLDocument(htmlConverterDefaultAddress + viewNameHtml);
        PageSetup pageSetup = configurePage(98, 512);
        ImageSaveOptions options = new ImageSaveOptions(ImageFormat.Bmp);
        options.setPageSetup(pageSetup);

        Converter.convertHTML(document, options,htmlConverterDefaultAddress + "output.bmp");
        if (document != null) {
            document.dispose();
        }

    }

    private PageSetup configurePage(int pageWidth, int pageHeigth) {
        PageSetup pageSetup = new PageSetup();
        Page page = new Page();
        Size pageSize = new Size(Length.fromPixels(pageWidth), Length.fromPixels(pageHeigth));
        page.setSize(pageSize);
        pageSetup.setAnyPage(page);
        return pageSetup;
    }

    private String addHtmlExtension(String viewName) {
        return viewName + FileType.HTML.getExtension();
    }

}
