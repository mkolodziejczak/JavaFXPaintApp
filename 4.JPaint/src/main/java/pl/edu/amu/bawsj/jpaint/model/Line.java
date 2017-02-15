package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Created by Maciek on 2016-12-27.
 */
public class Line extends Drawable implements Strokable
{
    private Coords start;
    private Coords end;
    private Paint strokeColor;

    public Line(GraphicsContext gc, Paint strokeColor)
    {
        super(gc);

        this.strokeColor = strokeColor!=null?strokeColor:gc.getStroke();

    }

    public void draw()
    {
        gc.setStroke(strokeColor);
        gc.strokeLine(this.start.getXValue(), this.start.getYValue(), this.end.getXValue(), this.end.getYValue());
    }

    public void setStrokeColor(Color strokeColor)
    {
        gc.setStroke(strokeColor);
    }

    public void setUpObject(Coords start, Coords end)
    {

        this.start = new Coords(start.getXValue(), start.getYValue());
        this.end = new Coords(end.getXValue(), end.getYValue());
    }
}