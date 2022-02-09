package com.bzeng.matrix;

import com.bzeng.objects3d.Apex3D;
import com.bzeng.objects3d.Plane3D;
import com.bzeng.objects3d.Vector3D;

public class MatrixLib {
    public static double[][] multiply(double[][] first, double[][] second) {
        double[][] ret = new double[first.length][second[0].length];
        for (int row = 0; row < first.length; row++)
            for (int col = 0; col < second[0].length; col++) {
                double tempSum = 0;
                for (int i = 0; i < first[0].length; i++)
                    tempSum += first[row][i] * second[i][col];
                ret[row][col] = tempSum;
            }
        return ret;
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        return new Matrix(multiply(m1.toArray(), m2.toArray()));
    }

    public static Vector3D rotateX(Vector3D v, double rad) {
        double[][] m = {{1, 0, 0},
                {0, Math.cos(rad), -1 * Math.sin(rad)},
                {0, Math.sin(rad), Math.cos(rad)}};
        return new Vector3D(multiply(m, v.toArray()));
    }

    public static Vector3D rotateY(Vector3D v, double rad) {
        double[][] m = {{Math.cos(rad), 0, Math.sin(rad)},
                {0, 1, 0},
                {-1 * Math.sin(rad), 0, Math.cos(rad)}};
        return new Vector3D(multiply(m, v.toArray()));
    }

    public static Vector3D rotateZ(Vector3D v, double rad) {
        double[][] m = {{Math.cos(rad), -1 * Math.sin(rad), 0},
                {Math.sin(rad), Math.cos(rad), 0},
                {0, 0, 1}};
        return new Vector3D(multiply(m, v.toArray()));
    }

    public static Vector3D translate(Vector3D v, Vector3D displacement) {
        return new Vector3D(v.x + displacement.x, v.y + displacement.y, v.z + displacement.z);
    }

    public static Vector3D scale(Vector3D v, double s) {
        return new Vector3D(v.x * s, v.y * s, v.z * s);
    }

    public static Vector3D diffVector(Vector3D from, Vector3D to) {
        return new Vector3D(to.x - from.x, to.y - from.y, to.z - from.z);
    }

    public static double dotProduct(Vector3D v1, Vector3D v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vector3D crossProduct(Vector3D v1, Vector3D v2) {
        return new Vector3D(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }

    public static boolean vectorInApex(Apex3D apex, Vector3D v) {
        // TODO: 2/8/22  what if the 3 vectors of apex are in the same plane?

        double p1 = vectorOnPlane(apex.planeXY(), v);
        double p2 = vectorOnPlane(apex.planeYZ(), v);
        double p3 = vectorOnPlane(apex.planeZX(), v);
        return p1 > 0 && p2 > 0 && p3 > 0;
    }

    public static double vectorOnPlane(Plane3D plane, Vector3D vector) {
        return dotProduct(diffVector(plane.point, vector), plane.normal);
    }

    public static void main(String[] args) {
        double[][] a1 = {{1.0, 3.0}, {0.0, 1.0}};
        double[][] a2 = {{0.0, 1.0}, {1.0, 0.0}};
        Matrix m1 = new Matrix(a1);
        Matrix m2 = new Matrix(a2);
        System.out.println(MatrixLib.multiply(m2, m1));
    }
}
