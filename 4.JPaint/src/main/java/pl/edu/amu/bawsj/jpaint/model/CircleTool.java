package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

/**
 * Created by Maciek on 2016-12-27.
 */
public class CircleTool implements Tool
{
    GraphicsContext gc;
    LayerManager layerManager;

    private Paint strokeColor;
    private Paint fillColor;
    private Boolean isCircle = false;

    public CircleTool(GraphicsContext gc, LayerManager layerManager)
    {
        this.gc = gc;
        this.layerManager = layerManager;

        this.strokeColor = gc.getStroke();
        this.fillColor = gc.getFill();
    }

    private Coords startCoords;
    private Coords endCoords;

    public void onMousePressed(MouseEvent mouseEvent)
    {
        this.startCoords = new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY());
    }

    public void onMouseReleased(MouseEvent mouseEvent)
    {
        if(mouseEvent.isShiftDown())
            isCircle=true;

        this.endCoords = new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY());

        Circle circle = new Circle(gc, this.strokeColor, this.fillColor);
        circle.setUpObject(this.startCoords, this.endCoords, isCircle);
        circle.draw();

        layerManager.addToCurrentLayer(circle);
    }

    public void onMouseDragged(MouseEvent mouseEvent)
    {
        layerManager.drawAllLayers();

        if(mouseEvent.isShiftDown())
            isCircle=true;
        else
            isCircle=false;

        Coords tempCoords = new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY());

        Circle circle = new Circle(gc,strokeColor, fillColor);
        circle.setUpObject(this.startCoords, tempCoords, isCircle);
        circle.draw();
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