package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Maciek on 2016-12-27.
 */
public abstract class Drawable
{
    GraphicsContext gc;
    public Boolean isRemoved=false;
    public abstract void draw();

    public Drawable (GraphicsContext gc)
    {
        this.gc = gc;
    }

}