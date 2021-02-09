package com.example.convertjsontobitmapspring.domain.enums;

public enum FileType {

    BITMAP(".bmp"),
    HTML(".html"),
    TEXT(".txt");
    
    private String extension;

    FileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return this.extension;
    }
}
