package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

/**
 * Created by Maciek on 2016-12-27.
 */
public class Curve extends Drawable implements Strokable
{
    private ArrayList<Coords> coordsList;
    private Paint strokeColor;

    public Curve(GraphicsContext gc, Paint strokeColor)
    {
        super(gc);
        this.strokeColor = strokeColor!=null?strokeColor:gc.getStroke();

    }

    public void draw()
    {
        gc.setStroke(strokeColor);

        for(int i=0; i<(coordsList.size()-1); i++)
        {
            gc.strokeLine(coordsList.get(i).getXValue(), coordsList.get(i).getYValue(), coordsList.get(i+1).getXValue(), coordsList.get(i+1).getYValue());
        }

    }

    public void setStrokeColor(Color strokeColor)
    {
        gc.setStroke(strokeColor);
    }

    public void setUpObject(ArrayList<Coords> coordsList) {
        this.coordsList = coordsList;
    }


}
