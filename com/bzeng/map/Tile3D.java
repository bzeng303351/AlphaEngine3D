package com.bzeng.map;

import com.bzeng.matrix.Matrix;
import com.bzeng.matrix.MatrixLib;
import com.bzeng.objects3d.Triangle3D;
import com.bzeng.objects3d.Vector3D;

public class Tile3D {

    public final Vector3D a;
    public final Vector3D b;
    public final Vector3D c;
    public final Vector3D d;
    public int fillColor;
    public int edgeColor;
    private final Triangle3D abc;
    private final Triangle3D acd;
    public final double pEdge; // thickness of edge/length of edge.
    private final double tEdge; // thickness of edge

    public Tile3D(Vector3D a, Vector3D b, Vector3D c, Vector3D d, int fillColor, int edgeColor, double pEdge) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
        this.abc = new Triangle3D(a, b, c);
        this.acd = new Triangle3D(a, c, d);
        this.pEdge = pEdge;
        this.tEdge = pEdge * MatrixLib.diffVector(a, b).mod();
    }

    public boolean inTile(Vector3D v) {
        return abc.inTriangle(v) || acd.inTriangle(v);
    }

    public boolean onEdge(Vector3D v) {
        if (abc.inTriangle(v)) {
            //System.out.println(abc.distanceLine23(v));
            if (abc.distanceLine12(v) < tEdge || abc.distanceLine23(v) < tEdge) {
                //System.out.println("on edge");
                return true;
            }
        }
        if (acd.inTriangle(v))
            if (acd.distanceLine31(v) < tEdge || acd.distanceLine23(v) < tEdge)
                return true;
        return false;
    }

    public int getColor(Vector3D v) {
        if (onEdge(v))
            return edgeColor;
        else
            return fillColor;
    }
    public Vector3D intersectPoint (Vector3D v) {
        if (abc.inTriangle(v))
            return abc.intersectPoint(v);
        if (acd.inTriangle(v))
            return acd.intersectPoint(v);
        return null;
    }
}
