package com.swenkalski;

import com.swenkalski.handler.CameraHandler;
import com.swenkalski.handler.ScreenHandler;
import com.swenkalski.level.level1.Map;
import com.swenkalski.level.level1.Textures;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends JFrame implements Runnable {

    private static final int MAP_WIDTH = 15;
    private static final int MAP_HEIGHT = 15;

    private Thread thread;
    private boolean running;
    private BufferedImage image;
    private int[] pixels;
    private CameraHandler camera;
    private ScreenHandler screen;

    public Game() {
        thread = new Thread(this);
        image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        camera = new CameraHandler(4.5, 4.5, 1, 0, 0, -.66);
        screen = new ScreenHandler(Map.getMap(), MAP_WIDTH, MAP_HEIGHT, Textures.getTextures(), 640, 480);

        addKeyListener(camera);
        setSize(640, 480);
        setResizable(false);
        setTitle("joom engine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setVisible(true);
        start();
    }

    private synchronized void start() {
        running = true;
        thread.start();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        bs.show();
    }

    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta = delta + ((now - lastTime) / ns);
            lastTime = now;
            while (delta >= 1) {
                screen.update(camera, pixels);
                camera.update(Map.getMap());
                delta--;
            }
            render();
        }
    }
}
