package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Maciek on 2016-12-27.
 */
public class Triangle  extends Drawable implements Fillable, Strokable {


    private ArrayList<Coords> coordsList;

    private Paint strokeColor;
    private Paint fillColor;

    public Triangle(GraphicsContext gc, Paint strokeColor, Paint fillColor) {
        super(gc);

        this.strokeColor = strokeColor!=null?strokeColor:gc.getStroke();
        this.fillColor = fillColor!=null?fillColor:gc.getFill();
    }

    public void draw() {

        gc.setStroke(strokeColor);
        gc.setFill(fillColor);

        double[] xArr = new double[3];
        double[] yArr = new double[3];

        for(int i=0; i<2; i++)
        {
            gc.strokeLine(coordsList.get(i).getXValue(), coordsList.get(i).getYValue(), coordsList.get(i+1).getXValue(), coordsList.get(i+1).getYValue());
            xArr[i]=coordsList.get(i).getXValue();
            yArr[i]=coordsList.get(i).getYValue();
        }
        gc.strokeLine(coordsList.get(2).getXValue(), coordsList.get(2).getYValue(), coordsList.get(0).getXValue(), coordsList.get(0).getYValue());
        xArr[2]=coordsList.get(2).getXValue();
        yArr[2]=coordsList.get(2).getYValue();


        gc.fillPolygon(xArr, yArr, 3);

    }

    public void setFillColor(Color fillColor) {
        gc.setFill(fillColor);
    }

    public void setStrokeColor(Color strokeColor) {
        gc.setStroke(strokeColor);
    }

    public void setUpObject(ArrayList<Coords> coordsList) {

        this.coordsList = coordsList;
    }
}