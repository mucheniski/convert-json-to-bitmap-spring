package com.example.convertjsontobitmapspring.common;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

@Service
public class Base64Converter {

    public String encodeImageToBase64AndSaveFile(String imgPath, String savePath) throws IOException {

        FileInputStream imageStream = new FileInputStream(imgPath);
        byte[] bytes = imageStream.readAllBytes();
        String imageString = Base64.getEncoder().encodeToString(bytes);

        // Write base64 in file if you need
        FileWriter fileWriter = new FileWriter(savePath);
        fileWriter.write(imageString);
        fileWriter.close();

        imageStream.close();

        return imageString;

    }

    public void decodeBase64ToImageAndSaveFile(String txtPath, String savePath) throws IOException {

        FileInputStream inputStream = new FileInputStream(txtPath);
        byte[] bytesTxt = inputStream.readAllBytes();
        byte[] bytes64 = Base64.getDecoder().decode(bytesTxt);

        // Export file
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        fileOutputStream.write(bytes64);
        fileOutputStream.close();

        inputStream.close();

    }

}
