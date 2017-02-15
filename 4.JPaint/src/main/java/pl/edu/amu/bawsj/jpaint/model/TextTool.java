package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Optional;

/**
 * Created by Maciek on 2016-12-28.
 */
public class TextTool  implements Tool
{
    GraphicsContext gc;
    LayerManager layerManager;

    private Paint strokeColor;

    public TextTool(GraphicsContext gc, LayerManager layerManager)
    {
        this.gc = gc;
        this.layerManager = layerManager;
    }

    private Coords startCoords;

    public void onMousePressed(MouseEvent mouseEvent)
    {
        this.startCoords = new Coords(mouseEvent.getSceneX(), mouseEvent.getSceneY());

    }

    public void onMouseReleased(MouseEvent mouseEvent)
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text");
        dialog.setHeaderText("Enter the text");
        Optional<String> textValue = dialog.showAndWait();

        if(textValue.isPresent())
        {
            Text text = new Text(gc, strokeColor);
            text.setUpObject(this.startCoords, textValue.get());
            text.draw();
            layerManager.addToCurrentLayer(text);
        }



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

    }
}
