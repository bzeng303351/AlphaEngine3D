package com.bzeng.objects3d;

import com.bzeng.matrix.MatrixLib;

public class Triangle3D {
    public final Vector3D p1;
    public final Vector3D p2;
    public final Vector3D p3;

    public Triangle3D(Vector3D p1, Vector3D p2, Vector3D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Plane3D toPlane() {
        return new Plane3D(getNormal(), p2);
    }

    public Vector3D getNormal() {
        Vector3D normal = MatrixLib.crossProduct(MatrixLib.diffVector(p1, p2), MatrixLib.diffVector(p2, p3));
        return normal;
    }

    public boolean inTriangle(Vector3D v) {
        Apex3D temp = new Apex3D(p1, p2, p3);
        return MatrixLib.vectorInApex(temp, v);
    }

    public Vector3D intersectPoint(Vector3D v) {
        if (MatrixLib.dotProduct(v, getNormal()) == 0) {
            // in this case, the vector is parallel with the triangle plane
            // either there is no intersection point or there are infinite intersection points.
            return null;
        }
        double x = MatrixLib.dotProduct(getNormal(), p2) / MatrixLib.dotProduct(getNormal(), v);
        return MatrixLib.scale(v, x);
    }

    public double distanceLine12(Vector3D v) {
        Vector3D line1 = MatrixLib.diffVector(p1, p2);
        Vector3D line2 = MatrixLib.diffVector(p1, intersectPoint(v));
        return MatrixLib.crossProduct(line1, line2).mod() / line1.mod();
    }

    public double distanceLine23(Vector3D v) {
        Vector3D line1 = MatrixLib.diffVector(p2, p3);
        Vector3D line2 = MatrixLib.diffVector(p2, intersectPoint(v));
        return MatrixLib.crossProduct(line1, line2).mod() / line1.mod();
    }

    public double distanceLine31(Vector3D v) {
        Vector3D line1 = MatrixLib.diffVector(p3, p1);
        Vector3D line2 = MatrixLib.diffVector(p3, intersectPoint(v));
        return MatrixLib.crossProduct(line1, line2).mod() / line1.mod();
    }
}
