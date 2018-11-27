package com.swenkalski.handler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureHandler {
    private final int SIZE;

    public int[] pixels;
    public String loc;

    public static TextureHandler wood = new TextureHandler("res/3.jpg", 64);
    public static TextureHandler brick = new TextureHandler("res/2.jpg", 64);
    public static TextureHandler bluestone = new TextureHandler("res/1.jpg", 64);
    public static TextureHandler stone = new TextureHandler("res/4.jpg", 64);

    public int getSize(){
        return SIZE;
    }

    private TextureHandler(String location, int size) {
        loc = location;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(new File(loc));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}