package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;


import java.util.ArrayList;

/**
 * Created by Maciek on 2016-12-28.
 */
public class TriangleTool implements Tool
{

    GraphicsContext gc;
    LayerManager layerManager;

    private Paint strokeColor;
    private Paint fillColor;

    public TriangleTool(GraphicsContext gc, LayerManager layerManager)
    {
        this.gc = gc;
        this.layerManager = layerManager;
    }

    private ArrayList<Coords> coordsList;

    public void onMousePressed(MouseEvent mouseEvent)
    {
        if(coordsList == null)
        {
            coordsList = new ArrayList<Coords>();
        }
        coordsList.add(new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY()));

        if(coordsList.size() == 3)
        {

            Triangle triangle = new Triangle(gc, strokeColor, fillColor);
            triangle.setUpObject(coordsList);
            triangle.draw();


            layerManager.addToCurrentLayer(triangle);
            coordsList= null;
        }
    }

    public void onMouseReleased(MouseEvent mouseEvent)
    {

    }

    public void onMouseDragged(MouseEvent mouseEvent)
    {

    }

    public void onStrokeColorChange(Paint strokeColor)
    {
        this.strokeColor = strokeColor;
    }
    public void onFillColorChange(Paint fillColor)
    {
        this.fillColor = fillColor;
    }
}