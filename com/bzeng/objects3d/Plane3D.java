package com.bzeng.objects3d;

import com.bzeng.matrix.MatrixLib;

public class Plane3D {
    public final Vector3D normal;
    public final Vector3D point;

    public Plane3D(Vector3D normal, Vector3D point) {
        this.normal = normal;
        this.point = point;
    }

    public double vectorOnPlane(Vector3D v) {
        return MatrixLib.vectorOnPlane(this, v);
    }
}
