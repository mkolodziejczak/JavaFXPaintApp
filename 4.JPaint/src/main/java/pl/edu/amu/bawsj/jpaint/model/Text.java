package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static java.lang.Math.abs;

/**
 * Created by Maciek on 2016-12-28.
 */
public class Text extends Drawable implements Strokable
{

    private Coords start;

    private Paint strokeColor;
    private String string;


    public Text(GraphicsContext gc, Paint strokeColor)
    {
        super(gc);

        this.strokeColor = strokeColor!=null?strokeColor:gc.getStroke();

    }

    public void draw()
    {
        gc.setStroke(this.strokeColor);
        gc.strokeText(string, start.getXValue(), start.getYValue());
    }


    public void setStrokeColor(Color strokeColor)
    {
        gc.setStroke(strokeColor);
    }

    public void setUpObject(Coords start, String string)
    {
        this.start = start;
        this.string = string;

    }

}