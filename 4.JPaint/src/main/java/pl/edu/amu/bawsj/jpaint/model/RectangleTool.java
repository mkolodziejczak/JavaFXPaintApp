package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

/**
 * Created by Maciek on 2016-12-27.
 */
public class RectangleTool implements Tool
{
    GraphicsContext gc;
    LayerManager layerManager;

    private Paint strokeColor;
    private Paint fillColor;
    private Boolean isSquare =false;

    public RectangleTool(GraphicsContext gc, LayerManager layerManager)
    {
        this.gc = gc;
        this.layerManager = layerManager;
    }

    private Coords startCoords;
    private Coords endCoords;

    public void onMousePressed(MouseEvent mouseEvent)
    {
        this.startCoords = new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY());
    }

    public void onMouseReleased(MouseEvent mouseEvent)
    {
        this.endCoords = new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY());
        if(mouseEvent.isShiftDown())
            isSquare=true;
        Rectangle rectangle = new Rectangle(gc, strokeColor, fillColor);
        rectangle.setUpObject(this.startCoords, this.endCoords, isSquare);
        rectangle.draw();

        layerManager.addToCurrentLayer(rectangle);
    }

    public void onMouseDragged(MouseEvent mouseEvent)
    {

        layerManager.drawAllLayers();

        if(mouseEvent.isShiftDown())
            isSquare=true;
        else
            isSquare=false;

        Coords tempCoords = new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY());

        Rectangle rectangle = new Rectangle(gc, strokeColor, fillColor);
        rectangle.setUpObject(this.startCoords, tempCoords, isSquare);
        rectangle.draw();
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