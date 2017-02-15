package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by Maciek on 2016-12-28.
 */
public class CommandManager {
    private ArrayList<Command> doneCommands = new ArrayList<>();
    private GridPane doneCommandsPane;

    private ArrayList<Command> undoneCommands = new ArrayList<>();
    private GridPane undoneCommandsPane;

    private Canvas canvas;
    private String POSTURL = "http://127.0.0.1:8080/mysql/images";

    public void clearAll()
    {
        doneCommands.clear();
        undoneCommands.clear();
        doneCommandsPane.getChildren().clear();
        undoneCommandsPane.getChildren().clear();
    }

    public void addCommand(Drawable object)
    {
        doneCommands.add(new Command(object));
        uploadCanvas();
    }

    public void undoCommand()
    {
        if(doneCommands.size() > 0)
        {
            Command undoneCommand = doneCommands.remove(doneCommands.size() - 1);
            undoneCommand.setUndone();

            undoneCommands.add(undoneCommand);uploadCanvas();

        }
    }


    public void redoCommand()
    {
        if(undoneCommands.size() > 0)
        {
            Command redoneCommand = undoneCommands.remove(undoneCommands.size() - 1);
            redoneCommand.setDone();

            doneCommands.add(redoneCommand);
            uploadCanvas();
        }
    }

    public void drawCommandsList()
    {
        if(doneCommandsPane != null)
        {
            doneCommandsPane.getChildren().clear();
            for(int i=0; i<doneCommands.size(); i++)
            {
                Label tempLabel = new Label("Drawn "+doneCommands.get(i).getName()+" at "+doneCommands.get(i).getDateTime());
                doneCommandsPane.add(tempLabel, 1, i+1);
            }
        }

    }

    public void drawUndoneCommandsList()
    {
        if(undoneCommandsPane != null)
        {
            undoneCommandsPane.getChildren().clear();
            for(int i=0; i<undoneCommands.size(); i++)
            {
                Label tempLabel = new Label("Undone draw " + undoneCommands.get(i).getName() + " at " + undoneCommands.get(i).getDateTime());
                undoneCommandsPane.add(tempLabel, 1, i + 1);
            }
        }

    }

    public void setDoneCommandsPane(GridPane gpanel)
    {
        this.doneCommandsPane = gpanel;
    }

    public void setUndoneCommandsPane(GridPane gpanel)
    {
        this.undoneCommandsPane = gpanel;
    }

    public void setCanvas(Canvas canvas) { this.canvas = canvas;}

    private void uploadCanvas()
    {
        try {
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            FileWriter fileWriter = new FileWriter(canvas, s);
            fileWriter.SaveFile();
            byte[] result  = s.toByteArray();
            s.close();

            Base64.Encoder base64_enc = Base64.getEncoder();
            String imageToSend = base64_enc.encodeToString(result);


            HttpPOSTSender sender = new HttpPOSTSender(POSTURL);
            sender.addParam("byteArray", imageToSend);
            sender.sendPost();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
