package com.example.trytothemain;
public class ImageData {
    private int id;
    private byte[] imageBytes;
    private String description;

    public ImageData(int id, byte[] imageBytes, String description) {
        this.id = id;
        this.imageBytes = imageBytes;
        this.description = description;
    }
    public ImageData(byte[] imageBytes) {

        this.imageBytes = imageBytes;
    }

    public byte[] getImageBytes() {

        return imageBytes;
    }


    // ... Getters and setters ...
}

