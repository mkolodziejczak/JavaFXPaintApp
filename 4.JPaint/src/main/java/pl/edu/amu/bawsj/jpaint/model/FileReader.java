package pl.edu.amu.bawsj.jpaint.model;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import pl.edu.amu.bawsj.jpaint.JPaintView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Maciek on 2016-12-28.
 */
public class FileReader {
    private Canvas canvas;
    private File file;

    public FileReader(Canvas canvas)
    {
        this.canvas=canvas;
    }

    public RasterGraphics readFile()
    {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            return new RasterGraphics(canvas.getGraphicsContext2D(), image);

        } catch (IOException ex) {

            return null;
        }
    }

    public File getFile()
    {
        return this.file;
    }


}
