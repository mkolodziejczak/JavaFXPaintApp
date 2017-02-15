package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import static java.lang.Math.abs;

/**
 * Created by Maciek on 2016-12-27.
 */
public class Rectangle extends Drawable implements Fillable, Strokable
{

    private Coords start;
    private double width;
    private double height;
    private Paint strokeColor;
    private Paint fillColor;
    public Rectangle(GraphicsContext gc, Paint strokeColor, Paint fillColor)
    {
        super(gc);

        this.strokeColor = strokeColor!=null?strokeColor:gc.getStroke();
        this.fillColor = fillColor!=null?fillColor:gc.getFill();
    }

    public void draw()
    {
        gc.setStroke(strokeColor);
        gc.setFill(fillColor);
        gc.fillRect(this.start.getXValue(), this.start.getYValue(), this.width, this.height);
        gc.strokeRect(this.start.getXValue(), this.start.getYValue(), this.width, this.height);
    }

    public void setFillColor(Color fillColor)
    {
        gc.setFill(fillColor);
    }

    public void setStrokeColor(Color strokeColor)
    {
        gc.setStroke(strokeColor);
    }

    public void setUpObject(Coords start, Coords end, Boolean isSquare)
    {
        double startXValue= start.getXValue() < end.getXValue()?start.getXValue():end.getXValue();
        double startYValue= start.getYValue() < end.getYValue()?start.getYValue():end.getYValue();

        this.start = new Coords(startXValue, startYValue);

        if(isSquare)
        {
            double length = abs(start.getXValue() - end.getXValue()) > abs(start.getYValue() - end.getYValue())?abs(start.getXValue() - end.getXValue()):abs(start.getYValue() - end.getYValue());
            this.width = length;
            this.height = length;
        }
        else
        {
            this.width = abs(start.getXValue() - end.getXValue());
            this.height = abs(start.getYValue() - end.getYValue());
        }

    }

}