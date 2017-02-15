package pl.edu.amu.bawsj.jpaint;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import pl.edu.amu.bawsj.jpaint.model.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

/**
 * Created by s396350 on 02.12.2016.
 */
public class JPaintPresenter {


    private JPaintView view;



    public Tool pickedTool;
    public File file;

    public CommandManager commandManager = new CommandManager();
    public LayerManager layerManager = new LayerManager(commandManager);

    public JPaintPresenter( JPaintView jPaintView )
    {
        this.view = jPaintView;
        commandManager.setDoneCommandsPane(jPaintView.commandsGrid);
        commandManager.setUndoneCommandsPane(jPaintView.undoneCommandsGrid);
        commandManager.setCanvas(jPaintView.canvas);
        layerManager.setCanvas(jPaintView.canvas);
        layerManager.addLayer();

    }


    public void initCircleTool(GraphicsContext gc)
    {
        pickedTool = new CircleTool(gc, layerManager);
    }

    public void initRectangleTool(GraphicsContext gc)
    {
        pickedTool = new RectangleTool(gc, layerManager);
    }

    public void initLineTool(GraphicsContext gc)
    {
        pickedTool = new LineTool(gc, layerManager);
    }

    public void initCurveTool(GraphicsContext gc)
    {
        pickedTool = new CurveTool(gc, layerManager);
    }

    public void initTriangleTool(GraphicsContext gc)
    {
        pickedTool = new TriangleTool(gc, layerManager);
    }

    public void initTextTool(GraphicsContext gc)
    {
        pickedTool = new TextTool(gc, layerManager);
    }

    public void keyPressHandler(KeyEvent pressedKey, Canvas canvas) {

        if (pressedKey.getCode().toString().equals("O") && pressedKey.isControlDown())
        {
            FileReader fileReader = new FileReader(canvas);
            RasterGraphics graphics = fileReader.readFile();
            this.file=fileReader.getFile();

            layerManager.clear();

            layerManager.addLayer();
            layerManager.addToCurrentLayer(graphics);
            commandManager.clearAll();
            canvas.setHeight(graphics.getHeight());
            canvas.setWidth(graphics.getWidth());

            layerManager.drawAllLayers();
        }
        else  if (pressedKey.getCode().toString().equals("S") && pressedKey.isShiftDown() && pressedKey.isControlDown())
        {
            NewFileWriter fileWriter = new NewFileWriter(canvas);
            fileWriter.writeToFile();
            this.file=fileWriter.getFile();
        }
        else  if (pressedKey.getCode().toString().equals("S") && pressedKey.isControlDown())
        {
            if(this.file != null)
            {
                FileWriter fileWriter = new FileWriter(canvas, this.file);
                fileWriter.SaveFile();
            }
            else
            {
                NewFileWriter fileWriter = new NewFileWriter(canvas);
                fileWriter.writeToFile();
                this.file=fileWriter.getFile();
            }

        }
        else  if (pressedKey.getCode().toString().equals("Z") && pressedKey.isControlDown())
        {
            commandManager.undoCommand();
            commandManager.drawCommandsList();
            commandManager.drawUndoneCommandsList();
            layerManager.drawAllLayers();
        }
        else  if (pressedKey.getCode().toString().equals("Y") && pressedKey.isControlDown())
        {
            commandManager.redoCommand();
            commandManager.drawCommandsList();
            commandManager.drawUndoneCommandsList();
            layerManager.drawAllLayers();
        }

    }



}
