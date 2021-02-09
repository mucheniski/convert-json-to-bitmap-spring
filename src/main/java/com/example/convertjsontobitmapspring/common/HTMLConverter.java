package com.example.convertjsontobitmapspring.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aspose.html.HTMLDocument;
import com.aspose.html.saving.ImageSaveOptions;
import com.aspose.html.rendering.image.ImageFormat;
import com.aspose.html.converters.Converter;

import javax.validation.Valid;

@Service
public class HTMLConverter {

    @Value("${htmlConverter.defaultAddress}")
    private String htmlConverterDefaultAddress;

    public void convertHtmlToBitmap() {
        HTMLDocument document = new HTMLDocument(htmlConverterDefaultAddress + "statement.html");

        try {
            ImageSaveOptions options = new ImageSaveOptions(ImageFormat.Bmp);
            Converter.convertHTML(document, options,htmlConverterDefaultAddress + "output.bmp");
        } finally {
            if (document != null) {
                document.dispose();
            }
        }

    }

}
