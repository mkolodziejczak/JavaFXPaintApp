package pl.edu.amu.bawsj.jpaint.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Maciek on 2016-12-28.
 */
public class FileWriter {
    private Canvas canvas;
    private File file;
    private ByteArrayOutputStream stream;

    public FileWriter(Canvas canvas, File file)
    {
        this.file=file;
        this.canvas=canvas;
    }

    public FileWriter(Canvas canvas, ByteArrayOutputStream stream)
    {
        this.stream=stream;
        this.canvas=canvas;
    }

    public void SaveFile(){
        WritableImage writableImage = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
        canvas.snapshot(null, writableImage);
        BufferedImage bImage = SwingFXUtils.fromFXImage(writableImage, null);
        try {
            if(file != null)
                ImageIO.write(bImage, "png", file);
            else if(stream != null )
                ImageIO.write(bImage, "png", stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
