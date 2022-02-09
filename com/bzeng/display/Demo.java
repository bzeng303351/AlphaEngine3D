package com.bzeng.display;

import com.bzeng.map.World3D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Demo extends Frame implements KeyListener {

    public BufferedImage bi;
    public Camera camera;
    public World3D world;
    public double rotateStep = 0.1;
    public double moveStep = 5.0;

    public Demo(int width, int height, Camera camera, World3D world) {
        bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.camera = camera;
        this.world = world;
        this.camera.render(world, bi);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bi, 0, 100, this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            camera.rotateX(rotateStep);
            camera.render(world, bi);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            camera.rotateX(-1.0 * rotateStep);
            camera.render(world, bi);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            camera.rotateY(rotateStep);
            camera.render(world, bi);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            camera.rotateY(-1.0 * rotateStep);
            camera.render(world, bi);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            camera.move(moveStep);
            camera.render(world, bi);
            repaint();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            camera.move(-1.0 * moveStep);
            camera.render(world, bi);
            repaint();
        }
    }
    public static void main(String[] args) {
        int width = 400;
        int height = 400;
        Camera camera = new Camera(120);
        World3D world = new World3D();
        Demo demo = new Demo(width, height, camera, world);
        demo.setSize(width, height);
        demo.addKeyListener(demo);
        demo.setVisible(true);

    }
}
