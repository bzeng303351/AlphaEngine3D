package com.bzeng.matrix;

import com.bzeng.objects3d.Vector3D;

import java.util.Arrays;

public class Matrix {

    private final double[][] m;

    public Matrix(double[][] m) {
        this.m = new double[m.length][];
        for (int i = 0; i < m.length; i++)
            this.m[i] = Arrays.copyOf(m[i], m[i].length);
    }

    public double[][] toArray() {
        double[][] ret = new double[m.length][];
        for (int i = 0; i < m.length; i++)
            ret[i] = Arrays.copyOf(m[i], m[i].length);
        return ret;
    }

    public Vector3D toVector3D() {
        return new Vector3D(m[0][0], m[1][0], m[2][0]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m.length; i++) {
            sb.append(Arrays.toString(m[i]));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        double[][] arr = {{0.0, 0.1}, {0.0, 0.1}};
        Matrix m1 = new Matrix(arr);
        arr[0][0] = 0.1;
        System.out.println(m1);
    }
}
