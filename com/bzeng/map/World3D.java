package com.bzeng.map;

import com.bzeng.matrix.MatrixLib;
import com.bzeng.objects3d.Vector3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World3D {
    public final int[][] map = {
            {0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0},
    };
    public final double tileSize = 50.0;
    public final List<Tile3D> world = new ArrayList<>();
    public int fillColor = Color.BLUE.getRGB();
    public int edgeColor = Color.WHITE.getRGB();
    public double pEdge = 0.01;

    public World3D() {
        makeWorld();
    }

    private List<Tile3D> makeBlock(Vector3D origin) {
        Vector3D a = origin;
        Vector3D b = new Vector3D(a.x + tileSize, a.y, a.z);
        Vector3D c = new Vector3D(a.x + tileSize, a.y + tileSize, a.z);
        Vector3D d = new Vector3D(a.x, a.y + tileSize, a.z);
        Vector3D e = new Vector3D(a.x, a.y, a.z + tileSize);
        Vector3D f = new Vector3D(a.x + tileSize, a.y, a.z + tileSize);
        Vector3D g = new Vector3D(a.x + tileSize, a.y + tileSize, a.z + tileSize);
        Vector3D h = new Vector3D(a.x, a.y + tileSize, a.z + tileSize);
        List<Tile3D> ret = new ArrayList<>();
        ret.add(new Tile3D(a, b, c, d, fillColor, edgeColor, pEdge));
        ret.add(new Tile3D(a, d, h, e, fillColor, edgeColor, pEdge));
        ret.add(new Tile3D(e, h, g, f, fillColor, edgeColor, pEdge));
        ret.add(new Tile3D(g, c, b, f, fillColor, edgeColor, pEdge));
        ret.add(new Tile3D(c, g, h, d, fillColor, edgeColor, pEdge));
        ret.add(new Tile3D(a, e, f, b, fillColor, edgeColor, pEdge));
        return ret;
    }

    private void makeWorld() {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
            {
                double x = j * tileSize;
                double y = 0.0;
                double z = i * tileSize;
                if (map[i][j] == 1)
                    world.addAll(makeBlock(new Vector3D(x, y, z)));
            }
    }

    public List<Tile3D> newWorld(Vector3D start) {
        List<Tile3D> ret = new ArrayList<>();
        for (Tile3D t : world)
            ret.add(new Tile3D(
                    MatrixLib.diffVector(start, t.a),
                    MatrixLib.diffVector(start, t.b),
                    MatrixLib.diffVector(start, t.c),
                    MatrixLib.diffVector(start, t.d),
                    fillColor, edgeColor, pEdge
            ));
        return ret;
    }

    public static void main(String[] args) {
        World3D world = new World3D();
    }
}
