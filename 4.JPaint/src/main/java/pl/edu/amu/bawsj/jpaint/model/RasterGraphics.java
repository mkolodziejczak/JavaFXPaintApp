package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by Maciek on 2016-12-28.
 */
public class RasterGraphics extends Drawable
{
    private Image image;
    private double height;
    private double width;
    RasterGraphics(GraphicsContext gc, Image image)
    {
        super(gc);
        this.image=image;
        this.height=image.getHeight();
        this.width=image.getWidth();
    }

    public void draw()
    {
        gc.drawImage(image, 0, 0);
    }

    public double getHeight()
    {
        return this.height;
    }

    public double getWidth()
    {
        return this.width;
    }
}
