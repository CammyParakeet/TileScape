package com.parakeetstudios.tilescape.data;

import org.bukkit.Rotation;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;

public class ModelData {

    private ItemStack model;
    private Rotation rotation;
    private Transformation transformation;
    private ItemDisplay.ItemDisplayTransform displayMode;

    public ItemStack getModel() { return model; }
    public void setModel(ItemStack model) { this.model = model; }

    public Rotation getRotation() { return rotation; }
    public void setRotation(Rotation rotation) { this.rotation = rotation; }

    public Transformation getTransformation() { return this.transformation; }
    public void setTransformation(Transformation transformation) { this.transformation = transformation; }

}
