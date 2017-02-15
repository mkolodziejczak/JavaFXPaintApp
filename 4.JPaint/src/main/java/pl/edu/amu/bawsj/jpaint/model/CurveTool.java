package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

/**
 * Created by Maciek on 2016-12-27.
 */
public class CurveTool implements Tool
{
    GraphicsContext gc;
    LayerManager layerManager;
    private Paint strokeColor;

    public CurveTool(GraphicsContext gc, LayerManager layerManager)
    {
        this.gc = gc;
        this.layerManager = layerManager;
        this.strokeColor = gc.getStroke();
    }

    private ArrayList<Coords> coordsList;

    public void onMousePressed(MouseEvent mouseEvent)
    {
        coordsList = new ArrayList<Coords>();
        coordsList.add(new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY()));

    }

    public void onMouseReleased(MouseEvent mouseEvent)
    {
        coordsList.add(new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY()));

        Curve curve = new Curve(gc, this.strokeColor);
        curve.setUpObject(coordsList);
        curve.draw();

        layerManager.addToCurrentLayer(curve);
    }

    public void onMouseDragged(MouseEvent mouseEvent)
    {

        layerManager.drawAllLayers();

        coordsList.add(new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY()));

        Curve curve = new Curve(gc, this.strokeColor);
        curve.setUpObject(coordsList);
        curve.draw();

    }

    public void onStrokeColorChange(Paint strokeColor)
    {
        this.strokeColor = strokeColor;
    }

    public void onFillColorChange(Paint fillColor)
    {

    }
}
