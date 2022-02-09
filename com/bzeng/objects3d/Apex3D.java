package com.bzeng.objects3d;


import com.bzeng.matrix.MatrixLib;

// Apex3D is made by 3 vectors, starting from the same point
// the sequence of 3 vectors should follow the right-hand rule
public class Apex3D {
    public final Vector3D vx;
    public final Vector3D vy;
    public final Vector3D vz;

    public Apex3D(Vector3D vx, Vector3D vy, Vector3D vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    public boolean inApex(Vector3D v) {
        return MatrixLib.vectorInApex(this, v);
    }

    public Plane3D planeXY() {
        return new Plane3D(MatrixLib.crossProduct(vx, vy), new Vector3D(0.0, 0.0, 0.0));
    }
    public Plane3D planeYZ() {
        return new Plane3D(MatrixLib.crossProduct(vy, vz), new Vector3D(0.0, 0.0, 0.0));
    }
    public Plane3D planeZX() {
        return new Plane3D(MatrixLib.crossProduct(vz, vx), new Vector3D(0.0, 0.0, 0.0));
    }
}
