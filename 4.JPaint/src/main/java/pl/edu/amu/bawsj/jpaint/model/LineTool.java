package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

/**
 * Created by Maciek on 2016-12-27.
 */
public class LineTool implements Tool
{
    GraphicsContext gc;
    LayerManager layerManager;
    private Paint strokeColor;

    public LineTool(GraphicsContext gc, LayerManager layerManager)
    {
        this.gc = gc;
        this.layerManager = layerManager;
        this.strokeColor = gc.getStroke();
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

        Line line = new Line(gc, strokeColor);
        line.setUpObject(this.startCoords, this.endCoords);
        line.draw();

        layerManager.addToCurrentLayer(line);
    }

    public void onMouseDragged(MouseEvent mouseEvent)
    {

        layerManager.drawAllLayers();

        Coords tempCoords = new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY());

        Line line = new Line(gc, this.strokeColor);
        line.setUpObject(this.startCoords, tempCoords);
        line.draw();

    }

    public void onStrokeColorChange(Paint strokeColor)
    {
        this.strokeColor = strokeColor;
    }

    public void onFillColorChange(Paint fillColor)
    {

    }
}