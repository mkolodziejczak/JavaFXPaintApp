package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

/**
 * Created by Maciek on 2016-12-27.
 */
public interface Tool
{
    public void onMousePressed(MouseEvent mouseEvent);
    public void onMouseReleased(MouseEvent mouseEvent);
    public void onMouseDragged(MouseEvent mouseEvent);
    public void onStrokeColorChange(Paint strokeColor);
    public void onFillColorChange(Paint fillColor);
}