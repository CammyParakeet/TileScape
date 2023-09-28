package com.parakeetstudios.tilescape.utils;


import org.bukkit.entity.Entity;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

/**
 * @author Cammy
 * @version 1.0
 */



/**
 * Bukkit Transformation Wrapper - Matrix form
 */
public class Matrix {

    private Transformation T;
    private Vector3f translation, scale;
    private AxisAngle4f left_rot, right_rot;

    public Matrix() {
        translation = new Vector3f();
        scale = new Vector3f();
        left_rot = new AxisAngle4f();
        right_rot = new AxisAngle4f();

        T = new Transformation(
                translation,
                left_rot,
                scale,
                right_rot
        );
    }

    public void setScale(float s) { this.scale = new Vector3f(s); }
    public void setScale(float x, float y, float z) { this.scale = new Vector3f(x, y, z); }
    public void setScale(Vector v) {
        this.scale = v.toVector3f();
    }

    public  void setTranslation(float x, float y, float z) { this.translation = new Vector3f(x, y, z); }

    public void setLeftRot(Entity e, float angle) {
        this.left_rot = new AxisAngle4f(angle, e.getLocation().getDirection().toVector3f());
    }

    public void setRightRot(Entity e, float angle) {
        this.right_rot = new AxisAngle4f(angle, e.getLocation().getDirection().toVector3f());
    }

    public Transformation toTransformation() {
        T = new Transformation(translation, left_rot, scale, right_rot);
        return T;
    }

}
