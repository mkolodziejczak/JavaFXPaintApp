package pl.edu.amu.bawsj.jpaint.model;

import javafx.scene.canvas.Canvas;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by Maciek on 2016-12-28.
 */
public class NewFileWriter {

    private Canvas canvas;
    private File file;
    public NewFileWriter(Canvas canvas){
        this.canvas = canvas;
    }

    public void writeToFile()
    {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        file = fileChooser.showSaveDialog(null);

        if(file != null){
            ;
            FileWriter fileWriter = new FileWriter(canvas, file);
            fileWriter.SaveFile();
        }
    }

    public File getFile()
    {
        return this.file;
    }


}