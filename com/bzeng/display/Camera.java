package com.bzeng.display;

import com.bzeng.map.Tile3D;
import com.bzeng.map.World3D;
import com.bzeng.matrix.MatrixLib;
import com.bzeng.objects3d.Vector3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Camera {
    private final double xmin = -1.0;
    private final double xmax = 1.0;
    private double viewAngle;
    private Vector3D position;
    private Vector3D normal;
    private Vector3D horizontal;
    private Vector3D vertical;
    private double zDist;

    public Camera(double viewAngleDeg) {
        this.viewAngle = viewAngle;
        zDist = xmax / Math.tan(Math.PI * viewAngleDeg / 360);
        position = new Vector3D(200.0, 25.0, 0.0);
        normal = new Vector3D(0.0, 0.0, 1.0);
        horizontal = new Vector3D(1.0, 0.0, 0.0);
        vertical = new Vector3D(0.0, 1.0, 0.0);
    }

    public void rotateX(double angleRad) {
        // ToDo: this is wrong implementation, should rotate around horizontal axis.
        normal = MatrixLib.rotateX(normal, angleRad);
        horizontal = MatrixLib.rotateX(horizontal, angleRad);
        vertical = MatrixLib.rotateX(vertical, angleRad);
    }

    public void rotateY(double angleRad) {
        // ToDo: this is wrong implementation, should rotate around horizontal axis.
        normal = MatrixLib.rotateY(normal, angleRad);
        horizontal = MatrixLib.rotateY(horizontal, angleRad);
        vertical = MatrixLib.rotateY(vertical, angleRad);
    }

    public void move(double step) {
        position = new Vector3D(
                position.x + step * normal.x,
                position.y + step * normal.y,
                position.z + step * normal.z
        );
    }

    public void render(World3D world, BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        List<Tile3D> newWorld = world.newWorld(position);
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                double sx = convertPixelToX(j, width, xmax);
                double sy = convertPixelToY(i, height, width, xmax);
                Vector3D ray = new Vector3D(
                        zDist * normal.x + sx * horizontal.x + sy * vertical.x,
                        zDist * normal.y + sx * horizontal.y + sy * vertical.y,
                        zDist * normal.z + sx * horizontal.z + sy * vertical.z
                );
                bi.setRGB(j, i, getColor(ray, newWorld));
            }
    }

    private double convertPixelToX(int j, int width, double xmax) {
        return (2.0 * xmax / ((double) width)) * (width / 2 - j);
    }

    private double convertPixelToY(int i, int height, int width, double xmax) {
        return (2.0 * xmax / ((double) width)) * (height / 2 - i);
    }

    private int getColor(Vector3D ray, List<Tile3D> world) {
        Double minDist = Double.POSITIVE_INFINITY;
        int color = Color.BLACK.getRGB();
        for (Tile3D t : world) {
            if (t.inTile(ray)) {
                double dist = t.intersectPoint(ray).mod();
                if (dist < minDist && dist > zDist) {
                    minDist = dist;
                    color = t.getColor(ray);
                }
            }
        }
        return color;
    }
}
