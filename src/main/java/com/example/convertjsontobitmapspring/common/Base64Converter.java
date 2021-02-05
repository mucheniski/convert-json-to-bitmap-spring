package com.example.convertjsontobitmapspring.common;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Base64;

@Service
public class Base64Converter {

    public String encodeImageToBase64(String imgPath, String savePath) {

        try {
            FileInputStream imageStream = new FileInputStream(imgPath);
            byte[] bytes = imageStream.readAllBytes();
            String imageString = Base64.getEncoder().encodeToString(bytes);

            // Write base64 in file
            FileWriter fileWriter = new FileWriter(savePath);
            fileWriter.write(imageString);
            fileWriter.close();

            imageStream.close();

            return imageString;
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }

    }

    public void decodeBase64ToImage(String txtPath, String savePath) {

        try{

            FileInputStream inputStream = new FileInputStream(txtPath);
            byte[] bytesTxt = inputStream.readAllBytes();
            byte[] bytes64 = Base64.getDecoder().decode(bytesTxt);

            // Export file
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            fileOutputStream.write(bytes64);
            fileOutputStream.close();

            inputStream.close();

        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }

    }

}
