package com.bzeng.objects3d;

import com.bzeng.matrix.Matrix;

public class Vector3D {
    public final double x;
    public final double y;
    public final double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(double[] m) {
        this.x = m[0];
        this.y = m[1];
        this.z = m[2];
    }

    public Vector3D(double[][] m) {
        this.x = m[0][0];
        this.y = m[1][0];
        this.z = m[2][0];
    }

    public double mod() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3D opposite() {
        return new Vector3D(-1 * x, -1 * y, -1 * z);
    }

    public Matrix toMatrix() {
        return new Matrix(toArray());
    }

    public double[][] toArray() {
        double[][] ret = new double[3][1];
        ret[0][0] = x;
        ret[1][0] = y;
        ret[2][0] = z;
        return ret;
    }
}
