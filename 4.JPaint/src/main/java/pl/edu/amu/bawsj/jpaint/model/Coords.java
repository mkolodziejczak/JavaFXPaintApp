package pl.edu.amu.bawsj.jpaint.model;

/**
 * Created by Maciek on 2016-12-27.
 */
public class Coords
{
    private double x;
    private double y;

    public Coords (double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getXValue()
    {
        return this.x;
    }

    public double getYValue()
    {
        return this.y;
    }
}